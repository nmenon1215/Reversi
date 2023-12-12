package cs3500.reversi.model;

import cs3500.reversi.player.Player;
import cs3500.reversi.provider.cell.HexagonCell;

/**
 * An ITile is a representation of a Tile. Each Tile must have a position on the board
 * as well as a Player field describing which player is occupying it. The Player field is left null
 * if there is no Player occupying the field, so be careful with null pointer errors.
 */
public interface ITile {
  //OPERATIONS

  /**
   * Flips the Player field of this tile to the given Player.
   *
   * @param p the player who now owns this tile
   * @throws IllegalArgumentException if the player is null.
   */
  void flipTo(Player p);

  //OBSERVATIONS

  /**
   * Displays the player that is currently occupying this cell.
   * *********
   * *WARNING*  Will return null if the Tile is not occupied by a player.
   * *********
   *
   * @return The player that is currently occupying the tile.
   */
  Player getPlayer();

  /**
   * Returns a Posn representing the position of this tile relative to the game board.
   *
   * @return the position of this tile relative to the game board.
   */
  Posn getPosition();

  /**
   * Returns the providers equivalent version of ITile.
   * @return the HexagonCell connected to the ITile
   */
  HexagonCell getProviderHexagonCell();

  /**
   * Dereferences the Cell from its tile.
   * Mainly used when making a new model as to not edit provider tiles.
   */
  void duplicateProviderHexagonCell();

  /**
   * Creates a copy of the current ITile.
   */
  ITile copy();
}
