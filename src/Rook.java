class Rook extends Piece {
    final protected String name = "R";
    public Rook(int[] position, boolean isWhite) {
        super(position, isWhite, isWhite ? '♖' : '♜');
    }
    @Override
    public boolean canMove(int[] destination, Piece[][] board) {
        boolean status = false;
        if ((position[0] == destination[0] || position[1] == destination[1]) && LackOfMovement(destination, board)) {
            if (isPathClear(destination, board)) {
                status =  !board[destination[0]][destination[1]].getPieceType() || board[destination[0]][destination[1]].isWhite != this.isWhite;
            }
        }
        return status;
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
    @Override
    public String getName() {
        return name;
    }
    @Override
    public boolean LackOfMovement(int[] dest, Piece[][] board) {
        return (position[0] != dest[0] || position[1] != dest[1]);
    }
}