package cs3500.reversi.provider.view.gui;

import cs3500.reversi.provider.controller.ViewFeatures;
import java.awt.Dimension;
import java.awt.Graphics;


/**
 * An interface to represent the panel of the view of a Reversi game. The panel goes inside
 * the frame and displays the model of the game.
 */
public interface PanelView {

  /**
   * paints all the hexagons in the game board of a reversi game onto the panel.
   * @param graphics the <code>Graphics</code> object to protect
   */
  void paintComponent(Graphics graphics);

  /**
   * This method tells Swing what the "natural" size should be
   * for this panel.
   * @return  Our preferred *physical* size.
   */
  Dimension getPreferredSize();


  /**
   * Adds a listener to the view to react to the signals the view sends.
   * @param features the listener (the controller).
   */
  void addFeatureListener(ViewFeatures features);

  /**
   * Deselects all hexagons on the board so that they all return grey.
   */
  void deselectAll();


}
