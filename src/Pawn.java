public class Pawn extends Piece{
    final protected String name = "P";
    private boolean hasMovedTwoSquares;

    public Pawn(int[] position, boolean isWhite) {
        super(position, isWhite, isWhite ? '♙' : '♟');
        this.hasMovedTwoSquares = false;
    }
    public boolean hasMovedTwoSquares() {
        return this.hasMovedTwoSquares;
    }
    public void setHasMovedTwoSquares(boolean hasMovedTwoSquares) {
        this.hasMovedTwoSquares = hasMovedTwoSquares;
    }
//    @Override
//    public boolean canMove(int[] destination, Piece[][] board) {
//        boolean status = false;
//        if (isWhite) {
//            if (position[0] == 6) {
//                if ((position[0] - destination[0] == 1 || position[0] - destination[0] == 2) && position[1] == destination[1]) {
//                    if (position[0] - destination[0] == 2) {
//                        setHasMovedTwoSquares(true);
//                        status = !board[destination[0]][destination[1]].getPieceType() && !board[destination[0] + 1][destination[1]].getPieceType(); // if the pawn is moving 2 squares, check if the square in between is empty and set has moved two true
//                    } else {
//                        status = !board[destination[0]][destination[1]].getPieceType(); // if the pawn is moving 1 square, check if the square is empty
//                    }
//                } else if (position[0] - destination[0] == 1 && Math.abs(position[1] - destination[1]) == 1) { // if the pawn is moving diagonally
//                    status = board[destination[0]][destination[1]].getPieceType() && !board[destination[0]][destination[1]].isWhite; // check if the square is occupied by an enemy piece
//                }
//            } else {  //if the pawn is not in the starting position
//                if (position[0] - destination[0] == 1 && position[1] == destination[1]) { // if the pawn is moving 1 square, check if the square is empty
//                    status = !board[destination[0]][destination[1]].getPieceType();
//                } else if (Math.abs(position[1] - destination[1]) == 1) { // if the pawn is moving diagonally
//                    status = board[destination[0]][destination[1]].getPieceType() && !board[destination[0]][destination[1]].isWhite; // check if the square is occupied by an enemy piece
//                }
//                else if (board[position[0]] [position[1]-1] instanceof Pawn && !board[position[0]] [position[1]-1].isWhite) {
//                    Pawn neighboingPawn = (Pawn) board[position[0]][position[1] - 1];
//                    if (neighboingPawn.hasMovedTwoSquares()) {
//                        status = true;
//                    }
//                }
//                else if (board[position[0]][position[1] + 1] instanceof Pawn && !board[position[0]][position[1] + 1].isWhite) {
//                    Pawn neighboingPawn = (Pawn) board[position[0]][position[1] + 1];
//                    if (neighboingPawn.hasMovedTwoSquares()) {
//                        status = true;
//                    }
//                }
//            }
//        } else {  //BLACK
//            if (position[0] == 1) {
//                if ((destination[0] - position[0] == 1 || destination[0] - position[0] == 2) && position[1] == destination[1]) {
//                    if (destination[0] - position[0] == 2) {
//                        status = !board[destination[0]][destination[1]].getPieceType() && !board[destination[0] - 1][destination[1]].getPieceType();
//                    } else {
//                        status = !board[destination[0]][destination[1]].getPieceType();
//                    }
//                } else if (destination[0] - position[0] == 1 && Math.abs(position[1] - destination[1]) == 1) {
//                    status = board[destination[0]][destination[1]].getPieceType() && board[destination[0]][destination[1]].isWhite;
//                }
//            } else {
//                if (destination[0] - position[0] == 1 && position[1] == destination[1]) {
//                    status = !board[destination[0]][destination[1]].getPieceType();
//                } else if (Math.abs(position[1] - destination[1]) == 1) {
//                    status = board[destination[0]][destination[1]].getPieceType() && board[destination[0]][destination[1]].isWhite;
//                }
//                else if (board[position[0]] [position[1]-1] instanceof Pawn && board[position[0]] [position[1]-1].isWhite) {
//                    Pawn neighboingPawn = (Pawn) board[position[0]][position[1] - 1];
//                    if (neighboingPawn.hasMovedTwoSquares()) {
//                        status = true;
//                    }
//                }
//                else if (board[position[0]][position[1] + 1] instanceof Pawn && board[position[0]][position[1] + 1].isWhite) {
//                    Pawn neighboingPawn = (Pawn) board[position[0]][position[1] + 1];
//                    if (neighboingPawn.hasMovedTwoSquares()) {
//                        status = true;
//                    }
//                }
//            }
//        }
//        return status;
//    }
    @Override
    public boolean canMove(int[] destination, Piece[][] board) {
        if (isWhite) {
            if (position[0] == 6) {
                if ((position[0] - destination[0] == 1 || position[0] - destination[0] == 2) && position[1] == destination[1]) {
                    if (position[0] - destination[0] == 2) {
                        setHasMovedTwoSquares(true);
                        return !board[destination[0]][destination[1]].getPieceType() && !board[destination[0] + 1][destination[1]].getPieceType();
                    } else {
                        return !board[destination[0]][destination[1]].getPieceType();
                    }
                } else if (position[0] - destination[0] == 1 && Math.abs(position[1] - destination[1]) == 1) {
                    return board[destination[0]][destination[1]].getPieceType() && !board[destination[0]][destination[1]].isWhite;
                }
            } else {
                if (position[0] - destination[0] == 1 && position[1] == destination[1]) {
                    return !board[destination[0]][destination[1]].getPieceType();
                } else if (Math.abs(position[1] - destination[1]) == 1) {
                    return board[destination[0]][destination[1]].getPieceType() && !board[destination[0]][destination[1]].isWhite;
                }
            }
        }
        else {
            if (position[0] == 1) {
                if ((destination[0] - position[0] == 1 || destination[0] - position[0] == 2) && position[1] == destination[1]) {
                    if (destination[0] - position[0] == 2) {
                        return !board[destination[0]][destination[1]].getPieceType() && !board[destination[0] - 1][destination[1]].getPieceType();
                    } else {
                        return !board[destination[0]][destination[1]].getPieceType();
                    }
                } else if (destination[0] - position[0] == 1 && Math.abs(position[1] - destination[1]) == 1) {
                    return board[destination[0]][destination[1]].getPieceType() && board[destination[0]][destination[1]].isWhite;
                }
            } else {
                if (destination[0] - position[0] == 1 && position[1] == destination[1]) {
                    return !board[destination[0]][destination[1]].getPieceType();
                } else if (Math.abs(position[1] - destination[1]) == 1) {
                    return board[destination[0]][destination[1]].getPieceType() && board[destination[0]][destination[1]].isWhite;
                }
            }
        }
        return false;
    }
    public boolean canEnPassant(Pawn pawn, int[] destination, Chessboard board) {
        if (pawn.isWhite && pawn.position[0] == 3 && this.position[0] == 3) {
            if (!board.getPiece(destination).getPieceType()) {
                if (pawn.position[1] == this.position[1] + 1 || pawn.position[1] == this.position[1] - 1) {
                    if (pawn.position[1] == destination[1] && destination[0]==pawn.position[0]-1){
                        return true;
                    }
                }
            }
        }if (!pawn.isWhite && pawn.position[0] == 4 && this.position[0] == 4) {
            if (!board.getPiece(destination).getPieceType()) {
                if (pawn.position[1] == this.position[1] + 1 || pawn.position[1] == this.position[1] - 1) {
                    if (pawn.position[1] == destination[1] && destination[0]==pawn.position[0]+1){
                        return true;
                    }
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
}
