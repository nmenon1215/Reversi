package cs3500.view.gui;

import java.awt.event.KeyListener;
import java.util.List;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs3500.model.ROReversiModel;

/**
 * Represents the background frame.
 */
public class JFrameReversiView extends JFrame implements ReversiView {

  JReversiPanel panel;
  
  /**
   * Create a background frame.
   * @param model the model to follow.
   */
  public JFrameReversiView(ROReversiModel model) {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel = new JReversiPanel(this, model);
    this.setContentPane(panel);
    this.setLocationRelativeTo(null);
    this.setLayout(null);
    this.pack();
    this.setFocusable(true);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void update() {
    this.panel.updateBoard();
  }

  @Override
  public void displayException(Exception e) {
    JOptionPane errorMsg = new JOptionPane();
    errorMsg.showMessageDialog(this.panel, e.getMessage());
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    this.panel.addKeyListener(listener);
  }

  @Override
  public boolean requestFocusInWindow() {
    return this.panel.requestFocusInWindow();
  }

  @Override
  public List<Integer> getHighlighted() {
    return this.panel.getHighlighted();
  }
}
