package cs3500.reversi.provider.controller;


import cs3500.reversi.model.Posn;

/**
 * An interface that describes player actions that have the ability to change the view.
 */
public interface ViewFeatures {


  /**
   * signals a player move when a hexagon is clicked and the player presses "m", "M", the space bar,
   * or enter.
   * @param posn the position where the player is to make a move to.
   */
  void playerMove(Posn posn);

  /**
   * signals a player pass when a player presses "p" or "P".
   */
  void playerPass();

}
