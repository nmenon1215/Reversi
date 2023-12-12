package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.controller.Controller;
import cs3500.reversi.player.Player;

/**
 * A HexagonalReversiModel is a representation of a Reversi board with the ability to place
 * pieces of any type, and skip a turn. The model does not keep track of which player is placing
 * the pieces in which order. This is the job of the controller. This allows for multiple players
 * if we change the constructor.
 */
public class HexagonalReversiModel extends ReversiModel {

  /**
   * Constructs the board of size 5 and initializes with the starting piece setup which is:
   *  X O
   * O - X
   *  X O
   * placed in the center of the board with X representing player 1, and O representing player 2.
   *
   * @param players A list that consists of the first and second players in the game. (Used for the
   *                initialization of the game.)
   * @throws IllegalArgumentException if there is a null value in players, the players list is null,
   *                                  or the list has less than 2 distinct players.
   */
  public HexagonalReversiModel(List<Player> players) {
    super(players);
  }

  /**
   * Constructs the board of given size and initializes with the starting piece setup which is:
   * X O
   * O - X
   * X O
   * placed in the center of the board with X representing player 1, and O representing player 2.
   *
   * @param players   A list that consists of the first and second players in the game.
   *                  (Used for the initialization of the game.)
   * @param boardSize is the size of board defined by how many tiles away from the center the edge
   *                  of the board is. Ex: board above is size 1. boardSize must be >= 2.
   * @throws IllegalArgumentException if the board size is less than 2 or players are null.
   */
  public HexagonalReversiModel(List<Player> players, int boardSize) {
    super(players, boardSize);
  }

  /**
   * Constructs a duplicate Reversi Model.
   *
   * @param model is a current working model
   */
  public HexagonalReversiModel(ROReversiModel model) {
    super(model);
  }

  @Override
  public List<Posn> getCorners() {
    List<Posn> corners = new ArrayList<>();
    corners.add(this.board.get(0).get(0).getPosition());
    corners.add(this.board.get(0).get(this.boardSize).getPosition());
    corners.add(this.board.get(this.boardSize).get(0).getPosition());
    corners.add(this.board.get(this.boardSize).get(this.boardSize * 2).getPosition());
    corners.add(this.board.get(this.boardSize * 2).get(0).getPosition());
    corners.add(this.board.get(this.boardSize * 2).get(this.boardSize).getPosition());

    return corners;
  }

  @Override
  protected List<List<ITile>> getSurroundingLines(ITile placingTile) {
    List<List<ITile>> surroundingLines = new ArrayList<>();

    // PLEASE READ JAVADOC FOR findLine
    surroundingLines.add(findLine(List.of(0, 1, 2), placingTile.getPosition().getCoords()));
    surroundingLines.add(findLine(List.of(0, 2, 1), placingTile.getPosition().getCoords()));
    surroundingLines.add(findLine(List.of(1, 2, 0), placingTile.getPosition().getCoords()));
    surroundingLines.add(findLine(List.of(1, 0, 2), placingTile.getPosition().getCoords()));
    surroundingLines.add(findLine(List.of(2, 0, 1), placingTile.getPosition().getCoords()));
    surroundingLines.add(findLine(List.of(2, 1, 0), placingTile.getPosition().getCoords()));

    // remove all empty lists
    for (int i = 0; i < surroundingLines.size(); i++) {
      if (surroundingLines.get(i).isEmpty()) {
        surroundingLines.remove(i);
        i--;
      }
    }
    return surroundingLines;
  }

  @Override
  protected void startGame(List<Player> players) {
    Player p1 = players.get(0);
    Player p2 = players.get(1);
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
      List<ITile> row = new ArrayList<>();
      for (int q = qStart; q <= qEnd; q++) {
        int s = -q - r;
        row.add(new HexagonalTile(new HexagonalPosn(q, r, s)));
      }
      this.board.add(row);
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
        throw new IllegalArgumentException("Index list must only contain "
                + "indexes from 0-2 inclusive");
      }
    }
    findTile(new HexagonalPosn(coords)); // this throws exception if the coords are not valid

    // create the line
    List<Integer> newTile = new ArrayList<>(coords);
    List<ITile> line = new ArrayList<>();
    while (newTile.get(indexList.get(1)) < boardSize
            && newTile.get(indexList.get(2)) > -boardSize) {
      // add 1 to value at add index, sub 1 from value at sub index
      newTile.set(indexList.get(1), newTile.get(indexList.get(1)) + 1);
      newTile.set(indexList.get(2), newTile.get(indexList.get(2)) - 1);

      line.add(findTile(new HexagonalPosn(newTile)));
    }
    return line;
  }
}
