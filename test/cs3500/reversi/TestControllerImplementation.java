package cs3500.reversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.controller.Controller;
import cs3500.reversi.model.HexagonalPosn;
import cs3500.reversi.model.HexagonalReversiModel;
import cs3500.reversi.model.MockModel;
import cs3500.reversi.model.MutableReversiModel;
import cs3500.reversi.model.ROReversiModel;
import cs3500.reversi.player.AI;
import cs3500.reversi.player.CaptureMaxPieces;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.User;
import cs3500.reversi.provider.view.gui.FrameView;
import cs3500.reversi.view.gui.JFrameReversiView;
import cs3500.reversi.view.gui.MockView;

/**
 * Implementation specific tests regarding how the controller interacts with each public method.
 */
public class TestControllerImplementation {

  Player p1;
  Player p2;
  Player aiMax;
  MutableReversiModel model;
  MockModel mockModel;
  ROReversiModel roModel;
  FrameView view;
  MockView mockView;
  Appendable appendableView;
  Appendable appendableModel;
  Controller controllerP1;
  Controller controllerP2;

  @Before
  public void init() {
    p1 = new User('X');
    p2 = new User('O');
    aiMax = new AI('O', List.of(new CaptureMaxPieces()));

    appendableModel = new StringBuilder();
    model = new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2)), 3);
    mockModel = new MockModel(model, appendableModel);
    roModel = model;

    appendableView = new StringBuilder();
    view = new JFrameReversiView(model);
    //mockView = new MockView(appendableView, view);

    //controllerP1 = new Controller(model, mockView, p1);
    //controllerP2 = new Controller(model, mockView, p2);
  }

  @Test
  public void testStart() {
    controllerP1.start();
    // confirm that the view has requestFocusInWindow in its appendable
    Assert.assertTrue(mockView.getAppendable().toString().contains("requestFocusInWindow()"));
  }

  @Test
  public void testKeyPressed() {
    model.placePiece(p1, new HexagonalPosn(1, -2, 1));
    model.placePiece(p2, new HexagonalPosn(2, -3, 1));
    model.placePiece(p1, new HexagonalPosn(-1, 2, -1));
    model.placePiece(p2, new HexagonalPosn(-1, 3, -2));
    model.placePiece(p1, new HexagonalPosn(-2, 3, -1));
    model.placePiece(p2, new HexagonalPosn(1, 1, -2));
    model.placePiece(p1, new HexagonalPosn(0, 3, -3));
    KeyEvent pEvent = new KeyEvent(
            new JFrameReversiView(roModel),
            KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(),
            0,
            KeyEvent.VK_P,
            'p'
    );

    // check to see the view is are effected correctly from keyPressed KeyEvent.VK_S
    Assert.assertTrue(mockView.getAppendable().toString().contains("update()"));


  }

  @Test
  public void testMakeMovePlace() {
    model.placePiece(p1, new HexagonalPosn(1, -2, 1));
    model.placePiece(p2, new HexagonalPosn(2, -3, 1));
    model.placePiece(p1, new HexagonalPosn(-1, 2, -1));
    model.placePiece(p2, new HexagonalPosn(-1, 3, -2));
    model.placePiece(p1, new HexagonalPosn(-2, 3, -1));
    model.placePiece(p2, new HexagonalPosn(1, 1, -2));

    controllerP1.makeMove("p");
    // check to see the view is effected correctly from makeMove p call
    Assert.assertTrue(mockView.getAppendable().toString().contains("update()"));
  }

  @Test
  public void testMakeMoveSkip() {
    model.placePiece(p1, new HexagonalPosn(1, -2, 1));
    model.placePiece(p2, new HexagonalPosn(2, -3, 1));
    model.placePiece(p1, new HexagonalPosn(-1, 2, -1));
    model.placePiece(p2, new HexagonalPosn(-1, 3, -2));
    model.placePiece(p1, new HexagonalPosn(-2, 3, -1));
    model.placePiece(p2, new HexagonalPosn(1, 1, -2));
    model.placePiece(p1, new HexagonalPosn(0, 3, -3));

    controllerP2.makeMove("s");
    // check to see the view is are effected correctly from makeMove s call
    Assert.assertTrue(mockView.getAppendable().toString().contains("update()"));
  }

  @Test
  public void testStartTurn() {
    controllerP1.startTurn();
    // confirm that the view has update and requestFocusInWindow in its appendable
    Assert.assertTrue(mockView.getAppendable().toString().contains("requestFocusInWindow()"));
    Assert.assertTrue(mockView.getAppendable().toString().contains("update()"));
  }
}
