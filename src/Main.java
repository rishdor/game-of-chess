import com.sun.source.doctree.IndexTree;

import java.text.ParsePosition;

public class Main {
    public static void main(String[] args) {

        Szachownica cb = new Szachownica();

        cb.FillInChessBoard();
        cb.PrintChessBoard();

        //TESTS
        System.out.println(cb.tab[0][1]);
        System.out.println(cb.IsItFriend(cb.tab[0][1], cb.tab[1][1]));
        //System.out.println(cb.Rook(cb.ParsePosition1(cb.tab[0][1]), cb.ParsePosition1(cb.tab[0][1])));
        System.out.println(cb.ParsePosition1(cb.tab[0][1])[0] + " " + cb.ParsePosition1(cb.tab[0][1])[1]);

    }
    public static int[] ParsePosition(){
        return new int[2];
    }

}

//link do symboli: https://en.wikipedia.org/wiki/Miscellaneous_Symbols