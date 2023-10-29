package cs3500.model;

import org.junit.Before;
import org.junit.Test;

public class TestModelImplementation {
  @Before
  public void init() {}

  // TESTING placePiece(Player p, Posn posn)
  @Test(expected = IllegalArgumentException.class)
  public void placePieceThrowsIllegalArgWithNullPlayer() {}

  @Test(expected = IllegalArgumentException.class)
  public void placePieceThrowsIllegalArgWithNullPosition() {}

  @Test(expected = IllegalArgumentException.class)
  public void placePieceThrowsIllegalArgWithInvalidPosn() {}

  @Test(expected = IllegalStateException.class)
  public void placePieceThrowsIllegalStateWithNewPlayer() {}

  // TESTING getTileAt(Posn posn)
  @Test(expected = IllegalArgumentException.class)
  public void getTileAtPosnOutsideOfGridBoundsFails() {}

  @Test
  public void getTileAtRandomTileWorks() {}

  @Test
  public void getTileAtDoesNotCompromiseTheTileToModification() {}

  // TESTING isGameOver()
  @Test
  public void isGameOverReturnsTrueWhenGameIsOver() {}

  @Test
  public void isGameOverReturnsFalseWhenGameIsNotOver() {}

  // TESTING possibleMoves(Player p)
  @Test(expected = IllegalArgumentException.class)
  public void possibleMovesWithNullPlayerThrowsIllegalArg() {}

  @Test
  public void possibleMovesWithNewPlayerReturnsEmptyList() {}

  @Test
  public void possibleMovesWithNoPossibleMovesReturnsEmptyList() {}

  @Test
  public void possibleMovesWithMultiplePossibleMovesWorks() {}

  @Test
  public void possibleMovesDoesNotCompromiseTheTilesToModification() {}

  // TESTING getScore(Player p)
  @Test(expected = IllegalArgumentException.class)
  public void getScoreOfNullPlayerThrowsIllegalArg() {}
  
  @Test
  public void getScoreOfNewPlayerReturns0() {}

  @Test
  public void getScoreOfPlayerReturnsCorrectValueMidGame() {}
}
