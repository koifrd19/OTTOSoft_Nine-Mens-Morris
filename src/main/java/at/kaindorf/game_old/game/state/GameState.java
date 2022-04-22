package at.kaindorf.game_old.game.state;

import at.kaindorf.game_old.controller.PlayerController;
import at.kaindorf.game_old.entity.Token;
import at.kaindorf.game_old.input.Input;

public class GameState extends State{
    public GameState(Input input) {
        super(input);
        gameObjects.add(new Token(new PlayerController(input)));
    }

}
