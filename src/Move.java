public class Move extends Command {
    private final Piece piece;
    private final String move;
    private final int[] source;
    private final int[] destination;
    private boolean capture;
    private boolean check;
    private boolean checkmate;
    private boolean promotion;
    private char promotionPiece;

    public Move(String move, Chessboard board) {
        this.move = move;
        this.source = convertNotationToCoordinate(move.split(" ")[0]);
        this.destination = convertNotationToCoordinate(move.split(" ")[1]);
        this.piece = board.getPiece(source);
    }

    public void setCapture(boolean capture) {
        this.capture = capture;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public void setCheckmate(boolean checkmate) {
        this.checkmate = checkmate;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public void setPromotionPiece(char promotionPiece) {
        this.promotionPiece = promotionPiece;
    }

    public Piece getPiece() {
        return piece;
    }

    public int[] getSource() {
        return source;
    }

    public int[] getDestination() {
        return destination;
    }

    public static int[] convertNotationToCoordinate(String input) {
        int[] coordinate = new int[2];
        coordinate[1] = input.toLowerCase().charAt(0) - 'a';
        coordinate[0] = Character.getNumericValue(input.charAt(1)) - 1;
        return coordinate;
    }

    public String convertToAlgebraic() {
        String[] parts = move.split(" ");
        String from = parts[0];
        String to = parts[1];

        String pieceType = piece.getName();

        if (pieceType.equals("P")) {
            pieceType = "";
        }
        if (capture) {
            if (pieceType.isEmpty()) {
                pieceType = from.charAt(0) + "x";
            } else {
                pieceType += "x";
            }
        }
        if (promotion) {
            to = to + "=" + Character.toUpperCase(promotionPiece);
        }
        if (check) {
            to += "+";
        }
        if (checkmate) {
            to += "#";
        }
        return pieceType + to.toLowerCase();
    }

    @Override
    public boolean endTheGame() {
        return false;
    }
}
