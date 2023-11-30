package cs3500.view.gui;

import java.awt.event.KeyListener;
import java.util.List;
import java.util.Objects;

/**
 * Records all calls made to the view.
 */
public class MockView implements ReversiView {

  private Appendable appendable;
  private ReversiView view;

  /**
   * Creates a mock view.
   * @param appendable the appendable
   * @param view the view being mocked
   */
  public MockView(Appendable appendable, ReversiView view) {
    this.appendable = Objects.requireNonNull(appendable);
    this.view = Objects.requireNonNull(view);
  }

  @Override
  public void makeVisible() {

  }

  @Override
  public void update() {

  }

  /**
   * Displays a popup with the given exception.
   */
  void displayException(Exception e);

  void addKeyListener(KeyListener listener);

  boolean requestFocusInWindow();

  List<Integer> getHighlighted();

}
