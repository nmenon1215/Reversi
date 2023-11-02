package cs3500.model;

import java.util.List;

/**
 * This is a representation of an immutable Reversi Model. The functions in this model are only
 * getters, so the Reversi Model cannot be changed. A Reversi Model is a model of the game Reversi
 * where players place pieces on a tiled board. A player can only place a piece if it flips
 * over another tile. A placement flips over all tiles that form a sandwich between the piece
 * flipped and another one of their pieces. For more precise descriptions, please check out the
 * wiki for Reversi or Othello.
 */
public interface ROReversiModel {
  //OBSERVATIONS

  /**
   * Retrieves a copy of the tile at the given position.
   *
   * @param posn the position of the tile we want to get.
   * @return a copy of the tile at the given position.
   * @throws IllegalArgumentException if the posn does not correspond to a place on the board or is
   *                                  null
   */
  ITile getTileAt(Posn posn);

  /**
   * Retrieves a list of tiles surrounding the tile at the given position. Starts with the tile
   * that is directly to the left of the center tile.
   * @param posn the position of the center tile.
   * @return a list of tiles surrounding the tile at the given position.
   */
  List<ITile> getSurroundingTiles(Posn posn);

  /**
   * Determines if the game is over. The game is over if
   * skip is called for all players consecutively,
   * or all tiles are filled by a player.
   *
   * @return if the game is over.
   */
  boolean isGameOver();

  /**
   * Returns a list of all the possible moves that a player can make. Returns an empty list
   * if there are no possible moves for the player to make.
   *
   * @param p the player who is attempting to make a move.
   * @return A list of all possible moves that the player p can make.
   * @throws IllegalArgumentException if the player passed in is null.
   */
  List<ITile> possibleMoves(Player p);

  /**
   * Returns a boolean correlating to whether or not a player can make a move at a certain posn.
   * @param p the player whose move is in question.
   * @param posn the position that is being checked if it is legal.
   * @return A boolean whether the move is legal.
   */
  boolean isLegalMove(Player p, Posn posn);

  /**
   * Returns a boolean correlating to whether or not a player has any possible moves to make.
   * @param p the player who may or may not have legal moves.
   * @return A boolean whether a player has any legal moves.
   */
  boolean hasLegalMoves(Player p);

  /**
   * Determines the score of the current player. The score of a player is the number of tiles that
   * the player is currently occupying.
   *
   * @param p is the player whose score we are determining.
   * @return the score of the given player.
   * @throws IllegalArgumentException if the player passed in is null.
   */
  int getScore(Player p);

  /**
   * Returns the size of the board. The size of the board is the distance from the center to any
   * edge.
   *
   * @return the size of the board.
   */
  int getBoardSize();
}
