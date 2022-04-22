package at.kaindorf.game_old.game.state;

import at.kaindorf.game_old.entity.GameObject;
import at.kaindorf.game_old.input.Input;

import java.util.ArrayList;
import java.util.List;

public class State {
    protected List<GameObject> gameObjects;
    protected Input input;

    public State(Input input) {
        this.input = input;
        this.gameObjects = new ArrayList<>();
    }

    public void update() {
        gameObjects.forEach(gameObject -> gameObject.update());
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
}
