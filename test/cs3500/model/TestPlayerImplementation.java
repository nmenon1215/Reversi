package cs3500.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.player.AI;
import cs3500.player.AvoidCellsNextToCorners;
import cs3500.player.CaptureMaxPieces;
import cs3500.player.PlaceAtCorners;
import cs3500.player.Player;
import cs3500.player.Strategy;
import cs3500.player.User;
import cs3500.view.ReversiTextualView;

public class TestPlayerImplementation {
  Player p1;
  Player p2;
  Player aiBasic;
  Strategy corners;
  Strategy noCorners;
  Strategy maxPieces;
  MutableReversiModel bigModel;
  MutableReversiModel smallModel;

  List<Player> players;

  @Before
  public void init() {
    p1 = new User('X');
    p2 = new User('O');
    aiBasic = new AI('O', new ArrayList<>(List.of(new CaptureMaxPieces())));
    players = new ArrayList<>();
    players.add(p1);
    players.add(aiBasic);

    corners = new PlaceAtCorners();
    noCorners = new AvoidCellsNextToCorners();
    maxPieces = new CaptureMaxPieces();

    bigModel = new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2)));
    smallModel = new HexagonalReversiModel(players, 3);
  }

  // TESTING equals(Object obj)
  @Test
  public void compareAIAndUserWithSamePlayerValue() {
    Assert.assertEquals(p1, aiBasic);
  }

  // TESTING parameter checks work
  @Test(expected = IllegalArgumentException.class)
  public void ensureModelCannotBeNull() {
    corners.filterMoves(null, p1,
            new ArrayList<>(List.of(new HexagonalPosn(0, 0, 0))));
    noCorners.filterMoves(null, p1,
            new ArrayList<>(List.of(new HexagonalPosn(0, 0, 0))));
    maxPieces.filterMoves(null, p1,
            new ArrayList<>(List.of(new HexagonalPosn(0, 0, 0))));
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensurePlayerCannotBeNull() {
    corners.filterMoves(new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2))), null,
            new ArrayList<>(List.of(new HexagonalPosn(0, 0, 0))));
    noCorners.filterMoves(new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2))), null,
            new ArrayList<>(List.of(new HexagonalPosn(0, 0, 0))));
    maxPieces.filterMoves(new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2))), null,
            new ArrayList<>(List.of(new HexagonalPosn(0, 0, 0))));
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureThereCannotBeNullMoves() {
    corners.filterMoves(new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2))),
            p1, null);
    noCorners.filterMoves(new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2))),
            p1, null);
    maxPieces.filterMoves(new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2))),
            p1, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureHavingNoMovesThrowsAnException() {
    corners.filterMoves(new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2))),
            p1, new ArrayList<>());
    noCorners.filterMoves(new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2))),
            p1, new ArrayList<>());
    maxPieces.filterMoves(new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2))),
            p1, new ArrayList<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureGivenMovesAreActuallyValid() {
    corners.filterMoves(new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2))),
            p1, new ArrayList<>(List.of(new HexagonalPosn(0, 1, 0))));
    noCorners.filterMoves(new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2))),
            p1, new ArrayList<>(List.of(new HexagonalPosn(0, 1, 0))));
    maxPieces.filterMoves(new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2))),
            p1, new ArrayList<>(List.of(new HexagonalPosn(0, 1, 0))));
  }

  // TESTING CaptureMaxPieces Strategy
  @Test
  public void choosesMoveWithMoreTilesToBeFlipped() {
    System.out.println(aiBasic.placePiece(smallModel).getCoords());
    Assert.assertEquals(new HexagonalPosn(1, -2, 1), aiBasic.placePiece(smallModel));
    String board =
            "   _ _ _ _ \n" +
                    "  _ _ _ _ _ \n" +
                    " _ _ X O _ _ \n" +
                    "_ _ O _ X _ _ \n" +
                    " _ _ X O _ _ \n" +
                    "  _ _ _ _ _ \n" +
                    "   _ _ _ _ \n";
  }
  @Test
  public void topLeftOptionChosenWhenMaxNumberTies() {}

  // TESTING AvoidCellsNextToCorners Strategy
  @Test
  public void aiChoosesMoveAwayFromCorner() {}

  // TESTING PlaceAtCorners
  @Test
  public void aiChoosesToPlaceAPieceInCorner() {}

  @Test
  public void topLeftOptionChosenWhenTwoCornersOpen() {}

  // TESTING CaptureMaxPieces as Priority Strategy
  @Test
  public void capturesMaxNumberOfPiecesWhenPresentedWithCornerMove() {}

  @Test
  public void capturesMaxNumberOfPiecesWhenPresentedWithSpaceNextToCorner() {}

  // TESTING AvoidCellsNextToCorners as Priority Strategy
  @Test
  public void ifMaxNumberOfCellsFlippedIsNextToCornerDontDoIt() {}

  // TESTING PlaceAtCorners as Priority Strategy
  @Test
  public void willPlaceInCornerEvenIfItWontCaptureMaxPieces() {}

  @Test
  public void choosesTopLeftCornerPlacement() {}
}
