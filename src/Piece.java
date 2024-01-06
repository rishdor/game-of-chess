public abstract class Piece {
    protected int[] position;
    protected boolean isWhite;
    protected String name;
    protected char unicode;
    protected boolean hasMoved = false;
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
    public abstract String getName();
}

