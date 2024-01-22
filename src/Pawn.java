import java.util.Arrays;
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
                        setHasMovedTwoSquares(true);
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
        if (pawn.isWhite && pawn.position[0] == 4 && this.position[0] == 4) {
            if (!board.getPiece(destination).getPieceType()) {
                if (pawn.position[1] == this.position[1] + 1 || pawn.position[1] == this.position[1] - 1) {
                    if (pawn.position[1] == destination[1] && destination[0]==pawn.position[0]+1){
                        return true;
                    }
                }
            }
        }
        if (!pawn.isWhite && pawn.position[0] == 3 && this.position[0] == 3) {
            if (!board.getPiece(destination).getPieceType()) {
                if (pawn.position[1] == this.position[1] + 1 || pawn.position[1] == this.position[1] - 1) {
                    if (pawn.position[1] == destination[1] && destination[0]==pawn.position[0]-1){
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
