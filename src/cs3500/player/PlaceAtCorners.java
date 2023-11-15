package cs3500.player;

import java.util.ArrayList;
import java.util.List;

import cs3500.model.ITile;
import cs3500.model.Posn;
import cs3500.model.ROReversiModel;

public class PlaceAtCorners implements Strategy{
  @Override
  public List<Posn> filterMoves(ROReversiModel model, Player p, List<Posn> moves) {
    validateParams(model, p, moves);
    List<Posn> cornerMoves = new ArrayList<>();
    for(Posn posn : model.getCorners()) {
      if (moves.contains(posn)) {
        cornerMoves.add(posn);
      }
    }
    if (cornerMoves.isEmpty()) {
      // no corners found, do not filter anything
      return moves;
    }
    return cornerMoves;
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
    if(moves.size() < 1) {
      throw new IllegalArgumentException("Must include at least 1 valid move.");
    }
    for (Posn posn : moves) {
      List<Posn> allPossibleMoves = new ArrayList<>();
      for(ITile tile : model.possibleMoves(p)) {
        allPossibleMoves.add(tile.getPosition());
      }
      if (!allPossibleMoves.contains(posn)) {
        throw new IllegalArgumentException("All moves given must be valid moves.");
      }
    }
  }
}
