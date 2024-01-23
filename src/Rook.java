class Rook extends Piece {
    // Name of the piece
    final protected String name = "R";

    /**
     * Constructor for the Rook class
     * @param position An integer array representing the position of the piece on the board
     * @param isWhite A boolean representing the color of the piece. True if the piece is white, false otherwise
     */
    public Rook(int[] position, boolean isWhite) {
        super(position, isWhite, isWhite ? '♖' : '♜');
    }

    /**
     * Checks if the rook can move to the given destination
     * @param destination An integer array representing the destination position on the board
     * @param board A 2D array of Piece objects representing the current state of the board
     * @return A boolean indicating if the move is valid
     */
    @Override
    public boolean canMove(int[] destination, Piece[][] board) {
        boolean status = false;
        if (position[0] == destination[0] || position[1] == destination[1]) {
            if (isPathClear(destination, board)) {
                status =  !board[destination[0]][destination[1]].getPieceType() || board[destination[0]][destination[1]].isWhite != this.isWhite;
            }
        }
        return status;
    }

    /**
     * Checks if the path to the destination is clear
     * @param dest An integer array representing the destination position on the board
     * @param board A 2D array of Piece objects representing the current state of the board
     * @return A boolean indicating if the path is clear
     */
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

    /**
     * Returns the type of the piece
     * @return A boolean indicating the type of the piece. True for Rook, false otherwise
     */
    @Override
    public boolean getPieceType() {
        return true;
    }

    /**
     * Returns the name of the piece
     * @return A string representing the name of the piece
     */
    @Override
    public String getName() {
        return name;
    }
}