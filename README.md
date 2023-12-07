# Reversi

## Overview

This code creates a hexagonal version of the game Reversi/Othello. The main problem being addressed
is creating a model that allows players to make moves, flip opponent pieces, and determine the
game's outcome. The code assumes the users are already familiar with the rules of Reversi and a
hexagonal grid system. Looking ahead, this code is designed to accommodate the potential for square
tiles and multiple players with some changes to the existing code.

## Quick Start

TO RUN THE PROGRAM FROM JAR:  
For two player game: java -jar Reversi.jar human human  
For single player: replace the second human with either (strategy1, strategy2, strategy3) ex: java -jar Reversi.jar human strategy1  

// sets up the players and the game  
Player p1 = new User('X');  
Player p2 = new User('O');  
MutableReversiModel model = new HexagonalReversiModel(p1, p2);

// starts moving pieces to valid board spaces    
model.placePiece(p1, new HexagonalPosn(2, -1, -1));   
model.placePiece(p2, new HexagonalPosn(1, -2, 1));

## Key Components

The key components of our code are as follows:
The Model is based on the Tile and Player interfaces. It is also somewhat tightly coupled with the
Posn interface. The Model uses the Posn to find tiles and make new ones when returning copies. It
uses Tiles to generate a board that represents the state of the game. It uses Players to determine
who is making a move on the board.

- The model determines game rules and ways to interact with the game.
- The Tile and Posn together represent each piece of the gameboard and its state
- The Player represents a user making a move on the game board.
  The View is based on the JFrame and JPanel Interfaces. It also uses ActionListener and
  KeyListener. The JPanel is used as a holder of buttons that are then placed onto the JFrame. This
  then generates the view of the hexagonal grid that is made up of JButtons.
- The view displays the rules enforced by the model by using Java Swing.

## Key Subcomponents

- The board(in the model) is a list of tiles which each contain where they are located and who
  currently owns them.
- Each tile contains a player who is occupying the tile, and a posn representing where the tile is
  located on the board.
- Each Player contains a char representing its playing piece.
- Each AI contains its playing piece and a list of strategies for it to use.
- Each JButton contains whether it is clicked to determine if it should be highlighted.

## Source Organization

### CS3500 package

#### Model Package

Interfaces: ROReversiModel, MutableReversiModel, ITile, Posn  
Classes: HexagonalReversiModel, HexagonalTile, HexagonalPosn, MockModel

#### Controller Package

Interface: ReversiController  
Class: Controller  

#### View Package

Interfaces: Player, Strategy  
Classes: User, AI, Minimax, AvoidCellsNextToCorners, CaptureMaxPieces, PlaceAtCorners

##### Gui Package

Interface: ReversiView  
Class: JReversiPanel, JFrameReversiView, HexagonalButton, MockView  

##### Textual Package

Interface: TextualView  
Class: ReversiTextualView  

#### Reversi Package

Class: Reversi

#### Tests

Classes: ReversiExamples, TestModelImplementation, TestTileImplementation, TestPlayerImplementation, TestControllerImplementation

## Roles on The Team

Nikhil: PLANNING, model logic (Model Class), player strategies, displaying cells, controller logic, some README  
Simon: PLANNING, some model logic (Supplemental Classes), model tests, view, key clicks, mouse clicks, mocks, controller tests, most README  

## Changes Made To Model

1. We now keep track of whose turn it is
2. Change constructor to accept List<Player>() instead of Player
3. Use playerTurn which cycles through list (added private method nextPlayer();)
4. Updated skip to account for player turn
5. Updated placePiece() to account for player turn
6. New Getters (getCurrentPlayer(), getBoardIf(Player p, Posn posn), isLegalMove(Player p, Posn
   posn), hasLegalMoves(Player p), getSurroundingTiles())

By keeping track of whose turn it is, we eliminate the possibility of one player placing pieces
repeatedly until they win the game. Changes 1-5 helped account for that. The new getters are there
to help enforce players turn and for the strategies.

## Invariant

1. **Board Consistency/Size**:
    - The `board` field is a list of `ITile` objects, and this list represents the game board. The
      class enforces that the `board` field is always consistent with the specified `boardSize`. It
      ensures that the board contains the correct number of tiles based on the hexagonal grid
      structure and `boardSize`. This is ensured by stating that boardSize is final, and the board
      has no methods other than startGame which add or remove from the board.
    - In the constructor that accepts a custom board size, the code enforces that the board size
      must be at least 2. This prevents the creation of invalid game boards with sizes less than 2.

2. **Number of Players**:
    - The `numPlayers` doesn't allow changing the number of players during the game.

3. **Player Skips**:
    - The `skipsInRow` field keeps track of consecutive player skips. The code enforces that if a
      player skips a turn, `skipsInRow` is incremented, and if both players consecutively skip their
      turns, the game is considered over (`isGameOver` returns `true`).

4. **Tile Flipping Rules**:
    - The code enforces rules related to tile flipping, ensuring that a tile can only be flipped if
      it is sandwiched between the player's tiles.

## Part 3

### Control Flow
Quick runthrough: When a game is started, 2 controllers and views are created. Each controller  
subscribes to the model to listen for when it is their turn to make a move. The model connects  
each player to its controller and then when switching turns, it tells the next player to notify  
its controller that it is their turn.

Once a game is started, the Controller tells the view that it is the main screen. The controller  
then waits for a key event to occur. When the key event occurs, it triggers the key event in the  
controller. The controller sends this information to a function called make move. It then asks the  
player to identify its move. AI will analyze the model, and User will analyze the view to see what  
button is pressed. The controller will then prompt the model to make the move. If the move is  
successful, the model will move turn to the next player and notify the player that it is their turn.  
The player will then notify the controller. If the player is AI, it will automatically call placePiece  
once again, but if it is a User, it will declare its view as the main view.

## Changes For Part 3

  1. Fixed the view in part 2:  
     a. made buttons clickable with highlighting  
     b. fixed spacing of buttons  
     c. made key events  
  2. added function to model so controller could subscribe.  
  3. Deleted functions from player such as getPlayableTiles()
  4. Updated Player functionality to automatically tell controller to place piece or tell controller
     to wait for key action.

## Class/Interface Additions

  1. ReversiController- Moderates the connection between a player, its view, and the joint model.  
  2. Controller- Holds the code for the controller to serve as a pipeline from the model to view.  
  3. MockView- Adds called methods to an Appendable to allow for testing.  
  4. TestControllerImplementations- Holds the tests for the controller to check the connection.

## Changes For Part 4
  - Removed the mouseListener from the Controller and moved it to the view
  - Modularized our controller and view
  - Implemented a features interface
  - Added the Provider's view and made it compliant with our controller and model
  - To place a piece, press 'm'
  - To skip a turn, press 'p'
  - Skip is only supported when the player cannot make any other move (Design choice)
  - We added a makeTitle to create titles for the windows

#### Working vs. Non-working Features
  - All of the features and strategies are working
  - The different strategies are easy, medium, and hard with the way they stack
