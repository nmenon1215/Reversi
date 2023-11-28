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
        /* TODO:
            - check which key was pressed
            - either highlight for a move or pass
            - refresh the board at the end of it
         */
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
    double width = calculatePieceWidth(BOARDWIDTH); // of a single piece
    double height = calculatePieceHeight(BOARDHEIGHT); // of a single piece
    double y = 0; //Don't know if u need size for this. You might need nothing if you top left align
    for (int r = -size; r <= size; r++) {
      List<HexagonalButton> rowOfButtons = new ArrayList<>();
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
        HexagonalButton button = new HexagonalButton();

        // Add a MouseListener to each button directly within the loop
        button.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            // notify the controller of where this position is
            // TODO: return the logical position of the button
          }
        });

        add(button);
        button.setBounds((int) x, (int) y, (int) width, (int) height);
        rowOfButtons.add(button);
        x += width; // assume positive right
      }
      this.board.add(rowOfButtons);
      y += 3 * height / 4; // assume positive down
    }
    updateBoard();
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
        ITile tile = model.getTileAt(new HexagonalPosn(q, r, -r - q));
        HexagonalButton button = board.get(r + size).get(q - qStart);
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

  // TODO:
  //  - go through each tile and repaint it with the updated values
  // Repaints the board so that the new moves are represented.
  private void refresh() {
    for (int r = 0; r <= board.size(); r++) {
      for (int q = 0; q <= board.get(r).size(); q++) {
        HexagonalButton button = board.get(r).get(r);
        button.repaint();
      }
    }
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
