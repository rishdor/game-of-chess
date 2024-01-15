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
    //    private String gameRecord;
    private int countMoves = 0;

    public GameFlow(Player whitePlayer, Player blackPlayer, Chessboard board) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.board = board;
//        this.gameRecord = "";
    }

    Command command;
    Command previousCommand;

    public void start() {
        board.FillInChessBoard();
        currentPlayer = whitePlayer;
        countMoves = 0;
        command = null;
        previousCommand = null;
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

                if (piece.getPieceType() && piece.isWhite == currentPlayer.isWhite() && piece.canMove(destination, board.getBoard())) {
                    if (board.getPiece(destination).getPieceType()) { //check if there is a piece on the destination and sets caoture to true
                        board.getPiece(destination).setKilled(true);
                        move.setCapture(true);
                        board.movePiece(source, destination);
                    }
                    else if (piece.getName().equals("P") && (destination[0] == 0 || destination[0] == 7)) { //check if it's a pawn promotion
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
                }else if (piece instanceof Pawn pawn && piece.isWhite == currentPlayer.isWhite()) { //check if its en passant
                    if (previousCommand instanceof Move previousMove && previousMove.getPiece().getName().equals("P")) {
                        Pawn piece2 = (Pawn)previousMove.getPiece();
                        if (piece2.hasMovedTwoSquares() && pawn.canEnPassant(piece2, destination, board)) {
                            board.enPassant(source,destination, pawn.position);
                        }
                    }
                }else if (!piece.getPieceType()) {
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
                currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
                previousCommand = command;

                if (isWhiteTurn) {
                    countMoves++;
                }

            } else if (command == null){
                System.out.println("Invalid move. Try again.");
            } else if (command instanceof RestartCommand) { //restart the game
                command.endTheGame();
                start();
            }
            else {
                command.endTheGame();
            }
        }
    }
    public boolean isGameOver(Command command){
        if (countMoves == 50) {
            System.out.println("Draw by 50 moves rule.");
            return true;
        } else if (command instanceof QuitCommand || command instanceof DrawCommand || command instanceof RestartCommand) {
            return true;
        }
        return false;
        //TODO: implement later
        //check if it's not a checkmate
        // check if it's not a stalemate
    }
}


