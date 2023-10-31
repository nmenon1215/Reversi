package cs3500.model;

public class HexagonalTile implements ITile{

  private Posn posn;
  private Player player;

  public HexagonalTile(Posn p) {
  }

  public HexagonalTile(ITile other) {
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

  @Override
  public String toString() {
    return "UNFINISHED";
  }

  @Override
  public boolean equals(Object obj) {
    return false;
  }

  @Override
  public int hashCode() {
    return 0;
  }
}
