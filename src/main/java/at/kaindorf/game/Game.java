package at.kaindorf.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private Display display;
    private List<GameObject> gameObjects;

    public Game(int width, int height) {
        this.display = new Display(width, height);
        this.gameObjects = new ArrayList<>();
        gameObjects.add(new Token());
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void update() {
        gameObjects.forEach(gameObject -> gameObject.update());
    }

    public void render() {
        display.render(this);
    }


}
