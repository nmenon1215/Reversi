package cs3500.model;

import java.util.ArrayList;
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

    ITile placingTile = findTile(posn);
    if(placingTile.getPlayer() != null) {
      throw new IllegalStateException("This tile is already occupied.");
    }

    List<List<ITile>> surroundingLines =  getSurroundingLines(placingTile);

    boolean pieceFlipped = false;
    for(List<ITile> line : surroundingLines) {
      if(isSandwich(line)) {
        flipTiles(line);
        pieceFlipped = true;
      }
    }

    if(!pieceFlipped) {
      throw new IllegalStateException("No tiles were flipped by this move.");
    }

    placingTile.flipTo(p);
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
    List<ITile> possibleMoves = new ArrayList<>();
    // for every tile in the board
    for (ITile tile : board) {
      List<List<ITile>> surroundingLines = getSurroundingLines(tile);
      for(List<ITile> line : surroundingLines) {
        if(isSandwich(line)) {
          possibleMoves.add(tile);
          break;
        }
      }
    }
    return possibleMoves;
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

  private List<List<ITile>> getSurroundingLines(ITile placingTile) {
    throw new RuntimeException(errormsg);
  }

  private boolean flipTiles(List<ITile> line) {
    throw new RuntimeException(errormsg);
  }

  private boolean isSandwich(List<ITile> line) {
    throw new RuntimeException(errormsg);
  }
}
