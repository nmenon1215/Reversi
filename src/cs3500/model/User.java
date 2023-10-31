package cs3500.model;

import java.util.List;
import java.util.Objects;

public class User implements Player {

  private final char player;

  public User(char player){
    this.player = player;
  }


  String errormsg = "If you got this to run, the code compiles!";

  @Override
  public void placePiece(Posn p) {
    throw new RuntimeException(errormsg);
  }

  @Override
  public void skipTurn() {
    throw new RuntimeException(errormsg);
  }

  @Override
  public List<ITile> getPossibleMoves() {
    throw new RuntimeException(errormsg);
  }

  @Override
  public String toString() {
    throw new RuntimeException(errormsg);
  }

  @Override
  public boolean equals(Object obj) {
    if(!(obj instanceof User)) {
      return false;
    }
    User other = (User) obj;
    return this.hashCode() == other.hashCode();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.player);
  }
}
