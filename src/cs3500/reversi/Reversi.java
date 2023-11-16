package cs3500.reversi;

import java.util.ArrayList;
import java.util.Arrays;

import cs3500.model.HexagonalReversiModel;
import cs3500.player.Player;
import cs3500.model.ROReversiModel;
import cs3500.player.User;
import cs3500.view.JFrameReversiView;
import cs3500.view.ReversiView;

public final class Reversi {

  // test players to try to make a board
  static Player p1 = new User('X');
  static Player p2 = new User('O');

  public static void main(String[] args) {
    // INPUTS NOT DONE
    ROReversiModel model = new HexagonalReversiModel( new ArrayList<>(Arrays.asList(p1, p2)),  2);
    ReversiView view = new JFrameReversiView(model);
    view.setVisible(true);
  }
}
