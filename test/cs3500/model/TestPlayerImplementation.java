package cs3500.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPlayerImplementation {
  Player p1;
  Player ai;

  @Before
  public void init() {
    p1 = new User('X');
    ai = new AI('X');
  }

  // TESTING equals(Object obj)
  @Test
  public void compareAIAndUserWithSamePlayerValue() {
    Assert.assertEquals(p1, ai);
  }
}