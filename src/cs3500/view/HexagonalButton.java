package cs3500.view;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Path2D;

import javax.swing.JButton;

/**
 * Creates a hexagonal button that will be added to a board to create the grid for the Reversi
 * game.
 */
public class HexagonalButton extends JButton {

  private int width;
  private int height;
  private boolean click = false;

  /**
   * Gives the hexagon the properties to make it filled and look like a hexagon.
   */
  public HexagonalButton() {
    setContentAreaFilled(true);
    setOpaque(false);
    setBorderPainted(true);

    addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        click = !click;
        highlight();
      }
    });
  }

  private void highlight() {
    if (click) {
      setBackground(Color.RED);
    }
    else {
      setBackground(Color.DARK_GRAY);
    }
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    Rectangle bounds= this.getBounds();
    width = bounds.width;
    height = bounds.height;
    Path2D hexagon = createHexagon();

    g2d.setColor(Color.DARK_GRAY);
    g2d.fill(hexagon);
  }

  private Path2D createHexagon() {
    Path2D line = new Path2D.Double();

    double sideLength = (double) width / 2;
    double midpoint = Math.sqrt(3) / 2 * sideLength;
    double x = width / 2.0;
    double y = height / 2.0;

    line.moveTo(x + sideLength, y);
    line.lineTo(x + sideLength / 2, y - midpoint);
    line.lineTo(x - sideLength / 2, y - midpoint);
    line.lineTo(x - sideLength, y);
    line.lineTo(x - sideLength / 2, y + midpoint);
    line.lineTo(x + sideLength / 2, y + midpoint);
    line.closePath();

    return line;
  }
}