package cs3500.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.player.AI;
import cs3500.player.CaptureMaxPieces;
import cs3500.player.Player;
import cs3500.player.User;

/**
 * Implementation specific tests regarding how the controller interacts with each public method.
 */
public class TestControllerImplementation {

  Player p1;
  Player p2;
  Player aiMax;
  MutableReversiModel model;
  MutableReversiModel mock;

  @Before
  public void init() {
    p1 = new User('X');
    p2 = new User('O');
    aiMax = new AI('O', List.of(new CaptureMaxPieces()));

    model = new HexagonalReversiModel(new ArrayList<>(List.of(p1, p2)), 3);
    mock = new MockModel(model);
  }

  @Test
  public test
}
