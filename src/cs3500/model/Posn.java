package cs3500.model;

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
}
