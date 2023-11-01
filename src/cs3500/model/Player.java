package cs3500.model;

import java.util.List;

/**
 * This is a representation of a Player. A Player is anyone who can interact with the model
 * through placing pieces or skipping their turn. Each player has a display which is how the textual
 * view knows how to display a player.
 * The general idea is to have each view be paired with a player. The player will interact with its
 * specific view to play the game. The view will pass its player through the controller to the model
 * which will use the player to update who owns which tile.
 */
public interface Player {
  //OPERATIONS

  /**
   * A player can choose to place a piece on the board at any valid position.
   *
   * @param p a position on the board where the piece should be placed.
   * @throws IllegalArgumentException if the given posn is null or does not correspond to a valid
   *                                  board coordinate.
   * @throws IllegalStateException    if the given posn corresponds to a tile that is already occupied
   *                                  or the move is invalid (does not flip over any other pieces).
   */
  void placePiece(Posn p);

  /**
   * Skips the turn of the player. You are only allowed to skip your turn when you have no possible
   * moves to make.
   *
   * @throws IllegalStateException if the player has any possible moves that they can make.
   */
  void skipTurn();

  //OBSERVATIONS

  /**
   * A player can attempt to see all the possible moves that they can make. Later on, this might
   * evolve to allow for hints with those moves as well.
   *
   * @return a list of all possible tiles that are valid moves for this player.
   */
  List<ITile> getPossibleMoves();

  /**
   * Represents the player as a string. Each unique player has a unique toSting.
   *
   * @return textual representation of a player
   */
  String toString();
}
