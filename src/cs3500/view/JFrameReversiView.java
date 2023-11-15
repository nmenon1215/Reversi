package cs3500.view;

import javax.swing.JFrame;

import cs3500.model.Posn;
import cs3500.model.ROReversiModel;

public class JFrameReversiView extends JFrame implements ReversiView {

  private final JReversiPanel panel;

  public JFrameReversiView(ROReversiModel model) {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panel = new JReversiPanel(this, model);
    this.setContentPane(panel);
    this.setLocationRelativeTo(null);
    this.setLayout(null);
    this.pack();
  }

  @Override
  public void display(boolean show) {
    this.setVisible(show);
  }
}
