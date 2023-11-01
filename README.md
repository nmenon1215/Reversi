# Reversi
## Overview
Designed a model to represent the game board for Reversi. This consists of interfaces and concrete classes.

## Quick Start
// sets up the players and the game
Player p1 = new User('X');
Player p2 = new User('O');
MutableReversiModel model = new HexagonalReversiModel(p1, p2);
// starts moving pieces to valid board spaces
model.placePiece(p1, new HexagonalPosn(2, -1, -1));
model.placePiece(p2, new HexagonalPosn(1, -2, 1));

## Key Components

## Key Subcomponents

## Source Organization
