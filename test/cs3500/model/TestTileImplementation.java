package cs3500.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTileImplementation {
  ITile tile;
  @Before
  public void init() {
    tile = new HexagonalTile(new HexagonalPosn(0,0,0));
  }

  // TESTING flipTo(Player p)
  @Test(expected = IllegalArgumentException.class)
  public void flipToThrowsIllegalArgWhenPlayerIsNull() {
    tile.flipTo(null);
  }

  @Test
  public void flipToWithAnyPlayerWorks() {
    tile.flipTo(new User('A'));
    Assert.assertEquals(new AI('A'), tile.getPlayer());

    tile.flipTo(new AI('c'));
    Assert.assertEquals(new User('c'), tile.getPlayer());
  }

  // TESTING getPlayer()
  @Test
  public void getPlayerReturnsNullWhenTileIsUnoccupied() {
  }

  @Test
  public void getPlayerReturnsCorrectPlayerWhenOccupied() {
  }

  // TESTING getPosn()
  @Test
  public void getPosnWorksOnTile() {
  }

  // TESTING Posn class
  @Test
  public void getCoorsReturnsCoordinatesInCorrectOrder() {
  }
}
