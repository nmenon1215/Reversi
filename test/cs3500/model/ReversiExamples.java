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
    p2 = new User('O');
    model = new HexagonalReversiModel(p1, p2);
    tv = new ReversiTextualView(model, System.out);
  }

  @Test
  public void piecesStartAtCorrectPlacesOnBoardSizeFive() {
    String initialBoard =
            "     _ _ _ _ _ _ \n" +
                    "    _ _ _ _ _ _ _ \n" +
                    "   _ _ _ _ _ _ _ _ \n" +
                    "  _ _ _ _ _ _ _ _ _ \n" +
                    " _ _ _ _ X O _ _ _ _ \n" +
                    "_ _ _ _ O _ X _ _ _ _ \n" +
                    " _ _ _ _ X O _ _ _ _ \n" +
                    "  _ _ _ _ _ _ _ _ _ \n" +
                    "   _ _ _ _ _ _ _ _ \n" +
                    "    _ _ _ _ _ _ _ \n" +
                    "     _ _ _ _ _ _ \n";
    Assert.assertEquals(initialBoard, tv.toString());
  }

  @Test
  public void piecesStartAtCorrectPlacesOnBoardSizeThree() {
    String initialBoard =
            "   _ _ _ _ \n" +
                    "  _ _ _ _ _ \n" +
                    " _ _ X O _ _ \n" +
                    "_ _ O _ X _ _ \n" +
                    " _ _ X O _ _ \n" +
                    "  _ _ _ _ _ \n" +
                    "   _ _ _ _ \n";
    tv = new ReversiTextualView(new HexagonalReversiModel(p1, p2, 3), System.out);
    Assert.assertEquals(initialBoard, tv.toString());
  }

  @Test
  public void placePieceWithOnlyOneTileFlipped() {
  }

  @Test
  public void placePieceWithMultipleTilesFlippedInOneDirection() {
  }

  @Test
  public void placePieceWithMultipleTilesFlippedInMultipleDirections() {
  }

  @Test(expected = IllegalStateException.class)
  public void confirmPlacePieceWithPlacementNextToNoPiecesFails() {
  }

  @Test(expected = IllegalStateException.class)
  public void placePieceWithPlacementWhereNoTilesAreFlippedFails() {
  }

  @Test(expected = IllegalStateException.class)
  public void placePieceOnAlreadyOccupiedTileFails() {
  }
}
