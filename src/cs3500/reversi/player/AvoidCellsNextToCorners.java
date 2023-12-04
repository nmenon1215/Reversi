package cs3500.player;

import java.util.ArrayList;
import java.util.List;

import cs3500.model.ITile;
import cs3500.model.Posn;
import cs3500.model.ROReversiModel;

/**
 * Returns all moves that don't place next to a corner. If all moves place next to a corner,
 * return all the moves.
 */
public class AvoidCellsNextToCorners implements Strategy {
  @Override
  public List<Posn> filterMoves(ROReversiModel model, Player p, List<Posn> moves) {
    validateParams(model, p, moves);
    // Find all moves not next to corner
    List<Posn> notAdjacentToCorner = new ArrayList<>();
    for (Posn posn : moves) {
      if (!adjacentToCorner(posn, model)) {
        notAdjacentToCorner.add(posn);
      }
    }
    // If we find none, return all moves
    if (notAdjacentToCorner.isEmpty()) {
      return moves;
    }
    // Otherwise, return all moves not adjacent to corner.
    return notAdjacentToCorner;
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

  private boolean adjacentToCorner(Posn position, ROReversiModel model) {
    List<Posn> corners = model.getCorners();
    for (int i = 0; i < corners.size(); i++) {
      if (position.adjacentTo(corners.get(i))) {
        return true;
      }
    }
    return false;
  }
}
