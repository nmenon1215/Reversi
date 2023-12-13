package cs3500.reversi.view.gui;

import java.awt.*;
import java.awt.geom.Path2D;

import cs3500.reversi.model.Posn;
import cs3500.reversi.model.ROReversiModel;
import cs3500.reversi.player.Player;

public class SquareButton extends ReversiButton{
  public SquareButton(Posn posn, ROReversiModel roModel, Player player) {
    super(posn, roModel, player);
  }

  @Override
  protected void createShape(Path2D.Double line) {
    Rectangle bounds = this.getBounds();
    double side = bounds.getHeight();
    line.moveTo(0, 0);
    line.lineTo(side, 0);
    line.lineTo(side, side);
    line.lineTo(0, side);
    line.closePath();
  }
}
