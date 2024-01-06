public class Knight extends Piece{
    final protected String name = "N";
    public Knight(int[] position, boolean isWhite) {
        super(position, isWhite, isWhite ? '♘' : '♞');
    }
    @Override
    public boolean canMove(int[] destination, Piece[][] board) {
        if((Math.abs(position[0] - destination[0]) == 2 && Math.abs(position[1] - destination[1]) == 1) || (Math.abs(position[0] - destination[0]) == 1 && Math.abs(position[1] - destination[1]) == 2)){
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
}
