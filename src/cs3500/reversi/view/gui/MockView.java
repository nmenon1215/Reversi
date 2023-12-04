package cs3500.reversi.view.gui;

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
    try {
      appendable.append("makeVisible()\n");
    }
    catch (Exception e) {
      throw new RuntimeException("Mock failed");
    }
    this.view.makeVisible();
  }

  @Override
  public void update() {
    try {
      appendable.append("update()\n");
    }
    catch (Exception e) {
      throw new RuntimeException("Mock failed");
    }
    this.view.update();
  }

  @Override
  public void displayException(Exception e) {
    try {
      appendable.append("displayException(" + e.getMessage() + ")\n");
    }
    catch (Exception ex) {
      throw new RuntimeException("Mock failed");
    }
    this.view.displayException(e);
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    try {
      appendable.append("addKeyListener(" + listener + ")\n");
    }
    catch (Exception e) {
      throw new RuntimeException("Mock failed");
    }
    this.view.addKeyListener(listener);
  }

  @Override
  public boolean requestFocusInWindow() {
    try {
      appendable.append("requestFocusInWindow()\n");
    }
    catch (Exception e) {
      throw new RuntimeException("Mock failed");
    }
    return this.view.requestFocusInWindow();
  }

  @Override
  public List<Integer> getHighlighted() {
    try {
      appendable.append("getHighlighted()\n");
    }
    catch (Exception e) {
      throw new RuntimeException("Mock failed");
    }
    return List.of(2, -1, -1);
  }

  @Override
  public void createTitle(String toString) {
    try {
      appendable.append("createTitle(" + toString + ")\n");
    }
    catch (Exception e) {
      throw new RuntimeException("Mock failed");
    }
    this.view.createTitle(toString);
  }

  /**
   * Gets the appendable for testing.
   * @return the appendable
   */
  public Appendable getAppendable() {
    return appendable;
  }
}
