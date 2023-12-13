package cs3500.reversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.model.HexagonalTile;
import cs3500.reversi.model.ITile;
import cs3500.reversi.model.MutableReversiModel;
import cs3500.reversi.model.Posn;
import cs3500.reversi.model.SquarePosn;
import cs3500.reversi.model.SquareReversiModel;
import cs3500.reversi.player.AI;
import cs3500.reversi.player.CaptureMaxPieces;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.User;
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
  ITile tile;

  @Before
  public void init() {
    p1 = new User('X');
    p2 = new User('O');
    bigModel = new SquareReversiModel(new ArrayList(List.of(p1, p2)), 8);
    bigTv = new SquareReversiTextualView(bigModel, System.out);
    smallModel = new SquareReversiModel(new ArrayList(List.of(p1, p2)), 4);
    smallTv = new SquareReversiTextualView(smallModel, System.out);
    tile = new HexagonalTile(new SquarePosn(0, 0));
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

  @Test
  public void placePieceWithTileFlippedAbove() {
    smallModel.placePiece(p1, new SquarePosn(2, 0));
    String board =
            "_ _ X _ \n" +
                    "_ X X _ \n" +
                    "_ O X _ \n" +
                    "_ _ _ _ \n";

    Assert.assertEquals(board, smallTv.toString());
  }

  @Test
  public void placePieceWithTileFlippedAboveRight() {
    smallModel.placePiece(p1, new SquarePosn(2, 0));
    smallModel.placePiece(p2, new SquarePosn(3, 0));
    String board =
                    "_ _ X O \n" +
                    "_ X O _ \n" +
                    "_ O X _ \n" +
                    "_ _ _ _ \n";
    Assert.assertEquals(board, smallTv.toString());
  }

  @Test
  public void placePieceWithTileFlippedRight() {
    smallModel.placePiece(p1, new SquarePosn(3, 1));
    String board =
            "_ _ _ _ \n" +
                    "_ X X X \n" +
                    "_ O X _ \n" +
                    "_ _ _ _ \n";

    Assert.assertEquals(board, smallTv.toString());
  }

  @Test
  public void placePieceWithTileFlippedBelowRight() {
    smallModel.placePiece(p1, new SquarePosn(3, 1));
    smallModel.placePiece(p2, new SquarePosn(3, 2));
    smallModel.placePiece(p1, new SquarePosn(3, 3));
    String board =
            "_ _ _ _ \n" +
                    "_ X X X \n" +
                    "_ O X X \n" +
                    "_ _ _ X \n";

    Assert.assertEquals(board, smallTv.toString());
  }

  @Test
  public void placePieceWithTileFlippedBelow() {
    smallModel.placePiece(p1, new SquarePosn(1, 3));
    String board =
            "_ _ _ _ \n" +
                    "_ X O _ \n" +
                    "_ X X _ \n" +
                    "_ X _ _ \n";

    Assert.assertEquals(board, smallTv.toString());
  }

  @Test
  public void placePieceWithTileFlippedBelowLeft() {
    smallModel.placePiece(p1, new SquarePosn(1, 3));
    smallModel.placePiece(p2, new SquarePosn(0, 3));
    String board =
            "_ _ _ _ \n" +
                    "_ X O _ \n" +
                    "_ O X _ \n" +
                    "O X _ _ \n";

    Assert.assertEquals(board, smallTv.toString());
  }

  @Test
  public void placePieceWithTileFlippedLeft() {
    smallModel.placePiece(p1, new SquarePosn(0, 2));
    String board =
            "_ _ _ _ \n" +
                    "_ X O _ \n" +
                    "X X X _ \n" +
                    "_ _ _ _ \n";

    Assert.assertEquals(board, smallTv.toString());
  }

  @Test
  public void placePieceWithTileFlippedAboveLeft() {
    smallModel.placePiece(p1, new SquarePosn(0, 2));
    smallModel.placePiece(p2, new SquarePosn(0, 1));
    smallModel.placePiece(p1, new SquarePosn(0, 0));
    String board =
                    "X _ _ _ \n" +
                    "X X O _ \n" +
                    "X X X _ \n" +
                    "_ _ _ _ \n";

    Assert.assertEquals(board, smallTv.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void placePieceWithPlacementNextToNoPiecesFails() {
    bigModel.placePiece(p1, new SquarePosn(0, 0));
  }

  @Test(expected = IllegalStateException.class)
  public void placePieceWithPlacementWhereNoTilesAreFlippedFails() {
    smallModel.placePiece(p1, new SquarePosn(0, 0));
  }

  @Test(expected = IllegalStateException.class)
  public void placePieceOnAlreadyOccupiedTileFails() {
    smallModel.placePiece(p1, new SquarePosn(1, 1));
  }

  @Test(expected = IllegalStateException.class)
  public void canOnlySkipWithNoPossibleMoves() {
    smallModel.skip(p1);
  }

  @Test
  public void gameOverIfNumberOfPlayersEqualsNumberOfSkips() {
    smallModel.placePiece(p1, new SquarePosn(3, 1));
    smallModel.placePiece(p2, new SquarePosn(1, 0));
    smallModel.placePiece(p1, new SquarePosn(0, 0));
    smallModel.placePiece(p2, new SquarePosn(3, 2));
    smallModel.placePiece(p1, new SquarePosn(3, 3));
    smallModel.placePiece(p2, new SquarePosn(0, 1));
    smallModel.placePiece(p1, new SquarePosn(2, 0));
    smallModel.placePiece(p2, new SquarePosn(3, 0));
    smallModel.placePiece(p1, new SquarePosn(0, 2));
    smallModel.placePiece(p2, new SquarePosn(0, 3));
    smallModel.placePiece(p1, new SquarePosn(1, 3));
    smallModel.placePiece(p2, new SquarePosn(2, 3));
    smallModel.skip(p1);
    smallModel.skip(p2);
    Assert.assertTrue(smallModel.isGameOver());
  }

  @Test
  public void getCornersReturnsCorners() {
    List<Posn> corners = new ArrayList<>();
    corners.add(new SquarePosn(0, 0));
    corners.add(new SquarePosn(3, 0));
    corners.add(new SquarePosn(0, 3));
    corners.add(new SquarePosn(3, 3));

    Assert.assertEquals(smallModel.getCorners(), corners);
  }

  @Test
  public void getTurnIndexReturnsCorrectly() {
    Assert.assertEquals(smallModel.getTurnIndex(), 0);
    smallModel.placePiece(p1, new SquarePosn(2, 0));
    Assert.assertEquals(smallModel.getTurnIndex(), 1);
    smallModel.placePiece(p2, new SquarePosn(1, 0));
  }

  @Test
  public void getPlayersReturnsCorrectly() {
    List<Player> players = new ArrayList<>(List.of(new User('X'), new User('O')));
    Assert.assertEquals(smallModel.getPlayers(), players);
  }

  // TESTING placePiece(Player p, Posn posn)
  @Test(expected = IllegalArgumentException.class)
  public void placePieceThrowsIllegalArgWithNullPlayer() {
    smallModel.placePiece(null, new SquarePosn(2, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void placePieceThrowsIllegalArgWithNullPosition() {
    smallModel.placePiece(p1, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void placePieceThrowsIllegalArgWithInvalidPosn() {
    smallModel.placePiece(p1, new SquarePosn(-1, 0));
  }

  @Test(expected = IllegalStateException.class)
  public void placePieceThrowsIllegalStateWithNewPlayer() {
    smallModel.placePiece(new User('c'), new SquarePosn(2, 1));
  }

  // TESTING getTileAt(Posn posn)
  @Test(expected = IllegalArgumentException.class)
  public void getTileAtPosnOutsideOfGridBoundsFails() {
    smallModel.getTileAt(new SquarePosn(4, 4));
  }

  @Test
  public void getTileAtDoesNotCompromiseTheTileToModification() {
    ITile t = smallModel.getTileAt(new SquarePosn(0, 0));
    t.flipTo(p1);
    Assert.assertNotEquals(t, smallModel.getTileAt(new SquarePosn(0, 0)));
  }

  // TESTING isGameOver()
  @Test
  public void isGameOverReturnsTrueWhenGameIsOver() {
    smallModel.placePiece(p1, new SquarePosn(3, 1));
    smallModel.placePiece(p2, new SquarePosn(1, 0));
    smallModel.placePiece(p1, new SquarePosn(0, 0));
    smallModel.placePiece(p2, new SquarePosn(3, 2));
    smallModel.placePiece(p1, new SquarePosn(3, 3));
    smallModel.placePiece(p2, new SquarePosn(0, 1));
    smallModel.placePiece(p1, new SquarePosn(2, 0));
    smallModel.placePiece(p2, new SquarePosn(3, 0));
    smallModel.placePiece(p1, new SquarePosn(0, 2));
    smallModel.placePiece(p2, new SquarePosn(0, 3));
    smallModel.placePiece(p1, new SquarePosn(1, 3));
    smallModel.placePiece(p2, new SquarePosn(2, 3));
    Assert.assertTrue(smallModel.isGameOver());
  }

  @Test
  public void isGameOverReturnsFalseWhenGameIsNotOver() {
    smallModel.placePiece(p1, new SquarePosn(2, 0));
    Assert.assertFalse(smallModel.isGameOver());
  }

  // TESTING possibleMoves(Player p)
  @Test(expected = IllegalArgumentException.class)
  public void possibleMovesWithNullPlayerThrowsIllegalArg() {
    smallModel.possibleMoves(null);
  }

  @Test
  public void possibleMovesWithNewPlayerReturnsEmptyList() {
    Assert.assertTrue(smallModel.possibleMoves(new User('v')).isEmpty());
  }

  @Test
  public void possibleMovesWithNoPossibleMovesReturnsEmptyList() {
    smallModel.placePiece(p1, new SquarePosn(3, 1));
    smallModel.placePiece(p2, new SquarePosn(1, 0));
    smallModel.placePiece(p1, new SquarePosn(0, 0));
    smallModel.placePiece(p2, new SquarePosn(3, 2));
    smallModel.placePiece(p1, new SquarePosn(3, 3));
    smallModel.placePiece(p2, new SquarePosn(0, 1));
    smallModel.placePiece(p1, new SquarePosn(2, 0));
    smallModel.placePiece(p2, new SquarePosn(3, 0));
    smallModel.placePiece(p1, new SquarePosn(0, 2));
    smallModel.placePiece(p2, new SquarePosn(0, 3));
    smallModel.placePiece(p1, new SquarePosn(1, 3));
    smallModel.placePiece(p2, new SquarePosn(2, 3));
    Assert.assertTrue(smallModel.possibleMoves(p2).isEmpty());
  }

  @Test
  public void possibleMovesWithMultiplePossibleMovesWorks() {
    Assert.assertEquals(4, smallModel.possibleMoves(p1).size());
  }

  @Test
  public void possibleMovesDoesNotCompromiseTheTilesToModification() {
    List<ITile> tiles = smallModel.possibleMoves(p1);
    ITile evil = tiles.get(0);
    evil.flipTo(new User('s'));
    Assert.assertNotEquals(evil, smallModel.getTileAt(new SquarePosn(2, 1)));
  }

  // TESTING getScore(Player p)
  @Test(expected = IllegalArgumentException.class)
  public void getScoreOfNullPlayerThrowsIllegalArg() {
    smallModel.getScore(null);
  }

  @Test
  public void getScoreOfNewPlayerReturns0() {
    Assert.assertEquals(0, smallModel.getScore(new User('n')));
  }

  @Test
  public void getScoreOfPlayerReturnsCorrectValueMidGame() {
    smallModel.placePiece(p1, new SquarePosn(3, 1));
    smallModel.placePiece(p2, new SquarePosn(1, 0));
    smallModel.placePiece(p1, new SquarePosn(0, 0));
    smallModel.placePiece(p2, new SquarePosn(3, 2));
    smallModel.placePiece(p1, new SquarePosn(3, 3));
    smallModel.placePiece(p2, new SquarePosn(0, 1));
    Assert.assertEquals(5, smallModel.getScore(p2));
  }

  // TESTING skip(Player p)
  @Test(expected = IllegalArgumentException.class)
  public void exceptionIfNullPlayerTriesToSkip() {
    smallModel.skip(null);
  }

  // TESTING isLegalMove(Player p, Posn posn)
  @Test(expected = IllegalArgumentException.class)
  public void isLegalMoveWithNullPlayerThrowsIllegalArg() {
    smallModel.isLegalMove(null, new SquarePosn(2, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void isLegalMoveWithNullPosnThrowsIllegalArg() {
    smallModel.isLegalMove(p1, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void isLegalMoveWithOutOfBoundsPosnThrowsIllegalArg() {
    smallModel.isLegalMove(p1, new SquarePosn(4, 4));
  }

  @Test
  public void isLegalMoveWithNewPlayerIsFalse() {
    Assert.assertFalse(smallModel.isLegalMove(new User('v'), new SquarePosn(2, 1)));
  }

  @Test
  public void isLegalMoveWithIllegalMoveIsFalse() {
    Assert.assertFalse(smallModel.isLegalMove(p1, new SquarePosn(1, 2)));
  }

  @Test
  public void isLegalMoveWithLegalMoveIsTrue() {
    Assert.assertTrue(smallModel.isLegalMove(p1, new SquarePosn(3, 1)));
  }

  // TESTING hasLegalMove(Player p)
  @Test(expected = IllegalArgumentException.class)
  public void hasLegalMovesWithNullPlayerThrowsIllegalArg() {
    smallModel.hasLegalMoves(null);
  }

  @Test
  public void hasLegalMovesWithNewPlayerIsFalse() {
    Assert.assertFalse(smallModel.hasLegalMoves(new User('b')));
  }

  @Test
  public void hasLegalMovesWithNoLegalMovesIsFalse() {
    smallModel.placePiece(p1, new SquarePosn(3, 1));
    smallModel.placePiece(p2, new SquarePosn(1, 0));
    smallModel.placePiece(p1, new SquarePosn(0, 0));
    smallModel.placePiece(p2, new SquarePosn(3, 2));
    smallModel.placePiece(p1, new SquarePosn(3, 3));
    smallModel.placePiece(p2, new SquarePosn(0, 1));
    smallModel.placePiece(p1, new SquarePosn(2, 0));
    smallModel.placePiece(p2, new SquarePosn(3, 0));
    smallModel.placePiece(p1, new SquarePosn(0, 2));
    smallModel.placePiece(p2, new SquarePosn(0, 3));
    smallModel.placePiece(p1, new SquarePosn(1, 3));
    smallModel.placePiece(p2, new SquarePosn(2, 3));
    Assert.assertFalse(smallModel.hasLegalMoves(p2));
  }

  @Test
  public void hasLegalMovesWithLegalMovesIsTrue() {
    Assert.assertTrue(smallModel.hasLegalMoves(p2));
  }

  // TESTING getSurroundingTiles(Posn posn)
  @Test(expected = IllegalArgumentException.class)
  public void getSurroundingTilesWithNullPosnThrowsIllegalArg() {
    smallModel.getSurroundingTiles(null);
  }

  @Test
  public void getSurroundingTilesWithCornerReturnsThreeTiles() {
    Assert.assertEquals(3, smallModel.getSurroundingTiles(new SquarePosn(0, 0)).size());
  }

  @Test
  public void getSurroundingTilesInCenterReturnsEightTiles() {
    Assert.assertEquals(8, smallModel.getSurroundingTiles(new SquarePosn(2, 2)).size());
  }

  @Test
  public void getSurroundingTilesDoesNotCompromiseTilesToMutation() {
    List<ITile> tiles = smallModel.getSurroundingTiles(new SquarePosn(2, 2));
    for (ITile tile : tiles) {
      tile.flipTo(p1);
    }
    Assert.assertNotEquals(6, smallModel.getScore(p1));
  }

  // TESTING constructor
  @Test(expected = IllegalArgumentException.class)
  public void createReversiWithNullListOfPlayersThrowsIllegalArg() {
    new SquareReversiModel(null, 8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createReversiWithNoPlayersThrowsIllegalArg() {
    new SquareReversiModel(new ArrayList<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void createReversiWithOnlyOnePlayerThrowsIllegalArg() {
    new SquareReversiModel(new ArrayList<>(List.of(p1)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void createReversiWithTwoOfTheSamePlayersThrowsIllegalArg() {
    new SquareReversiModel(new ArrayList<>(List.of(p1, p1)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void createReversiWithNullPlayersThrowsIllegalArg() {
    List<Player> players = new ArrayList<>();
    players.add(p1);
    players.add(p2);
    players.add(null);
    new SquareReversiModel(players);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createReversiWithBoardSizeOneThrowsIllegalArg() {
    new SquareReversiModel(new ArrayList<>(List.of(p1, p2)), 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createReversiWithNegativeBoardSizeThrowsIllegalArg() {
    new SquareReversiModel(new ArrayList<>(List.of(p1, p2)), -1);
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
    Assert.assertEquals(new SquarePosn(0, 0), tile.getPosition());
  }

  // TESTING Posn class
  @Test
  public void getCoorsReturnsCoordinatesInCorrectOrder() {
    Posn increasing = new SquarePosn(1, 2);
    Assert.assertEquals(List.of(1, 2), increasing.getCoords());
  }
}
