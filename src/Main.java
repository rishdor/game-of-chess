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
        System.out.println(cb.IsItFriend(cb.tab[piece1[0]][piece1[1]], cb.tab[piece2[0]][piece2[1]]));
    }
    public static int[] GetPosition(){
        System.out.println("Give a position: ");
        Scanner scanner = new Scanner(System.in);
        int[] position = new int[2];
        position[0] = scanner.nextInt();
        position[1] = scanner.nextInt();
        return position;
    }
}

//link do symboli: https://en.wikipedia.org/wiki/Miscellaneous_Symbols