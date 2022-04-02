package at.kaindorf.game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

    private Canvas canvas;

    public Display(int width, int height) {
        initComponents(width, height);
    }

    private void initComponents(int width, int height) {
        setTitle("OTTOsoft's Nine men's morris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);
        add(canvas);
        pack(); // Calculates size of the JFRAME

        canvas.createBufferStrategy(3); // Create two screen if not implemented then the screen flickers

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void render(Game game) {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        game.getGameObjects().forEach(gameObject -> graphics.drawImage(
                gameObject.getSprite(),
                gameObject.getPosition().getX(),
                gameObject.getPosition().getY(),
                null
        ));

        graphics.dispose(); // frees memory
        bufferStrategy.show(); // brings buffer to the front
    }
}
