package cs3500.model;

public class HexagonalTile implements ITile{

  private Posn posn;

  HexagonalTile() {
  }

  HexagonalTile(ITile other) {
    if(other == null) {
      throw new IllegalArgumentException("Cannot copy a null tile.");
    }
    this.posn = other.getPosition();
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
