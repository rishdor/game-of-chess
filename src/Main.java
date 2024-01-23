import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the chess game!");
        System.out.println("To move a piece, type the position of the piece and the position you want to move it to (e.g. A2 A4)");
        System.out.println("To do castling, type the position of the king and the position of the rook (e.g. E1 H1)");
        System.out.println("To quit the game, type 'quit'");
        System.out.println("To restart the game, type 'restart'");
        System.out.println("To declare a draw, type 'draw'");
        System.out.print("Give the name of the first player (w): ");
        Scanner scanner = new Scanner(System.in);
        String name1 = scanner.nextLine();
        System.out.print("Give the name of the second player (b): ");
        String name2 = scanner.nextLine();
        // Create the players
        Player player1 = new Player(name1, 'w');
        Player player2 = new Player(name2, 'b');
        // Create the game
        GameFlow game = new GameFlow(player1, player2, new Chessboard());
        // Start the game
        game.start();
    }
}

//link do symboli: https://en.wikipedia.org/wiki/Miscellaneous_Symbols