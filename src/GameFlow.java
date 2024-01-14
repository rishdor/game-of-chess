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
    Command command;
    Command previousCommand;
    public void start() {
        board.FillInChessBoard();
        while (!command.endTheGame()) {
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

            command = currentPlayer.getCommand(board);

        }
    }
//    public boolean isGameOver (Command command){
//        if (countMoves == 50) {
//            System.out.println("Draw by 50 moves rule.");
//            return true;
//        } else if (command instanceof QuitCommand) {
//            System.out.println("Game over.");
//            return true;
//        } else if (command instanceof DrawCommand) {
//            System.out.println("Draw.");
//            return true;
//        } else if (command instanceof RestartCommand) {
//            System.out.println("Restarting the game.");
//            return true;
//        }
//        return false;
//        //TODO: implement later
//        //check if it's not a checkmate
//        //check if it's not a stalemate
//    }
}

