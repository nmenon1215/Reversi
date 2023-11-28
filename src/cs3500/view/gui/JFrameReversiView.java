package cs3500.view.gui;

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

    // TODO:
    //  - add a key listener that senses m and p key strokes and calls the methods for it
  }

  @Override
  public void display(boolean show) {
    this.setVisible(show);
  }
}
