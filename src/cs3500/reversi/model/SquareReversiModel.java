package cs3500.reversi.model;

import java.util.List;

import cs3500.reversi.player.Player;

public class SquareReversiModel extends ReversiModel{
  public SquareReversiModel(List<Player> players) {
    super(players);
  }

  public SquareReversiModel(List<Player> players, int boardSize) {
    super(players, boardSize);
  }

  public SquareReversiModel(ROReversiModel model) {
    super(model);
  }
}
