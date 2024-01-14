public class Move {
    private final Piece piece;
    private final int[] source;
    private final int[] destination;
    private final String move;
    private final boolean capture;
    private final boolean check;
    private final boolean checkmate;
    private final boolean promotion;
    private final char promotionPiece;

    public Move(Piece piece, String move, String move1, boolean capture, boolean check, boolean checkmate, boolean promotion, char promotionPiece) {
        this.piece = piece;
        this.source =  convertNotationToCoordinate(move.split(" ")[0]);
        this.destination = convertNotationToCoordinate(move.split(" ")[1]);
        this.move = move;
        this.capture = capture;
        this.check = check;
        this.checkmate = checkmate;
        this.promotion = promotion;
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

    public boolean isCapture() {
        return capture;
    }

    public boolean isCheck() {
        return check;
    }

    public boolean isCheckmate() {
        return checkmate;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public char getPromotionPiece() {
        return promotionPiece;
    }

    public static int[] convertNotationToCoordinate(String input) {
        int[] coordinate = new int[2];
        coordinate[1] = input.charAt(0) - 'A';
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
}
