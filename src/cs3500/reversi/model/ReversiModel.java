package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.controller.Controller;
import cs3500.reversi.player.Player;

public abstract class ReversiModel implements MutableReversiModel {
  protected List<List<ITile>> board;
  protected final int boardSize;
  private final int numPlayers;
  private int skipsInRow;
  private List<Player> players;
  private int turnIndex;

  /**
   * Constructs the board of size 5 and initializes with the starting piece setup which is:
   * X O
   * O - X
   * X O
   * placed in the center of the board with X representing player 1, and O representing player 2.
   *
   * @param players A list that consists of the first and second players in the game. (Used for the
   *                initialization of the game.)
   * @throws IllegalArgumentException if there is a null value in players, the players list is null,
   *                                  or the list has less than 2 distinct players.
   */
  public ReversiModel(List<Player> players) {
    if (players == null || players.stream().distinct().count() < 2) {
      throw new IllegalArgumentException("Players list can't be null.");
    }
    for (Player p : players) {
      if (p == null) {
        throw new IllegalArgumentException("Individual players can't be null.");
      }
    }
    this.boardSize = 5;
    this.board = new ArrayList<>();
    this.numPlayers = players.size();
    this.skipsInRow = 0;
    this.players = players;
    this.turnIndex = 0;
    startGame(players);
  }

  /**
   * Constructs the board of given size and initializes with the starting piece setup which is:
   * X O
   * O - X
   * X O
   * placed in the center of the board with X representing player 1, and O representing player 2.
   *
   * @param boardSize is the size of board defined by how many tiles away from the center the edge
   *                  of the board is. Ex: board above is size 1. boardSize must be >= 2.
   * @param players   A list that consists of the first and second players in the game.
   *                  (Used for the initialization of the game.)
   * @throws IllegalArgumentException if the board size is less than 2 or players are null.
   */
  public ReversiModel(List<Player> players, int boardSize) {
    if (players == null || players.stream().distinct().count() < 2) {
      throw new IllegalArgumentException("Players list can't be null.");
    }
    for (Player p : players) {
      if (p == null) {
        throw new IllegalArgumentException("Individual players can't be null.");
      }
    }
    if (boardSize < 2) {
      throw new IllegalArgumentException("Board size must be at least 2.");
    }
    this.boardSize = boardSize;
    this.board = new ArrayList<>();
    this.numPlayers = players.size();
    this.skipsInRow = 0;
    this.players = players;
    this.turnIndex = 0;
    startGame(players);
  }

  /**
   * Constructs a duplicate Reversi Model.
   *
   * @param model is a current working model
   */
  public ReversiModel(ROReversiModel model) {
    this.board = model.getBoard();
    this.boardSize = model.getBoardSize();
    this.numPlayers = model.getPlayers().size();
    this.skipsInRow = model.getSkipsInARow();
    this.players = model.getPlayers();

    // make sure to duplicate cell link when making a duplicate model
    for (List<ITile> row : board) {
      for(ITile tile : row) {
        tile.duplicateProviderHexagonCell();
      }
    }
  }

  @Override
  public void placePiece(Player p, Posn posn) {
    if (p == null) {
      throw new IllegalArgumentException("The given player can't be null.");
    }
    if (posn == null) {
      throw new IllegalArgumentException("The given position can't be null.");
    }

    if (!this.getTurn().equals(p)) {
      throw new IllegalStateException("It is not this players turn.");
    }

    ITile placingTile = findTile(posn);
    if (placingTile.getPlayer() != null) {
      throw new IllegalStateException("This tile is already occupied.");
    }

    List<List<ITile>> surroundingLines = getSurroundingLines(placingTile);

    boolean pieceFlipped = false;
    for (List<ITile> line : surroundingLines) {
      if (isSandwich(line, p)) {
        flipTiles(line, p);
        pieceFlipped = true;
      }
    }

    if (!pieceFlipped) {
      throw new IllegalStateException("No tiles were flipped by this move.");
    }

    placingTile.flipTo(p);
    this.skipsInRow = 0;
    nextTurn();
  }

  @Override
  public void skip(Player p) {
    if (p == null) {
      throw new IllegalArgumentException("The given player can't be null.");
    }
    if (this.hasLegalMoves(p)) {
      throw new IllegalStateException("Player can only skip if they have no possible moves.");
    }
    if (!this.getTurn().equals(p)) {
      throw new IllegalStateException("It is not this players turn.");
    }

    this.nextTurn();
    this.skipsInRow++;
  }

  @Override
  public void subscribe(Controller controller, Player p) {
    p.setController(controller);
  }

  @Override
  public ITile getTileAt(Posn p) {
    return findTile(p).copy();
  }

  @Override
  public List<ITile> getSurroundingTiles(Posn posn) {
    List<List<ITile>> surroundingLines = this.getSurroundingLines(this.findTile(posn));
    List<ITile> surroundingTiles = new ArrayList<>();

    for (List<ITile> line : surroundingLines) {
      surroundingTiles.add(new HexagonalTile(line.get(0)));
    }
    return surroundingTiles;
  }

  @Override
  public boolean isGameOver() {
    if (this.skipsInRow >= this.numPlayers) {
      return true;
    }
    //NOTE: Will never occur in hexagonal. Need to create better algo
    for (List<ITile> row : this.board) {
      for (ITile tile : row) {
        if (tile.getPlayer() == null) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public List<ITile> possibleMoves(Player p) {
    if (p == null) {
      throw new IllegalArgumentException("The given player can't be null.");
    }
    List<ITile> possibleMoves = new ArrayList<>();
    // for every tile in the board
    for (List<ITile> row : this.board) {
      for (ITile tile : row) {
        List<List<ITile>> surroundingLines = new ArrayList<>();
        if (tile.getPlayer() == null) {
          surroundingLines = getSurroundingLines(tile);
        }
        for (List<ITile> line : surroundingLines) {
          if (isSandwich(line, p)) {
            possibleMoves.add(new HexagonalTile(tile));
            break;
          }
        }
      }
    }
    return possibleMoves;
  }

  @Override
  public boolean isLegalMove(Player p, Posn posn) {
    for (ITile tile : possibleMoves(p)) {
      if (this.findTile(posn).equals(tile)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean hasLegalMoves(Player p) {
    return !possibleMoves(p).isEmpty();
  }

  @Override
  public int getScore(Player p) {
    if (p == null) {
      throw new IllegalArgumentException("The given Player can't be null.");
    }
    int score = 0;
    for (List<ITile> row : this.board) {
      for (ITile tile : row) {
        if (p.equals(tile.getPlayer())) {
          score++;
        }
      }
    }
    return score;
  }

  @Override
  public int getBoardSize() {
    return this.boardSize;
  }

  @Override
  public Player getTurn() {
    return this.players.get(this.turnIndex);
  }

  @Override
  public List<List<ITile>> getBoard() {
    List<List<ITile>> copyBoard = new ArrayList<>();
    for (List<ITile> row : this.board) {
      List<ITile> copyRow = new ArrayList<>();
      for (ITile tile : row) {
        copyRow.add(tile.copy());
      }
      copyBoard.add(copyRow);
    }
    return copyBoard;
  }

  @Override
  public List<Player> getPlayers() {
    return this.players;
  }

  @Override
  public int getTurnIndex() {
    return this.turnIndex;
  }

  @Override
  public int getSkipsInARow() {
    return this.skipsInRow;
  }

  @Override
  public abstract List<Posn> getCorners();

  @Override
  public int countPiecesGained(Player p, Posn posn) {
    if (p == null) {
      throw new IllegalArgumentException("The given player can't be null.");
    }
    if (posn == null) {
      throw new IllegalArgumentException("The given position can't be null.");
    }

    if (!this.getTurn().equals(p)) {
      //throw new IllegalStateException("It is not this players turn.");
    }

    ITile placingTile = findTile(posn);
    if (placingTile.getPlayer() != null) {
      throw new IllegalStateException("This tile is already occupied.");
    }

    List<List<ITile>> surroundingLines = getSurroundingLines(placingTile);

    int numFlipped = 0;
    for (List<ITile> line : surroundingLines) {
      if (isSandwich(line, p)) {
        numFlipped += countFlips(line, p);
      }
    }

    return numFlipped;
  }

  private int countFlips(List<ITile> line, Player p) {
    int flipped = 0;
    for (ITile tile : line) {
      if (tile.getPlayer().equals(p)) {
        break;
      }
      flipped++;
    }
    return flipped;
  }

  // Retrieves the tile at the given position. Does not make copy, so we can edit tile.
  // NOTE: Should become abstract and done better in square and hexagonal.
  protected ITile findTile(Posn p) {
    if (p == null) {
      throw new IllegalArgumentException("The given position can't be null.");
    }
    for (List<ITile> row : this.board) {
      for (ITile tile : row) {
        if (tile.getPosition().equals(p)) {
          return tile;
        }
      }
    }
    throw new IllegalArgumentException("The given position is out of bounds for the board.");
  }

  // returns list of 6 lines surrounding the given tile not including the tile
  // if the line is empty, it is removed.
  protected abstract List<List<ITile>> getSurroundingLines(ITile placingTile);


  // ONLY call if there is a sandwich. Flips all tiles in the sandwich.
  private void flipTiles(List<ITile> line, Player p) {
    for (ITile tile : line) {
      if (tile.getPlayer().equals(p)) {
        break;
      }
      tile.flipTo(p);
    }
  }

  // Determines if a line without its starting piece is a sandwich.
  private boolean isSandwich(List<ITile> line, Player p) {
    if (p.equals(line.get(0).getPlayer())) {
      // nothing was sandwiched :(
      return false;
    }
    for (ITile tile : line) {
      if (tile.getPlayer() == null) {
        // there is a gap in the sandwich :(
        return false;
      }
      if (p.equals(tile.getPlayer())) {
        // we reached the end of a sandwich :)
        return true;
      }
    }
    // we reached the end, but no end piece :(
    return false;
  }

  // Starts the game by initializing the board.
  protected abstract void startGame(List<Player> players);

  // Moves the turn index to the next players turn
  private void nextTurn() {
    this.turnIndex++;
    this.turnIndex = this.turnIndex % this.players.size();
    players.get(turnIndex).notifyController(this);
  }
}
