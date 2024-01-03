import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Give the name of the first player (w): ");
        Scanner scanner = new Scanner(System.in);
        String name1 = scanner.nextLine();
        System.out.println("Give the name of the second player (b): ");
        String name2 = scanner.nextLine();
        Player player1 = new Player(name1, 'w');
        Player player2 = new Player(name2, 'b');
        GameFlow game = new GameFlow(player1, player2, new Chessboard());
        game.start();

        //TESTS
//        int[] piece1 = GetPosition();
//        int[] piece2 = GetPosition();
//        System.out.println(cb.IsItFriend(piece1, piece2));
//
//        if(cb.tab[piece1[0]][piece1[1]] == '\u2656' || cb.tab[piece1[0]][piece1[1]] == '\u265C'){
//            if(cb.Rook(piece1, piece2)) System.out.println("Rook can move there");
//            else System.out.println("Rook can't move there");
//        }
//        else if(cb.tab[piece1[0]][piece1[1]] == '\u2658' || cb.tab[piece1[0]][piece1[1]] == '\u265E'){
//            if(cb.Knight(piece1, piece2)) System.out.println("Knight can move there");
//            else System.out.println("Knight can't move there");
//        }
//        else if (cb.tab[piece1[0]][piece1[1]] == '\u2657' || cb.tab[piece1[0]][piece1[1]] == '\u265D'){
//            if(cb.Bishop(piece1, piece2)) System.out.println("Bishop can move there");
//            else System.out.println("Bishop can't move there");
//        }
//        else if (cb.tab[piece1[0]][piece1[1]] == '\u2655' || cb.tab[piece1[0]][piece1[1]] == '\u265B'){
//            if(cb.Queen(piece1, piece2)) System.out.println("Queen can move there");
//            else System.out.println("Queen can't move there");
//        }
//        else if (cb.tab[piece1[0]][piece1[1]] == '\u2654' || cb.tab[piece1[0]][piece1[1]] == '\u265A'){
//            if(cb.King(piece1, piece2)) System.out.println("King can move there");
//            else System.out.println("King can't move there");
//        }
//        else if (cb.tab[piece1[0]][piece1[1]] == '\u2659' || cb.tab[piece1[0]][piece1[1]] == '\u265F'){
//            if(cb.Pawn(piece1, piece2)) System.out.println("Pawn can move there");
//            else System.out.println("Pawn can't move there");
//        }
//        else System.out.println("There is no piece on this position");

    }
//    public static int[] GetPosition(){
//        System.out.println("Give a position: ");
//        Scanner scanner = new Scanner(System.in);
//        int[] position = new int[2];
//        position[0] = scanner.nextInt();
//        position[1] = scanner.nextInt();
//        return position;
//    }
}

//link do symboli: https://en.wikipedia.org/wiki/Miscellaneous_Symbols