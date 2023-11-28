package cs3500.view.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        char keyPressed = e.getKeyChar();
        if (keyPressed == 's') {
          //TODO: attempt to pass turn
          System.out.println("s");
        }
        else if (keyPressed == 'p') {
          //TODO: attempt to place a piece.
          System.out.println("p");
        }
        //TODO: Make sure controller tells view to refresh!!!
      }
    });

    this.setFocusable(true);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }
}
