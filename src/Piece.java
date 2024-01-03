public abstract class Piece {
    protected int[] position;
    protected boolean isWhite;
    protected char unicode;
    protected boolean isKilled = false;

    public Piece(int[] position, boolean isWhite, char unicode) {
        this.position = position;
        this.isWhite = isWhite;
        this.unicode = unicode;
    }
    public abstract boolean canMove(int[] destination, Piece[][] board);
    @Override
    public String toString() {
        return String.valueOf(unicode);
    }
    public void setKilled(boolean killed) {
        isKilled = killed;
    }
    public abstract boolean getPieceType();
}
class EmptyPiece extends Piece {
    public EmptyPiece(int[] position) {
        super(position, false, '⛚');
    }
    @Override
    public boolean canMove(int[] destination, Piece[][] board) {
        return false;
    }
    @Override
    public boolean getPieceType() {
        return false;
    }
}
class Rook extends Piece {
    public Rook(int[] position, boolean isWhite) {
        super(position, isWhite, isWhite ? '♖' : '♜');
    }
    @Override
    public boolean canMove(int[] destination, Piece[][] board) {
        if (position[0] == destination[0] || position[1] == destination[1]) {
            if (isPathClear(destination, board)) {
                return !board[destination[0]][destination[1]].getPieceType() || board[destination[0]][destination[1]].isWhite != this.isWhite;
            }
        }
        return false;
    }
    public boolean isPathClear(int[] dest, Piece[][] board) {
        if (position[0] == dest[0]) {
            int start = Math.min(position[1], dest[1]);
            int end = Math.max(position[1], dest[1]);
            for (int i = start + 1; i < end; i++) {
                if (board[position[0]][i].getPieceType()) {
                    return false;
                }
            }
        } else {
            int start = Math.min(position[0], dest[0]);
            int end = Math.max(position[0], dest[0]);
            for (int i = start + 1; i < end; i++) {
                if (board[i][position[1]].getPieceType()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean getPieceType() {
        return true;
    }
}