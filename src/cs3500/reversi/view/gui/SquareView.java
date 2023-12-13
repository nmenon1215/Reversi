package cs3500.reversi.view.gui;

import javax.swing.*;

import cs3500.reversi.model.ROReversiModel;

public class SquareView extends JFrameReversiView{
  public SquareView(ROReversiModel model) {
    super(model);
    panel = new SquarePanel(this, model);
    this.setContentPane(panel);
    this.setLocationRelativeTo(null);
    this.setLayout(null);
    this.pack();
    this.setFocusable(true);
  }
}
