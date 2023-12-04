package cs3500.reversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import cs3500.reversi.model.HexagonalPosn;
import cs3500.reversi.model.HexagonalTile;
import cs3500.reversi.model.ITile;
import cs3500.reversi.model.Posn;
import cs3500.reversi.player.AI;
import cs3500.reversi.player.CaptureMaxPieces;
import cs3500.reversi.player.User;

/**
 * Tests regarding the public methods of both the Tile and the Posn.
 */
public class TestTileImplementation {
  ITile tile;

  @Before
  public void init() {
    tile = new HexagonalTile(new HexagonalPosn(0, 0, 0));
  }

  // TESTING flipTo(Player p)
  @Test(expected = IllegalArgumentException.class)
  public void flipToThrowsIllegalArgWhenPlayerIsNull() {
    tile.flipTo(null);
  }

  @Test
  public void flipToWithAnyPlayerWorks() {
    tile.flipTo(new User('A'));
    Assert.assertEquals(new AI('A', List.of(new CaptureMaxPieces())), tile.getPlayer());

    tile.flipTo(new AI('c', List.of(new CaptureMaxPieces())));
    Assert.assertEquals(new User('c'), tile.getPlayer());
  }

  // TESTING getPlayer()
  @Test
  public void getPlayerReturnsNullWhenTileIsUnoccupied() {
    Assert.assertEquals(null, tile.getPlayer());
  }

  @Test
  public void getPlayerReturnsCorrectPlayerWhenOccupied() {
    tile.flipTo(new User('X'));
    Assert.assertEquals("X", tile.getPlayer().toString());
  }

  // TESTING getPosn()
  @Test
  public void getPosnWorksOnTile() {
    Assert.assertEquals(new HexagonalPosn(0, 0, 0), tile.getPosition());
  }

  // TESTING Posn class
  @Test
  public void getCoorsReturnsCoordinatesInCorrectOrder() {
    Posn increasing = new HexagonalPosn(-3, 1, 2);
    Assert.assertEquals(List.of(-3, 1, 2), increasing.getCoords());
  }

  @Test(expected = IllegalArgumentException.class)
  public void posnCoordinatesMustAddToZero() {
    Posn invalid = new HexagonalPosn(1, 2, 3);
  }
}
