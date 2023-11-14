package cs3500.player;

import java.util.List;

import cs3500.model.ITile;
import cs3500.model.Posn;
import cs3500.model.ROReversiModel;

public interface Strategy {
  List<ITile> filterMoves(ROReversiModel model, Player p, List<ITile> moves);
}
