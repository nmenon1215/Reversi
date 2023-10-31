package cs3500.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    smallModel.placePiece(ai, new HexagonalPosn(2, -1, -1));
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
  }

  @Test
  public void possibleMovesWithNewPlayerReturnsEmptyList() {
  }

  @Test
  public void possibleMovesWithNoPossibleMovesReturnsEmptyList() {
  }

  @Test
  public void possibleMovesWithMultiplePossibleMovesWorks() {
  }

  @Test
  public void possibleMovesDoesNotCompromiseTheTilesToModification() {
  }

  // TESTING getScore(Player p)
  @Test(expected = IllegalArgumentException.class)
  public void getScoreOfNullPlayerThrowsIllegalArg() {
    smallModel.getScore(null);
  }

  @Test
  public void getScoreOfNewPlayerReturns0() {
    Assert.assertEquals(0, smallModel.getScore(p1));
  }

  @Test
  public void getScoreOfPlayerReturnsCorrectValueMidGame() {
    smallModel.placePiece(p1, new HexagonalPosn(2, -1, -1));
    smallModel.placePiece(p2, new HexagonalPosn(1, -2, 1));
    smallModel.placePiece(p1, new HexagonalPosn(1, 1, -2));
    smallModel.placePiece(p2, new HexagonalPosn(1, 2, -3));
    Assert.assertEquals(0, smallModel.getScore(p1));
  }

  // TESTING equals(Object obj)
  @Test
  public void compareAIAndUserWithSamePlayerValue() {
    Assert.assertTrue(p1.equals(ai));
  }

  // TESTING skip(Player p)
  @Test(expected = IllegalArgumentException.class)
  public void exceptionIfNullPlayerTriesToSkip() {
    smallModel.skip(null);
  }
}
