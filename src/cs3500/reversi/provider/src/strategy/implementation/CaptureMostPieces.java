package strategy;

import cell.HexagonCell;
import java.util.ArrayList;
import model.Player;
import model.ReadOnlyReversiModel;



/**
 * A strategy to a game of reversi that places a piece at the available cell that gains the
 * player the most new cells.
 */
public class CaptureMostPieces extends ReversiStrategy {

  /**
   * Picks the coordinate that a player should move to by getting the available piece that gains
   * the player the most new cells.
   * @param model the model of a reversi game.
   * @param player the player placing a piece.
   * @return a cell that gains the player the most cells, null if there are no available cells.
   * @throws IllegalArgumentException if it is not the players's turn
   */
  @Override
  public Coord chooseMove(ReadOnlyReversiModel model, Player player) {
    if (!model.getPlayerTurn().toString().equals(player.toString())) {
      throw new IllegalArgumentException("not player turn");
    }
    //gets the cells in the board that are valid moves
    ArrayList<HexagonCell> valids = this.getValidCells(model);
    //returns the cell from the valid cells that gains the most cells
    if (valids.isEmpty()) {
      return null;
    }
    return this.getBestCell(model, valids);
  }

}
