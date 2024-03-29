package cs3500.reversi.controller;

import java.util.Objects;

import cs3500.reversi.model.HexagonalPosn;
import cs3500.reversi.model.MutableReversiModel;
import cs3500.reversi.model.Posn;
import cs3500.reversi.player.Player;
import cs3500.reversi.provider.controller.ViewFeatures;
import cs3500.reversi.provider.view.gui.FrameView;

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
    this.view.makeTitle(this.p);
    this.view.setVisible();
  }

  @Override
  public void startTurn() {
    this.view.deselectAll();
    this.view.refresh();
  }

  @Override
  public void playerMove(Posn posn) {
    try {
      model.placePiece(this.p, posn);
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
