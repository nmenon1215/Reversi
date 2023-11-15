package cs3500.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.model.HexagonalReversiModel;
import cs3500.model.ITile;
import cs3500.model.MutableReversiModel;
import cs3500.model.Posn;
import cs3500.model.ROReversiModel;

/**
 * Captures the max number of pieces. If there is a tie, it chooses the topmost, leftmost piece.
 */
public class CaptureMaxPieces implements Strategy{
  @Override
  public List<ITile> filterMoves(ROReversiModel model, Player p, List<ITile> moves) {
    validateParams(model, p, moves);
    Map<ITile, Integer> scoredMoves = new HashMap<>();
    //Populate the map with all the moves and their scores
    int maxScore = 0;
    for(int i = 0; i < moves.size(); i++) {
      MutableReversiModel mock = new HexagonalReversiModel(model);
      int moveScore = -model.getScore(p);
      mock.placePiece(p, moves.get(i).getPosition());
      moveScore += mock.getScore(p);
      if(moveScore > maxScore) {
        maxScore = moveScore;
      }
      scoredMoves.put(moves.get(i), moveScore);
    }
    //Find all values in map with this max score and return those tiles in a list.
    List<ITile> filtered = new ArrayList<>();
    for(ITile t : moves) {
      if (scoredMoves.get(t) == maxScore) {
        filtered.add(t);
      }
    }
    return filtered;
  }

  private void validateParams(ROReversiModel model, Player p, List<ITile> moves) {
    if (model == null) {
      throw new IllegalArgumentException("The given model can't be null.");
    }
    if (p == null) {
      throw new IllegalArgumentException("The given player can't be null.");
    }
    if (moves == null) {
      throw new IllegalArgumentException("The given list of moves can't be null.");
    }
    if (moves.contains(null)) {
      throw new IllegalArgumentException("The given list of moves can't contain a null move.");
    }
    for (ITile t : moves) {
      if (!model.possibleMoves(p).contains(t)) {
        throw new IllegalArgumentException("All moves given must be valid moves.");
      }
    }
  }
}
