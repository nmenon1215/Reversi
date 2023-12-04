package cs3500.reversi.provider.strategy.implementation;

import cs3500.reversi.provider.cell.HexagonCell;
import java.util.ArrayList;
import cs3500.reversi.provider.model.Player;
import cs3500.reversi.provider.model.ReadOnlyReversiModel;
import cs3500.reversi.provider.strategy.Coord;
import cs3500.reversi.provider.strategy.ReversiCoord;
import cs3500.reversi.provider.strategy.Strategy;


/**
 * An abstract class representing the basic Strategy with methods used to differentiate
 * between coordinate options.
 */
abstract class ReversiStrategy implements Strategy {

  /**
   * picks the coordinate that a player should move to.
   * @param model the model of a reversi game.
   * @param player the player placing a piece.
   * @return the coordinate of the cell the player should move to.
   * @throws IllegalArgumentException if it is not the player's turn.
   */
  public abstract Coord chooseMove(ReadOnlyReversiModel model, Player player);


  /**
   * returns all the cells in the game board that would currently be a valid move.
   * @param model the reversi game model.
   * @return the cells in the game board that a player can move to.
   */
  ArrayList<HexagonCell> getValidCells(ReadOnlyReversiModel model) {
    ArrayList<ArrayList<HexagonCell>> board = model.getGameBoard();
    ArrayList<HexagonCell> valids = new ArrayList<>();
    for (ArrayList<HexagonCell> list : board) {
      for (HexagonCell cell : list) {
        int diagonalPos = cell.getDiagonalPos();
        int rowPos = cell.getRowPos();
        if (model.checkValid(diagonalPos, rowPos)) {
          valids.add(cell);
        }
      }
    }
    return valids;
  }


  Coord getBestCell(ReadOnlyReversiModel model, ArrayList<HexagonCell> validCells) {
    int bestMove = 0;
    HexagonCell bestCell = null;
    for (HexagonCell cell : validCells) {
      //finds the number of cells a player would gain by placing a piece at this cell
      int cellsGained = model.countPiecesGained(cell.getDiagonalPos(), cell.getRowPos());
      //replaces the cell with this cell if it gains more pieces than the current best cell.
      if (cellsGained > bestMove) {
        bestMove = cellsGained;
        bestCell = cell;
      }
    }
    return new ReversiCoord(bestCell.getDiagonalPos(), bestCell.getRowPos());
  }

  ArrayList<HexagonCell> getNoCorners(ReadOnlyReversiModel model, ArrayList<HexagonCell> valids) {
    ArrayList<HexagonCell> noCorners = new ArrayList<>();
    for (HexagonCell cell : valids) {
      if (!model.posnNextToCorner(cell.getDiagonalPos(), cell.getRowPos())) {
        noCorners.add(cell);
      }
    }
    return noCorners;
  }


  ArrayList<HexagonCell> getCorners(ReadOnlyReversiModel model, ArrayList<HexagonCell> valids) {
    ArrayList<HexagonCell> corners = new ArrayList<>();
    for (HexagonCell cell : valids) {
      if (model.posnInCorner(cell.getDiagonalPos(), cell.getRowPos())) {
        corners.add(cell);
      }
    }
    return corners;
  }

}
