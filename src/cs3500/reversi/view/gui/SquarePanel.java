package cs3500.reversi.view.gui;

import javax.swing.*;

import cs3500.reversi.model.Posn;
import cs3500.reversi.model.ROReversiModel;
import cs3500.reversi.model.SquarePosn;

public class SquarePanel extends JReversiPanel{
  public SquarePanel(JFrame frame, ROReversiModel reversiModel) {
    super(frame, reversiModel);
  }

  @Override
  protected int numRows() {
    return this.size;
  }

  @Override
  protected int numColsInRow(int row) {
    return this.size;
  }

  @Override
  protected double startingX(int r) {
    return 0;
  }

  @Override
  protected double yChange(double pieceHeight) {
    return pieceHeight;
  }

  @Override
  protected double calculatePieceWidth(double boardWidth) {
    return boardWidth / size;
  }

  @Override
  protected double calculatePieceHeight(double boardHeight) {
    return boardHeight / size;
  }

  @Override
  protected double calculateBoardHeight() {
    return BOARDWIDTH;
  }

  @Override
  protected Posn gridToAxialCoord(int row, int col) {
    return new SquarePosn(col, row);
  }

  @Override
  protected ReversiButton createButton(int row, int col, double x, double y,
                                       double pieceWidth, double pieceHeight) {
    ReversiButton button = new SquareButton(gridToAxialCoord(row, col),
            this.model, this.model.getTurn());
    createListener(button);
    add(button);
    button.setBounds((int) x, (int) y, (int) pieceWidth, (int) pieceHeight);
    button.setBorderPainted(true);
    return button;
  }
}
