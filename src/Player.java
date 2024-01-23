import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Player class represents a player in the chess game.
 */
class Player {
    private final String name;
    private final char color; // 'w' for white, 'b' for black

    /**
     * The constructor for the Player class.
     *
     * @param name A string representing the name of the player.
     * @param color A char indicating the color of the player's pieces. 'w' for white, 'b' for black.
     */
    public Player(String name, char color){
        this.name = name;
        this.color = color;
    }

    /**
     * Returns the name of the player.
     *
     * @return A string representing the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the player's pieces are white.
     *
     * @return A boolean indicating whether the player's pieces are white. True if white, false otherwise.
     */
    public boolean isWhite(){
        return color == 'w';
    }

    /**
     * Gets the player's command.
     *
     * @param board A Chessboard object representing the current state of the board.
     * @return A Command object representing the player's command.
     */
    public Command getCommand(Chessboard board) {
        Command command = null;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Your move: ");
        String input = scanner.nextLine();
        Pattern p = Pattern.compile("[a-hA-H][1-8] [a-hA-H][1-8]");
        Matcher m = p.matcher(input);

        if (m.matches()) {
            command = new Move(input, board);
        } else if (input.equalsIgnoreCase("quit")) {
            command = new QuitCommand();
        } else if (input.equalsIgnoreCase("restart")) {
            command = new RestartCommand();
        } else if (input.equalsIgnoreCase("draw")) {
            command = new DrawCommand();
        }

        return command;
    }
}