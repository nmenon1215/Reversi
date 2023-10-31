package cs3500.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.view.ReversiTextualView;

public class ReversiExamples {

  Player p1;
  Player p2;
  Player pRigged;
  MutableReversiModel bigModel;
  ReversiTextualView bigTv;
  MutableReversiModel smallModel;
  ReversiTextualView smallTv;

  @Before
  public void init() {
    p1 = new User('X');
    p2 = new User('O');
    pRigged = new User('X');
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
    smallModel.placePiece(p1, new HexagonalPosn(2, -1, -1));
    smallModel.placePiece(p2, new HexagonalPosn(1, -2, 1));
    smallModel.placePiece(p1, new HexagonalPosn(1, 1, -2));
    smallModel.placePiece(p2, new HexagonalPosn(1, 2, -3));
    String board =
            "   _ _ _ _ \n" +
                    "  _ _ O _ _ \n" +
                    " _ _ O O X _ \n" +
                    "_ _ O _ O _ _ \n" +
                    " _ _ X X O _ \n" +
                    "  _ _ _ _ O \n" +
                    "   _ _ _ _ \n";

    Assert.assertEquals(board, smallTv.toString());
  }

  @Test
  public void placePieceWithMultipleTilesFlippedInMultipleDirections() {
    bigModel.placePiece(p1, new HexagonalPosn(2, -1, -1));
    bigModel.placePiece(p2, new HexagonalPosn(-2, 1, 1));
    bigModel.placePiece(p1, new HexagonalPosn(-1, 2, -1));
    bigModel.placePiece(p2, new HexagonalPosn(1, -2, 1));
    bigModel.placePiece(p1, new HexagonalPosn(-3, 1, 2));
    bigModel.placePiece(p2, new HexagonalPosn(3, -1, -2));
    bigModel.placePiece(p1, new HexagonalPosn(1, -3, 2));
    bigModel.placePiece(p2, new HexagonalPosn(2, -3, 1));
    bigModel.placePiece(p1, new HexagonalPosn(-1, -1, 2));

    String board =
            "     _ _ _ _ _ _ \n" +
                    "    _ _ _ _ _ _ _ \n" +
                    "   _ _ _ X O _ _ _ \n" +
                    "  _ _ _ _ O _ _ _ _ \n" +
                    " _ _ _ X X X O O _ _ \n" +
                    "_ _ _ _ X _ X _ _ _ _ \n" +
                    " _ _ X X X X _ _ _ _ \n" +
                    "  _ _ _ _ X _ _ _ _ \n" +
                    "   _ _ _ _ _ _ _ _ \n" +
                    "    _ _ _ _ _ _ _ \n" +
                    "     _ _ _ _ _ _ \n";
    Assert.assertEquals(board, bigTv.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void placePieceWithPlacementNextToNoPiecesFails() {
    smallModel.placePiece(p1, new HexagonalPosn(0, -3, 3));
  }

  @Test(expected = IllegalStateException.class)
  public void placePieceWithPlacementWhereNoTilesAreFlippedFails() {
    smallModel.placePiece(p1, new HexagonalPosn(0, 0, 0));
  }

  @Test(expected = IllegalStateException.class)
  public void placePieceOnAlreadyOccupiedTileFails() {
    smallModel.placePiece(p1, new HexagonalPosn(0, -1, 1));
  }

  @Test(expected = IllegalStateException.class)
  public void canOnlySkipWithNoPossibleMoves() {
    smallModel.skip(p1);
  }

  @Test
  public void gameOverIfNumberOfPlayersEqualsNumberOfSkips() {
    smallModel.placePiece(p1, new HexagonalPosn(2, -1, -1));
    smallModel.placePiece(p1, new HexagonalPosn(1, 1, -2));
    smallModel.placePiece(p1, new HexagonalPosn(-1, -1, 2));
    smallModel.skip(p1);
    smallModel.skip(p1);
    Assert.assertTrue(smallModel.isGameOver());
  }

  @Test
  public void nonconsecutiveSkipsDoesNotEndTheGame() {
    String initialBoard =
                    "   _ _ _ _ \n" +
                    "  _ _ _ _ _ \n" +
                    " _ _ X O _ _ \n" +
                    "_ _ O _ X _ _ \n" +
                    " _ _ X O _ _ \n" +
                    "  _ _ _ _ _ \n" +
                    "   _ _ _ _ \n";
    Assert.assertFalse(smallModel.isGameOver());
  }

}
