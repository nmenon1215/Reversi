package cs3500.reversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.model.HexagonalPosn;
import cs3500.reversi.model.HexagonalReversiModel;
import cs3500.reversi.model.MockModel;
import cs3500.reversi.model.MutableReversiModel;
import cs3500.reversi.model.ROReversiModel;
import cs3500.reversi.player.AI;
import cs3500.reversi.player.AvoidCellsNextToCorners;
import cs3500.reversi.player.CaptureMaxPieces;
import cs3500.reversi.player.PlaceAtCorners;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.Strategy;
import cs3500.reversi.player.User;
import cs3500.reversi.view.gui.ReversiView;

/**
 * Testing suite for Players and their possible strategies.
 */
public class TestPlayerImplementation {
  Player p1;
  Player p2;
  Player ai;
  Player aiMax;
  Player aiNoCorners;
  Player aiCorners;
  Player aiMaxPriority;
  Player aiNoCorPriority;
  Player aiCornerPriority;
  Strategy corners;
  Strategy noCorners;
  Strategy maxPieces;
  MutableReversiModel bigModel;
  MutableReversiModel smallModel;
  ROReversiModel roSmallModel;
  List<Player> players;
  ReversiView view;

  @Before
  public void init() {
    p1 = new User('X');
    p2 = new User('O');
    ai = new AI('X', new ArrayList<>());
    aiMax = new AI('O', new ArrayList<>(List.of(new CaptureMaxPieces())));
    aiNoCorners = new AI('O', new ArrayList<>(List.of(new AvoidCellsNextToCorners())));
    aiCorners = new AI('O', new ArrayList<>(List.of(new PlaceAtCorners())));
    aiMaxPriority = new AI('O', new ArrayList<>(List.of(new CaptureMaxPieces(),
            new PlaceAtCorners())));
    aiNoCorPriority = new AI('O', new ArrayList<>(List.of(new AvoidCellsNextToCorners(),
            new CaptureMaxPieces())));
    aiCornerPriority = new AI('O', new ArrayList<>(List.of(new PlaceAtCorners(),
            new CaptureMaxPieces())));
    players = new ArrayList<>();
    players.add(p1);
    players.add(aiMax);

    corners = new PlaceAtCorners();
    noCorners = new AvoidCellsNextToCorners();
    maxPieces = new CaptureMaxPieces();

    bigModel = new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2)));
    smallModel = new HexagonalReversiModel(players, 3);
    roSmallModel = new HexagonalReversiModel(players, 3);

    //view = new JFrameReversiView(roSmallModel);
  }

  // TESTING equals(Object obj)
  @Test
  public void compareAIAndUserWithSamePlayerValue() {
    Assert.assertEquals(p1, ai);
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
    smallModel.placePiece(p1, new HexagonalPosn(2, -1, -1));
    Assert.assertEquals(new HexagonalPosn(3, -2, -1), aiMax.placePiece(smallModel, view));
  }

  @Test
  public void topLeftOptionChosenWhenMaxNumberTies() {
    smallModel.placePiece(p1, new HexagonalPosn(2, -1, -1));
    smallModel.placePiece(aiMax, new HexagonalPosn(3, -2, -1));
    smallModel.placePiece(p1, new HexagonalPosn(1, 1, -2));
    smallModel.placePiece(aiMax, new HexagonalPosn(-1, -1, 2));
    smallModel.placePiece(p1, new HexagonalPosn(-1, -2, 3));
    Assert.assertEquals(new HexagonalPosn(-1, 2, -1), aiMax.placePiece(smallModel, view));
  }

  // TESTING AvoidCellsNextToCorners Strategy
  @Test
  public void aiChoosesMoveAwayFromCorner() {
    smallModel.placePiece(p1, new HexagonalPosn(2, -1, -1));
    Assert.assertNotEquals(new HexagonalPosn(3, -2, -1), aiNoCorners.placePiece(smallModel, view));

  }

  // TESTING PlaceAtCorners
  @Test
  public void aiChoosesToPlaceAPieceInCorner() {
    smallModel.placePiece(p1, new HexagonalPosn(2, -1, -1));
    smallModel.placePiece(aiNoCorners, new HexagonalPosn(3, -2, -1));
    smallModel.placePiece(p1, new HexagonalPosn(1, 1, -2));
    smallModel.placePiece(aiNoCorners, new HexagonalPosn(-1, -1, 2));
    smallModel.placePiece(p1, new HexagonalPosn(-1, -2, 3));
    smallModel.placePiece(aiNoCorners, new HexagonalPosn(-2, -1, 3));
    smallModel.placePiece(p1, new HexagonalPosn(1, -2, 1));
    Assert.assertEquals(new HexagonalPosn(0, -3, 3), aiNoCorners.placePiece(smallModel, view));

  }

  @Test
  public void topLeftOptionChosenWhenTwoCornersOpen() {
    smallModel.placePiece(p1, new HexagonalPosn(2, -1, -1));
    smallModel.placePiece(aiNoCorners, new HexagonalPosn(3, -2, -1));
    smallModel.placePiece(p1, new HexagonalPosn(1, 1, -2));
    smallModel.placePiece(aiNoCorners, new HexagonalPosn(-1, -1, 2));
    smallModel.placePiece(p1, new HexagonalPosn(-1, -2, 3));
    smallModel.placePiece(aiNoCorners, new HexagonalPosn(-2, -1, 3));
    smallModel.placePiece(p1, new HexagonalPosn(1, -2, 1));
    smallModel.placePiece(aiNoCorners, new HexagonalPosn(-1, 2, -1));
    smallModel.placePiece(p1, new HexagonalPosn(-1, 3, -2));
    smallModel.placePiece(aiNoCorners, new HexagonalPosn(-2, 3, -1));
    smallModel.placePiece(p1, new HexagonalPosn(3, -1, -2));
    Assert.assertEquals(new HexagonalPosn(0, -3, 3), aiNoCorners.placePiece(smallModel, view));
  }

  // TESTING CaptureMaxPieces as Priority Strategy
  @Test
  public void capturesMaxNumberOfPiecesWhenPresentedWithCornerMove() {
    smallModel.placePiece(p1, new HexagonalPosn(2, -1, -1));
    smallModel.placePiece(aiMaxPriority, new HexagonalPosn(3, -2, -1));
    smallModel.placePiece(p1, new HexagonalPosn(1, 1, -2));
    smallModel.placePiece(aiMaxPriority, new HexagonalPosn(-1, -1, 2));
    smallModel.placePiece(p1, new HexagonalPosn(-1, -2, 3));
    smallModel.placePiece(aiMaxPriority, new HexagonalPosn(-2, -1, 3));
    smallModel.placePiece(p1, new HexagonalPosn(1, -2, 1));
    Assert.assertEquals(new HexagonalPosn(-1, 2, -1), aiMaxPriority.placePiece(smallModel, view));
  }

  // TESTING AvoidCellsNextToCorners as Priority Strategy
  @Test
  public void ifMaxNumberOfCellsFlippedIsNextToCornerDontDoIt() {
    smallModel.placePiece(p1, new HexagonalPosn(2, -1, -1));
    Assert.assertNotEquals(new HexagonalPosn(3, -2, -1),
            aiNoCorPriority.placePiece(smallModel, view).getCoords());
  }

  // TESTING PlaceAtCorners as Priority Strategy
  @Test
  public void willPlaceInCornerEvenIfItWontCaptureMaxPieces() {
    smallModel.placePiece(p1, new HexagonalPosn(2, -1, -1));
    smallModel.placePiece(aiCornerPriority, new HexagonalPosn(3, -2, -1));
    smallModel.placePiece(p1, new HexagonalPosn(1, 1, -2));
    smallModel.placePiece(aiCornerPriority, new HexagonalPosn(-1, -1, 2));
    smallModel.placePiece(p1, new HexagonalPosn(-1, -2, 3));
    smallModel.placePiece(aiCornerPriority, new HexagonalPosn(-2, -1, 3));
    smallModel.placePiece(p1, new HexagonalPosn(1, -2, 1));
    Assert.assertEquals(new HexagonalPosn(0, -3, 3),
            aiCornerPriority.placePiece(smallModel, view));
  }

  @Test
  public void useMockToConfirmPiecePlacement() {
    Player p1 = new AI('X', List.of(new CaptureMaxPieces()));
    Player p2 = new User('O');
    MutableReversiModel actualModel = new HexagonalReversiModel(List.of(p1, p2));
    MutableReversiModel mock = new MockModel(actualModel, new StringBuilder());
    Assert.assertEquals(new HexagonalPosn(1, -2, 1), p1.placePiece(mock, view));
    mock.placePiece(p1, p1.placePiece(mock, view));

  }
}
