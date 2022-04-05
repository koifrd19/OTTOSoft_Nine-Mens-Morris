package at.kaindorf.game;

import at.kaindorf.game.game.Game;
import at.kaindorf.game.game.GameLoop;

public class Launcher {
    public static void main(String[] args) {
        new Thread(new GameLoop(new Game(800, 600))).start();
    }
}
