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
import cs3500.model.ITile;
import cs3500.model.Posn;
import cs3500.model.ROReversiModel;

/**
 * A JReversiPanel will draw all the tiles on the board, allow users to click on them,
 * and play the game.
 */
public class JReversiPanel extends JPanel implements ActionListener, KeyListener {

  private final double BOARDWIDTH = 450;
  private final double BOARDHEIGHT = (int) Math.ceil(Math.sqrt(3)/2 * BOARDWIDTH);
  private final ROReversiModel model;

  private final int size;
  private JFrame frame;
  private JButton background;

  private boolean mouseIsDown;

  public JReversiPanel(JFrame frame, ROReversiModel reversiModel) {
    this.model = Objects.requireNonNull(reversiModel);
    this.size = model.getBoardSize();
    this.frame = Objects.requireNonNull(frame);
    this.frame.setPreferredSize(this.getPreferredSize());

    this.populateBoard();
  }

  /**
   * This method tells Swing what the "natural" size should be
   * for this panel.  Here, we set it to 500x500 pixels.
   * @return  Our preferred *physical* size.
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension((int) BOARDWIDTH, (int) BOARDHEIGHT);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
  }

  private void populateBoard() {
    double width = calculatePieceWidth(BOARDWIDTH); // of a single piece
    double height = calculatePieceHeight(BOARDHEIGHT); // of a single piece
    double y = 0; //Don't know if u need size for this. You might need nothing if you top left align
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
      double x = startingX(r, BOARDWIDTH);
      for (int q = qStart; q <= qEnd; q++) {
        JButton button = new HexagonalButton();
        add(button);
        button.setBounds((int) x, (int) y, (int) width, (int) height);
        x += width; // assume positive right
      }
      y += 3 * height / 4; // assume positive down
    }
  }

  private void updateBoard() {
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
      for (int q = qStart; q <= qEnd; q++) {
        ITile tile = model.getTileAt(new HexagonalPosn(q, r, -r -q));
      }
    }
  }

  private double startingX(int r, double boardWidth) {
    if (r < 0) {
      return -r * calculatePieceWidth(boardWidth) / 2;
    } else {
      return r * calculatePieceWidth(boardWidth) / 2;
    }
  }

  private double calculatePieceWidth(double boardWidth) {
    return boardWidth / (size * 2 + 1);
  }

  private double calculatePieceHeight(double boardHeight) {
    return boardHeight * 2 / (size * 3 + 2);
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
