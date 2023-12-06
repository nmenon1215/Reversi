package cs3500.reversi.player;


import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.model.Posn;
import cs3500.reversi.model.ROReversiModel;
import cs3500.reversi.provider.model.PlayerEnum;
import cs3500.reversi.view.gui.ReversiView;

/**
 * This is a representation of a Player. A Player is anyone who can interact with the model
 * through placing pieces or skipping their turn. Each player has a display which is how the textual
 * view knows how to display a player.
 * The general idea is to have each view be paired with a player. The player will interact with its
 * specific view to play the game. The view will pass its player through the controller to the model
 * which will use the player to update who owns which tile.
 */
public interface Player extends cs3500.reversi.provider.model.Player {
  //OPERATIONS

  /**
   * A player can choose to place a piece on the board at any valid position.
   *
   * @param model is the current model that we are operating on.
   * @return null to skip the turn, or a valid Posn on the board where we would like to place the \
   *         piece.
   */
  Posn placePiece(ROReversiModel model, ReversiView view);

  /**
   * Represents the player as a string. Each unique player has a unique toSting.
   *
   * @return textual representation of a player
   */
  String toString();

  void setController(ReversiController controller);

  void notifyController(ROReversiModel model);

  PlayerEnum getPlayerEnum();
}
