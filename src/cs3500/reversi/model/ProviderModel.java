package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cs3500.reversi.provider.cell.Cell;
import cs3500.reversi.provider.cell.HexagonCell;
import cs3500.reversi.provider.model.Player;
import cs3500.reversi.provider.model.PlayerEnum;
import cs3500.reversi.provider.model.ReadOnlyReversiModel;

/**
 * Delegate Read Only version of reversi model which implements Provider's version
 * of the model by delegating back to our Read Only model.
 */
public class ProviderModel implements ReadOnlyReversiModel {

  ROReversiModel rootModel;
  ArrayList<ArrayList<HexagonCell>> gameBoard;

  /**
   * Takes in our version of the model and delegates all functions back to our versions.
   * Also establishes a set list of Cells which do not change since provider's view references
   * the isClicked in the cell which must remain the same.
   * @param rootModel our implementation of a model.
   */
  public ProviderModel(ROReversiModel rootModel) {
    this.rootModel = Objects.requireNonNull(rootModel);
    gameBoard = new ArrayList<>();
    for (int row = 0; row < rootModel.getBoardSize() * 2 + 1; row++) {
      ArrayList<HexagonCell> rowOfCells = new ArrayList<>();
      for (int col = 0;
           col < -Math.abs(row - rootModel.getBoardSize()) + rootModel.getBoardSize() * 2 + 1;
           col++) {
        List<Integer> coord = gridToAxialCoord(row, col);
        rowOfCells.add(rootModel.getTileAt(new HexagonalPosn(coord)).getProviderHexagonCell());
      }
      gameBoard.add(rowOfCells);
    }
  }

  @Override
  public ArrayList<ArrayList<HexagonCell>> getGameBoard() {
    return gameBoard;
  }

  @Override
  public Player getPlayerTurn() {
    return this.rootModel.getTurn();
  }

  @Override
  public List<Boolean> getTurnsTaken() {
    throw new RuntimeException("THIS WAS NOT EXPECTED TO RUN!!!!!!!!");
  }

  @Override
  public Cell getCellAtPosn(int diagonalPos, int rowPos) {
    throw new RuntimeException("THIS WAS NOT EXPECTED TO RUN!!!!!!!!");
  }

  @Override
  public boolean isGameOver() {
    throw new RuntimeException("THIS WAS NOT EXPECTED TO RUN!!!!!!!!");
  }

  @Override
  public boolean forcedPass() {
    throw new RuntimeException("THIS WAS NOT EXPECTED TO RUN!!!!!!!!");
  }

  @Override
  public Player getWinner() {
    throw new RuntimeException("THIS WAS NOT EXPECTED TO RUN!!!!!!!!");
  }

  @Override
  public boolean checkValid(int diagonalPos, int rowPos) {
    return this.rootModel.isLegalMove(this.rootModel.getTurn(),
            new HexagonalPosn(diagonalPos, rowPos));
  }

  @Override
  public int getPlayerScore(PlayerEnum player) {
    throw new RuntimeException("THIS WAS NOT EXPECTED TO RUN!!!!!!!!");
  }

  @Override
  public int getBoardSideLength() {
    return rootModel.getBoardSize() + 1;
  }

  @Override
  public int countPiecesGained(int diagonalPos, int rowPos) {
    return this.rootModel.countPiecesGained(this.rootModel.getTurn(),
            new HexagonalPosn(diagonalPos, rowPos));
  }

  @Override
  public boolean posnNextToCorner(int diagonalPos, int rowPos) {
    List<Posn> corners = rootModel.getCorners();
    Posn position = new HexagonalPosn(diagonalPos, rowPos);
    for (int i = 0; i < corners.size(); i++) {
      if (position.adjacentTo(corners.get(i))) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean posnInCorner(int diagonalPos, int rowPos) {
    List<Posn> corners = rootModel.getCorners();
    return corners.contains(new HexagonalPosn(diagonalPos, rowPos));
  }

  private List<Integer> gridToAxialCoord(int row, int col) {
    int qStart;
    int r = row - rootModel.getBoardSize();
    if (r < 0) {
      qStart = -r - rootModel.getBoardSize();
    }
    else {
      qStart = -rootModel.getBoardSize();
    }
    int q = qStart + col;
    int s = - r - q;
    return List.of(q, r, s);
  }
}
