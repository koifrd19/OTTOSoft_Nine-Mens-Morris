package at.kaindorf.mill.gui.menu;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class MenuDlg extends JDialog {
    private Clip clip;

    public MenuDlg(JFrame game, boolean modal) {
        super(game, modal);
        initComponents();
        setLocationRelativeTo(game);
    }

    private void initComponents(){
        ImageIcon background = new ImageIcon(this.getClass().getResource("/Abendmahl mit LB scaled.png"));
        ImageIcon buttonImage = new ImageIcon(this.getClass().getResource("/holzplanke.png"));
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

        JButton button = new JButton(buttonImage);
        button.setBounds(610, 250, 450, 74);
        button.setBorder(null);
        backgroundLabel.add(button);

        this.add(backgroundLabel);
        this.setSize(1680, 945);
        this.setTitle("OttoSoft's Nine Men's morris");


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clip.close();
                dispose();
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
