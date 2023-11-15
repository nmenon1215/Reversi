package cs3500.player;

import java.util.ArrayList;
import java.util.List;

import cs3500.model.HexagonalPosn;
import cs3500.model.ITile;
import cs3500.model.Posn;
import cs3500.model.ROReversiModel;

public class AvoidCellsNextToCorners implements Strategy{
  @Override
  public List<ITile> filterMoves(ROReversiModel model, Player p, List<ITile> moves) {
    validateParams(model, p, moves);
    // Find all moves not next to corner
    List<ITile> notAdjacentToCorner = new ArrayList<>();
    for(ITile t : moves) {
      if(!adjacentToCorner(t, model)) {
        notAdjacentToCorner.add(t);
      }
    }
    // If we find none, return all moves
    if(notAdjacentToCorner.isEmpty()) {
      return moves;
    }
    // Otherwise, return all moves not adjacent to corner.
    return notAdjacentToCorner;
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
    if(moves.size() < 1) {
      throw new IllegalArgumentException("Must include at least 1 valid move.");
    }
    for (ITile t : moves) {
      if (!model.possibleMoves(p).contains(t)) {
        throw new IllegalArgumentException("All moves given must be valid moves.");
      }
    }
  }

  private boolean adjacentToCorner(ITile t, ROReversiModel model) {
    Posn position = t.getPosition();
    List<Posn> corners = model.getCorners();
    for(int i  = 0; i < corners.size(); i++) {
      if(position.adjacentTo(corners.get(i))) {
        return true;
      }
    }
    return false;
  }
}
