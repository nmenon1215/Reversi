# Reversi
## Overview
Designed a model to represent the game board for Reversi. This consists of interfaces and concrete classes.

## Quick Start
// sets up the players and the game \n
Player p1 = new User('X'); \n
Player p2 = new User('O'); \n
MutableReversiModel model = new HexagonalReversiModel(p1, p2); \n
// starts moving pieces to valid board spaces \n
model.placePiece(p1, new HexagonalPosn(2, -1, -1)); \n
model.placePiece(p2, new HexagonalPosn(1, -2, 1)); \n

## Key Components

## Key Subcomponents

## Source Organization
