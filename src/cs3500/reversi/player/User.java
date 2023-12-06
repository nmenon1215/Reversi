package cs3500.reversi.player;

import java.util.Objects;

import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.model.HexagonalPosn;
import cs3500.reversi.model.Posn;
import cs3500.reversi.model.ROReversiModel;
import cs3500.reversi.provider.model.PlayerEnum;
import cs3500.reversi.view.gui.ReversiView;

/**
 * A User is the class that represents any human playing this game. Humans can place pieces and
 * skip their own turns if no other options are available.
 */
public class User implements Player {

  private final char player;
  private ReversiController controller;

  /**
   * This creates a basic User with a given display character.
   *
   * @param player is the character that represents this player
   */
  public User(char player) {
    this.player = player;
  }


  // this will most likely interact with either view or controller which we don't have yet.
  @Override
  public Posn placePiece(ROReversiModel model, ReversiView view) {
    return new HexagonalPosn(view.getHighlighted());
  }

  /**
   * This displays the player using its display character.
   *
   * @return the player's display character as a string
   */
  @Override
  public String toString() {
    return this.player + "";
  }

  @Override
  public void setController(ReversiController controller) {
    this.controller = controller;
  }

  @Override
  public void notifyController(ROReversiModel model) {
    if (this.controller != null) {
      this.controller.startTurn();
    }
  }

  @Override
  public PlayerEnum getPlayerEnum() {
    if (this.player == 'X') {
      return PlayerEnum.X;
    }
    else if (this.player == 'O') {
      return PlayerEnum.O;
    }
    else {
      return PlayerEnum.Empty;
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Player)) {
      return false;
    }
    Player other = (Player) obj;
    return this.hashCode() == other.hashCode();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.player);
  }
}
