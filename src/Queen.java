/**
 * The Queen class extends the Piece class and represents a queen in a game of chess.
 */
class Queen extends Piece{
    // The name of the piece
    final protected String name = "Q";

    /**
     * The constructor for the Queen class.
     *
     * @param position An integer array representing the current position of the piece on the board.
     * @param isWhite A boolean indicating the color of the piece. If true, the piece is white; if false, the piece is black.
     */
    public Queen(int[] position, boolean isWhite) {
        super(position, isWhite, isWhite ? '♕' : '♛');
    }

    /**
     * Checks if the queen can move to a given destination.
     *
     * @param destination An integer array representing the destination coordinates.
     * @param board A 2D array of Piece objects representing the current state of the board.
     * @return A boolean indicating whether the move is valid.
     */
    @Override
    public boolean canMove(int[] destination, Piece[][] board) {
        boolean status = false;
        if((Math.abs(position[0] - destination[0]) == Math.abs(position[1] - destination[1])) || (position[0] == destination[0] || position[1] == destination[1])){
            if(isPathClear(destination, board)){
                status = !board[destination[0]][destination[1]].getPieceType() || board[destination[0]][destination[1]].isWhite != this.isWhite;
            }
        }
        return status;
    }

    /**
     * Checks if the path to the destination is clear (i.e., no pieces are in the way).
     *
     * @param dest An integer array representing the destination coordinates.
     * @param board A 2D array of Piece objects representing the current state of the board.
     * @return A boolean indicating whether the path is clear.
     */
    public boolean isPathClear(int[] dest, Piece[][] board) {
        int x = position[0];
        int y = position[1];
        int x1 = dest[0];
        int y1 = dest[1];
        if(x < x1 && y < y1){
            for(int i = x + 1, j = y + 1; i < x1 && j < y1; i++, j++){
                if(board[i][j].getPieceType()){
                    return false;
                }
            }
        }
        else if(x < x1 && y > y1){
            for(int i = x + 1, j = y - 1; i < x1 && j > y1; i++, j--){
                if(board[i][j].getPieceType()){
                    return false;
                }
            }
        }
        else if(x > x1 && y < y1){
            for(int i = x - 1, j = y + 1; i > x1 && j < y1; i--, j++){
                if(board[i][j].getPieceType()){
                    return false;
                }
            }
        }
        else if(x > x1 && y > y1){
            for(int i = x - 1, j = y - 1; i > x1 && j > y1; i--, j--){
                if(board[i][j].getPieceType()){
                    return false;
                }
            }
        }else if (position[0] == dest[0]) {
            int start = Math.min(position[1], dest[1]);
            int end = Math.max(position[1], dest[1]);
            for (int i = start + 1; i < end; i++) {
                if (board[position[0]][i].getPieceType()) {
                    return false;
                }
            }
        }else {
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

    /**
     * Returns the type of the piece.
     *
     * @return A boolean indicating the type of the piece. For the Queen class, this method always returns true.
     */
    @Override
    public boolean getPieceType() {
        return true;
    }

    /**
     * Returns the name of the piece.
     *
     * @return A string representing the name of the piece.
     */
    @Override
    public String getName() {
        return name;
    }
}