public class Pawn extends Piece{
    final protected String name = "P";
    private boolean enPassant = false;
    public Pawn(int[] position, boolean isWhite) {
        super(position, isWhite, isWhite ? '♙' : '♟');
    }
    public boolean getEnPassant() {
        return enPassant;
    }
    public boolean EnPassant(){
        if (isWhite & position[0]==6){
            enPassant = true;
        }
        else if (!isWhite & position[0]==1){
            enPassant = true;
        }
        return enPassant;
    }
    @Override
    public boolean canMove(int[] destination, Piece[][] board) {
        boolean status = false;
        if (isWhite) {
            if (position[0] == 6) {
                if ((position[0] - destination[0] == 1 || position[0] - destination[0] == 2) && position[1] == destination[1]) {
                    if (position[0] - destination[0] == 2) {
                        status = !board[destination[0]][destination[1]].getPieceType() && !board[destination[0] + 1][destination[1]].getPieceType(); // if the pawn is moving 2 squares, check if the square in between is empty
                    } else {
                        status = !board[destination[0]][destination[1]].getPieceType(); // if the pawn is moving 1 square, check if the square is empty
                    }
                } else if (position[0] - destination[0] == 1 && Math.abs(position[1] - destination[1]) == 1) { // if the pawn is moving diagonally
                    status = board[destination[0]][destination[1]].getPieceType() && !board[destination[0]][destination[1]].isWhite; // check if the square is occupied by an enemy piece
                }
            } else {  //if the pawn is not in the starting position
                if (position[0] - destination[0] == 1 && position[1] == destination[1]) { // if the pawn is moving 1 square, check if the square is empty
                    status = !board[destination[0]][destination[1]].getPieceType();
                } else if (position[0] - destination[0] == 1 && Math.abs(position[1] - destination[1]) == 1) { // if the pawn is moving diagonally
                    status = board[destination[0]][destination[1]].getPieceType() && !board[destination[0]][destination[1]].isWhite; // check if the square is occupied by an enemy piece
                }
                else if (getEnPassant()){
                    if (position[0] == 3 && Math.abs(position[1] - destination[1]) == 1 && position[0] - destination[0] == 1) {
                        status = EnPassant();
                    }
                }
            }
        } else {  //BLACK
            if (position[0] == 1) {
                if ((destination[0] - position[0] == 1 || destination[0] - position[0] == 2) && position[1] == destination[1]) {
                    if (destination[0] - position[0] == 2) {
                        status = !board[destination[0]][destination[1]].getPieceType() && !board[destination[0] - 1][destination[1]].getPieceType();
                    } else {
                        status = !board[destination[0]][destination[1]].getPieceType();
                    }
                } else if (destination[0] - position[0] == 1 && Math.abs(position[1] - destination[1]) == 1) {
                    status = board[destination[0]][destination[1]].getPieceType() && board[destination[0]][destination[1]].isWhite;
                }
            } else {
                if (destination[0] - position[0] == 1 && position[1] == destination[1]) {
                    status = !board[destination[0]][destination[1]].getPieceType();
                } else if (destination[0] - position[0] == 1 && Math.abs(position[1] - destination[1]) == 1) {
                    status = board[destination[0]][destination[1]].getPieceType() && board[destination[0]][destination[1]].isWhite;
                }
            }

        }
        return status;
    }

    @Override
    public boolean getPieceType() {
        return true;
    }
    @Override
    public String getName() {
        return name;
    }
}
