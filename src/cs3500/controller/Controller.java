package cs3500.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

import cs3500.model.MutableReversiModel;
import cs3500.model.Posn;
import cs3500.player.Player;
import cs3500.view.gui.ReversiView;

public class Controller implements ReversiController, KeyListener {
  MutableReversiModel model;
  ReversiView view;
  Player p;
  Features skip;
  Features placePiece;

  public Controller(MutableReversiModel model, ReversiView view, Player p) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.p = Objects.requireNonNull(p);

    this.view.addKeyListener(this);
    this.view.requestFocusInWindow();
  }

  @Override
  public void start() {

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
        catch(IllegalArgumentException | IllegalStateException e) {
          view.displayException(e);
        }
        break;
      case "s":
        try {
          this.p.skipTurn();
        }
        catch(IllegalStateException e) {
          view.displayException(e);
        }
        break;
      default:
        // do nothing
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // do nothing
  }
}
