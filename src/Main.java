import com.sun.source.doctree.IndexTree;

import java.text.ParsePosition;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Szachownica cb = new Szachownica();

        cb.FillInChessBoard();
        cb.PrintChessBoard();

        //TESTS
        int[] piece1 = GetPosition();
        int[] piece2 = GetPosition();
        System.out.println(cb.IsItFriend(piece1, piece2));

        if(cb.tab[piece1[0]][piece1[1]] == '\u2656' || cb.tab[piece1[0]][piece1[1]] == '\u265C'){
            if(cb.Rook(piece1, piece2)) System.out.println("Rook can move there");
            else System.out.println("Rook can't move there");
        }
        else if(cb.tab[piece1[0]][piece1[1]] == '\u2658' || cb.tab[piece1[0]][piece1[1]] == '\u265E'){
            if(cb.Knight(piece1, piece2)) System.out.println("Knight can move there");
            else System.out.println("Knight can't move there");
        }else if (cb.tab[piece1[0]][piece1[1]] == '\u2657' || cb.tab[piece1[0]][piece1[1]] == '\u265D'){
            if(cb.Bishop(piece1, piece2)) System.out.println("Bishop can move there");
            else System.out.println("Bishop can't move there");
        }

    }
    public static int[] GetPosition(){  //temporary, will be used as converter from algebraic notation to array indexes
        System.out.println("Give a position: ");
        Scanner scanner = new Scanner(System.in);
        int[] position = new int[2];
        position[0] = scanner.nextInt();
        position[1] = scanner.nextInt();
        return position;
    }
}

//link do symboli: https://en.wikipedia.org/wiki/Miscellaneous_Symbols