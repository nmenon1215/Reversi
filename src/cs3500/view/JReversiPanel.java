package cs3500.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Objects;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import cs3500.model.ROReversiModel;

/**
 * A JReversiPanel will draw all the tiles on the board, allow users to click on them,
 * and play the game.
 */
public class JReversiPanel extends JPanel {

  /**
   * Our view will need to display a model, so it needs to get the current sequence from the model.
   */
  private final ROReversiModel reversiModel;

  private boolean mouseIsDown;
  public JReversiPanel(ROReversiModel reversiModel) {
    this.reversiModel = Objects.requireNonNull(reversiModel);

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
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
