package cs3500.reversi.provider.view.gui;

import cs3500.reversi.provider.cell.HexagonCell;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

/**
 * a class to represent an image of a hexagon cell in a reversi game.
 */
public class Hexagon extends Path2D.Double {
  private final HexagonCell hexCell;
  //the pixel x-coordinate of a cell (where it is placed in the panel)
  private final int centerX;
  //the pixel y-coordinate of a cell (where it is placed in the panel)
  private final int centerY;
  //if the hexagon cell is clicked/highlighted
  private boolean isClicked;

  /**
   * constructs a hexagon shape.
   * @param hexCell a single hexagon cell in a reversi game model.
   */
  public Hexagon(HexagonCell hexCell, int centerX, int centerY) {
    this.hexCell = hexCell;
    this.centerX = centerX;
    this.centerY = centerY;
    this.isClicked = this.hexCell.selected();
  }

  /**
   * outlines a hexagon shape of a given side length.
   * @param g2d the graphics that the hexagon is drawn on (passed through in paint component)
   * @param hexSideLength the side length of the hexagon length
   */
  public void drawHex(Graphics2D g2d, double hexSideLength) {
    int[] xPoints = new int[6];
    int[] yPoints = new int[6];

    //the angle at each vertex in a hexagon
    double angle = (2 * Math.PI) / 6;

    for (int i = 0; i < 6; i++) {
      //the next vertex in a hexagon
      int xCoord = centerX + (int) (hexSideLength * Math.sin(angle * i));
      int yCoord = centerY - (int) (hexSideLength * Math.cos(angle * i));
      //sets the array values to be the x and y points at each vertex
      xPoints[i] = xCoord;
      yPoints[i] = yCoord;
    }
    Polygon hexagon = new Polygon(xPoints, yPoints, 6);
    //set the outline thickness
    g2d.setStroke(new BasicStroke(3.0f));
    //draw the hexagon outline
    g2d.setColor(Color.BLACK);
    g2d.draw(hexagon);
    //set the stroke back to default
    g2d.setStroke(new BasicStroke(1.0f));
    //sets the fill color and fill the hexagon
    this.setCellColor(g2d);
    g2d.fill(hexagon);
    //adds a game piece if the cell is occupied by a player
    this.addGamePiece(g2d, hexSideLength);
  }


  /**
   * determines what the fill color of the hexagon cell should be. Is set to gray if
   * the cell is not click, and is set to cyan if the cell is clicked.
   * @param g2d the graphics that the hexagon is drawn on (passed into drawHex).
   */
  private void setCellColor(Graphics2D g2d) {
    if (this.isClicked) {
      g2d.setColor(Color.CYAN);
    }
    else {
      g2d.setColor(Color.GRAY);
    }
  }


  /**
   * adds a game piece at a position in the g2d
   * white circle if player O, black circle if player X, none if the cell is empty.
   * @param g2d the graphics that the circle is drawn on (passed into drawHex).
   * @param hexSideLength the side length of a single hexagon cell on the game board.
   */
  private void addGamePiece(Graphics2D g2d, double hexSideLength) {
    if (!hexCell.isEmpty()) {
      int radius = (int) hexSideLength / 3;
      int diameter = radius * 2;
      int circleX = centerX - radius;
      int circleY = centerY - radius;
      Ellipse2D.Double circle = new Ellipse2D.Double(circleX, circleY, diameter, diameter);

      if (hexCell.toString().equals("X")) {
        g2d.setColor(Color.BLACK);
      }
      else {
        g2d.setColor(Color.WHITE);
      }
      g2d.draw(circle);
      g2d.fill(circle);
    }
  }

  /**
   * changes the cell state so that it is clicked.
   */
  public void selectCell() {
    this.hexCell.selectCell();
  }


  public int getCenterX() {
    return this.centerX;
  }

  public int getCenterY() {
    return this.centerY;
  }

  public HexagonCell getHexCell() {
    return this.hexCell;
  }

  public boolean isClicked() {
    return this.isClicked;
  }

}
