package cs3500.reversi.provider.strategy;

import cs3500.reversi.provider.model.Player;
import cs3500.reversi.provider.model.ReadOnlyReversiModel;

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
  Coord chooseMove(ReadOnlyReversiModel model, Player player);

}
