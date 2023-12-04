package cs3500.reversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.model.HexagonalPosn;
import cs3500.reversi.model.HexagonalReversiModel;
import cs3500.reversi.model.HexagonalTile;
import cs3500.reversi.model.ITile;
import cs3500.reversi.model.MutableReversiModel;
import cs3500.reversi.player.AI;
import cs3500.reversi.player.CaptureMaxPieces;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.User;
import cs3500.reversi.view.textual.ReversiTextualView;

/**
 * Implementation specific tests regarding how the model interacts with each public method.
 */
public class TestModelImplementation {
  Player p1;
  Player p2;
  Player pRigged;
  Player ai;
  MutableReversiModel bigModel;
  ReversiTextualView bigTv;
  MutableReversiModel smallModel;
  ReversiTextualView smallTv;

  @Before
  public void init() {
    p1 = new User('X');
    p2 = new User('O');
    pRigged = new User('X');
    ai = new AI('X', List.of(new CaptureMaxPieces()));
    bigModel = new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2)));
    bigTv = new ReversiTextualView(bigModel, System.out);
    smallModel = new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2)), 3);
    smallTv = new ReversiTextualView(smallModel, System.out);
  }

  // TESTING placePiece(Player p, Posn posn)
  @Test(expected = IllegalArgumentException.class)
  public void placePieceThrowsIllegalArgWithNullPlayer() {
    smallModel.placePiece(null, new HexagonalPosn(2, -1, -1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void placePieceThrowsIllegalArgWithNullPosition() {
    smallModel.placePiece(p1, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void placePieceThrowsIllegalArgWithInvalidPosn() {
    smallModel.placePiece(p1, new HexagonalPosn(1, 0, 0));
  }

  @Test(expected = IllegalStateException.class)
  public void placePieceThrowsIllegalStateWithNewPlayer() {
    smallModel.placePiece(new User('c'), new HexagonalPosn(2, -1, -1));
  }

  // TESTING getTileAt(Posn posn)
  @Test(expected = IllegalArgumentException.class)
  public void getTileAtPosnOutsideOfGridBoundsFails() {
    smallModel.getTileAt(new HexagonalPosn(4, -1, -3));
  }

  @Test
  public void getTileAtRandomTileWorks() {
    Assert.assertEquals(new HexagonalTile(new HexagonalPosn(0, 0, 0)),
            smallModel.getTileAt(new HexagonalPosn(0, 0, 0)));
  }

  @Test
  public void getTileAtDoesNotCompromiseTheTileToModification() {
    ITile t = smallModel.getTileAt(new HexagonalPosn(0, 0, 0));
    t.flipTo(p1);
    Assert.assertNotEquals(t, smallModel.getTileAt(new HexagonalPosn(0, 0, 0)));
  }

  // TESTING isGameOver()
  @Test
  public void isGameOverReturnsTrueWhenGameIsOver() {
    smallModel.placePiece(p1, new HexagonalPosn(2, -1, -1));
    smallModel.placePiece(pRigged, new HexagonalPosn(1, 1, -2));
    smallModel.placePiece(p1, new HexagonalPosn(-1, -1, 2));
    smallModel.skip(pRigged);
    smallModel.skip(p1);
    Assert.assertTrue(smallModel.isGameOver());
  }

  @Test
  public void isGameOverReturnsFalseWhenGameIsNotOver() {
    smallModel.placePiece(p1, new HexagonalPosn(2, -1, -1));
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
    smallModel.placePiece(p1, new HexagonalPosn(1, -2, 1));
    smallModel.placePiece(p2, new HexagonalPosn(2, -3, 1));
    smallModel.placePiece(p1, new HexagonalPosn(-1, 2, -1));
    smallModel.placePiece(p2, new HexagonalPosn(-1, 3, -2));
    smallModel.placePiece(p1, new HexagonalPosn(-2, 3, -1));
    smallModel.placePiece(p2, new HexagonalPosn(1, 1, -2));
    smallModel.placePiece(p1, new HexagonalPosn(0, 3, -3));
    Assert.assertTrue(smallModel.possibleMoves(p2).isEmpty());
  }

  @Test
  public void possibleMovesWithMultiplePossibleMovesWorks() {
    Assert.assertEquals(6, smallModel.possibleMoves(p1).size());
  }

  @Test
  public void possibleMovesDoesNotCompromiseTheTilesToModification() {
    List<ITile> tiles = smallModel.possibleMoves(p1);
    ITile evil = tiles.get(0);
    evil.flipTo(new User('s'));
    Assert.assertNotEquals(evil, smallModel.getTileAt(new HexagonalPosn(1, -2, 1)));
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
    smallModel.placePiece(p1, new HexagonalPosn(2, -1, -1));
    smallModel.placePiece(p2, new HexagonalPosn(1, -2, 1));
    smallModel.placePiece(p1, new HexagonalPosn(1, 1, -2));
    smallModel.placePiece(p2, new HexagonalPosn(1, 2, -3));
    Assert.assertEquals(7, smallModel.getScore(p2));
  }

  // TESTING skip(Player p)
  @Test(expected = IllegalArgumentException.class)
  public void exceptionIfNullPlayerTriesToSkip() {
    smallModel.skip(null);
  }

  // TESTING isLegalMove(Player p, Posn posn)
  @Test(expected = IllegalArgumentException.class)
  public void isLegalMoveWithNullPlayerThrowsIllegalArg() {
    smallModel.isLegalMove(null, new HexagonalPosn(2, -1, -1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void isLegalMoveWithNullPosnThrowsIllegalArg() {
    smallModel.isLegalMove(p1, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void isLegalMoveWithOutOfBoundsPosnThrowsIllegalArg() {
    smallModel.isLegalMove(p1, new HexagonalPosn(-10, 10, 0));
  }

  @Test
  public void isLegalMoveWithNewPlayerIsFalse() {
    Assert.assertFalse(smallModel.isLegalMove(new User('v'), new HexagonalPosn(2, -1, -1)));
  }

  @Test
  public void isLegalMoveWithIllegalMoveIsFalse() {
    Assert.assertFalse(smallModel.isLegalMove(p1, new HexagonalPosn(0, 0, 0)));
  }

  @Test
  public void isLegalMoveWithLegalMoveIsTrue() {
    Assert.assertTrue(smallModel.isLegalMove(p2, new HexagonalPosn(2, -1, -1)));
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
    smallModel.placePiece(p1, new HexagonalPosn(1, -2, 1));
    smallModel.placePiece(p2, new HexagonalPosn(2, -3, 1));
    smallModel.placePiece(p1, new HexagonalPosn(-1, 2, -1));
    smallModel.placePiece(p2, new HexagonalPosn(-1, 3, -2));
    smallModel.placePiece(p1, new HexagonalPosn(-2, 3, -1));
    smallModel.placePiece(p2, new HexagonalPosn(1, 1, -2));
    smallModel.placePiece(p1, new HexagonalPosn(0, 3, -3));
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
    Assert.assertEquals(3, smallModel.getSurroundingTiles(new HexagonalPosn(0, 3, -3)).size());
  }

  @Test
  public void getSurroundingTilesInCenterReturnsSixTiles() {
    Assert.assertEquals(6, smallModel.getSurroundingTiles(new HexagonalPosn(0, 0, 0)).size());
  }

  @Test
  public void getSurroundingTilesDoesNotCompromiseTilesToMutation() {
    List<ITile> tiles = smallModel.getSurroundingTiles(new HexagonalPosn(0, 0, 0));
    for (ITile tile : tiles) {
      tile.flipTo(p1);
    }
    Assert.assertNotEquals(6, smallModel.getScore(p1));
  }

  // TESTING constructor
  @Test(expected = IllegalArgumentException.class)
  public void createReversiWithNullListOfPlayersThrowsIllegalArg() {
    new HexagonalReversiModel(null, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createReversiWithNoPlayersThrowsIllegalArg() {
    new HexagonalReversiModel(new ArrayList<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void createReversiWithOnlyOnePlayerThrowsIllegalArg() {
    new HexagonalReversiModel(new ArrayList<>(List.of(p1)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void createReversiWithTwoOfTheSamePlayersThrowsIllegalArg() {
    new HexagonalReversiModel(new ArrayList<>(List.of(p1, p1)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void createReversiWithNullPlayersThrowsIllegalArg() {
    List<Player> players = new ArrayList<>();
    players.add(p1);
    players.add(p2);
    players.add(null);
    new HexagonalReversiModel(players);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createReversiWithBoardSizeOneThrowsIllegalArg() {
    new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2)), 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createReversiWithNegativeBoardSizeThrowsIllegalArg() {
    new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2)), -1);
  }
}
