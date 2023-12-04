package cs3500.reversi.model;

import java.util.Objects;

import cs3500.reversi.player.Player;

/**
 * A HexagonalTile is a tile that operates on the hexagonal coordinate system. For more info on that
 * please visit HexagonalPosn. All Tiles have a position of where they are on the board as well as
 * a player which is currently occupying the tile. If no player occupies the tile, then the player
 * field is null.
 */
public class HexagonalTile implements ITile {

  private Posn posn;
  private Player player;

  /**
   * Initialize a HexagonalTile with the given position coordinate.
   *
   * @param p a position representing where the tile is.
   * @throws IllegalArgumentException if the given posn is null.
   */
  public HexagonalTile(Posn p) {
    if (p == null) {
      throw new IllegalArgumentException("The given position can't be null.");
    }
    this.posn = p;
  }

  /**
   * Copy constructor.
   *
   * @param other another tile
   * @throws IllegalArgumentException if the given tile is null.
   */
  public HexagonalTile(ITile other) {
    if (other == null) {
      throw new IllegalArgumentException("Cannot copy a null tile.");
    }
    this.posn = other.getPosition();
    this.player = other.getPlayer();
  }

  @Override
  public void flipTo(Player p) {
    if (p == null) {
      throw new IllegalArgumentException("Player can't be null.");
    }
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
    if (this.player == null) {
      return "_";
    } else {
      return this.player.toString();
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ITile)) {
      return false;
    }
    ITile other = (ITile) obj;
    return this.hashCode() == other.hashCode();
  }

  @Override
  public int hashCode() {
    if (this.player == null) {
      return Objects.hash(posn);
    }
    return Objects.hash(posn, player);
  }
}
