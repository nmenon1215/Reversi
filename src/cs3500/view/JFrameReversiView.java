package cs3500.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JFrame;

import cs3500.model.ROReversiModel;

public class JFrameReversiView extends JFrame implements IFrame {

  private final JReversiPanel panel;

  public JFrameReversiView(ROReversiModel model) {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panel = new JReversiPanel(model);
    this.add(panel);
    this.pack();
  }

  @Override
  public void display(boolean show) {

  }
}
