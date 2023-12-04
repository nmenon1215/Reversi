package cs3500.reversi.provider.strategy;

/**
 * Represents a coordinate on the board.
 */
public interface Coord {
  /**
   * the diagonal position in the coordinate.
   * @return this diagonal position.
   */
  public int getDiagonalPos();

  /**
   * the row position in the coordinate.
   * @return this row position.
   */
  public int getRowPos();
}
