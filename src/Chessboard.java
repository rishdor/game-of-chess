import java.util.Objects;

public class Chessboard {
    public Piece[][] board;

    public Chessboard(){
        board = new Piece[8][8];
    }
    public Piece getPiece(int[] position) {
        return board[position[0]][position[1]];
    }
    public void movePiece(int[] source, int[] destination) {
        Piece piece = board[source[0]][source[1]];
        piece.position = destination;
        board[source[0]][source[1]] = new EmptyPiece(source);
        board[destination[0]][destination[1]] = piece;
        piece.hasMoved = true;
    }
    public Piece[][] getBoard() {
        return this.board;
    }
    public void FillInChessBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new EmptyPiece(new int[]{i, j});
            }

            board[0][0] = new Rook(new int[]{0, 0}, false);
            board[0][7] = new Rook(new int[]{0, 7}, false);
            board[7][7] = new Rook(new int[]{7, 7}, true);
            board[7][0] = new Rook(new int[]{7, 0}, true);
            board[0][1] = new Knight(new int[]{0, 1}, false);
            board[0][6] = new Knight(new int[]{0, 6}, false);
            board[7][1] = new Knight(new int[]{7, 1}, true);
            board[7][6] = new Knight(new int[]{7, 6}, true);
            board[0][2] = new Bishop(new int[]{0, 2}, false);
            board[0][5] = new Bishop(new int[]{0, 5}, false);
            board[7][2] = new Bishop(new int[]{7, 2}, true);
            board[7][5] = new Bishop(new int[]{7, 5}, true);
            board[0][3] = new Queen(new int[]{0, 3}, false);
            board[7][3] = new Queen(new int[]{7, 3}, true);
            board[0][4] = new King(new int[]{0, 4}, false);
            board[7][4] = new King(new int[]{7, 4}, true);

            for (int j = 0; j < 8; j++) {
                board[1][j] = new Pawn(new int[]{1, j}, false);
                board[6][j] = new Pawn(new int[]{6, j}, true);
            }
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
