package controller;


/**
 * An interface that describes player actions that have the ability to change the view.
 */
public interface ViewFeatures {


  /**
   * signals a player move when a hexagon is clicked and the player presses "m", "M", the space bar,
   * or enter.
   * @param diagonalPos the diagonal position of the selected hexagon.
   * @param rowPos the row position of the selected hexagon.
   */
  void playerMove(int diagonalPos, int rowPos);

  /**
   * signals a player pass when a player presses "p" or "P".
   */
  void playerPass();

}
