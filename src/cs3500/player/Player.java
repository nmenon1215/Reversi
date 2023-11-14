package cs3500.player;

import java.util.List;

import cs3500.model.ITile;
import cs3500.model.Posn;
import cs3500.model.ROReversiModel;

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
   * @param model is the current model that we are operating on.
   * @return null to skip the turn, or a valid Posn on the board where we would like to place the \
   * piece.
   */
  Posn placePiece(ROReversiModel model);

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
