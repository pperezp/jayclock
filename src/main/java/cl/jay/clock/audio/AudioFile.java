package cl.jay.clock.audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AudioFile implements Runnable {

    private AudioInputStream audioInputStream;
    private DataLine.Info info;
    private Clip clip;
    private long milliseconds;

    public AudioFile(InputStream inputStream) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        createClip(new BufferedInputStream(inputStream));
    }

    public void play(boolean playNow) {
        Thread thread = new Thread(this);
        if (playNow) {
            thread.start();
        }
    }

    @Override
    public void run() {
        clip.start();
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    private void createClip(InputStream inputStream) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(inputStream);
        info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(audioInputStream);
        this.milliseconds = clip.getMicrosecondLength() / 1000;
    }
}
