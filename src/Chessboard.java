import java.util.Objects;

public class Chessboard {
    public Piece[][] board;

    public Chessboard(){
        board = new Piece[8][8];
    }
    public Piece getPiece(int[] position) {
        return board[position[0]][position[1]];
    }
    public void setPiece(int[] position, Piece piece) {
        board[position[0]][position[1]] = piece;
    }
    public void movePiece(int[] source, int[] destination) {
        Piece piece = board[source[0]][source[1]];
        board[source[0]][source[1]] = new EmptyPiece(source);
        board[destination[0]][destination[1]] = piece;
    }
    public Piece[][] getBoard() {
        return this.board;
    }
    public void FillInChessBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Rook(new int[]{i, j}, false);
            }
//            if(i == 0){ //BLACK
//                board[i][0] = '\u265C';   //rooks
//                board[i][7] = '\u265C';
//                board[i][1] = '\u265E';   //knights
//                board[i][6] = '\u265E';
//                board[i][2] = '\u265D';   //bishops
//                board[i][5] = '\u265D';
//                board[i][3] = '\u265B';   // queen
//                board[i][4] = '\u265A';   //king
//            }
//            if (i == 1) {   //black pawns
//                for (int j = 0; j < 8; j++) {
//                    board[i][j] = '\u265F';
//                }
//            }
//
//            if (i == 6) {   //white pawns
//                for (int j = 0; j < 8; j++) {
//                    board[i][j] = '\u2659';
//                }
//            }
//            if(i == 7){ //WHITE
//                board[i][0] = '\u2656';   //rooks
//                board[i][7] = '\u2656';
//                board[i][1] = '\u2658';   //knights
//                board[i][6] = '\u2658';
//                board[i][2] = '\u2657';   //bishops
//                board[i][5] = '\u2657';
//                board[i][4] = '\u2654';   //king
//                board[i][3] = '\u2655';   //queen
//            }
        }
    }

    public void PrintChessBoard(){
        System.out.print("  ");
        for(int i = 0; i< 8; i++){   //makes the letters above the chess board
            int firstRow = 65+i;
            System.out.print((char) firstRow + " ");

            if((i+1)%3 != 0) System.out.print(" ");
        }
        System.out.println();

        for(int i = 0; i<8; i++){
            System.out.print(i+1 +" ");
            for(int j = 0; j<8; j++) { //prints what's inside of chess board
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


//    public String IsItFriend(int[]p1, int[] p2) {
//        String status = "wrong input";
//        if(!IsInBoardersOfCB(p1) || !IsInBoardersOfCB(p2)) return status;
//        else {
//            char piece1 = board[p1[0]][p1[1]];
//            char piece2 = board[p2[0]][p2[1]];
//            char color;
//            if ((int) piece1 >= 9812 && (int) piece1 <= 9817) color = 'w';
//            else if ((int) piece1 >= 9818 && (int) piece1 <= 9823) color = 'b';
//            else return status;  //wrong input, not a chess piece
//
//            if ((int) piece2 >= 9812 && (int) piece2 <= 9817) {   //white piece
//                if (color == 'w') status = "friend";
//                else status = "enemy";
//            } else if ((int) piece2 >= 9818 && (int) piece2 <= 9823) {    //black piece
//                if (color == 'b') status = "friend";
//                else status = "enemy";
//            } else status = "unoccupied";
//        }
//        return status;
//    }

    public boolean IsInBoardersOfCB(int[] position){
        return position[0] >= 0 && position[0] <= 8 - 1 && position[1] >= 0 && position[1] <= 8 - 1;
    }
//    public boolean Rook(int[] p1, int[] p2){
//        if(p1[0] == p2[0] || p1[1] == p2[1]){
//            return !Objects.equals(IsItFriend(p1, p2), "friend");
//        }
//        else{
//            System.out.println("Out of rook's range");
//            return false;
//        }
//    }
//
//    public boolean Knight(int[] p1, int[] p2){
//        if((Math.abs(p1[0] - p2[0]) == 2 && Math.abs(p1[1] - p2[1]) == 1) || (Math.abs(p1[0] - p2[0]) == 1 && Math.abs(p1[1] - p2[1]) == 2)){
//            return !Objects.equals(IsItFriend(p1, p2), "friend");
//        }
//        else{
//            System.out.println("Out of knight's range");
//            return false;
//        }
//    }
//
//    public boolean Bishop(int[] p1, int[] p2){
//        if(Math.abs(p1[0] - p2[0]) == Math.abs(p1[1] - p2[1])){
//            return !Objects.equals(IsItFriend(p1, p2), "friend");
//        }
//        else{
//            System.out.println("Out of bishop's range");
//            return false;
//        }
//    }
//
//    public boolean Queen(int[] p1, int[] p2){
//        if((Math.abs(p1[0] - p2[0]) == Math.abs(p1[1] - p2[1])) || (p1[0] == p2[0] || p1[1] == p2[1])){
//            return !Objects.equals(IsItFriend(p1, p2), "friend");
//        }
//        else{
//            System.out.println("Out of queen's range");
//            return false;
//        }
//    }
//
//    public boolean King(int[] p1, int[] p2){
//        if((Math.abs(p1[0] - p2[0]) == 1 && Math.abs(p1[1] - p2[1]) == 1) || (Math.abs(p1[0] - p2[0]) == 1 && p1[1] == p2[1]) || (Math.abs(p1[1] - p2[1]) == 1 && p1[0] == p2[0])){
//            return !Objects.equals(IsItFriend(p1, p2), "friend");
//        }
//        else{
//            System.out.println("Out of king's range");
//            return false;
//        }
//    }
//
//    public boolean Pawn(int[] p1, int[] p2){
//        if(p1[0] == p2[0] && Math.abs(p1[1] - p2[1]) == 1){
//            return !Objects.equals(IsItFriend(p1, p2), "unoccupied");
//        }
//        else if(p1[0] == p2[0] && Math.abs(p1[1] - p2[1]) == 2){
//            return Objects.equals(IsItFriend(p1, p2), "unoccupied") && Objects.equals(IsItFriend(p1, new int[]{p1[0], p1[1] + 1}), "unoccupied");
//        }
//        else if(Math.abs(p1[0] - p2[0]) == 1 && Math.abs(p1[1] - p2[1]) == 1){
//            return Objects.equals(IsItFriend(p1, p2), "enemy");
//        }
//        else{
//            System.out.println("Out of pawn's range");
//            return false;
//        }
//    }

//    public boolean IsItCheck(int[] p1, int[] p2){
//        if(IsItFriend(p1, p2).equals("enemy")){
//            return board[p2[0]][p2[1]] == '\u2654' || board[p2[0]][p2[1]] == '\u265A';
//        }
//        else return false;
//    }

//    public boolean IsItCheckMate(int[] p1, int[] p2){
//        if(IsItCheck(p1, p2)){
//            if(board[p2[0]][p2[1]] == '\u2654' || board[p2[0]][p2[1]] == '\u265A'){
//                if(King(p1, p2)){
//                    return IsItCheck(p1, p2);
//                }
//                else{
//                    System.out.println("King can't move there");
//                    return false;
//                }
//            }
//            else{
//                System.out.println("It's not a king");
//                return false;
//            }
//        }
//        else{
//            System.out.println("It's not a check");
//            return false;
//        }
//    }

//    public boolean IsItStaleMate(int[] p1, int[] p2){
//        if(!IsItCheck(p1, p2)){
//            if(board[p2[0]][p2[1]] == '\u2654' || board[p2[0]][p2[1]] == '\u265A'){
//                if(King(p1, p2)){
//                    if(IsItCheck(p1, p2)) return false;
//                    else return true;
//                }
//                else{
//                    System.out.println("King can't move there");
//                    return false;
//                }
//            }
//            else{
//                System.out.println("It's not a king");
//                return false;
//            }
//        }
//        else{
//            System.out.println("It's not a check");
//            return false;
//        }
//    }
}
