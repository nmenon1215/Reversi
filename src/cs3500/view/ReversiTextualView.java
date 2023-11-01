package cs3500.view;

import java.io.IOException;

import cs3500.model.HexagonalPosn;
import cs3500.model.ITile;
import cs3500.model.ROReversiModel;

/**
 * This is a simple representation of the model. It is meant mainly for debug purposes.
 * This simply prints out any board given to it in ascii.
 */
public class ReversiTextualView implements TextualView {

  ROReversiModel model;
  Appendable out;

  /**
   * Create a TextualModel linked with the model and an appendable. The model is RO(read only) to
   * show that the view will not modify the model, but simply display it.
   * @param model a READONLY version of the model.
   * @param out where the model is appended to for render.
   * @throws IllegalArgumentException if model or out is null.
   */
  public ReversiTextualView(ROReversiModel model, Appendable out) {
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null.");
    }
    if (out == null) {
      throw new IllegalArgumentException("Appendable can't be null.");
    }
    this.model = model;
    this.out = out;
  }

  @Override
  public void render() throws IOException {
    out.append(this.toString());
  }

  /**
   * Displays the model tied to this textual view as a string.
   * @return the textual representation of the current state of the model.
   */
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
