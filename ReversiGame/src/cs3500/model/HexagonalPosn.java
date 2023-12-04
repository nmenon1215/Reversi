package cs3500.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a cube coordinate system with q, r, and s being the directions.
 * q is in the direction of northwest to southeast.
 * r is in the direction of west to east.
 * s is in the direction of southwest to northeast.
 * q + r + s = 0
 */
public class HexagonalPosn implements Posn {

  private final int q;
  private final int r;
  private final int s;

  /**
   * Creates a position coordinate with the given coordinates. q + r + s must equal 0.
   *
   * @param q the q coordinate
   * @param r the r coordinate
   * @param s the s coordinate
   * @throws IllegalArgumentException if the given coordinates don't add to 0.
   */
  public HexagonalPosn(int q, int r, int s) {
    if (q + r + s != 0) {
      throw new IllegalArgumentException("Coordinates must all add to zero to be valid.");
    }
    this.q = q;
    this.r = r;
    this.s = s;
  }

  /**
   * Given a list of integers, create a position with first value assigned to q, second to r, and
   * last to s.
   *
   * @param qrs a list of length 3 which represents the coordinates [q, r, s]
   * @throws IllegalArgumentException if the list is not length 3, or the vals don't add up to 0.
   */
  public HexagonalPosn(List<Integer> qrs) {
    if (qrs.size() != 3) {
      throw new IllegalArgumentException("Must be given exactly 3 coordinates.");
    }

    int q = qrs.get(0);
    int r = qrs.get(1);
    int s = qrs.get(2);

    if (q + r + s != 0) {
      throw new IllegalArgumentException("Coordinates must all add to zero to be valid.");
    }
    this.q = q;
    this.r = r;
    this.s = s;
  }

  /**
   * We are returning the coordinates in order as (q, r, s).
   *
   * @return (q, r, s)
   */
  @Override
  public List<Integer> getCoords() {
    List<Integer> coord = new ArrayList<>();
    coord.add(q);
    coord.add(r);
    coord.add(s);
    return coord;
  }

  @Override
  public boolean adjacentTo(Posn other) {
    if (other instanceof HexagonalPosn) {
      HexagonalPosn that = (HexagonalPosn) other;
      if (this.q == that.q) {
        return this.r + 1 == that.r || this.r - 1 == that.r;
      } else if (this.r == that.r) {
        return this.s + 1 == that.s || this.s - 1 == that.s;
      } else if (this.s == that.s) {
        return this.q + 1 == that.q || this.q - 1 == that.q;
      }
    }
    return false;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof HexagonalPosn)) {
      return false;
    }
    HexagonalPosn other = (HexagonalPosn) obj;
    return this.hashCode() == other.hashCode();
  }

  @Override
  public int hashCode() {
    return Objects.hash(q, r, s);
  }

}
