package at.kaindorf.game_old.game;

import at.kaindorf.game_old.game.state.GameState;
import at.kaindorf.game_old.game.state.State;
import at.kaindorf.game_old.input.Input;
import at.kaindorf.game_old.display.Display;

public class Game {
    private Display display;
    private Input input;
    private State state;

    public Game(int width, int height) {
        input = new Input();
        this.display = new Display(width, height, input);
        state = new GameState(input);
    }

    public void update(){
        state.update();
    }

    public void render() {
        display.render(state);
    }


}
