class EmptyPiece extends Piece {
    final protected String name = "E";
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
    @Override
    public String getName() {
        return name;
    }
}
