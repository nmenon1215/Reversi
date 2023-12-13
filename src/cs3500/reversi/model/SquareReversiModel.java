package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.player.Player;

public class SquareReversiModel extends ReversiModel{
  public SquareReversiModel(List<Player> players) {
    super(players, 8);
  }

  public SquareReversiModel(List<Player> players, int boardSize) {
    super(players, boardSize);
    if (boardSize % 2 != 0) {
      throw new IllegalArgumentException("Board size must be even");
    }
  }

  public SquareReversiModel(ROReversiModel model) {
    super(model);
  }

  @Override
  public List<Posn> getCorners() {
    List<Posn> corners = new ArrayList<>();
    corners.add(this.board.get(0).get(0).getPosition());
    corners.add(this.board.get(0).get(this.boardSize).getPosition());
    corners.add(this.board.get(this.boardSize).get(0).getPosition());
    corners.add(this.board.get(this.boardSize).get(this.boardSize).getPosition());

    return corners;
  }

  @Override
  protected List<List<ITile>> getSurroundingLines(ITile placingTile) {
    List<List<ITile>> surroundingLines = new ArrayList<>();
    List<Integer> rootPosition = placingTile.getPosition().getCoords();
    int x = rootPosition.get(0);
    int y = rootPosition.get(1);
    for (int xChange =  -1; xChange <= 1; xChange++) {
      for (int yChange = -1; yChange <= 1; yChange++) {
        if(xChange == 0 && yChange == 0) {
          yChange++;
        }
        surroundingLines.add(findLine(xChange, yChange, x, y));
      }
    }

    //remove all empty lines
    for (int i = 0; i < surroundingLines.size(); i++) {
      if (surroundingLines.get(i).isEmpty()) {
        surroundingLines.remove(i);
        i--;
      }
    }

    return surroundingLines;
  }

  @Override
  protected void startGame(List<Player> players) {
    Player p1 = players.get(0);
    Player p2 = players.get(1);
    for (int row = 0; row < this.boardSize; row++) {
      List<ITile> rowTiles = new ArrayList<>();
      for (int col = 0; col < this.boardSize; col++) {
        rowTiles.add(new HexagonalTile(new SquarePosn(col, row)));
      }
      this.board.add(rowTiles);
    }

    // Starting sequence
    /*
     X O
    O - X
     X O
     */
    int center = this.boardSize / 2;
    this.board.get(center - 1).get(center - 1).flipTo(p1);
    this.board.get(center - 1).get(center).flipTo(p2);
    this.board.get(center).get(center - 1).flipTo(p2);
    this.board.get(center).get(center).flipTo(p1);
  }

  private List<ITile> findLine(int xChange, int yChange, int x, int y) {
    x += xChange;
    y += yChange;
    List<ITile> line = new ArrayList<>();
    while (x < this.boardSize && x >= 0 && y < this.boardSize && y >= 0) {
      line.add(this.findTile(new SquarePosn(x, y)));
      x += xChange;
      y += yChange;
    }
    return line;
  }
}
