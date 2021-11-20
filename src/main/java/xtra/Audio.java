package xtra;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Audio {
    private Clip clip;

    public void stopAudio() {
        clip.stop();
    }

    public void afspilAudio(String filepath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("Lydfilen kan ikke afspilles.");
            e.printStackTrace();
        }
    }
}
