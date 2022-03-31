package at.kaindorf.game.gui;

import javax.swing.*;

public class GameFrame extends JFrame {

    private GameFrame(){
        initComponents();
    }

    private void initComponents(){
        setSize(1280,720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GameFrame();
    }
}
