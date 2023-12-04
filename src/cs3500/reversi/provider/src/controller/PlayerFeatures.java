package controller;


/**
 * An interface that performs player actions when it is a player's turn.
 * This is primarily used for just AI players, as no moves are made by a human player
 * without interacting with the view.
 */
public interface PlayerFeatures {

  /**
   * Signals that an AI player chooses to make a move to the given position.
   * @param diagonalPos the diagonal position of the cell the AI player is moving to.
   * @param rowPos the row position of the cell the AI player is moving to.
   */
  void makeAIMove(int diagonalPos, int rowPos);

  /**
   * Signals that an AI player chooses to pass on their turn.
   */
  void makeAIPass();

}
