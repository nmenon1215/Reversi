package cs3500.reversi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.controller.Controller;
import cs3500.controller.ReversiController;
import cs3500.model.HexagonalReversiModel;
import cs3500.model.MutableReversiModel;
import cs3500.player.AI;
import cs3500.player.CaptureMaxPieces;
import cs3500.player.Player;
import cs3500.player.User;
import cs3500.view.gui.JFrameReversiView;
import cs3500.view.gui.ReversiView;

/**
 * Starts a game of Reversi.
 */
public final class Reversi {

  /**
   * Starts the game.
   * @param args no args yet.
   */
  public static void main(String[] args) {
    Player p1 = new User('X');
    // Player p2 = new User('O');
    Player p2 = new AI('O', List.of(new CaptureMaxPieces()));
    MutableReversiModel model = new HexagonalReversiModel(
            new ArrayList<>(Arrays.asList(p1, p2)), 5);
    ReversiView viewPlayer1 = new JFrameReversiView(model);
    ReversiView viewPlayer2 = new JFrameReversiView(model);
    ReversiController p1Controller = new Controller(model, viewPlayer1, p1);
    ReversiController p2Controller = new Controller(model, viewPlayer2, p2);
    p1Controller.start();
  }
}
