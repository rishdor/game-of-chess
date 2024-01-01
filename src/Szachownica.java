public class Szachownica {
    public int size;
    public char[][] tab;

    public Szachownica(){
        this.size = 8;
        tab = new char[size][size];
    }

    public void FillInChessBoard(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {    //vacant spaces on the chess board
                tab[i][j] = '\u26DA';
            }
            if(i == 0){ //BLACK
                tab[i][0] = '\u265C';   //rookies
                tab[i][7] = '\u265C';
                tab[i][1] = '\u265E';   //knights
                tab[i][6] = '\u265E';
                tab[i][2] = '\u265D';   //bishops
                tab[i][5] = '\u265D';
                tab[i][3] = '\u265B';   // queen
                tab[i][4] = '\u265A';   //king
            }
            if (i == 1) {   //black pawns
                for (int j = 0; j < size; j++) {
                    tab[i][j] = '\u265F';
                }
            }

            if (i == 6) {   //white pawns
                for (int j = 0; j < size; j++) {
                    tab[i][j] = '\u2659';
                }
            }
            if(i == 7){ //WHITE
                tab[i][0] = '\u2656';   //rookies
                tab[i][7] = '\u2656';
                tab[i][1] = '\u2658';   //knights
                tab[i][6] = '\u2658';
                tab[i][2] = '\u2657';   //bishops
                tab[i][5] = '\u2657';
                tab[i][4] = '\u2654';   //king
                tab[i][3] = '\u2655';   //queen
            }
        }
    }

    public void PrintChessBoard(){
        System.out.print("  ");
        for(int i = 0; i< size; i++){   //makes the letters above the chess board
            int firstRow = 65+i;
            System.out.print((char) firstRow + " ");

            if((i+1)%3 != 0) System.out.print(" ");
        }
        System.out.println();

        for(int i = 0; i<size; i++){
            System.out.print(i+1 +" ");
            for(int j = 0; j<size; j++) { //prints what's inside of chess board
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
    }

}
