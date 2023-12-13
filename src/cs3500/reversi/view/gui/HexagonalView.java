package cs3500.reversi.view.gui;

import javax.swing.*;

import cs3500.reversi.model.ROReversiModel;

public class HexagonalView extends JFrameReversiView{
  public HexagonalView(ROReversiModel model) {
    super(model);
    panel = new HexagonalPanel(this, model);
    this.setContentPane(panel);
    this.setLocationRelativeTo(null);
    this.setLayout(null);
    this.pack();
    this.setFocusable(true);
  }
}
