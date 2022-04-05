package at.kaindorf.game.display;

import at.kaindorf.game.game.Game;
import at.kaindorf.game.input.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

    private Canvas canvas;
    private Renderer renderer;

    public Display(int width, int height, Input input) {
        initComponents(width, height, input);
        this.renderer = new Renderer();
    }

    private void initComponents(int width, int height, Input input) {
        setTitle("OTTOsoft's Nine men's morris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);
        add(canvas);
        addKeyListener(input);
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

        renderer.render(game, graphics);

        graphics.dispose(); // frees memory
        bufferStrategy.show(); // brings buffer to the front
    }
}
