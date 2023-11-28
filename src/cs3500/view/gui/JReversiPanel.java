package cs3500.view.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.JFrame;

import cs3500.model.HexagonalPosn;
import cs3500.model.ITile;
import cs3500.model.Posn;
import cs3500.model.ROReversiModel;

/**
 * A JReversiPanel will draw all the tiles on the board, allow users to click on them,
 * and play the game.
 */
public class JReversiPanel extends JPanel implements ActionListener, KeyListener {

  private final double BOARDWIDTH = 450;
  private final double BOARDHEIGHT = (int) Math.ceil(Math.sqrt(3) / 2 * BOARDWIDTH);
  private final ROReversiModel model;
  private List<List<HexagonalButton>> board;

  private final int size;

  // there is no button initially highlighted
  private HexagonalButton highlightedButton = null;

  /**
   * Constructs a ReversiPanel and populates the view with the current board state.
   * @param frame the background frame to display on.
   * @param reversiModel is the model which we are displaying.
   */
  public JReversiPanel(JFrame frame, ROReversiModel reversiModel) {
    this.model = Objects.requireNonNull(reversiModel);
    this.size = model.getBoardSize();
    frame.setPreferredSize(this.getPreferredSize());
    this.board = new ArrayList<>();

    this.populateBoard();

    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        char keyPressed = e.getKeyChar();
        if (keyPressed == 's') {
          //TODO: attempt to pass turn
        }
        else if (keyPressed == 'p') {
          //TODO: attempt to place a piece.
        }
        //TODO: Make sure controller tells view to refresh!!!
      }
    });
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

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
  }

  private void populateBoard() {
    double pieceWidth = calculatePieceWidth(BOARDWIDTH);
    double pieceHeight = calculatePieceHeight(BOARDHEIGHT);
    double y = 0;
    for (int row = 0; row < size * 2 + 1; row++) {
      List<HexagonalButton> rowOfButtons = new ArrayList<>();
      double x  = startingX(row);
      for (int col = 0; col < -Math.abs(row - size) + size * 2 + 1; col++) {
        HexagonalButton button = new HexagonalButton();
        List<Integer> coord = gridToAxialCoord(row, col);
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
        // TODO: return the logical position of the button
        System.out.println("clicked at q = " + q + " r = " + r + " s = " + s);
      }
    });
  }

  // TODO: each time it is updated, repaint the tile to reflect highlight
  private void updateBoard() {
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
  }

  private List<Integer> gridToAxialCoord(int row, int col) {
    int qStart;
    int r = row - size;
    if(r < 0) {
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
    // if a button is already highlighted, de-highlight it
    if (highlightedButton != null) {
      highlightedButton.toggleHighlight();
    }

    // highlights the current button
    if (button != null && button != highlightedButton) {
      highlightedButton = button;
      highlightedButton.toggleHighlight();
    }
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


            ////////////////////////
            // ARE THESE NEEDED???//
            ////////////////////////

  @Override
  public void actionPerformed(ActionEvent e) {
    //TODO
  }

  @Override
  public void keyTyped(KeyEvent e) {
    //TODO
  }

  @Override
  public void keyPressed(KeyEvent e) {
    //TODO
  }

  @Override
  public void keyReleased(KeyEvent e) {
    //TODO
  }
}
