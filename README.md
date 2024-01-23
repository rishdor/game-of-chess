# Chess Game

This is a text-based chess game implemented in Java. The game follows the standard rules of chess and runs in the terminal. It supports special chess rules such as en passant, castling, and pawn promotion. The game ends if a player is in checkmate, if a player resigns, or if the 50-move rule is invoked.

## Features

- **Move Tracking**: Players can make moves by entering the starting and ending coordinates of the move (e.g., "C2 C3").
- **Player Management**: The game keeps track of the current player and the number of moves made.
- **Game Termination**: The game ends if a player is in checkmate, if a player resigns, or if the 50-move rule is invoked.
- **Special Chess Rules**: Supports special chess rules such as en passant, castling, and pawn promotion.
- **Game State Detection**: Includes check, checkmate, and stalemate detection.
- **Game Saving**: The game can be saved to a text file for later review.

## Classes

The game is implemented using several classes:

- `Piece`: An abstract class that represents a chess piece. Each specific type of piece (Pawn, Rook, Knight, Bishop, Queen, King) is a subclass of `Piece`.
- `Player`: Represents a player. Each player has a name and a color (white or black).
- `Chessboard`: Represents the chessboard. The board is a 2D array of `Piece` objects.
- `GameFlow`: Controls the flow of the game. This class has methods to start the game, get the current player's move, and check if the game is over.
- `Move`: Represents a move made by a player. It contains information about the piece moved, the source and destination coordinates, and whether the move resulted in a capture, check, checkmate, promotion, castling, or draw offer.
- `Command`: An abstract class that represents a command. Each specific type of command (Move, RestartCommand, DrawCommand, QuitCommand) is a subclass of `Command`.

## Key Methods

Here are some of the key methods in the game:

- `start()`: Starts the game and controls the game loop.
- `isGameOver(Command command)`: Checks if the game is over based on the current state of the game and the most recent command.
- `convertNotationToCoordinate(String input)`: Converts a move in chess notation (like "C2") to a coordinate on the board.
- `convertToAlgebraic()`: Converts a move into algebraic notation.
- `saveGame()`: Saves the game moves to a text file.

## How to Run

To run the game, compile and run the `GameFlow` class in your Java environment. The game will start automatically.