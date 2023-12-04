package view.gui;


import controller.ViewFeatures;

/**
 * An interface that represents the Frame to the view of a reversi game.
 */
public interface FrameView {


  /**
   * Adds a listener to the view to react to the signals the view sends.
   * @param features the listener (the controller).
   */
  void addFeatureListener(ViewFeatures features);


  /**
   * updates the view to the current state of the model by repainting the panel.
   */
  void refresh();

}
