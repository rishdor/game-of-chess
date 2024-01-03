import java.util.Objects;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class GameFlow {
    public Chessboard board;
    public Player whitePlayer;
    public Player blackPlayer;
    public Player currentPlayer;
    private String gameRecord;
    private int countMoves = 0;

    public GameFlow(Player whitePlayer, Player blackPlayer, Chessboard board) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.board = board;
        this.currentPlayer = whitePlayer;
        this.gameRecord = "";
    }
    String move = "";
    public void start() {
        board.FillInChessBoard();
        while (!isGameOver(move)) {
            board.PrintChessBoard();
            boolean capture = false;
            boolean isWhiteTurn;
            if (currentPlayer.isWhite()) {
                System.out.println("Current player: " + whitePlayer.getName());
                isWhiteTurn = true;
            } else {
                System.out.println("Current player: " + blackPlayer.getName());
                isWhiteTurn = false;
            }

            move = currentPlayer.getMove();

            Pattern p = Pattern.compile("[A-H][1-8] [A-H][1-8]");
            Matcher m = p.matcher(move);

            if (m.matches()){
                int[] source = convertNotationToCoordinate(move.split(" ")[0]);
                int[] destination = convertNotationToCoordinate(move.split(" ")[1]);

                Piece piece = board.getPiece(source);

                if (piece.getPieceType() && piece.isWhite == currentPlayer.isWhite() && piece.canMove(destination, board.getBoard())) {
                    if (board.getPiece(destination).getPieceType()) {
                        board.getPiece(destination).setKilled(true);
                        capture = true;
                    }
                    board.movePiece(source, destination);
                } else if (!piece.getPieceType()) {
                    System.out.println("No piece there. Try again.");
                    continue;
                }
                else if (piece.isWhite != currentPlayer.isWhite()) {
                    System.out.println("That's not your piece. Try again.");
                    continue;
                }
                else if (!piece.canMove(destination, board.getBoard())) {
                    System.out.println("You can't move there. Try again.");
                    continue;
                }
                else {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }

                currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;

                if (isWhiteTurn) {
                    countMoves++;
                    gameRecord += countMoves + ". ";
                }
                move = convertToAlgebraic(move, capture, piece);
                gameRecord += move + " ";
                if (!isWhiteTurn) {
                    gameRecord += "\n";
                    saveGame("chessgame.txt");
                    gameRecord = "";
                }
            }
        }
        if (Objects.equals(move, "restart")){
            move = "";
            start();
        }
    }
    public boolean isGameOver(String move) {
        if (countMoves == 50) {
            System.out.println("Draw by 50 moves rule.");
            return true;
        }
        else if (Objects.equals(move, "quit")){
            System.out.println("Game over.");
            return true;
        }
        else if (Objects.equals(move, "draw")){
            System.out.println("Draw.");
            return true;
        }
        else if (Objects.equals(move, "restart")){
            System.out.println("Restarting the game.");
            return true;
        }
        return false;
        //TODO: implement later
        //check if it's not a checkmate
        //check if it's not a stalemate
    }

    public static int[] convertNotationToCoordinate(String input) {
        int[] coordinate = new int[2];
        coordinate[1] = input.charAt(0) - 'A';
        coordinate[0] = Character.getNumericValue(input.charAt(1)) - 1;
        return coordinate;
    }

    public static String convertToAlgebraic(String move, boolean capture, Piece piece) {
        String[] parts = move.split(" ");
        String from = parts[0];
        String to = parts[1];

        String pieceType = piece.getName();

        if (pieceType.equals("P")) {
            pieceType = "";
        }
        if (capture) {
            if (pieceType.isEmpty()) {
                pieceType = from.charAt(0) + "x";
            }
            else{
                pieceType += "x";
            }
        }
        return pieceType + to.toLowerCase();
    }

    public void saveGame(String filename) {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(gameRecord);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the game.");
            e.printStackTrace();
        }
    }
}

