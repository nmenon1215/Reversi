package strategy;

import cell.HexagonCell;
import java.util.ArrayList;
import model.Player;
import model.ReadOnlyReversiModel;

/**
 * A strategy to a game of reversi that avoids placing a piece at a cell that is next to a
 * corner on the reversi game board.
 */
public class AvoidCorners extends ReversiStrategy {

  /**
   * Picks the coordinate that a player should move to by checking and getting rid of any pieces
   * that are next to a corner cell.
   * @param model the model of a reversi game.
   * @param player the player placing a piece.
   * @return a cell that is not next to a corner that gains the most pieces that the player should
   *     move to, null if no cells not next to a corner exist.
   * @throws IllegalArgumentException if it is not the player's turn.
   */
  @Override
  public Coord chooseMove(ReadOnlyReversiModel model, Player player) {
    if (!model.getPlayerTurn().toString().equals(player.toString())) {
      throw new IllegalArgumentException("not player turn");
    }
    ArrayList<HexagonCell> valids = this.getValidCells(model);
    ArrayList<HexagonCell> noCorners = this.getNoCorners(model, valids);

    if (noCorners.isEmpty()) {
      return null;
    }
    else {
      return this.getBestCell(model, noCorners);
    }
  }
}
