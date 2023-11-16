package cs3500.reversi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.model.HexagonalReversiModel;
import cs3500.model.MutableReversiModel;
import cs3500.model.Posn;
import cs3500.player.AI;
import cs3500.player.CaptureMaxPieces;
import cs3500.player.Player;
import cs3500.model.ROReversiModel;
import cs3500.player.User;
import cs3500.view.JFrameReversiView;
import cs3500.view.ReversiView;

public final class Reversi {
  public static void main(String[] args) {
    Player p1 = new AI('X', List.of(new CaptureMaxPieces()));
    Player p2 = new AI('O', List.of(new CaptureMaxPieces()));
    // INPUTS NOT DONE
    MutableReversiModel model = new HexagonalReversiModel( new ArrayList<>(Arrays.asList(p1, p2)),  5);
    for(int i = 0; i < 6; i++) {
      Posn placement = p1.placePiece(model);
      if(placement == null) {
        model.skip(p1);
      }
      else {
        model.placePiece(p1, placement);
      }
      placement = p2.placePiece(model);
      if(placement == null) {
        model.skip(p2);
      }
      else {
        model.placePiece(p2, placement);
      }
    }
    ReversiView view = new JFrameReversiView(model);
    view.setVisible(true);
  }
}
