package cs3500.model;

import java.util.List;

public class HexagonalReversiModel implements MutableReversiModel{

  HexagonalReversiModel() {
  }

  String errormsg = "If you got this to run, the code compiles!";

  @Override
  public void placePiece(Player p, Posn posn) {
    throw new RuntimeException(errormsg);
  }

  @Override
  public ITile getTileAt(Posn p) {
    throw new RuntimeException(errormsg);
  }

  @Override
  public boolean isGameOver() {
    throw new RuntimeException(errormsg);
  }

  @Override
  public List<ITile> possibleMoves(Player p) {
    throw new RuntimeException(errormsg);
  }

  @Override
  public int getScore(Player p) {
    throw new RuntimeException(errormsg);
  }
}
