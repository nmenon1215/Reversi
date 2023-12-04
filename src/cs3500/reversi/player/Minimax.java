package cs3500.player;

import java.util.List;

import cs3500.model.Posn;
import cs3500.model.ROReversiModel;

/**
 * Find the move that gives the opponent the worst move. Assume the opponent's strategy is placing
 * wherever flips most tiles.
 */
public class Minimax implements Strategy {
  // assume opponent is just capturing max pieces
  @Override
  public List<Posn> filterMoves(ROReversiModel model, Player p, List<Posn> moves) {
    //TODO: Return List<Map<Posn, Integer>> each move is given a rating. filterMoves must also take
    // in a weightage. We will decide weightage by order of strats.
    return null;
  }
}
