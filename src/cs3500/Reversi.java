package cs3500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.reversi.controller.controller.Controller;
import cs3500.reversi.controller.controller.ReversiController;
import cs3500.model.HexagonalReversiModel;
import cs3500.model.MutableReversiModel;
import cs3500.player.AI;
import cs3500.player.AvoidCellsNextToCorners;
import cs3500.player.CaptureMaxPieces;
import cs3500.player.PlaceAtCorners;
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
    Player p1 = makePlayer(args[0], 'X');
    Player p2 = makePlayer(args[1], 'O');

    MutableReversiModel model = new HexagonalReversiModel(
            new ArrayList<>(Arrays.asList(p1, p2)), 5);
    ReversiView viewPlayer1 = new JFrameReversiView(model);
    ReversiView viewPlayer2 = new JFrameReversiView(model);
    ReversiController p1Controller = new Controller(model, viewPlayer1, p1);
    ReversiController p2Controller = new Controller(model, viewPlayer2, p2);
    p1Controller.start();
  }

  static private Player makePlayer(String input, char c) {
    if (input.equalsIgnoreCase("human")) {
      return new User(c);
    }
    else if (input.equalsIgnoreCase("strategy1")) {
      return new AI(c, List.of(new CaptureMaxPieces()));
    }
    else if (input.equalsIgnoreCase("strategy2")) {
      return new AI(c, List.of(new AvoidCellsNextToCorners(), new CaptureMaxPieces()));
    }
    else if (input.equalsIgnoreCase("strategy3")) {
      return new AI(c, List.of(new PlaceAtCorners(),
              new AvoidCellsNextToCorners(), new CaptureMaxPieces()));
    }
    else {
      throw new IllegalArgumentException("This start command was unrecognized.");
    }
  }
}
