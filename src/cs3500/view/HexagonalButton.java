package cs3500.view;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;

import javax.swing.JButton;

/**
 * Creates a hexagonal button that will be added to a board to create the grid for the Reversi
 * game.
 */
public class HexagonalButton extends JButton {

  /**
   * Gives the hexagon the properties to make it filled and look like a hexagon.
   */
  public HexagonalButton() {
    setContentAreaFilled(false);
    setOpaque(false);
    setBorderPainted(false);
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    Path2D hexagon = createHexagon(getWidth() / 2, getHeight() / 2,
            Math.min(getWidth(), getHeight()) / 2);

    g2d.setColor(getBackground());
    g2d.fill(hexagon);
  }

  private Path2D createHexagon(int q, int r, int size) {
    Path2D hexagon = new Path2D.Double();
    double angle;

    for (int i = 0; i < 6; i++) {
      angle = (2.0 * Math.PI * i) / 6;
      double x = size * (3.0 / 2.0) * q * Math.cos(angle);
      double y = size * Math.sqrt(3) * (r + q / 2.0) * Math.sin(angle);

      if (i == 0) {
        hexagon.moveTo(x, y);
      } else {
        hexagon.lineTo(x, y);
      }
    }

    hexagon.closePath();
    return hexagon;
  }
}