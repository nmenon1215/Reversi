package model;

/**
 * Represents the type of players that can occupy a cell in a game of reversi.
 */
public enum PlayerEnum implements Player {

  X("X"), O("O"), Empty("_");

  private final String disp;

  PlayerEnum(String disp) {
    this.disp = disp;
  }

  /**
   * the string representation of a player.
   * @return the string representation of a player.
   */
  @Override
  public String toString() {
    return disp;
  }

}
