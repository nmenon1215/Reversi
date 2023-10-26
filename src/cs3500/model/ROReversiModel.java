package cs3500.model;

/**
 * This is a representation of an immutable Reversi Model. The functions in this model are only
 * getters, so the Reversi Model cannot be changed. A Reversi Model is a model of the game Reversi
 * where players place pieces on a tiled board. A player can only place a piece if it flips
 * over another tile. A placement flips over all tiles that form a sandwich between the piece
 * flipped and another one of their pieces. For more precise descriptions, please check out the
 * wiki for Reversi or Othello.
 */
public interface ROReversiModel {
}
