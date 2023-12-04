package model;

import cell.Cell;
import cell.HexagonCell;
import java.util.ArrayList;
import java.util.List;

/**
 * A model of a Reversi Game that can observe the game, but cannot make any changes to the model.
 */
public interface ReadOnlyReversiModel {

  /**
   * Returns the current game board in a reversi game.
   * @return the current game board in a reversi game.
   * @throws IllegalStateException if the game has not been started.
   */
  ArrayList<ArrayList<HexagonCell>> getGameBoard();

  /**
   * the current player's turn in a reversi game.
   * @return the curent player turn in a reversi game.
   * @throws IllegalStateException if the game has not been started.
   */
  Player getPlayerTurn();


  /**
   * the list of the past two turns taken in the game.
   * @return the turns taken.
   * @throws IllegalStateException if the game has not been started.
   */
  List<Boolean> getTurnsTaken();


  /**
   * returns the cell on the game board at the given position.
   * @param diagonalPos the diagonal position of the targeted cell.
   * @param rowPos the row position of the targeted cell.
   * @return the cell in the game board with the given coordinated.
   * @throws IllegalArgumentException if the game board does not contain a
   *     cell at this position.
   * @throws IllegalStateException if the game has not been started.
   */
  Cell getCellAtPosn(int diagonalPos, int rowPos);


  /**
   * Determines if the game is over.
   * @return true if the game is over via a full board, a winner, or two passes in a row.
   * @throws IllegalStateException if the game has not been started.
   */
  boolean isGameOver();


  /**
   * determines if a player must have to make a forced pass.
   * @return true if there are no valid moves in any of the empty cells for the current player.
   * @throws IllegalStateException if the game has not been started.
   */
  boolean forcedPass();


  /**
   * the winner of a game of reversi.
   * @return whichever player has the most tiles when the game is over.
   * @throws IllegalStateException if the game has not been started.
   * @throws IllegalStateException if the game is not over.
   */
  Player getWinner();

  /**
   * checks if a player move to a given cell position is valid.
   * @param diagonalPos the diagonal position that they are playing a piece on.
   * @param rowPos the row position that they are playing a piece on.
   * @return true if the move is valid (if they sandwich at least one of the other pieces).
   * @throws IllegalStateException if the game has not been started.
   * @throws IllegalArgumentException if the coordinates are not in the game.
   */
  boolean checkValid(int diagonalPos, int rowPos);

  /**
   * gets a player's current score, which is the number of cells occupied by a given player.
   * @param player the player whose cell are being counted.
   * @return the current score of a given player.
   * @throws IllegalStateException if the game has not been started.
   */
  int getPlayerScore(PlayerEnum player);


  /**
   * returns the side length of the board, in number of hexagons.
   * @return side length of a reversi board.
   * @throws IllegalStateException if the game has not been started.
   */
  int getBoardSideLength();


  /**
   * counts all the pieces that WOULD be turned over if a player were to move at this cell.
   * @param diagonalPos the diagonal position of the cell.
   * @param rowPos the row position of the cell.
   * @return the number of pieces that a player would gain from this move.
   * @throws IllegalStateException if the game has not been started.
   * @throws IllegalArgumentException if the coordinates are not in the game.
   */
  int countPiecesGained(int diagonalPos, int rowPos);


  /**
   * determines if the given position is next to an edge of the board.
   * note: the cell is next to an edge is the sum of the absolute value of its q, r, and s
   *     coordinates is equals to the side length of the board.
   * @param diagonalPos the diagonal position of the cell.
   * @param rowPos the row position of the cell.
   * @return true if the cell is next to an edge.
   * @throws IllegalStateException if the game has not been started.
   * @throws IllegalArgumentException if the coordinates are not in the game.
   */
  boolean posnNextToCorner(int diagonalPos, int rowPos);

  /**
   * determines if the given position is in the corner of the board.
   * note: it is in the corner if the q,r,s coordinates are equal to 0, (side length - 1),
   * and -(side length - 1).
   * @param diagonalPos the diagonal position of the cell.
   * @param rowPos the row position of the cell.
   * @return true if the position given is at the corner of the reversi board.
   * @throws IllegalStateException if the game has not been started.
   * @throws IllegalArgumentException if the coordinates are not in the game.
   */
  boolean posnInCorner(int diagonalPos, int rowPos);

}
