package cs3500.reversi.view.gui;

import java.awt.*;
import java.awt.geom.Path2D;

import cs3500.reversi.model.Posn;
import cs3500.reversi.model.ROReversiModel;
import cs3500.reversi.player.Player;

public class HexagonalButton extends ReversiButton{
  public HexagonalButton(Posn posn, ROReversiModel roModel, Player player) {
    super(posn, roModel, player);
  }

  @Override
  protected void createShape(Path2D.Double line) {
    Rectangle bounds = this.getBounds();
    double height = bounds.getHeight();
    double sideLength = height / 2;
    double widthMovement = sideLength * Math.sqrt(3) / 2;
    line.moveTo(widthMovement, 0);
    line.lineTo(widthMovement * 2, sideLength / 2);
    line.lineTo(widthMovement * 2, 3 * sideLength / 2);
    line.lineTo(widthMovement, 2 * sideLength);
    line.lineTo(0, 3 * sideLength / 2);
    line.lineTo(0, sideLength / 2);
    line.closePath();
  }
}
