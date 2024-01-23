import java.util.Arrays;

/**
 * The Pawn class extends the Piece class and represents a pawn in a game of chess.
 */
public class Pawn extends Piece{
    // The name of the piece
    final protected String name = "P";
    // A boolean indicating whether the pawn has moved two squares
    private boolean hasMovedTwoSquares;

    /**
     * The constructor for the Pawn class.
     *
     * @param position An integer array representing the current position of the piece on the board.
     * @param isWhite A boolean indicating the color of the piece. If true, the piece is white; if false, the piece is black.
     */
    public Pawn(int[] position, boolean isWhite) {
        super(position, isWhite, isWhite ? '♙' : '♟');
        this.hasMovedTwoSquares = false;
    }

    /**
     * Checks if the pawn has moved two squares.
     *
     * @return A boolean indicating whether the pawn has moved two squares.
     */
    public boolean hasMovedTwoSquares() {
        return this.hasMovedTwoSquares;
    }

    /**
     * Sets the hasMovedTwoSquares property of the pawn.
     *
     * @param hasMovedTwoSquares A boolean indicating whether the pawn has moved two squares.
     */
    public void setHasMovedTwoSquares(boolean hasMovedTwoSquares) {
        this.hasMovedTwoSquares = hasMovedTwoSquares;
    }

    /**
     * Checks if the pawn can move to a given destination.
     *
     * @param destination An integer array representing the destination coordinates.
     * @param board A 2D array of Piece objects representing the current state of the board.
     * @return A boolean indicating whether the move is valid.
     */
    @Override
    public boolean canMove(int[] destination, Piece[][] board) {
        if (isWhite) { // Check if the pawn is white
            if (position[0] == 6) { // Check if the pawn is on its initial position
                // Check if the pawn is moving one or two squares forward and is not moving sideways
                if ((position[0] - destination[0] == 1 || position[0] - destination[0] == 2) && position[1] == destination[1]) {
                    // Check if the pawn is moving two squares forward
                    if (position[0] - destination[0] == 2) {
                        setHasMovedTwoSquares(true); // Set that the pawn has moved two squares
                        // Check if the destination square and the square in between are empty
                        return !board[destination[0]][destination[1]].getPieceType() && !board[destination[0] + 1][destination[1]].getPieceType();
                    } else {
                        // Check if the destination square is empty
                        return !board[destination[0]][destination[1]].getPieceType();
                    }
                }
                // Check if the pawn is capturing a piece diagonally
                else if (position[0] - destination[0] == 1 && Math.abs(position[1] - destination[1]) == 1) {
                    // Check if the destination square contains an opponent's piece
                    return board[destination[0]][destination[1]].getPieceType() && !board[destination[0]][destination[1]].isWhite;
                }
            } else { // If the pawn is not on its initial position
                // Check if the pawn is moving one square forward
                if (position[0] - destination[0] == 1 && position[1] == destination[1]) {
                    // Check if the destination square is empty
                    return !board[destination[0]][destination[1]].getPieceType();
                }
                // Check if the pawn is capturing a piece diagonally
                else if (Math.abs(position[1] - destination[1]) == 1) {
                    // Check if the destination square contains an opponent's piece
                    return board[destination[0]][destination[1]].getPieceType() && !board[destination[0]][destination[1]].isWhite;
                }
            }
        }
        else { // The same as above but for black pawns
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

    /**
     * Checks if the pawn can capture another pawn en passant.
     *
     * @param pawn The pawn that might be captured.
     * @param destination The destination coordinates.
     * @param board The current state of the board.
     * @return A boolean indicating whether the pawn can capture the other pawn en passant.
     */
    public boolean canEnPassant(Pawn pawn, int[] destination, Chessboard board) {
        // Check if the pawn is white and both pawns are on the 5th rank
        if (pawn.isWhite && pawn.position[0] == 4 && this.position[0] == 4) {
            // Check if the destination square is empty
            if (!board.getPiece(destination).getPieceType()) {
                // Check if the pawn is on an adjacent file
                if (pawn.position[1] == this.position[1] + 1 || pawn.position[1] == this.position[1] - 1) {
                    // Check if the destination square is directly in front of the pawn
                    if (pawn.position[1] == destination[1] && destination[0]==pawn.position[0]+1){
                        return true;
                    }
                }
            }
        }
        // Check if the pawn is black and both pawns are on the 4th rank
        if (!pawn.isWhite && pawn.position[0] == 3 && this.position[0] == 3) {
            // Check if the destination square is empty
            if (!board.getPiece(destination).getPieceType()) {
                // Check if the pawn is on an adjacent file
                if (pawn.position[1] == this.position[1] + 1 || pawn.position[1] == this.position[1] - 1) {
                    // Check if the destination square is directly in front of the pawn
                    if (pawn.position[1] == destination[1] && destination[0]==pawn.position[0]-1){
                        return true;
                    }
                }
            }
        }
        // If none of the conditions are met, return false
        return false;
    }

    /**
     * Returns the type of the piece.
     *
     * @return A boolean indicating the type of the piece. For the Pawn class, this method always returns true.
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