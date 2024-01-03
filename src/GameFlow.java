import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
class GameFlow {
    public Chessboard board;
    public Player whitePlayer;
    public Player blackPlayer;
    public Player currentPlayer;
    private String gameRecord;
    private int countMoves = 0;
    private boolean isWhiteTurn = true;

    public GameFlow(Player whitePlayer, Player blackPlayer, Chessboard board) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.board = board;
        this.currentPlayer = whitePlayer;
        this.gameRecord = "";
        board.FillInChessBoard();
    }
    public void start() {
        while (!isGameOver()) {
            board.PrintChessBoard();
            boolean capture = false;
            if (currentPlayer.isWhite()) {
                System.out.println("Current player: " + whitePlayer.getName());
                isWhiteTurn = true;
            } else {
                System.out.println("Current player: " + blackPlayer.getName());
                isWhiteTurn = false;
            }

            String move = currentPlayer.getMove();
            int[] source = convertNotationToCoordinate(move.split(" ")[0]);
            int[] destination = convertNotationToCoordinate(move.split(" ")[1]);

            Piece piece = board.getPiece(source);

            if (piece.getPieceType() && piece.isWhite == currentPlayer.isWhite() && piece.canMove(destination, board.getBoard())) {
                if (board.getPiece(destination) != null) {
                    board.getPiece(destination).setKilled(true);
                    capture = true;
                }
                board.movePiece(source, destination);
            } else {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;


            if (isWhiteTurn) {
                countMoves++;
                gameRecord += countMoves + ". ";
            }
            move = convertToAlgebraic(move, capture, piece);
            gameRecord += move;
            if (!isWhiteTurn) {
                gameRecord += "\n";
                saveGame("chessgame.txt");
                gameRecord = "";
            }
        }
    }
    public boolean isGameOver() {
        return false;
        //TODO: implement later
        //check if the input is not quit or draw or restart
        //check if it's not a checkmate
        //check if it's not a stalemate
        //saveGame("chessgame.txt");
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

        String pieceType = piece.name;
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

class Player {
    private final String name;
    private final char color; //w - white, b - black

    public Player(String name, char color){
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public boolean isWhite(){
        return color == 'w';
    }

    public String getMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Your move: ");
        return scanner.nextLine();
    }
}