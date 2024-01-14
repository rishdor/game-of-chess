import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Player {
    private final String name;
    private final char color; //w - white, b - black

    public Player(String name, char color){
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public boolean isWhite(){
        return color == 'w';
    }

    public Command getCommand(Chessboard board) {
        Command command = null;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Your move: ");
        String input = scanner.nextLine();
        Pattern p = Pattern.compile("[A-H][1-8] [A-H][1-8]");
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
