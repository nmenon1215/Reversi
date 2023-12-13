package cs3500.reversi.provider.view.gui;

import cs3500.reversi.model.HexagonalPosn;
import cs3500.reversi.provider.cell.HexagonCell;
import cs3500.reversi.provider.controller.ViewFeatures;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import cs3500.reversi.provider.model.ReadOnlyReversiModel;


/**
 * the panel and contents of the reversi game that goes inside the window frame.
 */
public class ReversiPanel extends JPanel implements PanelView {
  //the model of the reversi game
  ReadOnlyReversiModel model;
  //the set of hexagon images on the panel
  private ArrayList<Hexagon> hexagons;
  private final List<ViewFeatures> featuresListener;

  /**
   * constructs the panel by adding the model, adding mouse/key listeners, and setting
   * the background color.
   * @param model reversi game model.
   */
  public ReversiPanel(ReadOnlyReversiModel model) {
    this.model = Objects.requireNonNull(model);
    this.hexagons = new ArrayList<>();
    this.featuresListener = new ArrayList<>();
    setBackground(Color.DARK_GRAY);

    //add mouse listener
    MouseEventsListener listener = new MouseEventsListener();
    this.addMouseListener(listener);
    this.addMouseMotionListener(listener);

    //add key listener
    setFocusable(true);
    requestFocusInWindow();
    KeyEventsListener keyListener = new KeyEventsListener();
    this.addKeyListener(keyListener);
  }


  /**
   * paints all the hexagons in the game board of a reversi game onto the panel.
   * @param graphics the <code>Graphics</code> object to protect
   */
  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D g2d = (Graphics2D)graphics;
    Rectangle bounds = this.getBounds();
    double sideLength = this.getHexSideLength(bounds);

    //resets the board every time paint is called
    this.hexagons = new ArrayList<>();
    this.createHexagons(bounds);
    //draws the hexagons onto the panel
    for (Hexagon hex : hexagons) {
      hex.drawHex(g2d, sideLength);
    }
  }


  /**
   * Adds a listener to the view to react to the signals the view sends.
   * @param features the listener (the controller).
   */
  @Override
  public void addFeatureListener(ViewFeatures features) {
    this.featuresListener.add(Objects.requireNonNull(features));
  }


  /**
   * creates all the Hexagons from the HexagonCells in the reversi game board.
   * @param bounds the bounds of the panel.
   */
  private void createHexagons(Rectangle bounds) {
    double sideLength = this.getHexSideLength(bounds);
    double hexWidth = (Math.sqrt(3) * sideLength);

    for (ArrayList<HexagonCell> list : model.getGameBoard()) {
      for (int i = 0; i < list.size(); i ++) {
        HexagonCell cell = list.get(i);
        double middleXFrame = bounds.getWidth() / 2;
        //the number of half-widths the starting hexagon center should be from the center
        int numSpacesShift = ((model.getBoardSideLength() - 1) * 2) - (Math.abs(cell.getRowPos()));
        //the x value of the center of the first hexagon in each row
        double start = middleXFrame - ((hexWidth / 2) * numSpacesShift);
        //adds a one hexagon width to the x coordinate for every cell in the list
        int x = (int) (start + (hexWidth * i));

        //the y coordinate of the middle of the board
        double middleYFrame = bounds.getHeight() / 2;
        //adds/subtracts the height of one hexagon to the y coordinate depending on how far the
        //row is from the middle row
        int y = (int) (middleYFrame + (cell.getRowPos() * (sideLength * 1.5)));

        Hexagon hexagon = new Hexagon(cell, x, y);
        hexagons.add(hexagon);
      }
    }
  }


  /**
   * returns what the side length of one hexagon should be so that it fills up the screen
   * either horizontally or vertically.
   * @param bounds the bounds of the screen.
   * @return the side length for one hexagon.
   */
  private double getHexSideLength(Rectangle bounds) {
    double boardLength = bounds.getHeight();
    //the number of side lengths (of an individual hex cell) that should fit vertically
    int numVerticalSideLengths = (model.getBoardSideLength() * 3) - 1;
    double verticalSideLength = boardLength / numVerticalSideLengths;

    double boardWidth = bounds.getWidth();
    //the number of cells in the greatest horizontal row (the middle row)
    int numMaxHorizontalCells = (model.getBoardSideLength() * 2) - 1;
    double horizontalSideLength = boardWidth / (Math.sqrt(3) * numMaxHorizontalCells);

    //the side length will be the smallest of these values to ensure that the grid fits
    //both horizontally and vertically
    return Math.min(verticalSideLength, horizontalSideLength);
  }


  /**
   * This method tells Swing what the "natural" size should be
   * for this panel.  Here, we set it to 500x500 pixels.
   * @return  Our preferred *physical* size.
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(500, 500);
  }


  /**
   * Deselects all hexagons on the board so that they all return grey.
   */
  @Override
  public void deselectAll() {
    for (Hexagon hex : hexagons) {
      if (hex.isClicked()) {
        hex.selectCell();
      }
    }
  }


  /**
   * deselects any hexagons that is not the one being selected.
   * @param selected the hexagon being selected.
   */
  private void resetFlips(Hexagon selected) {
    for (Hexagon hex : hexagons) {
      if (!hex.equals(selected) && hex.isClicked()) {
        hex.selectCell();
      }
    }
  }


  /**
   * Is this click outside the board? check.
   * @param mouseX represents the X of click.
   * @param mouseY represents the Y of click.
   * @param sideLength represents the side boundary.
   */
  private void clickOutsideBoard(int mouseX, int mouseY, double sideLength) {
    boolean insideBoard = false;
    for (Hexagon hex : hexagons) {
      if (this.isPointInHexagon(mouseX, mouseY, hex.getCenterX(), hex.getCenterY(), sideLength)) {
        insideBoard = true;
      }
    }
    if (!insideBoard) {
      deselectAll();
      repaint();
    }
  }


  /**
   * Checks if a point is inside a hexagon.
   * @param x      X-coordinate of the point.
   * @param y      Y-coordinate of the point.
   * @param hexX   X-coordinate of the hexagon.
   * @param hexY   Y-coordinate of the hexagon.
   * @param length Side length of the hexagon.
   * @return True if the point is inside the hexagon, false otherwise.
   */
  private boolean isPointInHexagon(int x, int y, double hexX, double hexY, double length) {
    //checks that the distance from the center to the point is within the hexagon
    double distance = Math.sqrt(Math.pow(x - hexX, 2) + Math.pow(y - hexY, 2));
    return (distance < Math.sqrt(3) * length / 2);
  }


  /**
   * determines if any of the hexagon cells are selected/ highlighted.
   * @return true if there is a hexagon cell that is selected.
   */
  private boolean isOneSelected() {
    boolean anySelected = false;
    for (Hexagon hex : hexagons) {
      if (hex.isClicked()) {
        anySelected = true;
        break;
      }
    }
    return anySelected;
  }


  /**
   * returns the hexagon cell in the board that is selected.
   * @return the HexagonCell that is selected, or null if there is none.
   */
  private HexagonCell getSelected() {
    ArrayList<ArrayList<HexagonCell>> board = model.getGameBoard();
    HexagonCell selected = null;
    for (ArrayList<HexagonCell> list : board) {
      for (HexagonCell cell : list) {
        if (cell.selected()) {
          selected = cell;
        }
      }
    }
    return selected;
  }


  /**
   * A class that handles mouse events and holds methods that carry out actions based on
   * specific mouse events.
   */
  private class MouseEventsListener extends MouseInputAdapter {

    /**
     * selects (highlights) a cell if the mouse clicks on it, unselects all cells if the mouse
     * clicks anywhere outside the board.
     * @param e the event to be processed.
     */
    @Override
    public void mousePressed(MouseEvent e) {
      // Get the mouse click coordinates
      int mouseX = e.getX();
      int mouseY = e.getY();
      double sideLength = getHexSideLength(getBounds());
      clickOutsideBoard(mouseX, mouseY, sideLength);

      // Check if the click is within any hexagon bounds

      // Here the view highlights the clicked cell. It will click any cell within the board.
      // We decided to have any cell be able to be highlighted, whether it included a tile or not.
      // This is because the user should be able to highlight any spot, just not be able to make
      // a move there.

      for (Hexagon hex : hexagons) {
        if (isPointInHexagon(mouseX, mouseY, hex.getCenterX(), hex.getCenterY(), sideLength)) {
          resetFlips(hex);
          hex.selectCell();
          repaint();
        }
      }
    }
  }


  /**
   * A class that handles key events and holds methods to carry out specific actions based
   * on key events.
   */
  private class KeyEventsListener extends KeyAdapter {

    /**
     * Indicates that a player wants to make a move to the highlighted cell is the key "m"
     * is pressed, and indicates that a cell must be selected if there is no highlighted cell.
     * Indicates that a player wants to pass if the key "p" is pressed.
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
      Character key = e.getKeyChar();

      // resets the highlight but does and indicates a move to be made
      if (key.equals('m') || key.equals('\n') || key.equals('M') || key.equals(' ')) {
        if (isOneSelected()) {
          HexagonCell hex = getSelected();
          int diagonalPos = hex.getDiagonalPos();
          int rowPos = hex.getRowPos();

          for (ViewFeatures features : featuresListener) {
            features.playerMove(new HexagonalPosn(diagonalPos, rowPos));
          }

          deselectAll();
        }
      }
      if (key.equals('p') || key.equals('P')) {

        for (ViewFeatures features : featuresListener) {
          features.playerPass();
        }
        deselectAll();
      }
    }
  }

}