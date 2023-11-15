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
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import cs3500.model.HexagonalPosn;
import cs3500.model.HexagonalTile;
import cs3500.model.Posn;
import cs3500.model.ROReversiModel;

/**
 * A JReversiPanel will draw all the tiles on the board, allow users to click on them,
 * and play the game.
 */
public class JReversiPanel extends JPanel implements ActionListener, KeyListener {

  private final ROReversiModel model;

  private final int size;

  private HexagonalButton hex;
  private JButton background;

  private boolean mouseIsDown;

  public JReversiPanel(ReversiView reversiView, ROReversiModel reversiModel, int boardSize) {
    this.model = Objects.requireNonNull(reversiModel);
    this.size = boardSize;

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

//    // Draw the HexagonalButton at the specified location
//    hex.setLocation(getWidth() / 2, getHeight() / 2);
//    hex.setBounds(ge, getWidth(), getHeight());
    hex.paintComponent(g2d);

    g2d.dispose();
  }

  private void populateBoard(int centerX, int centerY, int size) {
    JButton center = new HexagonalButton();
    int width = calculateWidth(size); // of a single piece
    int height = calculateHeight(size); // of a single piece
    int y = startingY(centerX, centerY, size); //Don't know if u need size for this. You might need nothing if you top left align
    for (int r = -size; r <= size; r++) {
      int qStart;
      int qEnd;
      if (r < 0) {
        qStart = -size - r;
        qEnd = size;
      } else {
        qStart = -size;
        qEnd = size - r;
      }
      int x = startingX(r);
      for (int q = qStart; q <= qEnd; q++) {
        int s = -q - r;
        JButton button = new HexagonalButton();
        add(button);
        button.setBounds(x, y, width, height);
        x += width; // assume positive right
      }
      y += height; // assume positive down
    }
    /*
    for (int i = 1; i < size; i++) {
      for (int j = 0; j < calculateCircumference(i); j++) {
        JButton button = new HexagonalButton();
        add(button);
        List<Integer> placement = findPlacement(i, j);
        button.setBounds()
      }
    }
    */
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
