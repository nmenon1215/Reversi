package cs3500.view;

import java.io.IOException;

import cs3500.model.HexagonalPosn;
import cs3500.model.ITile;
import cs3500.model.ROReversiModel;

public class ReversiTextualView implements TextualView {

  ROReversiModel model;
  Appendable out;

  public ReversiTextualView(ROReversiModel model, Appendable out) {
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null.");
    }
    this.model = model;
  }

  @Override
  public void render() throws IOException {
    out.append(this.toString());
  }

  public String toString() {
    String display = "";
    int boardSize = model.getBoardSize();
    for (int r = -boardSize; r <= boardSize; r++) {
      int qStart;
      int qEnd;
      if (r < 0) {
        qStart = -boardSize;
        qEnd = boardSize;
      } else {
        qStart = -boardSize - r;
        qEnd = boardSize - r;
      }
      for (int q = qStart; q <= qEnd; q++) {
        int s = -q - r;
        try {
          ITile tileToDisplay = model.getTileAt(new HexagonalPosn(q, r, s));
          display += tileToDisplay.toString() + " ";
        } catch (IllegalArgumentException e) {
          display += " ";
        }
      }
      display += "\n";
    }
    return display;
  }
}
