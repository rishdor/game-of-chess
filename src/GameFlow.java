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
        board.FillInChessBoard();
        checkBoard.FillInChessBoard();
        yourBoard.FillInChessBoard();
        currentPlayer = whitePlayer;
        countMoves = 0;
        command = null;
        previousMove = null;
        while (!isGameOver(command)) {
            board.PrintChessBoard();
            boolean isWhiteTurn;
            if (currentPlayer.isWhite()) {
                System.out.println("Current player: " + whitePlayer.getName());
                isWhiteTurn = true;
            } else {
                System.out.println("Current player: " + blackPlayer.getName());
                isWhiteTurn = false;
            }
            command = currentPlayer.getCommand(board);
            if (command instanceof Move move) {
                Piece piece = move.getPiece();
                int[] source = move.getSource();
                int[] destination = move.getDestination();

                setYourBoard(board.clone());

                if (piece.getPieceType() && piece.isWhite == currentPlayer.isWhite() && piece.canMove(destination, board.getBoard())) {
                    if (board.getPiece(destination).getPieceType()) { //check if there is a piece on the destination and sets capture to true
                        board.getPiece(destination).setKilled(true);
                        move.setCapture(true);
                        board.movePiece(source, destination);
                    } else if (piece.getName().equals("P") && (destination[0] == 0 || destination[0] == 7)) { //check if it's a pawn promotion
                        move.setPromotion(true);
                        Scanner scanner = new Scanner(System.in);
                        String input = "";
                        Pattern p = Pattern.compile("[qrbnQRBN]");
                        Matcher m = p.matcher(input);
                        while (!m.matches()) {
                            System.out.print("Promote to (Q/R/B/N): ");
                            input = scanner.nextLine();
                            m = p.matcher(input);
                        }
                        move.setPromotionPiece(Character.toUpperCase(input.charAt(0)));
                        board.createNewPiece(input.charAt(0), piece.isWhite, destination);
                        board.createNewPiece(' ', false, source);
                    }
                    else{ //if it's not a pawn promotion and there is no piece on the destination
                        board.movePiece(source, destination);
                    }
                } else if (piece instanceof King king && piece.isWhite == currentPlayer.isWhite() && king.canCastle(destination, board)) { //check if it's a castling
                    board.castle(source, destination);
                    move.setIfCastling(true);
                }else if (piece instanceof Pawn pawn && piece.isWhite == currentPlayer.isWhite() && !board.getPiece(destination).getPieceType()) { //check if its en passant
                    if (previousMove != null && previousMove.getPiece().getName().equals("P")) {
                        Pawn piece2 = (Pawn)previousMove.getPiece();
                        if (piece2.hasMovedTwoSquares() && pawn.canEnPassant(piece2, destination, board)) {
                            board.enPassant(source,destination, piece2.position);
                        }
                    }
                } else if (!piece.getPieceType()) {
                    System.out.println("No piece there. Try again.");
                    continue;
                } else if (piece.isWhite != currentPlayer.isWhite()) {
                    System.out.println("That's not your piece. Try again.");
                    continue;
                } else if (!piece.canMove(destination, board.getBoard())) {
                    System.out.println("You can't move there. Try again.");
                    continue;
                } else {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }

                if (board.isCheckmate(currentPlayer.isWhite())) {
                    System.out.println("Checkmate. " + currentPlayer.getName() + " wins!");
                    break;
                }
                else if (board.isCheck(currentPlayer.isWhite()) && getCheckCount()==0) {
                    setCBoard(getYourBoard().clone());
                    System.out.println("Check remains. Try again.");
                    move.setCheck(true);
                    continue;
                }
                else if(board.isCheck(currentPlayer.isWhite()) && getCheckCount()>0) {//check if current move removed check
                    //"undo" the move -> revert the board
                    setCBoard(getCheckBoard().clone());
                    System.out.println("Check remains. Try again.");
                    move.setCheck(true);
                    continue;
                }
                else if (getCheckCount() > 0) {
                    setCheckCount(0);
                    move.setCheck(false);
                }

                currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;

                if (!isWhiteTurn) {
                    String algebraicMove = countMoves + 1 + ". "+ previousMove.convertToAlgebraic() + " " + move.convertToAlgebraic();
                    moves.add(algebraicMove);
                    countMoves++;
                }

                previousMove = move;

            } else if (command == null){
                System.out.println("Invalid move. Try again.");
            } else if (command instanceof RestartCommand) { //restart the game
                command.endTheGame();
                start();
            }
            else if (command instanceof DrawCommand) {
                previousMove.setIsDrawOffered(true);
                command.endTheGame();
            }
            else if (board.isCheckmate(currentPlayer.isWhite())) {
                previousMove.setCheckmate(true);
            }
            else {
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
    public boolean isGameOver(Command command){
        if (countMoves == 50) {
            System.out.println("Draw by 50 moves rule.");
            return true;
        }
        else if(command instanceof QuitCommand || command instanceof DrawCommand || command instanceof RestartCommand) {
            return true;
        }
        else if (board.isCheckmate(currentPlayer.isWhite())) {
            getCBoard().PrintChessBoard();
            System.out.println("Checkmate.");
            return true;
        }
        else if (board.isCheck(currentPlayer.isWhite())) {
            if (getCheckCount() == 0) {
                setCheckBoard(getCBoard().clone());
            }
            System.out.println("Check.");
            setCheckCount(getCheckCount() + 1);
            return false;
        }
        else if (board.isStalemate(currentPlayer.isWhite())) {
            System.out.println("Stalemate.");
            return true;
        }
        else
            return false;
    }
    public void saveGame() {
        String gameData = String.join("\n", moves);
        try (FileWriter writer = new FileWriter("game.txt")) {
            writer.write(gameData);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the game.");
        }
    }
}



