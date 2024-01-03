public class Pawn extends Piece{
    final protected String name = "P";
    public Pawn(int[] position, boolean isWhite) {
        super(position, isWhite, isWhite ? '♙' : '♟');
    }
    @Override
    public boolean canMove(int[] destination, Piece[][] board) {
        if (!LackOfMovement(destination, board)) {
            if (isWhite) {
                if (position[0] == 6) {
                    if ((position[0] - destination[0] == 1 || position[0] - destination[0] == 2) && position[1] == destination[1]) {
                        if (position[0] - destination[0] == 2) {
                            if (!board[destination[0]][destination[1]].getPieceType() && !board[destination[0] + 1][destination[1]].getPieceType()) { // if the pawn is moving 2 squares, check if the square in between is empty
                                return true;
                            }
                        } else {
                            if (!board[destination[0]][destination[1]].getPieceType()) { // if the pawn is moving 1 square, check if the square is empty
                                return true;
                            }
                        }
                    }else return false;
                }
                else {  //if the pawn is not in the starting position
                    if (position[0] - destination[0] == 1 && position[1] == destination[1]) { // if the pawn is moving 1 square, check if the square is empty
                        if (!board[destination[0]][destination[1]].getPieceType()) {
                            return true;
                        }
                    }
                }
                if (position[0] - destination[0] == 1 && Math.abs(position[1] - destination[1]) == 1) { // if the pawn is moving diagonally, check if the square is occupied by an enemy piece
                    return board[destination[0]][destination[1]].getPieceType() && !board[destination[0]][destination[1]].isWhite;
                }
            }
            else {  //BLACK
                if (position[0] == 1) {
                    if ((destination[0] - position[0] == 1 || destination[0] - position[0] == 2) && position[1] == destination[1]) {
                        if (destination[0] - position[0] == 2) {
                            if (!board[destination[0]][destination[1]].getPieceType() && !board[destination[0] - 1][destination[1]].getPieceType()) {
                                return true;
                            }
                        }
                        else {
                            if (!board[destination[0]][destination[1]].getPieceType()) {
                                return true;
                            }
                        }
                    }
                    else return false;
                }
                else {
                    if (destination[0] - position[0] == 1 && position[1] == destination[1]) {
                        if (!board[destination[0]][destination[1]].getPieceType()) {
                            return true;
                        }
                    }
                }
                if (destination[0] - position[0] == 1 && Math.abs(position[1] - destination[1]) == 1) {
                    return board[destination[0]][destination[1]].getPieceType() && board[destination[0]][destination[1]].isWhite;
                }
            }
        }
        return false;
    }

    @Override
    public boolean getPieceType() {
        return true;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public boolean LackOfMovement(int[] dest, Piece[][] board) {
        return (position[0] == dest[0] && position[1] == dest[1]);
    }
}
