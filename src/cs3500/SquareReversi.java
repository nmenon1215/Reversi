package cs3500;

import java.util.List;

import cs3500.reversi.model.MutableReversiModel;
import cs3500.reversi.model.SquarePosn;
import cs3500.reversi.model.SquareReversiModel;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.User;
import cs3500.reversi.view.textual.TextualView;
import cs3500.reversi.view.textual.SquareReversiTextualView;

public class SquareReversi {

  public static void main(String[] args) {
    Player p1 = new User('X');
    Player p2 = new User('O');
    MutableReversiModel model = new SquareReversiModel(List.of(p1, p2));
    Appendable out = new StringBuilder();
    TextualView view = new SquareReversiTextualView(model, out);
    System.out.println(view);
    model.placePiece(p1, new SquarePosn(4, 2));
    System.out.println(view);
    model.placePiece(p2, new SquarePosn(5, 2));
    System.out.println(view);
  }
}
