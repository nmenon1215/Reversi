package cs3500.reversi.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

import cs3500.reversi.model.HexagonalPosn;
import cs3500.reversi.model.MutableReversiModel;
import cs3500.reversi.model.Posn;
import cs3500.reversi.player.AI;
import cs3500.reversi.player.Player;
import cs3500.reversi.provider.controller.ViewFeatures;
import cs3500.reversi.provider.view.gui.FrameView;
import cs3500.reversi.view.gui.ReversiView;

/**
 * Creates a controller to gain a connection between the view and the model.
 */
public class Controller implements ReversiController, ViewFeatures {
  MutableReversiModel model;
  FrameView view;
  Player p;

  /**
   * Creates a controller that will be used for a player. It handles the connection between
   * the view and model.
   * @param model the given model of the game being played.
   * @param view the given view that represents the game being played.
   * @param p the player the controller refers to.
   */
  public Controller(MutableReversiModel model, FrameView view, Player p) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.p = Objects.requireNonNull(p);

    this.view.addFeatureListener(this);
    this.model.subscribe(this, this.p);
    if (this.p.toString().equals("X")) {
      this.view.makeTitle("BLACK");
    }
    else {
      this.view.makeTitle("WHITE");
    }
    this.view.setVisible();
  }

  //@Override
  public void start() {
    this.view.deselectAll();
    this.view.refresh();
  }

  @Override
  public void makeMove(String p) {
    switch (p) {
      case "p":
        try {
          //Posn move = this.p.placePiece(model, view);
          //model.placePiece(this.p, move);
          //view.update();
        }
        catch (IllegalArgumentException | IllegalStateException e) {
          //view.displayException(e);
        }
        break;
      case "s":
        try {
          this.model.skip(this.p);
          //view.update();
        }
        catch (IllegalStateException e) {
          //view.displayException(e);
        }
        break;
      default:
        // do nothing
    }
  }

  @Override
  public void startTurn() {
    this.view.deselectAll();
    this.view.refresh();
  }

  @Override
  public void playerMove(int diagonalPos, int rowPos) {
    try {
      model.placePiece(this.p, new HexagonalPosn(diagonalPos, rowPos));
      view.refresh();
    }
    catch (IllegalArgumentException | IllegalStateException e) {
      view.showMessageDialog(e.getMessage());
    }
  }

  @Override
  public void playerPass() {
    try {
      this.model.skip(this.p);
      view.refresh();
    }
    catch (IllegalStateException e) {
      view.showMessageDialog(e.getMessage());
    }
  }
}
