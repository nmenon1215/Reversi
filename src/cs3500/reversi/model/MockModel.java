package cs3500.reversi.model;

import java.util.List;
import java.util.Objects;

import cs3500.reversi.controller.Controller;
import cs3500.reversi.player.Player;

/**
 * Records all calls made to the model.
 */
public class MockModel implements MutableReversiModel {

  private MutableReversiModel model;
  private Appendable appendable;

  /**
   * Creates a mock of the model.
   * @param model the model being mocked
   */
  public MockModel(MutableReversiModel model, Appendable appendable) {
    this.model = Objects.requireNonNull(model);
    this.appendable = Objects.requireNonNull(appendable);
  }

  @Override
  public void placePiece(Player p, Posn posn) {
    try {
      appendable.append("placePiece(" + p + ", " + posn + ")\n");
    }
    catch (Exception e) {
      throw new RuntimeException("Mock failed");
    }
    this.model.placePiece(p, posn);
  }

  @Override
  public void skip(Player p) {
    try {
      appendable.append("skip(" + p + ")\n");
    }
    catch (Exception e) {
      throw new RuntimeException("Mock failed");
    }
    this.model.skip(p);
  }

  @Override
  public void subscribe(Controller controller, Player p) {
    try {
      appendable.append("subscribe(" + controller + ", " + p + ")\n");
    }
    catch (Exception e) {
      throw new RuntimeException("Mock failed");
    }
    this.model.subscribe(controller, p);
  }

  @Override
  public ITile getTileAt(Posn posn) {
    try {
      appendable.append("placePiece(" + posn + ")\n");
    }
    catch (Exception e) {
      throw new RuntimeException("Mock failed");
    }
    return this.model.getTileAt(posn);
  }

  @Override
  public List<ITile> getSurroundingTiles(Posn posn) {
    try {
      appendable.append("getSurroundingTiles(" + posn + ")\n");
    }
    catch (Exception e) {
      throw new RuntimeException("Mock failed");
    }
    return this.model.getSurroundingTiles(posn);
  }

  @Override
  public boolean isGameOver() {
    System.out.println("Called isGameOver");
    return model.isGameOver();
  }

  @Override
  public List<ITile> possibleMoves(Player p) {
    System.out.println("Called possibleMoves with: " + "PLAYER: " + p);
    return model.possibleMoves(p);
  }

  @Override
  public boolean isLegalMove(Player p, Posn posn) {
    System.out.println("Called isLegalMove with: " + "PLAYER: " + p + " Posn: " + posn);
    return model.isLegalMove(p, posn);
  }

  @Override
  public boolean hasLegalMoves(Player p) {
    System.out.println("Called hasLegalMoves with: " + "PLAYER: " + p);
    return model.hasLegalMoves(p);
  }

  @Override
  public int getScore(Player p) {
    System.out.println("Called getScore with: " + "PLAYER: " + p);
    return model.getScore(p);
  }

  @Override
  public int getBoardSize() {
    System.out.println("Called getBoardSize");
    return model.getBoardSize();
  }

  @Override
  public Player getTurn() {
    System.out.println("Called getTurn");
    return model.getTurn();
  }

  @Override
  public List<ITile> getBoard() {
    System.out.println("Called getBoard");
    return model.getBoard();
  }

  @Override
  public List<Player> getPlayers() {
    System.out.println("Called getPlayers");
    return model.getPlayers();
  }

  @Override
  public int getTurnIndex() {
    System.out.println("Called getTurnIndex");
    return model.getTurnIndex();
  }

  @Override
  public int getSkipsInARow() {
    System.out.println("Called getSkipsInARow");
    return model.getSkipsInARow();
  }

  @Override
  public List<Posn> getCorners() {
    System.out.println("Called getCorners");
    return model.getCorners();
  }

  @Override
  public int countPiecesGained(Player player, Posn posn) {
    return 0;
  }

  /**
   * Gets the appendable for testing.
   * @return the Appendable
   */
  public Appendable getAppendable() {
    return this.appendable;
  }
}
