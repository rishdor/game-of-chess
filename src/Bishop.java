public class Bishop extends Piece{
    final protected String name = "B";
    public Bishop(int[] position, boolean isWhite) {
        super(position, isWhite, isWhite ? '♗' : '♝');
    }
    @Override
    public boolean canMove(int[] destination, Piece[][] board) {
        if((Math.abs(position[0] - destination[0]) == Math.abs(position[1] - destination[1])) && !LackOfMovement(destination, board)){
            if(isPathClear(destination, board)){
                return !board[destination[0]][destination[1]].getPieceType() || board[destination[0]][destination[1]].isWhite != this.isWhite;
            }
        }
        return false;
    }
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
        return (position[0] == dest[0] && position[1] == dest[1]);
    }
}
