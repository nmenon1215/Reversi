package cs3500.reversi.model;

import java.util.List;

public class SquarePosn implements Posn{
  private final int x;
  private final int y;

  public SquarePosn(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public List<Integer> getCoords() {
    return List.of(x, y);
  }

  @Override
  public boolean adjacentTo(Posn other) {
    if (other instanceof SquarePosn) {
      SquarePosn that = (SquarePosn) other;
      if (this.x + 1 == that.x || this.x - 1 == that.x) {
        return this.y == that.y;
      }
      else if (this.y + 1 == that.y || this.y - 1 == that.y) {
        return this.x == that.x;
      }
    }
    return false;
  }
}
