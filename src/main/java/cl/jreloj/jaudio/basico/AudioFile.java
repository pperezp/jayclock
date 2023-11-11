package cl.jreloj.jaudio.basico;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.*;

/**
 *
 * @author Pato Pérez
 */
public class AudioFile implements Runnable {
    private AudioInputStream source;
    private DataLine.Info info;
    private Clip clip;
    private long miliSegundos;

    public AudioFile(InputStream inputStream) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        construirClip(new BufferedInputStream(inputStream));
    }

    public Thread play(boolean playAhora) {
        Thread hilo = new Thread(this);
        if (playAhora) hilo.start();
        return hilo;
    }

    @Override
    public void run() {
        clip.start();
    }

    public long getMiliSegundos() {
        return miliSegundos;
    }

    private void construirClip(InputStream inputStream) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        source = AudioSystem.getAudioInputStream(inputStream);
        info = new DataLine.Info(Clip.class, source.getFormat());
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(source);
        this.miliSegundos = clip.getMicrosecondLength() / 1000;
    }
}
