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
import cs3500.reversi.model.SquarePosn;
import cs3500.reversi.model.SquareReversiModel;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.User;
import cs3500.reversi.view.textual.HexagonalReversiTextualView;
import cs3500.reversi.view.textual.SquareReversiTextualView;

/**
 * Simple Examples of how the square model is supposed to operate.
 */
public class SquareReversiExamples {

  Player p1;
  Player p2;
  MutableReversiModel bigModel;
  SquareReversiTextualView bigTv;
  MutableReversiModel smallModel;
  SquareReversiTextualView smallTv;

  @Before
  public void init() {
    p1 = new User('X');
    p2 = new User('O');
    bigModel = new SquareReversiModel(new ArrayList(List.of(p1, p2)), 8);
    bigTv = new SquareReversiTextualView(bigModel, System.out);
    smallModel = new SquareReversiModel(new ArrayList(List.of(p1, p2)), 4);
    smallTv = new SquareReversiTextualView(smallModel, System.out);
  }

  @Test
  public void piecesStartAtCorrectPlacesOnBoardSizeEight() {
    String initialBoard =
            "_ _ _ _ _ _ _ _ \n" +
                    "_ _ _ _ _ _ _ _ \n" +
                    "_ _ _ _ _ _ _ _ \n" +
                    "_ _ _ X O _ _ _ \n" +
                    "_ _ _ O X _ _ _ \n" +
                    "_ _ _ _ _ _ _ _ \n" +
                    "_ _ _ _ _ _ _ _ \n" +
                    "_ _ _ _ _ _ _ _ \n";
    Assert.assertEquals(initialBoard, bigTv.toString());
  }

  @Test
  public void piecesStartAtCorrectPlacesOnBoardSizeFour() {
    String initialBoard =
            "_ _ _ _ \n" +
                    "_ X O _ \n" +
                    "_ O X _ \n" +
                    "_ _ _ _ \n";
    Assert.assertEquals(initialBoard, smallTv.toString());
  }

  @Test
  public void placePieceWithOnlyOneTileFlipped() {
    smallModel.placePiece(p1, new SquarePosn(3, 1));
    String board =
            "_ _ _ _ \n" +
                    "_ X X X \n" +
                    "_ O X _ \n" +
                    "_ _ _ _ \n";

    Assert.assertEquals(board, smallTv.toString());
  }

  @Test
  public void placePieceWithMultipleTilesFlippedInOneDirection() {
    bigModel.placePiece(p1, new SquarePosn(4, 2));
    bigModel.placePiece(p2, new SquarePosn(5, 4));
    bigModel.placePiece(p1, new SquarePosn(3, 5));
    bigModel.placePiece(p2, new SquarePosn(4, 1));

    String board =
                    "_ _ _ _ _ _ _ _ \n" +
                    "_ _ _ _ O _ _ _ \n" +
                    "_ _ _ _ O _ _ _ \n" +
                    "_ _ _ X O _ _ _ \n" +
                    "_ _ _ X O O _ _ \n" +
                    "_ _ _ X _ _ _ _ \n" +
                    "_ _ _ _ _ _ _ _ \n" +
                    "_ _ _ _ _ _ _ _ \n";

    Assert.assertEquals(board, bigTv.toString());
  }

  @Test
  public void placePieceWithMultipleTilesFlippedInMultipleDirections() {
    smallModel.placePiece(p1, new SquarePosn(3, 1));
    smallModel.placePiece(p2, new SquarePosn(1, 0));
    smallModel.placePiece(p1, new SquarePosn(0, 1));
    smallModel.placePiece(p2, new SquarePosn(3, 2));
    String board =
                    "_ O _ _ \n" +
                    "X X O X \n" +
                    "_ O O O \n" +
                    "_ _ _ _ \n";
    Assert.assertEquals(board, smallTv.toString());
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
