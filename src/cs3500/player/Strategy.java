package cs3500.player;

import cs3500.model.Posn;
import cs3500.model.ROReversiModel;

public interface Strategy {
  Posn choosePosn(ROReversiModel model, Player p);
}
