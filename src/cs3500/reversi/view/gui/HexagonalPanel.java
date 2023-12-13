package cs3500.reversi.view.gui;

import javax.swing.*;

import cs3500.reversi.model.HexagonalPosn;
import cs3500.reversi.model.Posn;
import cs3500.reversi.model.ROReversiModel;

public class HexagonalPanel extends JReversiPanel{
  /**
   * Constructs a ReversiPanel and populates the view with the current board state.
   *
   * @param frame        the background frame to display on.
   * @param reversiModel is the model which we are displaying.
   */
  public HexagonalPanel(JFrame frame, ROReversiModel reversiModel) {
    super(frame, reversiModel);
  }

  @Override
  protected int numRows() {
    return size * 2 + 1;
  }

  @Override
  protected int numColsInRow(int row) {
    return -Math.abs(row - size) + size * 2 + 1;
  }

  @Override
  protected double startingX(int r) {
    r -= size;
    if (r < 0) {
      return -r * calculatePieceWidth(BOARDWIDTH) / 2;
    } else {
      return r * calculatePieceWidth(BOARDWIDTH) / 2;
    }
  }

  @Override
  protected double yChange(double pieceHeight) {
    return 3 * pieceHeight / 4;
  }

  @Override
  protected double calculatePieceWidth(double boardWidth) {
    return boardWidth / (size * 2 + 1);
  }

  @Override
  protected double calculatePieceHeight(double boardHeight) {
    return boardHeight * 2 / (size * 3 + 2);
  }

  @Override
  protected double calculateBoardHeight() {
    return Math.sqrt(3) / 2 * BOARDWIDTH;
  }

  @Override
  protected ReversiButton createButton(int row, int col, double x, double y,
                                       double pieceWidth, double pieceHeight) {
    ReversiButton button = new HexagonalButton(gridToAxialCoord(row, col),
            this.model, this.model.getTurn());
    createListener(button);
    add(button);
    button.setBounds((int) x, (int) y, (int) pieceWidth, (int) pieceHeight);
    return button;
  }

  @Override
  protected Posn gridToAxialCoord(int row, int col) {
    int qStart;
    int r = row - size;
    if (r < 0) {
      qStart = -r - size;
    }
    else {
      qStart = -size;
    }
    int q = qStart + col;
    int s = - r - q;
    return new HexagonalPosn(q, r, s);
  }
}
