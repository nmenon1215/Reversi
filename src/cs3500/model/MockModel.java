package cs3500.model;

import java.util.List;
import java.util.Objects;

import cs3500.player.Player;

/**
 * Records all calls made to the model.
 */
public class MockModel implements MutableReversiModel {

  MutableReversiModel model;

  public MockModel(MutableReversiModel model) {
    this.model = Objects.requireNonNull(model);
  }

  @Override
  public void placePiece(Player p, Posn posn) {
    model.placePiece(p, posn);
    System.out.println("Called placePiece with: " + "PLAYER: " + p + " Posn: " + posn.getCoords());
  }

  @Override
  public void skip(Player p) {
    model.skip(p);
    System.out.println("Called skip with: " + "PLAYER: " + p);

  }

  @Override
  public ITile getTileAt(Posn posn) {
    System.out.println("Called getTileAt with: " + " Posn: " + posn);
    return model.getTileAt(posn);
  }

  @Override
  public List<ITile> getSurroundingTiles(Posn posn) {
    System.out.println("Called getSurroundingTiles with: " + " Posn: " + posn);
    return model.getSurroundingTiles(posn);
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
}
