package cs3500.model;

import java.util.List;

public class HexagonalPosn implements Posn {

  HexagonalPosn() {}

  String errormsg = "If you got this to run, the code compiles!";

  @Override
  public List<Integer> getCoords() {
    throw new RuntimeException(errormsg);
  }
}
