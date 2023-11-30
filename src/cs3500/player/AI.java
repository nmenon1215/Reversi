package cs3500.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cs3500.model.ITile;
import cs3500.model.Posn;
import cs3500.model.ROReversiModel;
import cs3500.view.gui.ReversiView;

/**
 * An AI is a Player who automatically places a piece or skips their turn if they have no moves.
 * The player will place the first piece that returns in the possible moves call.
 */
public class AI implements Player {

  private final char player;
  private List<Strategy> strats;

  /**
   * Creates an AI with the given char as its display token.
   *
   * @param player a character representing the display of this player.
   */
  public AI(char player, List<Strategy> strats) {
    if (strats == null) {
      throw new IllegalArgumentException("The given strategies can't be null.");
    }
    for (Strategy strat : strats) {
      if (strat == null) {
        throw new IllegalArgumentException("Strategy can't be null.");
      }
    }
    this.player = player;
    this.strats = strats;
  }

  String errormsg = "If you got this to run, the code compiles!";

  @Override
  public Posn placePiece(ROReversiModel model, ReversiView view) {
    if (model.hasLegalMoves(this)) {
      List<Posn> possibleMoves = new ArrayList<>();
      for (ITile tile : model.possibleMoves(this)) {
        possibleMoves.add(tile.getPosition());
      }

      for (int i = 0; i < strats.size(); i++) {
        if (possibleMoves.size() == 1) {
          return possibleMoves.get(0);
        }
        //filter the possible moves with the strategy
        possibleMoves = strats.get(i).filterMoves(model, this, possibleMoves);
      }
      return possibleMoves.get(0);
    }
    //The model has no possible moves, so we return null which means skip turn.
    return null;

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
   *
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
   *
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
