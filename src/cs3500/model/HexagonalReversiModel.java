package cs3500.model;

import java.util.ArrayList;
import java.util.List;

public class HexagonalReversiModel implements MutableReversiModel {

  private List<ITile> board;
  private final int boardSize;
  private final int numPlayers;
  private int skipsInRow;

  public HexagonalReversiModel(Player p1, Player p2) {
    this.boardSize = 5;
    this.board = new ArrayList<>();
    this.numPlayers = 2;
    this.skipsInRow = 0;
    startGame(p1, p2);
  }

  public HexagonalReversiModel(Player p1, Player p2, int boardSize) {
    if (boardSize < 2) {
      throw new IllegalArgumentException("Board size must be at least 2.");
    }
    this.boardSize = boardSize;
    this.board = new ArrayList<>();
    this.numPlayers = 2;
    this.skipsInRow = 0;
    startGame(p1, p2);
  }

  String errormsg = "If you got this to run, the code compiles!";

  @Override
  public void placePiece(Player p, Posn posn) {
    if (p == null) {
      throw new IllegalArgumentException("The given player can't be null.");
    }
    if (posn == null) {
      throw new IllegalArgumentException("The given position can't be null.");
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
  }

  @Override
  public void skip(Player p) {
    if(p == null) {
      throw new IllegalArgumentException("The given player can't be null.");
    }
    if(this.possibleMoves(p).size() != 0) {
      throw new IllegalStateException("Player can only skip if they have no possible moves.");
    }
    this.skipsInRow++;
  }

  @Override
  public ITile getTileAt(Posn p) {
    return new HexagonalTile(findTile(p));
  }

  @Override
  public boolean isGameOver() {
    if(this.skipsInRow >= this.numPlayers) {
      return true;
    }
    for (ITile tile : this.board) {
      if (tile.getPlayer() == null) {
        return false;
      }
    }
    return true;
  }

  @Override
  public List<ITile> possibleMoves(Player p) {
    List<ITile> possibleMoves = new ArrayList<>();
    // for every tile in the board
    for (ITile tile : board) {
      List<List<ITile>> surroundingLines = getSurroundingLines(tile);
      for (List<ITile> line : surroundingLines) {
        if (isSandwich(line, p)) {
          possibleMoves.add(tile);
          break;
        }
      }
    }
    return possibleMoves;
  }

  @Override
  public int getScore(Player p) {
    if (p == null) {
      throw new IllegalArgumentException("The given Player can't be null.");
    }
    int score = 0;
    for (ITile tile : board) {
      if (tile.getPlayer().equals(p)) {
        score++;
      }
    }
    return score;
  }

  @Override
  public int getBoardSize() {
    return this.boardSize;
  }

  // Retrieves the tile at the given position.
  private ITile findTile(Posn p) {
    if (p == null) {
      throw new IllegalArgumentException("The given position can't be null.");
    }
    for (ITile tile : this.board) {
      if (tile.getPosition().equals(p)) {
        return tile;
      }
    }
    throw new IllegalArgumentException("The given position is out of bounds for the board.");
  }

  private List<List<ITile>> getSurroundingLines(ITile placingTile) {
    List<List<ITile>> surroundingLines = new ArrayList<>();

    // PLEASE READ JAVADOC FOR findLine
    surroundingLines.add(findLine(List.of(0, 1, 2), placingTile.getPosition().getCoords()));
    surroundingLines.add(findLine(List.of(0, 2, 1), placingTile.getPosition().getCoords()));
    surroundingLines.add(findLine(List.of(1, 2, 0), placingTile.getPosition().getCoords()));
    surroundingLines.add(findLine(List.of(1, 0, 2), placingTile.getPosition().getCoords()));
    surroundingLines.add(findLine(List.of(2, 0, 1), placingTile.getPosition().getCoords()));
    surroundingLines.add(findLine(List.of(2, 1, 0), placingTile.getPosition().getCoords()));

    // remove all empty lists
    for(int i = 0; i < surroundingLines.size(); i++) {
      if(surroundingLines.get(i).size() == 0) {
        surroundingLines.remove(i);
        i--;
      }
    }
    return surroundingLines;
  }

  /**
   * Finds the line adjacent to a coordinate in some direction.
   * Example 1:
   * [0,1,2], [2,4,3]
   * 2,4,3 is the coordinate of the original tile
   * fixed = 2, add = 4, sub = 3
   * then line will consist of (2,5,2), (2,6,1), etc.
   * Example 2:
   * [1,0,2], [2,4,3]
   * fixed = 4, add = 2, sub = 3
   * then line will consist of (3,4,2), (4,4,1), (5,4,0), etc.
   * Why we did this:
   * Ideally, we would just pass in 3 ints to the function and rotate them as fixed, add, sub
   * Unfortunately, this leads to errors in findTile at the bottom of the while loop. Because
   * we don't know if fixed is q, r, or s, so we can't generate a new Posn properly. To fix this,
   * we passed in 2 lists. The first represents which values in the second list will be fixed, add,
   * and sub. This allows us to easily create a new Posn since the q r and s values stay in the same
   * place in the second list.
   *
   * @param indexList Represents which direction we are going in. The first value is the index of
   *                  which point is fixed, the next is which point will be added, and the last is
   *                  which point will be subtracted.
   * @param coords    Represents the starting coordinate which we are finding lines from.
   * @return The line(List of ITile) adjacent to a tile in a certain direction specified by
   * indexList
   */
  private List<ITile> findLine(List<Integer> indexList, List<Integer> coords) {
    // check inputs are valid
    if (indexList == null || coords == null) {
      throw new IllegalArgumentException("No null inputs");
    }
    if (indexList.size() != 3 || coords.size() != 3) {
      throw new IllegalArgumentException("All coordinates must be defined by exactly 3 ints.");
    }
    int count = 0;
    for (int i : indexList) {
      if (i > 2 || i < 0) {
        throw new IllegalArgumentException
                ("Index list must only contain indexs from 0-2 inclusive");
      }
    }
    findTile(new HexagonalPosn(coords)); // this throws exception if the coords are not valid

    // create the line
    List<Integer> newTile = new ArrayList<>(coords);
    List<ITile> line = new ArrayList<>();
    while (newTile.get(indexList.get(1)) < boardSize && newTile.get(indexList.get(2)) > -boardSize) {
      // add 1 to value at add index, sub 1 from value at sub index
      newTile.set(indexList.get(1), newTile.get(indexList.get(1)) + 1);
      newTile.set(indexList.get(2), newTile.get(indexList.get(2)) - 1);

      line.add(findTile(new HexagonalPosn(newTile)));
    }
    return line;
  }

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
  private void startGame(Player p1, Player p2) {
    for (int r = -boardSize; r <= boardSize; r++) {
      int qStart;
      int qEnd;
      if (r < 0) {
        qStart = -boardSize - r;
        qEnd = boardSize;
      } else {
        qStart = -boardSize;
        qEnd = boardSize - r;
      }
      for (int q = qStart; q <= qEnd; q++) {
        int s = -q - r;
        this.board.add(new HexagonalTile(new HexagonalPosn(q, r, s)));
      }
    }

    // Starting sequence
    /*
     X O
    O - X
     X O
     */
    findTile(new HexagonalPosn(0, -1, 1)).flipTo(p1);
    findTile(new HexagonalPosn(1, 0, -1)).flipTo(p1);
    findTile(new HexagonalPosn(-1, 1, 0)).flipTo(p1);

    findTile(new HexagonalPosn(0, 1, -1)).flipTo(p2);
    findTile(new HexagonalPosn(-1, 0, 1)).flipTo(p2);
    findTile(new HexagonalPosn(1, -1, 0)).flipTo(p2);
  }

}
