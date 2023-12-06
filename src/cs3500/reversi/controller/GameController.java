package cs3500.reversi.controller;

import java.util.Objects;

import cs3500.reversi.model.HexagonalPosn;
import cs3500.reversi.model.MutableReversiModel;
import cs3500.reversi.provider.controller.ActivePlayer;
import cs3500.reversi.provider.controller.ModelFeatures;
import cs3500.reversi.provider.controller.PlayerFeatures;
import cs3500.reversi.provider.controller.ViewFeatures;
import cs3500.reversi.provider.view.gui.FrameView;
import cs3500.reversi.player.Player;

public class GameController implements ActivePlayer, ModelFeatures, PlayerFeatures, ViewFeatures {
  MutableReversiModel model;
  FrameView view;
  Player player;

  public GameController(MutableReversiModel model, FrameView view, Player player) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.player = Objects.requireNonNull(player);

    //this.model.subscribe(this, this.player);
    this.view.addFeatureListener(this);
  }

  @Override
  public void makeMove() {
    if (this.player.equals(this.model.getTurn())) {
      //TODO: Make a move???
    }
  }

  @Override
  public boolean isHumanPlayer() {
    return false;
  }

  @Override
  public Player getPlayer() {
    return null;
  }

  @Override
  public void addFeaturesListener(PlayerFeatures features) {

  }

  @Override
  public void update() {
    this.view.refresh();
  }

  @Override
  public void notifyPlayerTurn() {
    if (this.model.hasLegalMoves(this.player)) {
      this.view.showMessageDialog("It is now your turn!");
    }
    else {
      this.view.showMessageDialog("You have no valid moves :(\nPress p to pass");
    }
  }

  @Override
  public void makeMoveIfAI() {
    if(!this.isHumanPlayer()) {
      //this.player.placePiece(model, view);
    }
    else {
      notifyPlayerTurn();
    }
  }

  @Override
  public void displayGameOver() {
    if (this.model.isGameOver()) {
      String statement = "GAME IS OVER!";
      for (Player p : this.model.getPlayers()) {
        statement += "\nPlayer " + p + "'s score : " + this.model.getScore(p);
      }
      this.view.showMessageDialog(statement);
    }
  }

  @Override
  public void makeAIMove(int diagonalPos, int rowPos) {

  }

  @Override
  public void makeAIPass() {

  }

  @Override
  public void playerMove(int diagonalPos, int rowPos) {
    try {
      this.model.placePiece(this.player, new HexagonalPosn(diagonalPos, rowPos));
      this.view.refresh();
    } catch(Exception e) {
      this.view.showMessageDialog(e.getMessage());
    }
  }

  @Override
  public void playerPass() {
    try {
      this.model.skip(this.player);
      this.view.refresh();
    } catch(Exception e) {
      this.view.showMessageDialog(e.getMessage());
    }
  }
}
