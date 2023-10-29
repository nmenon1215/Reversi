package cs3500.model;

import java.util.List;

public class HexagonalReversiModel implements MutableReversiModel{

  private List<ITile> board;

  HexagonalReversiModel() {
  }

  String errormsg = "If you got this to run, the code compiles!";

  @Override
  public void placePiece(Player p, Posn posn) {
    if(p == null) {
      throw new IllegalArgumentException("The given player can't be null.");
    }
    if(posn == null) {
      throw new IllegalArgumentException("The given position can't be null.");
    }
    // find the tile where we will place this piece
    // find all lines surrounding this tile (NEW METHOD)
    // for each line check for a sandwich (NEW METHOD)
      // if there is a sandwich make the move (NEW METHOD)
    // if no moves were ever made throw error
    throw new RuntimeException(errormsg);
  }

  @Override
  public ITile getTileAt(Posn p) {
    return new HexagonalTile(findTile(p));
  }

  @Override
  public boolean isGameOver() {
    for (ITile tile : this.board) {
      if (tile.getPlayer() == null) {
        return false;
      }
    }
    return true;
  }

  @Override
  public List<ITile> possibleMoves(Player p) {
    throw new RuntimeException(errormsg);
    // for every tile in the board
    // find all lines surrounding this tile (NEW METHOD)
    // for each line check for a sandwich (NEW METHOD)
    // if there is a sandwich add to the list of moves
    // if no moves were ever made throw error
  }

  @Override
  public int getScore(Player p) {
    if (p == null) {
      throw new IllegalArgumentException("The given Player can't be null.");
    }
    int score = 0;
    for(ITile tile : board) {
      if (tile.getPlayer().equals(p)) {
        score++;
      }
    }
    return score;
  }

  // Retrieves the tile at the given position.
  private ITile findTile(Posn p) {
    if(p == null) {
      throw new IllegalArgumentException("The given position can't be null.");
    }
    for(ITile tile : this.board) {
      if (tile.getPosition().equals(p)) {
        return tile;
      }
    }
    throw new IllegalArgumentException("The given position is out of bounds for the board.");
  }
}
