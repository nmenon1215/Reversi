package cs3500.reversi.provider.controller;

import cs3500.reversi.provider.model.Player;


/**
 * An interface to represent a player that is actively playing the game of reversi.
 * Note: this represents a player that controls the game and makes moves.
 */
public interface ActivePlayer {


  /**
   * Makes a move if it is this players turn.
   */
  void makeMove();

  /**
   * Determines if the player is a human player.
   * @return true if the player is a human player.
   */
  boolean isHumanPlayer();

  /**
   * Returns the player type that this player is playing as (black or white).
   * @return the player that the player is making moves for.
   */
  Player getPlayer();

  /**
   * Adds a listener to a player to react to the signals the player sends.
   * @param features the listener (the controller).
   */
  void addFeaturesListener(PlayerFeatures features);

}
