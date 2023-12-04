package cs3500.reversi.provider.strategy;

import java.util.Objects;

/**
 * An axial coordinate on a reversi game board.
 */
public class ReversiCoord implements Coord {
  //diagonal position in axial coordinates
  private final int diagonalPos;
  //row position in axial coordinates
  private final int rowPos;

  public ReversiCoord(int diagonalPos, int rowPos) {
    this.diagonalPos = diagonalPos;
    this.rowPos = rowPos;
  }


  /**
   * the diagonal position in the coordinate.
   * @return this diagonal position.
   */
  public int getDiagonalPos() {
    return this.diagonalPos;
  }

  /**
   * the row position in the coordinate.
   * @return this row position.
   */
  public int getRowPos() {
    return this.rowPos;
  }

  /**
   * overrides the equal method for comparing instances of axial Coords.
   * @param other instance of an object.
   * @return true if the coordinates are the same.
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    ReversiCoord coord = (ReversiCoord) other;
    return diagonalPos == coord.diagonalPos && rowPos == coord.rowPos;
  }

  /**
   * Create a unique number per object that exists.
   * Required method for any object.
   * @return a unique int for each object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(diagonalPos, rowPos);
  }

}
