package cs3500.reversi.provider.cell;

import java.util.Objects;
import cs3500.reversi.provider.model.PlayerEnum;

/**
 * a class that represents and individual cell in a hexagonal game board in reversi.
 */
public class HexagonCell implements Cell {
  //the state of a cell: whether it is occupied by player X, O, or empty
  private PlayerEnum state;
  //diagonal position (q axial coordinate) of the cell on a reversi game board
  private final int diagonalPos;
  //row position (r axial coordinate) of the cell on a reversi game board
  private final int rowPos;
  private boolean isSelected;

  /**
   * constructs a hexagon cell.
   * @param state the state of the cell (empty or occupied by player x or o).
   * @param diagonalPos the diagonal position of the cell on the game board.
   * @param rowPos the row position of the cell on the game board.
   */
  public HexagonCell(PlayerEnum state, int diagonalPos, int rowPos) {
    this.state = state;
    this.diagonalPos = diagonalPos;
    this.rowPos = rowPos;
    this.isSelected = false;
  }

  /**
   * constructs an empty hexagon cell.
   * @param diagonalPos the diagonal position of the cell on the game board.
   * @param rowPos the row position of the cell on the game board.
   */
  public HexagonCell(int diagonalPos, int rowPos) {
    this.state = PlayerEnum.Empty;
    this.diagonalPos = diagonalPos;
    this.rowPos = rowPos;
    this.isSelected = false;
  }


  /**
   * the diagonal position of a cell on the reversi game board.
   * @return this diagonal position.
   */
  public int getDiagonalPos() {
    return this.diagonalPos;
  }

  /**
   * the row position of a cell on the reversi game board.
   * @return this row position.
   */
  public int getRowPos() {
    return this.rowPos;
  }

  /**
   * adjusts the state of the cell to be occupied by the given player.
   * @param newState the player that will occupy the cell.
   */
  public void changeCellState(PlayerEnum newState) {
    this.state = newState;
  }


  /**
   * the string representation of a hexagon cell.
   * @return "_" if empty, "O" if occupied by player o, "X" if occupied by player x.
   */
  public String toString() {
    return state.toString();
  }

  /**
   * returns the s diagonal value of the given hexagon cell (as if in cubic coordinates).
   * the s value is always equal to -q (diagonal position) -r (row position).
   * @return the S diagonal value of the cell on the reversi game board.
   */
  public int getSVal() {
    return (- diagonalPos) - rowPos;
  }

  /**
   * determines if a given cell is empty.
   * @return true if the cell is not occupied by any players.
   */
  public boolean isEmpty() {
    return this.state == PlayerEnum.Empty;
  }


  /**
   * determines if a given cell is selected.
   * @return
   */
  public boolean selected() {
    return this.isSelected;
  }


  /**
   * Selects or deselects a cell.
   */
  public void selectCell() {
    this.isSelected = !this.isSelected;
  }


  /**
   * overrides the equal method for comparing instances of hexagon cells.
   * @param other instance of an object.
   * @return true if the toString of this and the object are equal.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof HexagonCell) {
      return this.toString().equals(other.toString());
    }
    else {
      return false;
    }
  }

  /**
   * Create a unique number per object that exists.
   * Required method for any object.
   * @return a unique int for each object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(state, diagonalPos, rowPos, isSelected);
  }

}
