package cs3500.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.view.ReversiTextualView;

public class ReversiExamples {

  MutableReversiModel model;
  Player p1;
  Player p2;
  ReversiTextualView tv;
  @Before
  public void init() {
    p1 = new User('X');
    p1 = new User('O');
    model = new HexagonalReversiModel(p1, p2);
    tv = new ReversiTextualView(model, System.out);
  }

  @Test
  public void confirmPiecesStartAtCorrectPlacesOnBoardSizeFive() {
    String initialBoard =
            "     _ _ _ _ _ _\n" +
            "    _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _ _ _\n" +
            "  _ _ _ _ _ _ _ _ _\n" +
            " _ _ _ _ X O _ _ _ _\n" +
            "_ _ _ _ O _ X _ _ _ _\n" +
            " _ _ _ _ X O _ _ _ _\n" +
            "  _ _ _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _ _ _\n" +
            "    _ _ _ _ _ _ _\n" +
            "     _ _ _ _ _ _\n";
    Assert.assertEquals(initialBoard, tv.toString());
  }

  @Test
  public void confirmPiecesStartAtCorrectPlacesOnBoardSizeThree() {}

  @Test
  public void confirmPlacePieceWithOnlyOneTileFlipped() {}

  @Test
  public void confirmPlacePieceWithMultipleTilesFlippedInOneDirection() {}

  @Test
  public void confirmPlacePieceWithMultipleTilesFlippedInMultipleDirections() {}

  @Test(expected = IllegalStateException.class)
  public void confirmPlacePieceWithPlacementNextToNoPiecesFails() {}

  @Test(expected = IllegalStateException.class)
  public void confirmPlacePieceWithPlacementWhereNoTilesAreFlippedFails() {}

  @Test(expected = IllegalStateException.class)
  public void placePieceOnAlreadyOccupiedPlaceFails() {}
}
