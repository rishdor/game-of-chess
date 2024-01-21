import java.util.Objects;

public class Chessboard {
    public Piece[][] board;

    public Chessboard() {
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

    public void createNewPiece(char name, boolean isWhite, int[] position) {
        switch (name) {
            case 'R', 'r':
                board[position[0]][position[1]] = new Rook(position, isWhite);
                break;
            case 'N', 'n':
                board[position[0]][position[1]] = new Knight(position, isWhite);
                break;
            case 'B', 'b':
                board[position[0]][position[1]] = new Bishop(position, isWhite);
                break;
            case 'Q', 'q':
                board[position[0]][position[1]] = new Queen(position, isWhite);
                break;
            default:
                board[position[0]][position[1]] = new EmptyPiece(position);
                break;
        }
    }

    public void castle(int[] source, int[] destination) {
        Piece king = board[source[0]][source[1]];
        Piece rook = board[destination[0]][destination[1]];
        king.position = new int[]{destination[0], destination[1] - 1};
        rook.position = new int[]{source[0], source[1] + 1};
        board[source[0]][source[1] + 1] = rook;
        board[destination[0]][destination[1] - 1] = king;
        board[source[0]][source[1]] = new EmptyPiece(source);
        board[destination[0]][destination[1]] = new EmptyPiece(source);
        king.hasMoved = true;
        rook.hasMoved = true;
    }

    public void enPassant(int[] source, int[] destination, int[] pawn2Position) {
        Piece pawn = board[source[0]][source[1]];
        pawn.position = destination;
        board[destination[0]][destination[1]] = pawn;
        board[source[0]][source[1]] = new EmptyPiece(source);
        board[pawn2Position[0]][pawn2Position[1]] = new EmptyPiece(source);
    }

    public Piece[][] getBoard() {
        return this.board;
    }

    public void FillInChessBoard() {
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

    public void PrintChessBoard() {
        System.out.print("  ");
        for (int i = 0; i < 8; i++) {   //makes the letters above the chess board
            int firstRow = 65 + i;
            System.out.print((char) firstRow + " ");

            if ((i + 1) % 3 != 0) System.out.print(" ");
        }
        System.out.println();

        for (int i = 0; i < 8; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < 8; j++) { //prints what's inside of chess board
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean IsInBoardersOfCB(int[] position) {
        return position[0] >= 0 && position[0] <= 8 - 1 && position[1] >= 0 && position[1] <= 8 - 1;
    }

    public boolean isCheck(boolean white) {
        int[] kingPosition = new int[2];
        for (int i = 0; i < 8; i++) { //finds the king
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getName().equals("K") && board[i][j].isWhite == white) {
                    kingPosition[0] = i;
                    kingPosition[1] = j;
                }
            }
        }
        for (int i = 0; i < 8; i++) { //checks if there is a piece that can kill the king
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getPieceType() && board[i][j].isWhite != white) {
                    if (board[i][j].canMove(kingPosition, board)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCheckmate(boolean white){
        int[] kingPosition = new int[2];
        for (int i = 0; i < 8; i++) { //finds the king
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getName().equals("K") && board[i][j].isWhite == white) {
                    kingPosition[0] = i;
                    kingPosition[1] = j;
                }
            }
        }
        for (int i = kingPosition[0] - 1; i <= kingPosition[0] + 1; i++) { //checks if there is a piece that can kill the king
            for (int j = kingPosition[1] - 1; j <= kingPosition[1] + 1; j++) {
                if (IsInBoardersOfCB(new int[]{i, j}) && board[i][j].getPieceType() && board[i][j].isWhite != white) {
                    if (board[i][j].canMove(kingPosition, board)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isStalemate(boolean white) {
        int[] kingPosition = new int[2];
        for (int i = 0; i < 8; i++) { //finds the king
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getName().equals("K") && board[i][j].isWhite == white) {
                    kingPosition[0] = i;
                    kingPosition[1] = j;
                }
            }
        }
        for (int i = 0; i < 8; i++) { //checks if there is a piece that can kill the king
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getPieceType() && board[i][j].isWhite != white) {
                    if (board[i][j].canMove(kingPosition, board)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void clone(Chessboard board) {

    }
}