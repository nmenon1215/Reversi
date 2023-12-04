package strategy;

import model.Player;
import model.ReadOnlyReversiModel;

/**
 * An interface to represent a strategy to a Reversi game. Holds methods that determine
 * what a player should do on their turn.
 */
public interface Strategy {

  /**
   * picks the coordinate that a player should move to.
   * @param model the model of a reversi game.
   * @param player the player placing a piece.
   * @return the coordinate of the cell the player should move to.
   * @throws IllegalArgumentException if it is not the player's turn.
   */
  ReversiCoord chooseMove(ReadOnlyReversiModel model, Player player);

}
