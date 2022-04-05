package at.kaindorf.game.entity;

import at.kaindorf.game.controller.Controller;
import at.kaindorf.game.corehelper.Position;
import at.kaindorf.game.entity.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Token extends GameObject {

    private Controller controller;

    public Token(Controller controller) {
        super();
        this.controller = controller;
    }

    @Override
    public void update() {
        int deltaX = 0;
        int deltaY = 0;

        if (controller.isRequestingUp()){
            deltaY--;
        }

        if (controller.isRequestingDown()){
            deltaY++;
        }

        if (controller.isRequestingLeft()){
            deltaX--;
        }

        if (controller.isRequestingRight()){
            deltaX++;
        }

        position = new Position(position.getX() + deltaX, position.getY() + deltaY);
    }

    @Override
    public Image getSprite() {
        BufferedImage image = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics();

        graphics2D.setColor(Color.lightGray);
        graphics2D.fillOval(0,0, size.getWidth(), size.getHeight());
        graphics2D.setColor(Color.RED);
        graphics2D.drawOval(5,5, size.getWidth() - 10, size.getHeight() - 10);
        graphics2D.drawOval(15,15, size.getWidth() - 30, size.getHeight() - 30);
        graphics2D.dispose();



        return image;
    }
}
