package cs3500.reversi.model;

import java.util.List;
import java.util.Objects;

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

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof SquarePosn)) {
      return false;
    }
    SquarePosn other = (SquarePosn) obj;
    return this.hashCode() == other.hashCode();
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
