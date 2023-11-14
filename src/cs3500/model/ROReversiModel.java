package cs3500.model;

import java.util.List;

import cs3500.player.Player;

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
   * @throws IllegalArgumentException if the given posn is out of bounds or null.
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
   * Returns a boolean correlating to whether a player can make a move at a certain posn.
   * @param p the player whose move is in question.
   * @param posn the position that is being checked if it is legal.
   * @return A boolean whether the move is legal.
   * @throws IllegalArgumentException if the given player or posn is null, or the position is out
   *                                  of bounds.
   */
  boolean isLegalMove(Player p, Posn posn);

  /**
   * Returns a boolean correlating to whether a player has any possible moves to make.
   * @param p the player who may or may not have legal moves.
   * @return A boolean whether a player has any legal moves.
   * @throws IllegalArgumentException if the given player is null
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

  /**
   * Returns the player whose turn it currently is.
   * @return the player whose turn it is.
   */
  Player getTurn();

  /**
   * Returns the list of Tiles that make up the board.
   * @return the list of tiles making up the board.
   */
  List<ITile> getBoard();

  /**
   * Returns a list of the players playing the game.
   * @return the list of players in the game.
   */
  List<Player> getPlayers();

  /**
   * Returns the index of whose turn it is in the list of players.
   * @return the index of whose turn it is
   */
  int getTurnIndex();

  /**
   * Returns the number of skips we have had in a row so far.
   * @return the number of skips in a row.
   */
  int getSkipsInARow();
}
