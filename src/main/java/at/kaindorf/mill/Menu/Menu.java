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
    private Clip clip;
    public static void main(String[] args) {
        new Menu().initComponents();
    }

    private void initComponents(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
                clip.stop();
            }});
    }

    private void playAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        String path = System.getProperty("user.dir");
        path +=  "\\src\\main\\resources\\audio.wav";
        File file = new File(path);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }
}
