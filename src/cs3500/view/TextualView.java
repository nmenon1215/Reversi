package cs3500.view;

import java.io.IOException;

/**
 * Text-based view that renders the klondike model as a String.
 */
public interface TextualView {
  /**
   * Renders a model in some manner (e.g. as text, or as graphics, etc.).
   *
   * @throws IOException if the rendering fails for some reason
   */
  void render() throws IOException;
}
