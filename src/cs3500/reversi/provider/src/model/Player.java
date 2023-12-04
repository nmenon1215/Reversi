package model;

/**
 * An interface that represents a player in a game of reversi.
 * Note: this does not represent the players that are controlling the game, but represents the
 * two types of players that can occupy a cell (ex player X and player O).
 */
public interface Player {

  /**
   * the string representation of a player.
   * @return the string representation of a player.
   */
  String toString();

}
