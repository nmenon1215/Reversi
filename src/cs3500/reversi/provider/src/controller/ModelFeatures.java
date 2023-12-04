package controller;


/**
 * An interface that communicates with the controller to update the view after a move is
 * made or certain changes are made to the model.
 */
public interface ModelFeatures {

  /**
   * Updates the every view that references the model by repainting the view panel.
   */
  void update();

  /**
   * Sends a message to a player's screen when it is their turn.
   * Also checks if they have any valid moves. If not, notifies the player that they must pass.
   */
  void notifyPlayerTurn();

  /**
   * Signals to the controller that if the next player is an AI player, they must make a move.
   */
  void makeMoveIfAI();

  /**
   * Displays a message that the game is over and who the winner is when the game is over.
   */
  void displayGameOver();

}
