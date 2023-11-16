package cs3500.view;

import javax.swing.JFrame;

import cs3500.model.ROReversiModel;

/**
 * Represents the background frame.
 */
public class JFrameReversiView extends JFrame implements ReversiView {


  /**
   * Create a background frame.
   * @param model the model to follow.
   */
  public JFrameReversiView(ROReversiModel model) {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setContentPane(new JReversiPanel(this, model));
    this.setLocationRelativeTo(null);
    this.setLayout(null);
    this.pack();
  }

  @Override
  public void display(boolean show) {
    this.setVisible(show);
  }
}
