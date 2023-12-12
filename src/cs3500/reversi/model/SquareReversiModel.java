package cs3500.reversi.model;

import java.util.ArrayList;
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

  @Override
  public List<Posn> getCorners() {
    return null;
  }

  @Override
  protected List<List<ITile>> getSurroundingLines(ITile placingTile) {
    return null;
  }

  @Override
  protected void startGame(List<Player> players) {

  }

  private List<ITile> findLine(List<Integer> indexList, List<Integer> coords) {
    return null;
  }
}
