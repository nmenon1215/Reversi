package cs3500.reversi.view.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.geom.Path2D;
import java.util.Objects;

import javax.swing.JButton;

import cs3500.reversi.model.Posn;
import cs3500.reversi.model.ROReversiModel;
import cs3500.reversi.player.Player;

import static java.awt.Color.BLACK;

/**
 * Creates a hexagonal button that will be added to a board to create the grid for the Reversi
 * game.
 */
public abstract class ReversiButton extends JButton {

  private int width;
  private int height;
  private boolean highlighted = false;
  private Color buttonColor = Color.DARK_GRAY;
  private Posn posn;
  private ROReversiModel roModel;
  private boolean hints;
  private Player player;

  /**
   * Gives the hexagon the properties to make it filled and look like a hexagon.
   */
  public ReversiButton(Posn posn, ROReversiModel roModel, Player player) {
    this.posn = Objects.requireNonNull(posn);
    this.roModel = roModel;
    this.player = player;
    setContentAreaFilled(false);
    setOpaque(false);
    setBorderPainted(false);
    setFocusPainted(false);
  }

  /**
   * Changes whether the button should be highlighted.
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
    Path2D.Double shape = new Path2D.Double();
    createShape(shape);

    // highlight the background
    if (highlighted) {
      g2d.setColor(Color.GREEN);
    }
    else {
      g2d.setColor(Color.DARK_GRAY);
    }
    g2d.fill(shape);

    // highlight the chip if it is gray
    if (highlighted && this.buttonColor.equals(Color.DARK_GRAY)) {
      if (hints) {
        int piecesGained = this.roModel.countPiecesGained(this.player,
                posn);
        g2d.setColor(BLACK);
        g2d.drawString(Integer.toString(piecesGained), width / 2, height / 2);
      }
      g2d.setColor(Color.GREEN);
    }
    else {
      g2d.setColor(this.buttonColor);
      g2d.fillOval(width / 4, height / 4, width / 2, width / 2);
    }



  }

  @Override
  public boolean contains(int x, int y) {
    Path2D.Double hexagon = new Path2D.Double();
    createShape(hexagon);

    return hexagon.contains(x, y);
  }

  protected abstract void createShape(Path2D.Double line);

  public void flipBlack() {
    buttonColor = Color.BLACK;
  }

  public void flipWhite() {
    buttonColor = Color.WHITE;
  }

  public Posn getPosn() {
    return posn;
  }

  public void toggleHints() {
    hints = !hints;
  }

  public void setCurrentPlayer(Player player) {
    this.player = player;
  }

}