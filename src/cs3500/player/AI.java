package cs3500.player;

import java.util.List;
import java.util.Objects;

import cs3500.model.ITile;
import cs3500.model.Posn;
import cs3500.model.ROReversiModel;

/**
 * An AI is a Player who automatically places a piece or skips their turn if they have no moves.
 * The player will place the first piece that returns in the possible moves call.
 */
public class AI implements Player {

  private final char player;
  private Strategy strat;

  /**
   * Creates an AI with the given char as its display token.
   * @param player a character representing the display of this player.
   */
  public AI(char player) {
    this.player = player;
  }

  String errormsg = "If you got this to run, the code compiles!";

  @Override
  public Posn placePiece(ROReversiModel model) {
    return this.strat.choosePosn(model, this);
  }

  @Override
  public void skipTurn() {
    throw new RuntimeException(errormsg);
  }

  @Override
  public List<ITile> getPossibleMoves() {
    throw new RuntimeException(errormsg);
  }

  /**
   * Displays the character which represents the player.
   * @return The character representing the player.
   */
  @Override
  public String toString() {
    return this.player + "";
  }

  /**
   * Players with the same display are equal. This is regardless of if they are AI or not, multiple
   * players with the same character are on the same team, and therefore equal for all intents and
   * purposes.
   * @param obj any object, ideally another player.
   * @return if the obj is equal to this player.
   */
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
