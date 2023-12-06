package cs3500.reversi.view.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.List;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputAdapter;

import cs3500.reversi.model.ROReversiModel;
import cs3500.reversi.provider.cell.HexagonCell;
import cs3500.reversi.provider.controller.ViewFeatures;
import cs3500.reversi.provider.view.gui.FrameView;
import cs3500.reversi.provider.view.gui.Hexagon;
import cs3500.reversi.provider.view.gui.ReversiPanel;

/**
 * Represents the background frame.
 */
public class JFrameReversiView extends JFrame implements FrameView {

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
  public void refresh() {
    this.panel.updateBoard();
  }

  @Override
  public void setVisible() {
    this.setVisible(true);
  }

  @Override
  public void showMessageDialog(String message) {
    JOptionPane errorMsg = new JOptionPane();
    errorMsg.showMessageDialog(this.panel, message);
  }

  @Override
  public void addFeatureListener(ViewFeatures features) {
    this.panel.addFeatureListener(features);
  }

  @Override
  public void deselectAll() {
    this.panel.deselectAll();
  }
}
