package cs3500.reversi.controller;

/**
 * The controller for the Reversi game which connects the players, model, and view.
 */
public interface ReversiController {
  /**
   * Signals the controller to start the turn and make their window the primary window.
   */
  void startTurn();
}
