package cs3500.reversi.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.reversi.model.HexagonalPosn;
import cs3500.reversi.model.HexagonalReversiModel;
import cs3500.reversi.model.ITile;
import cs3500.reversi.model.MutableReversiModel;
import cs3500.reversi.model.Posn;
import cs3500.reversi.model.ROReversiModel;

/**
 * Captures the max number of pieces. If there is a tie, it chooses the topmost, leftmost piece.
 */
public class CaptureMaxPieces implements Strategy {
  @Override
  public List<Posn> filterMoves(ROReversiModel model, Player p, List<Posn> moves) {
    validateParams(model, p, moves);
    Map<Posn, Integer> scoredMoves = new HashMap<>();
    //Populate the map with all the moves and their scores
    int maxScore = 0;
    for (int i = 0; i < moves.size(); i++) {
      MutableReversiModel mock = new HexagonalReversiModel(model);
      int moveScore = -model.getScore(p);
      mock.placePiece(p, moves.get(i));
      moveScore += mock.getScore(p);
      if (moveScore > maxScore) {
        maxScore = moveScore;
      }
      scoredMoves.put(moves.get(i), moveScore);
    }
    //Find all values in map with this max score and return those tiles in a list.
    List<Posn> filtered = new ArrayList<>();
    for (Posn posn : moves) {
      if (scoredMoves.get(posn) == maxScore) {
        filtered.add(posn);
      }
    }
    return filtered;
  }

  private void validateParams(ROReversiModel model, Player p, List<Posn> moves) {
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
    if (moves.size() < 1) {
      throw new IllegalArgumentException("Must include at least 1 valid move.");
    }
    for (Posn posn : moves) {
      List<Posn> allPossibleMoves = new ArrayList<>();
      for (ITile tile : model.possibleMoves(p)) {
        allPossibleMoves.add(tile.getPosition());
      }
      if (!allPossibleMoves.contains(posn)) {
        throw new IllegalArgumentException("All moves given must be valid moves.");
      }
    }
  }
}
