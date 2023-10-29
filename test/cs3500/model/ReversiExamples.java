package cs3500.model;

import org.junit.Before;
import org.junit.Test;

public class ReversiExamples {

  @Before
  public void init() {}

  @Test
  public void confirmPiecesStartAtCorrectPlacesOnBoardSizeSix() {}

  @Test
  public void confirmPiecesStartAtCorrectPlacesOnBoardSizeThree() {}

  @Test
  public void confirmPlacePieceWithOnly1TileFlipped() {}

  @Test
  public void confirmPlacePieceWithMultipleTilesFlippedInOneDirection() {}

  @Test
  public void confirmPlacePieceWithMultipleTilesFlippedInMultipleDirections() {}

  @Test(expected = IllegalStateException.class)
  public void confirmPlacePieceWithPlacementNextToNoPiecesFails() {}

  @Test(expected = IllegalStateException.class)
  public void confirmPlacePieceWithPlacementWhereNoTilesAreFlippedFails() {}
}
