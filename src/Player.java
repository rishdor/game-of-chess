import java.util.Scanner;

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

    public String getMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Your move: ");
        return scanner.nextLine();
    }
}
