package cs3500.model;

public class HexagonalTile implements ITile{

  HexagonalTile() {
  }

  String errormsg = "If you got this to run, the code compiles!";

  @Override
  public void flipTo(Player p) {
    throw new RuntimeException(errormsg);
  }

  @Override
  public Player getPlayer() {
    throw new RuntimeException(errormsg);
  }

  @Override
  public Posn getPosition() {
    throw new RuntimeException(errormsg);
  }
}
