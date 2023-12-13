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
import javax.swing.JFrame;

import cs3500.reversi.model.ITile;
import cs3500.reversi.model.Posn;
import cs3500.reversi.model.ROReversiModel;
import cs3500.reversi.player.Player;
import cs3500.reversi.provider.controller.ViewFeatures;

/**
 * A JReversiPanel will draw all the tiles on the board, allow users to click on them,
 * and play the game.
 */
public abstract class JReversiPanel extends JPanel {

  protected final double BOARDWIDTH = 450;
  protected final double BOARDHEIGHT = calculateBoardHeight();
  protected final ROReversiModel model;
  protected List<List<ReversiButton>> board;
  protected final int size;
  private ReversiButton highlightedButton = null;
  //private final JLabel clickedCoords;
  private final List<ViewFeatures> featuresListener;
  private Player currentPlayer;

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
    //COORDS in bottom of corner might interfere with view in square reversi
//    clickedCoords = new JLabel("Coords");
//    add(clickedCoords);
//    clickedCoords.setBounds(10, (int) BOARDHEIGHT - 20, 300, 20);
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
    for (int row = 0; row < numRows(); row++) {
      List<ReversiButton> rowOfButtons = new ArrayList<>();
      double x  = startingX(row);
      System.out.println(numColsInRow(row));
      for (int col = 0; col < numColsInRow(row); col++) {
        rowOfButtons.add(createButton(row, col, x, y, pieceWidth, pieceHeight));
        x += pieceWidth;
      }
      this.board.add(rowOfButtons);
      y += yChange(pieceHeight);
    }
    updateBoard();
  }

  protected void createListener(ReversiButton button) {
    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        // notify the controller of where this position is
        buttonClicked(button);
        //printCoords(button.getPosn());
        requestFocusInWindow();
      }
    });
  }

  protected void updateBoard() {
    for (int row = 0; row < this.board.size(); row++) {
      for (int col = 0; col < board.get(row).size(); col++) {
        ITile tile = model.getTileAt(gridToAxialCoord(row, col));
        ReversiButton button = board.get(row).get(col);
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
  private void printCoords(Posn posn) {
    List<Integer> coords = posn.getCoords();
    String infoText = String.format("q= %d, r= %d, s= %d",
            coords.get(0), coords.get(1), coords.get(2));
    //clickedCoords.setText(infoText);
  }

  // takes in the button that was clicked
  private void buttonClicked(ReversiButton button) {
    if (highlightedButton != null) {
      highlightedButton.setHighlight(false);
    }
    highlightedButton = button;
    highlightedButton.setHighlight(true);
    this.repaint();
  }

  protected abstract int numRows();

  protected abstract int numColsInRow(int row);

  protected abstract double startingX(int r);

  protected abstract double yChange(double pieceHeight);

  protected abstract double calculatePieceWidth(double boardWidth);

  protected abstract double calculatePieceHeight(double boardHeight);

  protected abstract double calculateBoardHeight();

  protected abstract Posn gridToAxialCoord(int row, int col);

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

  protected abstract ReversiButton createButton(int row, int col, double x, double y,
                                                double pieceWidth, double pieceHeight);

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
          for (ViewFeatures features : featuresListener) {
            features.playerMove(highlightedButton.getPosn());
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
    for (List<ReversiButton> row : this.board) {
      for (ReversiButton button : row) {
        button.toggleHints();
      }
    }
  }

  public void setPlayer(Player p) {
    this.currentPlayer = p;
    for (List<ReversiButton> row : this.board) {
      for (ReversiButton button : row) {
        button.setCurrentPlayer(p);
      }
    }
  }
}
