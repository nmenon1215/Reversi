package cs3500.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.player.AI;
import cs3500.player.CaptureMaxPieces;
import cs3500.player.Player;
import cs3500.player.User;

public class TestPlayerImplementation {
  Player p1;
  Player aiBasic;

  @Before
  public void init() {
    p1 = new User('X');
    aiBasic = new AI('X', new ArrayList<>(List.of(new CaptureMaxPieces())));
  }

  // TESTING equals(Object obj)
  @Test
  public void compareAIAndUserWithSamePlayerValue() {
    Assert.assertEquals(p1, aiBasic);
  }

  // TESTING parameter checks work
  @Test
  public void ensureModelCannotBeNull() {}

  @Test
  public void ensurePlayerCannotBeNull() {}

  @Test
  public void ensureThereCannotBeNullMoves() {}

  @Test
  public void ensureHavingNoMovesThrowsAnException() {}

  @Test
  public void ensureGivenMovesAreActuallyValid() {}

  // TESTING CaptureMaxPieces Strategy
  @Test
  public void choosesMoveWithMoreTilesToBeFlipped() {}

  // TESTING AvoidCellsNextToCorners Strategy
  @Test
  public void aiChoosesMoveAwayFromCorner() {}

  // TESTING PlaceAtCorners

  // TESTING CaptureMaxPieces as Priority Strategy

  // TESTING AvoidCellsNextToCorners as Priority Strategy

  // TESTING PlaceAtCorners as Priority Strategy

}
