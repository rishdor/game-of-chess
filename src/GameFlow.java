import java.util.Scanner;
class GameFlow {
    public Chessboard board;
    public Player whitePlayer;
    public Player blackPlayer;
    public Player currentPlayer;

    public GameFlow(Player whitePlayer, Player blackPlayer, Chessboard board) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.board = board;
        this.currentPlayer = whitePlayer;
        board.FillInChessBoard();
    }
    public void start() {
        while (!isGameOver()) {
            board.PrintChessBoard();
            System.out.println("Current player: " + (currentPlayer.isWhite()? whitePlayer.getName() : blackPlayer.getName()));

            String move = currentPlayer.getMove();
            int[] source = convertNotationToCoordinate(move.split(" ")[0]);
            int[] destination = convertNotationToCoordinate(move.split(" ")[1]);
            Piece piece = board.getPiece(source);
            if (piece.getPieceType() && piece.isWhite == currentPlayer.isWhite() && piece.canMove(destination, board.getBoard())) {
                if (board.getPiece(destination) != null) {
                    board.getPiece(destination).setKilled(true);
                }
                board.movePiece(source, destination);
            } else {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
        }
    }
    public boolean isGameOver() {
        return false;
        //TODO: implement later
        //check if the input is not quit or draw or restart
        //check if it's not a checkmate
        //check if it's not a stalemate
    }

    public static int[] convertNotationToCoordinate(String input) {
        int[] coordinate = new int[2];
        coordinate[0] = input.charAt(0) - 'A';
        coordinate[1] = Character.getNumericValue(input.charAt(1)) - 1;
        return coordinate;
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