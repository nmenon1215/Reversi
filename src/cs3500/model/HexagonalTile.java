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
    if(this.player == null) {
      return "_";
    }
    else return this.player.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if(!(obj instanceof HexagonalTile)) {
      return false;
    }
    HexagonalTile other = (HexagonalTile) obj;
    return this.hashCode() == other.hashCode();
  }

  @Override
  public int hashCode() {
    return this.posn.hashCode() + this.player.hashCode();
  }
}
