package cs3500.model;

/**
 * This is a representation of a mutable Reversi Model. A Reversi Model is a model of the game Reversi
 * where players place pieces on a tiled board. A player can only place a piece if it flips
 * over another tile. A placement flips over all tiles that form a sandwich between the piece
 * flipped and another one of their pieces. For more precise descriptions, please check out the
 * wiki for Reversi or Othello.
 */
public interface MutableReversiModel extends ROReversiModel {
  //OPERATIONS

  /**
   * Places a piece of the given player at the given position. Follows the rules of Reversi to
   * modify the game board based on the placement.
   *
   * @param p
   * @param posn
   * @throws IllegalArgumentException if the player or posn is null, or if the posn is not in bounds
   * @throws IllegalStateException    if the move is not possible with the current board. For example,
   *                                  the player posn combination does not result in any sandwiches.
   */
  void placePiece(Player p, Posn posn);
}
