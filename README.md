# Chess Game

This is a simple text-based chess game that runs in the terminal. The game is implemented in Java and follows the standard rules of chess.

## Features

- Players can make moves by entering the starting and ending coordinates of the move (e.g., "C2 C3").
- Keeps track of the current player and the number of moves made.
- The game ends if a player is in checkmate, if a player resigns, or if the 50-move rule is invoked.
- Supports special chess rules such as en passant, castling, and pawn promotion.
- Includes check, checkmate, and stalemate detection.

## Classes

The game is implemented using several classes:

- `Piece`: An abstract class that represents a chess piece. Each specific type of piece (Pawn, Rook, Knight, Bishop, Queen, King) is a subclass of `Piece`.
- `Player`: Represents a player. Each player has a name and a color (white or black).
- `Chessboard`: Represents the chessboard. The board is a 2D array of `Piece` objects.
- `Game`: Controls the flow of the game. This class has methods to start the game, get the current player's move, and check if the game is over.

## Methods

Here are some of the key methods in the game:

- `start()`: Starts the game and controls the game loop.
- `isGameOver(String move)`: Checks if the game is over based on the current state of the game and the most recent move.
- `convertNotationToCoordinate(String input)`: Converts a move in chess notation (like "C2") to a coordinate on the board.
- `convertToAlgebraic(String move, boolean capture, Piece piece)`: Converts a move and a capture event into algebraic notation.

## How to Run

To run the game, compile and run the `Game` class in your Java environment. The game will start automatically.
