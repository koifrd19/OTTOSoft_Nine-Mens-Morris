package at.kaindorf.game.game;

import at.kaindorf.game.input.Input;
import at.kaindorf.game.controller.PlayerController;
import at.kaindorf.game.display.Display;
import at.kaindorf.game.entity.GameObject;
import at.kaindorf.game.entity.Token;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Display display;
    private List<GameObject> gameObjects;
    private Input input;

    public Game(int width, int height) {
        input = new Input();
        this.display = new Display(width, height, input);
        this.gameObjects = new ArrayList<>();
        gameObjects.add(new Token(new PlayerController(input)));
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
