package cs3500.reversi.view.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs3500.reversi.model.ROReversiModel;
import cs3500.reversi.player.Player;
import cs3500.reversi.provider.controller.ViewFeatures;
import cs3500.reversi.provider.view.gui.FrameView;

/**
 * Represents the background frame.
 */
public abstract class JFrameReversiView extends JFrame implements FrameView {

  protected JReversiPanel panel;

  public JFrameReversiView(ROReversiModel model) {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
  public void makeTitle(Player p) {
    if (p.toString().equals("X")) {
      String title = "BLACK";
      this.setTitle(title);
    }
    else {
      String title = "WHITE";
      this.setTitle(title);
    }
    this.panel.setPlayer(p);
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
