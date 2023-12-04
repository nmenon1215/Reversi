package cs3500.reversi.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

import cs3500.reversi.model.MutableReversiModel;
import cs3500.reversi.model.Posn;
import cs3500.reversi.player.Player;
import cs3500.reversi.view.gui.ReversiView;

/**
 * Creates a controller to gain a connection between the view and the model.
 */
public class Controller implements ReversiController, KeyListener {
  MutableReversiModel model;
  ReversiView view;
  Player p;

  /**
   * Creates a controller that will be used for a player. It handles the connection between
   * the view and model.
   * @param model the given model of the game being played.
   * @param view the given view that represents the game being played.
   * @param p the player the controller refers to.
   */
  public Controller(MutableReversiModel model, ReversiView view, Player p) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.p = Objects.requireNonNull(p);

    this.view.makeVisible();
    this.view.addKeyListener(this);
    this.model.subscribe(this, this.p);
    this.view.createTitle(p.toString());
  }

  @Override
  public void start() {
    this.view.requestFocusInWindow();
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // do nothing
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_S :
        makeMove("s");
        break;
      case KeyEvent.VK_M :
      case KeyEvent.VK_P :
        makeMove("p");
        break;
      default:
        // do nothing
    }
  }

  @Override
  public void makeMove(String p) {
    switch (p) {
      case "p":
        try {
          Posn move = this.p.placePiece(model, view);
          model.placePiece(this.p, move);
          view.update();
        }
        catch (IllegalArgumentException | IllegalStateException e) {
          view.displayException(e);
        }
        break;
      case "s":
        try {
          this.model.skip(this.p);
          view.update();
        }
        catch (IllegalStateException e) {
          view.displayException(e);
        }
        break;
      default:
        // do nothing
    }
  }

  @Override
  public void startTurn() {
    this.view.update();
    this.view.requestFocusInWindow();
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // do nothing
  }
}
