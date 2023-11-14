package cs3500.player;

import java.util.List;
import java.util.Objects;

import cs3500.model.ITile;
import cs3500.model.Posn;
import cs3500.model.ROReversiModel;
import cs3500.player.Player;

/**
 * A User is the class that represents any human playing this game. Humans can place pieces and
 * skip their own turns if no other options are available.
 */
public class User implements Player {

  private final char player;

  /**
   * This creates a basic User with a given display character.
   * @param player is the character that represents this player
   */
  public User(char player) {
    this.player = player;
  }


  String errormsg = "If you got this to run, the code compiles!";

  // this will most likely interact with either view or controller which we don't have yet.
  @Override
  public Posn placePiece(ROReversiModel model) {
    throw new RuntimeException(errormsg);
  }

  // this will most likely interact with either view or controller which we don't have yet.
  @Override
  public void skipTurn() {
    throw new RuntimeException(errormsg);
  }

  // this will most likely interact with either view or controller which we don't have yet.
  @Override
  public List<ITile> getPossibleMoves() {
    throw new RuntimeException(errormsg);
  }

  /**
   * This displays the player using its display character.
   * @return the player's display character as a string
   */
  @Override
  public String toString() {
    return this.player + "";
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
