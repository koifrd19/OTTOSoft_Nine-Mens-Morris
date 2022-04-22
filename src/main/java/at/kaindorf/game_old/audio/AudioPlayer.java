package at.kaindorf.game_old.audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AudioPlayer {

    private List<AudioClip> audioClips;

    public AudioPlayer() {
        this.audioClips = new ArrayList<>();
    }

    public void playMusic(String fileName){

    }

    public Clip getClip(String fileName){
        final URL soundFile = AudioPlayer.class.getResource("/sound/" + fileName);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setMicrosecondPosition(0);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        return null;
    }
}
