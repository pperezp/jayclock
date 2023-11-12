package cl.jreloj.audio;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Player extends Thread {

    private AudioFile[] audioFiles;

    public Player(AudioFile... audioFiles) {
        this.audioFiles = audioFiles;
    }

    @Override
    public void run() {
        try {
            for (AudioFile audioFile : audioFiles) {
                audioFile.play(true);
                Thread.sleep(audioFile.getMilliseconds());
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void play() {
        this.start();
    }

    public long getMilliseconds() {
        long milliseconds = 0;

        for (AudioFile af : audioFiles) {
            milliseconds += af.getMilliseconds();
        }

        return milliseconds;
    }
}
