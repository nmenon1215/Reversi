package cs3500.player;

import java.util.List;

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
  public Posn choosePosn(ROReversiModel model, Player p) {
    if (!model.hasLegalMoves(p)) {
      return null;
    }
    int maxIndex = 0;
    int maxScore = 0;
    List<ITile> moves = model.possibleMoves(p);
    for(int i = 0; i < moves.size(); i++) {
      MutableReversiModel mock = new HexagonalReversiModel(model);
      int moveScore = -model.getScore(p);
      mock.placePiece(p, moves.get(i).getPosition());
      moveScore += mock.getScore(p);
      if(moveScore > maxScore) {
        maxScore = moveScore;
        maxIndex = i;
      }
    }
    return moves.get(maxIndex).getPosition();
  }
}
