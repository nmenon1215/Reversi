package cs3500.reversi.model;

import cs3500.reversi.controller.Controller;
import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.player.Player;

/**
 * This is a representation of a mutable Reversi Model. A Reversi Model is a model of the game
 * Reversi where players place pieces on a tiled board. A player can only place a piece if it flips
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
   * @param p    the player who's piece we are placing
   * @param posn the position at which the piece that is being placed
   * @throws IllegalArgumentException if the player or posn is null, or if the posn is not in bounds
   * @throws IllegalStateException    if the move is not possible with the current board.
   *                                  For example, the player posn combination does not result in
   *                                  any sandwiches.
   */
  void placePiece(Player p, Posn posn);

  /**
   * Skips this player's turn. Players are only allowed
   * to skip if they have no other possible moves.
   *
   * @param p represents the player who's turn is being skipped.
   * @throws IllegalArgumentException if the player given is null.
   * @throws IllegalStateException    if the player has other possible moves to be made.
   */
  void skip(Player p);

  /**
   * Subscribes the given controller player pair to listen for when it's their turn.
   *
   * @param controller the controller that needs to be paired
   * @param p the player that needs to be paired
   */
  void subscribe(Controller controller, Player p);
}
