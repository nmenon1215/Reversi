package cs3500.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.Objects;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import cs3500.model.Posn;
import cs3500.model.ROReversiModel;

/**
 * A JReversiPanel will draw all the tiles on the board, allow users to click on them,
 * and play the game.
 */
public class JReversiPanel extends JPanel implements ActionListener, KeyListener {

  private final ROReversiModel model;

  private final int boardSize;

  private HexagonalButton hex;

  private boolean mouseIsDown;

  public JReversiPanel(ReversiView reversiView, ROReversiModel reversiModel, int boardSize) {
    this.model = Objects.requireNonNull(reversiModel);
    this.boardSize = boardSize;

    hex = new HexagonalButton();
    add(hex);
  }

  /**
   * This method tells Swing what the "natural" size should be
   * for this panel.  Here, we set it to 400x400 pixels.
   * @return  Our preferred *physical* size.
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(450, 450);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
    // Set the location for the HexagonalButton within the panel
    int hexButtonX = 200;  // Adjust as needed
    int hexButtonY = 200;  // Adjust as needed

    // Draw the HexagonalButton at the specified location
    hex.setBounds(hexButtonX, hexButtonY, hex.getWidth(), hex.getHeight());
    hex.paintComponent(g2d);

    g2d.dispose();
  }

  /**
   * Computes the transformation that converts board coordinates
   * (with (0,0) in center, width and height our logical size)
   * into screen coordinates (with (0,0) in upper-left,
   * width and height in pixels).
   * <p>
   * This is the inverse of {@link JReversiPanel#transformPhysicalToLogical()}.
   * @return The necessary transformation
   */
  private AffineTransform transformLogicalToPhysical() {
    return null;
  }

  /**
   * Computes the transformation that converts screen coordinates
   * (with (0,0) in upper-left, width and height in pixels)
   * into board coordinates (with (0,0) in center, width and height
   * our logical size).
   * <p>
   * This is the inverse of {@link JReversiPanel#transformLogicalToPhysical()}.
   * @return The necessary transformation
   */
  private AffineTransform transformPhysicalToLogical() {
    return null;
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

  private class MouseEventsListener extends MouseInputAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
      JReversiPanel.this.mouseIsDown = true;
      this.mouseDragged(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      // This point is measured in actual physical pixels
      Point physicalP = e.getPoint();
      // For us to figure out which circle it belongs to, we need to transform it
      // into logical coordinates
      Point2D logicalP = transformPhysicalToLogical().transform(physicalP, null);
      // TODO: Figure out whether this location is inside a circle, and if so, which one
    }
  }
}
