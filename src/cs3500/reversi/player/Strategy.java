package cs3500.player;

import java.util.List;

import cs3500.model.Posn;
import cs3500.model.ROReversiModel;

/**
 * Uses filterMoves to filter the given strategies with the best possible moves.
 */
public interface Strategy {
  /**
   * Uses filterMoves to filter the given strategies with the best possible moves.
   *
   * @param model the given model.
   * @param p the player making the move.
   * @param moves the list of possible moves to filter.
   * @return the filtered list of moves with this strategy.
   */
  List<Posn> filterMoves(ROReversiModel model, Player p, List<Posn> moves);
}
