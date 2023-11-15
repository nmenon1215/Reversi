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
    setContentAreaFilled(false);
    setOpaque(false);
    setBorderPainted(false);
    setFocusPainted(false);

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
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);
    Rectangle bounds = this.getBounds();
    width = bounds.width;
    height = bounds.height;
    Path2D.Double hexagon = new Path2D.Double();
    createHexagon(hexagon);

    g2d.setColor(Color.DARK_GRAY);
    g2d.fill(hexagon);
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
}