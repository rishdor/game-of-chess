public abstract class Command {
    public abstract boolean endTheGame();
}

class QuitCommand extends Command {
    @Override
    public boolean endTheGame() {
        System.out.println("Game over.");
        return true;
    }
}

class RestartCommand extends Command {
    @Override
    public boolean endTheGame() {
        System.out.println("Restarting the game.");
        return true;
    }
}

class DrawCommand extends Command {
    @Override
    public boolean endTheGame() {
        System.out.println("Draw.");
        return true;
    }
}
