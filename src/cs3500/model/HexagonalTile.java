package cs3500.model;

public class HexagonalTile implements ITile{

  private Posn posn;
  private Player player;

  HexagonalTile() {
  }

  HexagonalTile(ITile other) {
    if(other == null) {
      throw new IllegalArgumentException("Cannot copy a null tile.");
    }
    this.posn = other.getPosition();
  }

  @Override
  public void flipTo(Player p) {
    this.player = p;
  }

  @Override
  public Player getPlayer() {
    return this.player;
  }

  @Override
  public Posn getPosition() {
    return this.posn;
  }
}
