package cs3500.reversi.view.textual;

import java.io.IOException;

import cs3500.reversi.model.HexagonalPosn;
import cs3500.reversi.model.ITile;
import cs3500.reversi.model.ROReversiModel;
import cs3500.reversi.model.SquarePosn;

public class SquareReversiTextualView implements TextualView {
  ROReversiModel model;
  Appendable out;

  /**
   * Create a TextualModel linked with the model and an appendable. The model is RO(read only) to
   * show that the view will not modify the model, but simply display it.
   *
   * @param model a READONLY version of the model.
   * @param out   where the model is appended to for render.
   * @throws IllegalArgumentException if model or out is null.
   */
  public SquareReversiTextualView(ROReversiModel model, Appendable out) {
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
   *
   * @return the textual representation of the current state of the model.
   */
  public String toString() {
    String display = "";
    int boardSize = model.getBoardSize();
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        try {
          ITile tileToDisplay = model.getTileAt(new SquarePosn(col, row));
          display += tileToDisplay.toString() + " ";
        } catch (IllegalArgumentException e) {
          display += "uhoh";
        }
      }
      display += "\n";
    }
    return display;
  }
}
