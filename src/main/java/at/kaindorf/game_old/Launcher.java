package at.kaindorf.game_old;

import at.kaindorf.game_old.game.Game;
import at.kaindorf.game_old.game.GameLoop;

public class Launcher {
    public static void main(String[] args) {
        new Thread(new GameLoop(new Game(800, 600))).start();
    }
}
