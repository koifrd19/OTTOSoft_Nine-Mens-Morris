package at.kaindorf.game_old.entity;

import at.kaindorf.game_old.controller.Controller;
import at.kaindorf.game_old.corehelper.Movement;

public abstract class MovingEntity extends GameObject {
    private Controller controller;
    private Movement movement;

    public MovingEntity(Controller controller) {
        super();
        this.controller = controller;
        this.movement = new Movement(2);
    }

    @Override
    public void update(){
        movement.update(controller);
        position.apply(movement);
    }

}
