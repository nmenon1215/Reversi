package cell;

import java.util.Objects;

import model.PlayerEnum;

/**
 * This interface represents a Cell on the Reversi Board.
 */
public interface Cell {
  /**
   * the diagonal position of a cell on the reversi game board.
   * @return this diagonal position.
   */
  public int getDiagonalPos();

  /**
   * the row position of a cell on the reversi game board.
   * @return this row position.
   */
  public int getRowPos();

  /**
   * adjusts the state of the cell to be occupied by the given player.
   * @param newState the player that will occupy the cell.
   */
  public void changeCellState(PlayerEnum newState);


  /**
   * the string representation of a hexagon cell.
   * @return "_" if empty, "O" if occupied by player o, "X" if occupied by player x.
   */
  public String toString();

  /**
   * returns the s diagonal value of the given hexagon cell (as if in cubic coordinates).
   * the s value is always equal to -q (diagonal position) -r (row position).
   * @return the S diagonal value of the cell on the reversi game board.
   */
  public int getSVal();

  /**
   * determines if a given cell is empty.
   * @return true if the cell is not occupied by any players.
   */
  public boolean isEmpty();


  /**
   * determines if a given cell is selected.
   * @return
   */
  public boolean selected();


  /**
   * Selects or deselects a cell.
   */
  public void selectCell();

}
