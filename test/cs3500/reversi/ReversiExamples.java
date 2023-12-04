package cs3500.reversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.model.HexagonalPosn;
import cs3500.reversi.model.HexagonalReversiModel;
import cs3500.reversi.model.MutableReversiModel;
import cs3500.reversi.model.Posn;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.User;
import cs3500.reversi.view.textual.ReversiTextualView;

/**
 * Simple Examples of how the model is supposed to operate. Please look at TestModelImplementation
 * for more specific implementation detail tests. Only includes tests that are related to mutation.
 */
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
    bigModel = new HexagonalReversiModel(new ArrayList(List.of(p1, p2)));
    bigTv = new ReversiTextualView(bigModel, System.out);
    smallModel = new HexagonalReversiModel(new ArrayList(List.of(p1, p2)), 3);
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
    smallModel.placePiece(p2, new HexagonalPosn(3, -2, -1));
    smallModel.placePiece(p1, new HexagonalPosn(3, -1, -2));
    smallModel.placePiece(p2, new HexagonalPosn(3, 0, -3));
    smallModel.placePiece(p1, new HexagonalPosn(-1, -1, 2));
    smallModel.placePiece(p2, new HexagonalPosn(-2, -1, 3));
    smallModel.placePiece(p1, new HexagonalPosn(1, 1, -2));
    smallModel.placePiece(p2, new HexagonalPosn(-2, 1, 1));
    smallModel.placePiece(p1, new HexagonalPosn(-3, 1, 2));
    smallModel.placePiece(p2, new HexagonalPosn(-3, 2, 1));
    smallModel.placePiece(p1, new HexagonalPosn(1, -2, 1));
    smallModel.placePiece(p2, new HexagonalPosn(2, 1, -3));
    smallModel.skip(pRigged);
    smallModel.skip(p1);
    Assert.assertTrue(smallModel.isGameOver());

    String initialBoard =
            "   _ _ _ _ \n" +
                    "  _ _ X _ O \n" +
                    " O O O X O O \n" +
                    "_ _ O _ X _ O \n" +
                    " X O O O O O \n" +
                    "  O _ _ _ _ \n" +
                    "   _ _ _ _ \n";
  }

  @Test
  public void nonconsecutiveSkipsDoesNotEndTheGame() {
    smallModel.placePiece(p1, new HexagonalPosn(1, -2, 1));
    smallModel.placePiece(p2, new HexagonalPosn(2, -3, 1));
    smallModel.placePiece(p1, new HexagonalPosn(-1, 2, -1));
    smallModel.placePiece(p2, new HexagonalPosn(-1, 3, -2));
    smallModel.placePiece(p1, new HexagonalPosn(-2, 3, -1));
    smallModel.placePiece(p2, new HexagonalPosn(1, 1, -2));
    smallModel.placePiece(p1, new HexagonalPosn(0, 3, -3));
    smallModel.skip(p2); // FIRST SKIP!!!
    smallModel.placePiece(p1, new HexagonalPosn(2, -1, -1));
    smallModel.placePiece(p2, new HexagonalPosn(3, -1, -2));
    smallModel.placePiece(p1, new HexagonalPosn(1, 2, -3));
    smallModel.placePiece(p2, new HexagonalPosn(2, 1, -3));
    smallModel.placePiece(p1, new HexagonalPosn(3, 0, -3));
    smallModel.skip(p2); // SECOND SKIP!!!
    // game is not over since skips were not in a row.
    Assert.assertFalse(smallModel.isGameOver());
  }

  @Test
  public void getCornersReturnsCorners() {
    List<Posn> corners = new ArrayList<>();
    corners.add(new HexagonalPosn(0, 3, -3));
    corners.add(new HexagonalPosn(0, -3, 3));
    corners.add(new HexagonalPosn(3, 0, -3));
    corners.add(new HexagonalPosn(-3, 0, 3));
    corners.add(new HexagonalPosn(3, -3, 0));
    corners.add(new HexagonalPosn(-3, 3, 0));
    Assert.assertEquals(smallModel.getCorners(), corners);
  }

  @Test
  public void getSkipsInARowReturnsCorrectly() {
    smallModel.placePiece(p1, new HexagonalPosn(1, -2, 1));
    smallModel.placePiece(p2, new HexagonalPosn(2, -3, 1));
    smallModel.placePiece(p1, new HexagonalPosn(-1, 2, -1));
    smallModel.placePiece(p2, new HexagonalPosn(-1, 3, -2));
    smallModel.placePiece(p1, new HexagonalPosn(-2, 3, -1));
    smallModel.placePiece(p2, new HexagonalPosn(1, 1, -2));
    smallModel.placePiece(p1, new HexagonalPosn(0, 3, -3));
    Assert.assertEquals(smallModel.getSkipsInARow(), 0); // NO SKIPS YET
    smallModel.skip(p2); // FIRST SKIP
    Assert.assertEquals(smallModel.getSkipsInARow(), 1); // ONE SKIP
  }

  @Test
  public void getTurnIndexReturnsCorrectly() {
    Assert.assertEquals(smallModel.getTurnIndex(), 0);
    smallModel.placePiece(p1, new HexagonalPosn(1, -2, 1));
    Assert.assertEquals(smallModel.getTurnIndex(), 1);
    smallModel.placePiece(p2, new HexagonalPosn(2, -3, 1));
  }

  @Test
  public void getPlayersReturnsCorrectly() {
    List<Player> players = new ArrayList<>(List.of(new User('X'), new User('O')));
    Assert.assertEquals(smallModel.getPlayers(), players);
  }
}
