package cs3500.reversi.provider.strategy.implementation;

import cs3500.reversi.provider.cell.HexagonCell;
import java.util.ArrayList;
import cs3500.reversi.provider.model.Player;
import cs3500.reversi.provider.model.ReadOnlyReversiModel;
import cs3500.reversi.provider.strategy.Coord;


/**
 * A strategy to a game of reversi that aims to place a piece at a cell that is the corner cell
 * of a reversi game board.
 */
public class GoForCorners extends ReversiStrategy {

  /**
   * Picks the coordinate that a player should move to by checking if there are any available
   * corner cells.
   * @param model the model of a reversi game.
   * @param player the player placing a piece.
   * @return a corner cell that gains the most pieces that the player should move to,
   *     null if there are no available corners.
   * @throws IllegalArgumentException if it is not the player's turn.
   */
  @Override
  public Coord chooseMove(ReadOnlyReversiModel model, Player player) {
    if (!model.getPlayerTurn().toString().equals(player.toString())) {
      throw new IllegalArgumentException("not player turn");
    }
    ArrayList<HexagonCell> valids = this.getValidCells(model);
    ArrayList<HexagonCell> corners = this.getCorners(model, valids);
    
    if (corners.isEmpty()) {
      return null;
    }
    else {
      return this.getBestCell(model, corners);
    }
  }

}
