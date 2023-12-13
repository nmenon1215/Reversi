package cs3500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.reversi.controller.Controller;
import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.model.HexagonalReversiModel;
import cs3500.reversi.model.MutableReversiModel;
import cs3500.reversi.model.SquareReversiModel;
import cs3500.reversi.player.AI;
import cs3500.reversi.player.AvoidCellsNextToCorners;
import cs3500.reversi.player.CaptureMaxPieces;
import cs3500.reversi.player.PlaceAtCorners;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.User;
import cs3500.reversi.provider.strategy.implementation.AvoidCorners;
import cs3500.reversi.provider.strategy.implementation.CaptureMostPieces;
import cs3500.reversi.provider.strategy.implementation.GoForCorners;
import cs3500.reversi.provider.strategy.implementation.TryTwo;
import cs3500.reversi.provider.view.gui.FrameView;
import cs3500.reversi.view.gui.HexagonalView;
import cs3500.reversi.view.gui.SquareView;

public class SquareReversiPlayable {
  public static void main(String[] args) {
    Player p1 = makePlayer(args[0], 'X');
    Player p2 = makePlayer(args[1], 'O');

    MutableReversiModel model = new SquareReversiModel(
            new ArrayList<>(Arrays.asList(p1, p2)), 8);
    //ReadOnlyReversiModel providerModel = new ProviderModel(model);
    FrameView viewPlayer1 =  new SquareView(model);
    FrameView viewPlayer2 = new SquareView(model);
    ReversiController p1Controller = new Controller(model, viewPlayer1, p1);
    ReversiController p2Controller = new Controller(model, viewPlayer2, p2);
    p1Controller.startTurn();
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
    else if (input.equalsIgnoreCase("easy")) {
      return new AI(c, new CaptureMostPieces());
    }
    else if (input.equalsIgnoreCase("medium")) {
      return new AI(c, new TryTwo(new AvoidCorners(), new CaptureMostPieces()));
    }
    else if (input.equalsIgnoreCase("hard")) {
      return new AI(c, new TryTwo(new GoForCorners(),
              new TryTwo(new AvoidCorners(), new CaptureMostPieces())));
    }
    else {
      throw new IllegalArgumentException("This start command was unrecognized.");
    }
  }
}
