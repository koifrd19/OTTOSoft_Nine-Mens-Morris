package at.kaindorf.game_old.entity;

// Template for each game object if needed because there is just one the token

import at.kaindorf.game_old.corehelper.Position;
import at.kaindorf.game_old.corehelper.Size;

import java.awt.*;

public abstract class GameObject {
    protected Position position;
    protected Size size;

    public GameObject() {
        this.position = new Position(50, 50);
        size = new Size(50, 50);
    }

    public abstract void update();
    public abstract Image getSprite();

    public Position getPosition() {
        return position;
    }

    public Size getSize() {
        return size;
    }
}
