package cs3500.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.view.ReversiTextualView;

public class ReversiExamples {

  Player p1;
  Player p2;
  MutableReversiModel bigModel;
  ReversiTextualView bigTv;
  MutableReversiModel smallModel;
  ReversiTextualView smallTv;

  @Before
  public void init() {
    p1 = new User('X');
    p2 = new User('O');
    bigModel = new HexagonalReversiModel(p1, p2);
    bigTv = new ReversiTextualView(bigModel, System.out);
    smallModel = new HexagonalReversiModel(p1, p2, 3);
    smallTv = new ReversiTextualView(smallModel, System.out);

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
    Assert.assertEquals(initialBoard, bigTv.toString());
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
    Assert.assertEquals(initialBoard, smallTv.toString());
  }

  @Test
  public void placePieceWithOnlyOneTileFlipped() {
    smallModel.placePiece(p1, new HexagonalPosn(2, -1, -1));
    String board =
                    "   _ _ _ _ \n" +
                    "  _ _ _ _ _ \n" +
                    " _ _ X X X _ \n" +
                    "_ _ O _ X _ _ \n" +
                    " _ _ X O _ _ \n" +
                    "  _ _ _ _ _ \n" +
                    "   _ _ _ _ \n";

    Assert.assertEquals(board, smallTv.toString());
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
