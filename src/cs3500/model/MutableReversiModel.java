package cs3500.model;

/**
 * This is a representation of a mutable Reversi Model. A Reversi Model is a model of the game Reversi
 * where players place pieces on a tiled board. A player can only place a piece if it flips
 * over another tile. A placement flips over all tiles that form a sandwich between the piece
 * flipped and another one of their pieces. For more precise descriptions, please check out the
 * wiki for Reversi or Othello.
 */
public interface MutableReversiModel extends ROReversiModel{
}
