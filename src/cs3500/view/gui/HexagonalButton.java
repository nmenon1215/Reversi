package cs3500.view.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;

import javax.swing.JButton;

/**
 * Creates a hexagonal button that will be added to a board to create the grid for the Reversi
 * game.
 */
public class HexagonalButton extends JButton {

  private int width;
  private int height;
  private boolean highlighted = false;
  private Color buttonColor = Color.DARK_GRAY;

  /**
   * Gives the hexagon the properties to make it filled and look like a hexagon.
   */
  public HexagonalButton() {
    setContentAreaFilled(false);
    setOpaque(false);
    setBorderPainted(false);
    setFocusPainted(false);
  }

  /**
   * Changes whether or not the button should be highlighted.
   */
  public void setHighlight(boolean highlighted) {
    this.highlighted = highlighted;
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);
    Rectangle bounds = this.getBounds();
    width = bounds.width;
    height = bounds.height;
    Path2D.Double hexagon = new Path2D.Double();
    createHexagon(hexagon);

    if (highlighted) {
      g2d.setColor(Color.GREEN);
    }
    else {
      g2d.setColor(Color.DARK_GRAY);
    }

    g2d.fill(hexagon);
    if (highlighted && this.buttonColor.equals(Color.DARK_GRAY)) {
      g2d.setColor(Color.GREEN);
    }
    else {
      g2d.setColor(this.buttonColor);
    }
    g2d.fillOval(width / 4, height / 4, width / 2, width / 2);
  }

  @Override
  public boolean contains(int x, int y) {
    Path2D.Double hexagon = new Path2D.Double();
    createHexagon(hexagon);

    return hexagon.contains(x, y);
  }

  private void createHexagon(Path2D.Double line) {
    Rectangle bounds = this.getBounds();
    double height = bounds.getHeight();
    double sideLength = height / 2;
    double widthMovement = sideLength * Math.sqrt(3) / 2;
    line.moveTo(widthMovement, 0);
    line.lineTo(widthMovement * 2, sideLength / 2);
    line.lineTo(widthMovement * 2, 3 * sideLength / 2);
    line.lineTo(widthMovement, 2 * sideLength);
    line.lineTo(0, 3 * sideLength / 2);
    line.lineTo(0, sideLength / 2);
    line.closePath();
  }

  public void flipBlack() {
    buttonColor = Color.BLACK;
  }

  public void flipWhite() {
    buttonColor = Color.WHITE;
  }
}