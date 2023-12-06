package cs3500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.reversi.controller.Controller;
import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.model.HexagonalReversiModel;
import cs3500.reversi.model.MutableReversiModel;
import cs3500.reversi.model.ProviderModel;
import cs3500.reversi.player.AI;
import cs3500.reversi.player.AvoidCellsNextToCorners;
import cs3500.reversi.player.CaptureMaxPieces;
import cs3500.reversi.player.PlaceAtCorners;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.User;
import cs3500.reversi.provider.model.ReadOnlyReversiModel;
import cs3500.reversi.provider.view.gui.FrameView;
import cs3500.reversi.provider.view.gui.ReversiFrame;
import cs3500.reversi.view.gui.JFrameReversiView;


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
    ReadOnlyReversiModel providerModel = new ProviderModel(model);
    FrameView viewPlayer1 =  new ReversiFrame(providerModel);// PROVIDER VIEW
    FrameView viewPlayer2 = new JFrameReversiView(model); // OUR VIEW
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
