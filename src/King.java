public class King extends Piece{
    final protected String name = "K";
    public King(int[] position, boolean isWhite) {
        super(position, isWhite, isWhite ? '♔' : '♚');
    }
    @Override
    public boolean canMove(int[] destination, Piece[][] board) {
        if(Math.abs(position[0] - destination[0]) <= 1 && Math.abs(position[1] - destination[1]) <= 1){
            return !board[destination[0]][destination[1]].getPieceType() || board[destination[0]][destination[1]].isWhite != this.isWhite;
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
    public boolean canCastle(int[] destination, Chessboard board) {
        // Check if the king has already moved
        if (this.hasMoved) {
            return false; // If the king has moved, it cannot castle
        }
        // Get the piece at the destination square
        Piece piece = board.getPiece(destination);
        // Check if the piece at the destination is a rook of the same color that has not moved
        if (getPieceType() && piece.getName().equals("R") && !piece.hasMoved && piece.isWhite == this.isWhite){
            // Determine the start and end of the path between the king and the rook
            int start = Math.min(this.position[1], destination[1]);
            int end = Math.max(this.position[1], destination[1]);
            // Check all the squares between the king and the rook
            for (int i = start + 1; i < end; i++) {
                // If there is a piece in the way, return false
                if (board.getPiece(new int[]{this.position[0], i}).getPieceType()) {
                    return false;
                }
            }
            // If all conditions are met, return true
            return true;
        }
        // If the piece at the destination is not a rook of the same color that has not moved, return false
        return false;
    }
}
