package at.kaindorf.mill.Menu;

import at.kaindorf.mill.MillGame;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Menu extends JFrame {
    public static void main(String[] args) {
        new Menu().initComponents();
    }

    private void initComponents(){
        ImageIcon background = new ImageIcon(this.getClass().getResource("/Abendmahl mit LB scaled.png"));
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setSize(1680, 945);

        try {
            this.playAudio();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        JButton button = new JButton("Start Game");
        button.setBounds(760, 550, 140, 50);
        backgroundLabel.add(button);


            JFrame jFrame = new JFrame();
        //jFrame.setLayout(new GridLayout(1, 3));
        jFrame.add(backgroundLabel);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setSize(1680, 945);
        jFrame.setLocationRelativeTo(null);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MillGame().getJFrame().show();
                jFrame.dispose();
            }});
    }

    private void playAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("C:\\Users\\Allwinger\\OneDrive - HTBLA Kaindorf\\HTL\\3DHIF\\2022\\POS\\NineMensMorris\\src\\main\\resources\\audio.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }
}
