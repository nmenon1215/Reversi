package cs3500.model;

import java.util.List;

public class AI implements Player {

  AI() {}

  String errormsg = "If you got this to run, the code compiles!";

  @Override
  public void placePiece(Posn p) {
    throw new RuntimeException(errormsg);
  }

  @Override
  public void skipTurn() {
    throw new RuntimeException(errormsg);
  }

  @Override
  public List<ITile> getPossibleMoves() {
    throw new RuntimeException(errormsg);
  }
  //some jank shit
}
