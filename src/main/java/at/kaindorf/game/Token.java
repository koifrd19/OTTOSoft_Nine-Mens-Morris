package at.kaindorf.game;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Token extends GameObject{



    @Override
    public void update() {
        position = new Position(position.getX() + 1, position.getY());
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
