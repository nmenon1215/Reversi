package cs3500.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a cube coordinate system with q, r, and s being the directions.
 * q is in the direction of northwest to southeast.
 * r is in the direction of west to east.
 * s is in the direction of southwest to northeast.
 */
public class HexagonalPosn implements Posn {

  private final int q, r, s;

  HexagonalPosn(int q, int r, int s) {
    this.q = q;
    this.r = r;
    this.s = s;
  }

  /**
   * We are returning the coordinates as (q, r, s).
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
}
