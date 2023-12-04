package cs3500.reversi.model;

import java.util.List;

/**
 * A Posn is a representation of a coordinate. Coordinates can be represented in many ways.
 * For example, they can be 3D coordinates with 3 nums, or 2D coordinates with 2 nums.
 */
public interface Posn {
  //OBSERVATIONS

  /**
   * Returns a list of coordinates for the posn. Order of coordinates is specified in the class
   *
   * @return the list of coordinates that correspond to this posn.
   */
  List<Integer> getCoords();

  /**
   * Determines if this position is adjacent to the given tile's position.
   *
   * @param other any other position
   * @return if the other position is adjacent to this position.
   */
  boolean adjacentTo(Posn other);
}
