# Reversi
## Overview
This code creates a hexagonal version of the game Reversi/Othello. The main problem being addressed is creating a model that allows players to make moves, flip opponent pieces, and determine the game's outcome. The code assumes the users are already familiar with the rules of Reversi and a hexagonal grid system. Looking ahead, this code is designed to accommodate the potential for square tiles and multiple players with some changes to the existing code. 

## Quick Start
// sets up the players and the game  
Player p1 = new User('X');  
Player p2 = new User('O');  
MutableReversiModel model = new HexagonalReversiModel(p1, p2);   
   
// starts moving pieces to valid board spaces    
model.placePiece(p1, new HexagonalPosn(2, -1, -1));   
model.placePiece(p2, new HexagonalPosn(1, -2, 1));   

## Key Components
The key components of our code are as follows:
The Model is based on the Tile, and Player interfaces. It is also somewhat tightly coupled with the Posn interface. The Model uses the Posn to find tiles and make new ones when returning copies. It uses Tiles to generate a board that represents the state of the game. It uses Players to determine who is making a move on the board.  
- The model determines game rules and ways to interact with the game. 
- The Tile and Posn together represent each piece of the gameboard and its state  
- The Player represents a user making a move on the game board.  


## Key Subcomponents
- The board(in the model) is a list of tiles which each contain where they are located and who currently owns them.
- Each tile contains a player who is occupying the tile, and a posn representing where the tile is located on the board.  
- Each Player contains a char representing its playing piece.  


## Source Organization
#### CS3500 package  
   ##### Model Package  
   Interfaces: ROReversiModel, MutableReversiModel, ITile, Posn, Player  
   Classes: HexagonalReversiModel, HexagonalTile, HexagonalPosn, User, AI  
   ##### View Package  
   Interface: TextualView  
   Class: ReversiTextualView  
#### Tests
Classes: ReversiExamples, TestModelImplementation, TestTileImplementation  

## Invariant

1. **Board Consistency/Size**:
   - The `board` field is a list of `ITile` objects, and this list represents the game board. The class enforces that the `board` field is always consistent with the specified `boardSize`. It ensures that the board contains the correct number of tiles based on the hexagonal grid structure and `boardSize`. This is ensured by stating that boardSize is final, and the board has no methods other than startGame which add or remove from the board. 
   - In the constructor that accepts a custom board size, the code enforces that the board size must be at least 2. This prevents the creation of invalid game boards with sizes less than 2.

2. **Number of Players**:
   - The `numPlayers` doesn't allow changing the number of players during the game.

3. **Player Skips**:
   - The `skipsInRow` field keeps track of consecutive player skips. The code enforces that if a player skips a turn, `skipsInRow` is incremented, and if both players consecutively skip their turns, the game is considered over (`isGameOver` returns `true`).

4. **Tile Flipping Rules**:
   - The code enforces rules related to tile flipping, ensuring that a tile can only be flipped if it is sandwiched between the player's tiles.

