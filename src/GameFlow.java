import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileWriter;
import java.util.*;

class GameFlow {
    public Chessboard board;
    public Chessboard checkBoard; //loaded when move did not remove check
    public Chessboard yourBoard; //loaded when you're back in check
    public Player whitePlayer;
    public Player blackPlayer;
    public Player currentPlayer;
    private List<String> moves = new ArrayList<>();
    private int countMoves = 0;
    public int checkCounter;    //first time the player is in check, the board is saved, so the board can be reverted, if the player does not remove check

    public GameFlow(Player whitePlayer, Player blackPlayer, Chessboard board) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.board = board;
        this.checkBoard = board;
        this.yourBoard = board;
        this.checkCounter = 0;
    }

    public int getCheckCount() {
        return checkCounter;
    }

    public void setCheckCount(int countCheck) {
        this.checkCounter = countCheck;
    }

    public Chessboard getCheckBoard() {
        return checkBoard;
    }

    public void setCheckBoard(Chessboard cBoard) {
        this.checkBoard = cBoard;
    }

    public Chessboard getCBoard() {
        return board;
    }
    public void setCBoard(Chessboard cBoard) {
        this.board = cBoard;
    }

    public Chessboard getYourBoard() {
        return yourBoard;
    }

    public void setYourBoard(Chessboard oBoard) {
        this.yourBoard = oBoard;
    }

    Command command;
    Move previousMove;
    public void start() {
        // Initialize the chessboard
        board.FillInChessBoard();
        // Initialize the checkBoard which is used when a player is in check
        checkBoard.FillInChessBoard();
        // Initialize yourBoard which is used to revert the board if a move does not remove check
        yourBoard.FillInChessBoard();
        currentPlayer = whitePlayer;
        // Reset the move counter
        countMoves = 0;
        // Reset the command
        command = null;
        // Reset the previous move
        previousMove = null;
        // Start the game loop
        while (!isGameOver(command)) {
            // Print the current state of the chessboard
            board.PrintChessBoard();
            boolean isWhiteTurn;
            // Check if the current player is white
            if (currentPlayer.isWhite()) {
                System.out.println("Current player: " + whitePlayer.getName());
                isWhiteTurn = true;
            } else {
                System.out.println("Current player: " + blackPlayer.getName());
                isWhiteTurn = false;
            }
            // Get the command from the current player
            command = currentPlayer.getCommand(board);
            // Check if the command is a move
            if (command instanceof Move move) {
                // Get the piece to be moved
                Piece piece = move.getPiece();
                // Get the source coordinates of the move
                int[] source = move.getSource();
                // Get the destination coordinates of the move
                int[] destination = move.getDestination();

                setYourBoard(board.clone());

                // Check if the piece is valid and belongs to the current player and can move to the destination
                if (piece.getPieceType() && piece.isWhite == currentPlayer.isWhite() && piece.canMove(destination, board.getBoard())) {
                    // Check if there is a piece on the destination
                    if (board.getPiece(destination).getPieceType()) {
                        // Set the piece on the destination as killed
                        board.getPiece(destination).setKilled(true);
                        // Set capture to true
                        move.setCapture(true);
                        // Move the piece to the destination
                        board.movePiece(source, destination);
                    }
                    // Check if it's a pawn promotion
                    else if (piece.getName().equals("P") && (destination[0] == 0 || destination[0] == 7)) {
                        // Set promotion to true
                        move.setPromotion(true);
                        // Create a scanner to get user input
                        Scanner scanner = new Scanner(System.in);
                        String input = "";
                        // Create a pattern to match the promotion piece
                        Pattern p = Pattern.compile("[qrbnQRBN]");
                        Matcher m = p.matcher(input);
                        // Loop until the user enters a valid promotion piece
                        while (!m.matches()) {
                            System.out.print("Promote to (Q/R/B/N): ");
                            input = scanner.nextLine();
                            m = p.matcher(input);
                        }
                        // Set the promotion piece
                        move.setPromotionPiece(Character.toUpperCase(input.charAt(0)));
                        // Create the new promotion piece at the destination
                        board.createNewPiece(input.charAt(0), piece.isWhite, destination);
                        // Remove the pawn from the source
                        board.createNewPiece(' ', false, source);
                    }
                    // If it's not a pawn promotion and there is no piece on the destination
                    else {
                        // Move the piece to the destination
                        board.movePiece(source, destination);
                    }
                } else if (piece instanceof King king && piece.isWhite == currentPlayer.isWhite() && king.canCastle(destination, board)) { // Check if the move is a castling move
                    board.castle(source, destination); // Perform the castling move
                    move.setIfCastling(true); // Set the castling flag to true
                }else if (piece instanceof Pawn pawn && piece.isWhite == currentPlayer.isWhite() && !board.getPiece(destination).getPieceType()) { // Check if the move is an en passant move
                    if (previousMove != null && previousMove.getPiece().getName().equals("P")) { // Check if the previous move was made by a pawn
                        Pawn piece2 = (Pawn)previousMove.getPiece(); // Get the pawn from the previous move
                        if (piece2.hasMovedTwoSquares() && pawn.canEnPassant(piece2, destination, board)) { // Check if the pawn moved two squares and can perform an en passant move
                            board.enPassant(source,destination, piece2.position); // Perform the en passant move
                        }
                    }
                } else if (!piece.getPieceType()) { // Check if there is no piece at the source
                    System.out.println("No piece there. Try again."); // Inform the user that there is no piece at the source
                    continue; // Skip the rest of the loop and start the next iteration
                } else if (piece.isWhite != currentPlayer.isWhite()) { // Check if the piece does not belong to the current player
                    System.out.println("That's not your piece. Try again."); // Inform the user that the piece does not belong to them
                    continue; // Skip the rest of the loop and start the next iteration
                } else if (!piece.canMove(destination, board.getBoard())) { // Check if the piece cannot move to the destination
                    System.out.println("You can't move there. Try again."); // Inform the user that the piece cannot move to the destination
                    continue; // Skip the rest of the loop and start the next iteration
                } else { // If none of the above conditions are met
                    System.out.println("Invalid move. Try again."); // Inform the user that the move is invalid
                    continue; // Skip the rest of the loop and start the next iteration
                }

                if (board.isCheckmate(currentPlayer.isWhite())) { // Check if the current player is in checkmate
                    System.out.println("Checkmate. " + currentPlayer.getName() + " wins!"); // Inform the user that the current player is in checkmate and has won the game
                    break; // End the game
                }
                else if (board.isCheck(currentPlayer.isWhite()) && getCheckCount()==0) { // Check if the current player is in check and it's the first check
                    setCBoard(getYourBoard().clone()); // Save the current state of the board
                    System.out.println("Check remains. Try again."); // Inform the user that the current player is still in check
                    move.setCheck(true); // Set the check flag to true
                    continue;
                }
                else if(board.isCheck(currentPlayer.isWhite()) && getCheckCount()>0) { // Check if the current player is in check and it's not the first check
                    setCBoard(getCheckBoard().clone()); // Revert the board to the state before the check
                    System.out.println("Check remains. Try again."); // Inform the user that the current player is still in check
                    move.setCheck(true); // Set the check flag to true
                    continue;
                }
                else if (getCheckCount() > 0) { // Check if the check count is greater than 0
                    setCheckCount(0); // Reset the check count
                    move.setCheck(false); // Set the check flag to false
                }

                // Switch the current player
                currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;

                // If it's not white's turn, record the move in algebraic notation and increment the move counter
                if (!isWhiteTurn) {
                    String algebraicMove = countMoves + 1 + ". "+ previousMove.convertToAlgebraic() + " " + move.convertToAlgebraic();
                    moves.add(algebraicMove);
                    countMoves++;
                }

                // Store the current move as the previous move for the next turn
                previousMove = move;

                // If the command is null, print an error message
            } else if (command == null){
                System.out.println("Invalid move. Try again.");
            // If the command is a restart command, end the game and start a new one
            } else if (command instanceof RestartCommand) { //restart the game
                command.endTheGame();
                start();
            // If the command is a draw command, offer a draw and end the game
            } else if (command instanceof DrawCommand) {
                previousMove.setIsDrawOffered(true);
                command.endTheGame();
            // If the current player is in checkmate, set the checkmate flag on the previous move
            } else if (board.isCheckmate(currentPlayer.isWhite())) {
                previousMove.setCheckmate(true);
            } else {
                command.endTheGame();
            }
        }

        System.out.println("Do you want to save the game? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (Objects.equals(input, "y")) {
            saveGame();
        }
        else {
            System.out.println("Game not saved.");
        }
    }
    /**
     * Checks if the game is over based on various conditions.
     */
    public boolean isGameOver(Command command){
        //Checks if the game is over based on 50 moves rule
        if (countMoves == 50) {
            System.out.println("Draw by 50 moves rule.");
            return true;
        }
        //Checks if the game is over based on commands (quit, draw, restart)
        else if(command instanceof QuitCommand || command instanceof DrawCommand || command instanceof RestartCommand) {
            return true;
        }
        //Checks if the game is over based on checkmate
        else if (board.isCheckmate(currentPlayer.isWhite())) {
            getCBoard().PrintChessBoard();
            System.out.println("Checkmate.");
            return true;
        }
        //Checks if last move results with check
        else if (board.isCheck(currentPlayer.isWhite())) {
            //if it's the first time the exact check occurs, the board is saved, so the board can be reverted, if the player does not remove check
            if (getCheckCount() == 0) {
                setCheckBoard(getCBoard().clone());
            }
            System.out.println("Check.");
            setCheckCount(getCheckCount() + 1);
            return false;
        }
        //Checks if the game is over based on stalemate
        else if (board.isStalemate(currentPlayer.isWhite())) {
            System.out.println("Stalemate.");
            return true;
        }
        //If none of the above conditions are met, the game is not over
        else
            return false;
    }

    /**
     * Saves the game to a file.
     */
    public void saveGame() {
        String gameData = String.join("\n", moves);
        try (FileWriter writer = new FileWriter("game.txt")) {
            writer.write(gameData);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the game.");
        }
    }
}



