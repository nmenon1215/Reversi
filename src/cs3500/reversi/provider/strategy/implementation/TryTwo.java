package cs3500.reversi.provider.strategy.implementation;

import cs3500.reversi.provider.model.Player;
import cs3500.reversi.provider.model.ReadOnlyReversiModel;
import cs3500.reversi.provider.strategy.Coord;
import cs3500.reversi.provider.strategy.Strategy;


/**
 * A strategy to a game of reversi that can combine two are more reversi game strategies.
 */
public class TryTwo extends ReversiStrategy {

  Strategy first;
  Strategy second;

  public TryTwo(Strategy first, Strategy second) {
    this.first = first;
    this.second = second;
  }

  /**
   * Chooses the coordinate that the player should place their piece at based on the first
   * strategy. If this returns null, the piece will then be chosen based off the second strategy.
   * @param model the model of a reversi game.
   * @param player the player placing a piece.
   * @return the coordinate that the player should move to based on the strategies given.
   * @throws IllegalArgumentException if it is not the player's turn.
   */
  @Override
  public Coord chooseMove(ReadOnlyReversiModel model, Player player) {
    if (!model.getPlayerTurn().toString().equals(player.toString())) {
      throw new IllegalArgumentException("not player turn");
    }
    Coord ans = this.first.chooseMove(model, player);
    if (ans != null) {
      return ans;
    }
    else {
      return second.chooseMove(model, player);
    }
  }

}
