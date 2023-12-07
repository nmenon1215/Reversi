package cs3500.reversi.provider.view.gui;


import cs3500.reversi.provider.controller.ViewFeatures;

/**
 * An interface that represents the Frame to the view of a reversi game.
 */
public interface FrameView {


  /**
   * Adds a message to the view.
   * @param message the written message to be added.
   */
  void showMessageDialog(String message);

  /**
   * Adds a listener to the view to react to the signals the view sends.
   * @param features the listener (the controller).
   */
  void addFeatureListener(ViewFeatures features);

  /**
   * Deselects all cells in the game board.
   */
  void deselectAll();

  /**
   * updates the view to the current state of the model by repainting the panel.
   */
  void refresh();

  /**
   * Sets the view to be visible.
   */
  void setVisible();

  /**
   * Sets the title to the given title.
   */
  void makeTitle(String title);
}
