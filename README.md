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

## Key Subcomponents

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
