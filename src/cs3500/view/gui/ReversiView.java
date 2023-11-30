package cs3500.view.gui;

import java.awt.event.KeyListener;
import java.util.List;

/**
 * Creates a gui view of our Reversi game.
 */
public interface ReversiView {
  /**
   * Sets the visibility of this View to true.
   */
  void makeVisible();

  /**
   * Updates the view.
   */
  void update();

  /**
   * Displays a popup with the given exception.
   */
  void displayException(Exception e);

  void addKeyListener(KeyListener listener);

  boolean requestFocusInWindow();

  List<Integer> getHighlighted();
}
