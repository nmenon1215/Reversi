package cs3500.reversi.view.gui;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;

import cs3500.reversi.model.HexagonalPosn;
import cs3500.reversi.model.ITile;
import cs3500.reversi.model.ROReversiModel;
import cs3500.reversi.provider.controller.ViewFeatures;

/**
 * A JReversiPanel will draw all the tiles on the board, allow users to click on them,
 * and play the game.
 */
public class JReversiPanel extends JPanel {

  private final double BOARDWIDTH = 450;
  private final double BOARDHEIGHT = (int) Math.ceil(Math.sqrt(3) / 2 * BOARDWIDTH);
  private final ROReversiModel model;
  private List<List<HexagonalButton>> board;
  private final int size;
  private HexagonalButton highlightedButton = null;
  private final JLabel clickedCoords;
  private final List<ViewFeatures> featuresListener;

  /**
   * Constructs a ReversiPanel and populates the view with the current board state.
   * @param frame the background frame to display on.
   * @param reversiModel is the model which we are displaying.
   */
  public JReversiPanel(JFrame frame, ROReversiModel reversiModel) {
    this.model = Objects.requireNonNull(reversiModel);
    this.size = model.getBoardSize();
    frame.setSize(this.getPreferredSize());
    this.board = new ArrayList<>();
    clickedCoords = new JLabel("Coords");
    add(clickedCoords);
    clickedCoords.setBounds(10, (int) BOARDHEIGHT - 20, 300, 20);
    featuresListener = new ArrayList<>();

    this.populateBoard();

    //add key listener
    setFocusable(true);
    requestFocusInWindow();
    KeyEventsListener keyListener = new KeyEventsListener();
    this.addKeyListener(keyListener);
  }

  /**
   * This method tells Swing what the "natural" size should be
   * for this panel.  Here, we set it to 500x500 pixels.
   *
   * @return Our preferred *physical* size.
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension((int) BOARDWIDTH, (int) BOARDHEIGHT);
  }

  private void populateBoard() {
    double pieceWidth = calculatePieceWidth(BOARDWIDTH);
    double pieceHeight = calculatePieceHeight(BOARDHEIGHT);
    double y = 0;
    for (int row = 0; row < size * 2 + 1; row++) {
      List<HexagonalButton> rowOfButtons = new ArrayList<>();
      double x  = startingX(row);
      for (int col = 0; col < -Math.abs(row - size) + size * 2 + 1; col++) {
        List<Integer> coord = gridToAxialCoord(row, col);
        HexagonalButton button = new HexagonalButton(coord, this.model, this.model.getTurn());
        createListener(button, coord.get(0), coord.get(1), coord.get(2));
        add(button);
        button.setBounds((int) x, (int) y, (int) pieceWidth, (int) pieceHeight);
        rowOfButtons.add(button);
        x += pieceWidth;
      }
      this.board.add(rowOfButtons);
      y += 3 * pieceHeight / 4; // assume positive down
    }
    updateBoard();
  }

  private void createListener(HexagonalButton button, int q, int r, int s) {
    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        // notify the controller of where this position is
        buttonClicked(button);
        printCoords(q, r, s);
        requestFocusInWindow();
      }
    });
  }

  void updateBoard() {
    for (int row = 0; row < size * 2 + 1; row++) {
      for (int col = 0; col < board.get(row).size(); col++) {
        ITile tile = model.getTileAt(new HexagonalPosn(gridToAxialCoord(row, col)));
        HexagonalButton button = board.get(row).get(col);
        if (tile.getPlayer() != null) {
          if (tile.getPlayer().toString().equals("X")) {
            button.flipBlack();
          } else if (tile.getPlayer().toString().equals("O")) {
            button.flipWhite();
          }
        }
      }
    }
    this.repaint();
  }

  // prints the logical coordinates of the clicked tile
  private void printCoords(int q, int r, int s) {
    String infoText = String.format("q= %d, r= %d, s= %d", q, r, s);
    clickedCoords.setText(infoText);
  }

  private List<Integer> gridToAxialCoord(int row, int col) {
    int qStart;
    int r = row - size;
    if (r < 0) {
      qStart = -r - size;
    }
    else {
      qStart = -size;
    }
    int q = qStart + col;
    int s = - r - q;
    return List.of(q, r, s);
  }

  // takes in the button that was clicked
  private void buttonClicked(HexagonalButton button) {
    if (highlightedButton != null) {
      highlightedButton.setHighlight(false);
    }
    highlightedButton = button;
    highlightedButton.setHighlight(true);
    this.repaint();
  }

  private double startingX(int r) {
    r -= size;
    if (r < 0) {
      return -r * calculatePieceWidth(BOARDWIDTH) / 2;
    } else {
      return r * calculatePieceWidth(BOARDWIDTH) / 2;
    }
  }

  private double calculatePieceWidth(double boardWidth) {
    return boardWidth / (size * 2 + 1);
  }

  private double calculatePieceHeight(double boardHeight) {
    return boardHeight * 2 / (size * 3 + 2);
  }

  public List<Integer> getHighlighted() {
    return this.highlightedButton.getCoords();
  }

  public void addFeatureListener(ViewFeatures features) {
    this.featuresListener.add(Objects.requireNonNull(features));
  }

  /**
   * Deselects all tiles currently selected.
   */
  public void deselectAll() {
    if (highlightedButton != null) {
      this.highlightedButton.setHighlight(false);
      this.highlightedButton = null;
    }
    updateBoard();
  }

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
        if (highlightedButton != null) {
          List<Integer> coords = highlightedButton.getCoords();
          for (ViewFeatures features : featuresListener) {
            features.playerMove(coords.get(0), coords.get(1));
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

      if (key.equals('h') || key.equals('H')) {
        toggleHintsForAll();
      }
    }
  }

  public void toggleHintsForAll() {
    for (List<HexagonalButton> row : this.board) {
      for (HexagonalButton button : row) {
        button.toggleHints();
      }
    }
  }
}
