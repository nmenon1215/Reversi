package cs3500.reversi.controller;

/**
 * The controller for the Reversi game which connects the players, model, and view.
 */
public interface ReversiController {
  /**
   * Begins playing the game and drives the game forward.
   */
  void start();

  /**
   * Makes a move or skips the turn based on input string.
   * Prompts player to return where to place piece. Use Posn to
   * make move and update view accordingly.
   * @param p either p for placePiece or s for skip
   */
  void makeMove(String p);

  /**
   * Signals the controller to start the turn and make their window the primary window.
   */
  void startTurn();
}
