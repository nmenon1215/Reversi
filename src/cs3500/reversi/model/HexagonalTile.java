package cs3500.reversi.model;

import java.util.Objects;

import cs3500.reversi.player.Player;
import cs3500.reversi.provider.cell.HexagonCell;
import cs3500.reversi.provider.model.PlayerEnum;

/**
 * A HexagonalTile is a tile that operates on the hexagonal coordinate system. For more info on that
 * please visit HexagonalPosn. All Tiles have a position of where they are on the board as well as
 * a player which is currently occupying the tile. If no player occupies the tile, then the player
 * field is null.
 */
public class HexagonalTile implements ITile {

  private Posn posn;
  private Player player;
  private HexagonCell providerCell;

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

    PlayerEnum playerEnum = PlayerEnum.Empty;
    if(this.player != null) {
      playerEnum = this.player.getPlayerEnum();
    }
    this.providerCell = new HexagonCell(playerEnum,
            getPosition().getCoords().get(0),
            getPosition().getCoords().get(1));
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
    this.providerCell = other.getProviderHexagonCell();
  }

  @Override
  public void flipTo(Player p) {
    if (p == null) {
      throw new IllegalArgumentException("Player can't be null.");
    }
    this.player = p;
    this.providerCell.changeCellState(this.player.getPlayerEnum());
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
  public HexagonCell getProviderHexagonCell() {
    return this.providerCell;
  }

  @Override
  public void duplicateProviderHexagonCell() {
    this.providerCell = new HexagonCell(0, 0);
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
