public abstract class Piece {
    // The current position of the piece on the board
    protected int[] position;
    // A boolean indicating the color of the piece. True if the piece is white, false otherwise
    protected boolean isWhite;
    // The name of the piece
    protected String name;
    // The unicode character representing the piece
    protected char unicode;
    // A boolean indicating whether the piece has moved
    protected boolean hasMoved = false;
    // A boolean indicating whether the piece is killed
    protected boolean isKilled = false;

    /**
     * The constructor for the Piece class.
     *
     * @param position An integer array representing the current position of the piece on the board.
     * @param isWhite A boolean indicating the color of the piece. If true, the piece is white; if false, the piece is black.
     * @param unicode A char representing the unicode character of the piece.
     */
    public Piece(int[] position, boolean isWhite, char unicode) {
        this.position = position; // Set the position
        this.isWhite = isWhite; // Set the color
        this.unicode = unicode; // Set the unicode character
    }

    /**
     * Checks if the piece can move to a given destination.
     *
     * @param destination An integer array representing the destination coordinates.
     * @param board A 2D array of Piece objects representing the current state of the board.
     * @return A boolean indicating whether the move is valid.
     */
    public abstract boolean canMove(int[] destination, Piece[][] board);

    /**
     * Returns the unicode character of the piece.
     *
     * @return A string representing the unicode character of the piece.
     */
    @Override
    public String toString() {
        return String.valueOf(unicode);
    }

    /**
     * Sets the killed status of the piece.
     *
     * @param killed A boolean indicating whether the piece is captured.
     */
    public void setKilled(boolean killed) {
        isKilled = killed; // Set the killed status
    }

    public abstract boolean getPieceType();

    public abstract String getName();
}