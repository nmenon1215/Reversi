package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cs3500.reversi.provider.cell.Cell;
import cs3500.reversi.provider.cell.HexagonCell;
import cs3500.reversi.provider.model.Player;
import cs3500.reversi.provider.model.PlayerEnum;
import cs3500.reversi.provider.model.ReadOnlyReversiModel;

public class ProviderModel implements ReadOnlyReversiModel {

  ROReversiModel rootModel;

  public ProviderModel(ROReversiModel rootModel) {
    this.rootModel = Objects.requireNonNull(rootModel);
  }

  @Override
  public ArrayList<ArrayList<HexagonCell>> getGameBoard() {
    ArrayList<ArrayList<HexagonCell>> gameBoard = new ArrayList<>();
    for (int row = 0; row < rootModel.getBoardSize() * 2 + 1; row++) {
      ArrayList<HexagonCell> rowOfCells = new ArrayList<>();
      for (int col = 0;
           col < -Math.abs(row - rootModel.getBoardSize()) + rootModel.getBoardSize() * 2 + 1;
           col++) {
        List<Integer> coord = gridToAxialCoord(row, col);
        rowOfCells.add(rootModel.getTileAt(new HexagonalPosn(coord)).toProviderHexagonCell());
      }
      gameBoard.add(rowOfCells);
    }
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
    int piecesGained;
    HexagonalReversiModel temp = new HexagonalReversiModel(rootModel);
    try {
      piecesGained = -temp.getScore(rootModel.getTurn());
      temp.placePiece(rootModel.getTurn(), new HexagonalPosn(diagonalPos, rowPos));
      piecesGained += temp.getScore(rootModel.getTurn());
    }
    catch (IllegalStateException e) {
      piecesGained = 0;
    }
    return piecesGained;
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
