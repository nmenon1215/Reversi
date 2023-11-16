package cs3500.view;

import javax.swing.*;

import cs3500.model.ROReversiModel;

/**
 * Represents the background frame.
 */
public class JFrameReversiView extends JFrame implements ReversiView {

  private final JReversiPanel panel;

  /**
   * Create a background frame.
   * @param model the model to follow.
   */
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
