package cs3500.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import cs3500.view.ReversiTextualView;

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
    ai = new AI('X');
    bigModel = new HexagonalReversiModel(p1, p2);
    bigTv = new ReversiTextualView(bigModel, System.out);
    smallModel = new HexagonalReversiModel(p1, p2, 3);
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
    smallModel.placePiece(p1, new HexagonalPosn(2, -1, -1));
    smallModel.placePiece(p1, new HexagonalPosn(1, 1, -2));
    smallModel.placePiece(p1, new HexagonalPosn(-1, -1, 2));
    Assert.assertTrue(smallModel.possibleMoves(p1).isEmpty());
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

  // TESTING equals(Object obj)
  @Test
  public void compareAIAndUserWithSamePlayerValue() {
    Assert.assertEquals(p1, ai);
  }

  // TESTING skip(Player p)
  @Test(expected = IllegalArgumentException.class)
  public void exceptionIfNullPlayerTriesToSkip() {
    smallModel.skip(null);
  }
}
