package cs3500.controller;

import cs3500.model.MutableReversiModel;
import cs3500.player.Player;

/**
 * A feature is any type of action that a player can make on Reversi.
 */
public interface Features {
  /**
   * Calls this feature on the given model with the given player
   */
  void go(MutableReversiModel model, Player p);
}
