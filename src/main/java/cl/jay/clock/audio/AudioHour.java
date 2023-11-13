package cl.jay.clock.audio;

import cl.jay.clock.util.Hour;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AudioHour {

    public static String AUDIOS_PATH = "/sounds/";

    public static Player playHour(int hour, int minute) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        String firstPart = "son_las";
        String time;
        String minuteString = Integer.toString(minute);
        boolean normalHour = true;

        switch (minute) {
            case 15: {
                minuteString = "y_cuarto";
                break;
            }
            case 30: {
                minuteString = "y_media";
                break;
            }
            case 35: {
                normalHour = false;
                firstPart = "faltan";

                if (hour == 0) {
                    minuteString = "25_para_la";
                } else {
                    minuteString = "25_para_las";
                }

                break;
            }
            case 40: {
                normalHour = false;
                firstPart = "faltan";

                if (hour == 0) {
                    minuteString = "20_para_la";
                } else {
                    minuteString = "20_para_las";
                }

                break;
            }
            case 45: {
                normalHour = false;
                firstPart = "son";

                if (hour == 0) {
                    minuteString = "un_cuarto_para_la";
                } else {
                    minuteString = "un_cuarto_para_las";
                }

                break;
            }
            case 50: {
                normalHour = false;
                firstPart = "faltan";

                if (hour == 0) {
                    minuteString = "10_para_la";
                } else {
                    minuteString = "10_para_las";
                }

                break;
            }
            case 55: {
                normalHour = false;
                firstPart = "faltan";

                if (hour == 0) {
                    minuteString = "5_para_la";
                } else {
                    minuteString = "5_para_las";
                }

                break;
            }
        }

        if (!normalHour) {
            hour = getNextHour(hour);
        }

        if (hour >= 0 && hour <= 6) {
            time = "de_la_madrugada";
        } else if (hour >= 7 && hour <= 11) {
            time = "de_la_mañana";
        } else if (hour >= 12 && hour <= 20) {
            time = "de_la_tarde";
        } else {
            time = "de_la_noche";
        }

        hour = getHour(hour);

        AudioFile fpp, fHour, fMinute = null, fTime;

        fpp = new AudioFile(getResourceAsStream(firstPart + ".wav"));
        fHour = new AudioFile(getResourceAsStream(hour + ".wav"));
        if (hour == 1) {
            fHour = new AudioFile(getResourceAsStream("una.wav"));
        }
        if (!minuteString.equalsIgnoreCase("0")) {
            fMinute = new AudioFile(getResourceAsStream(minuteString + ".wav"));
        }

        fTime = new AudioFile(getResourceAsStream(time + ".wav"));

        Player hr;

        if (minuteString.equalsIgnoreCase("0")) {
            hr = new Player(fpp, fHour, fTime, new AudioFile(getResourceAsStream("en_punto.wav")));
        } else if (normalHour) {
            hr = new Player(fpp, fHour, fMinute, fTime);
        } else {
            hr = new Player(fpp, fMinute, fHour, fTime);
        }

        hr.play();

        return hr;
    }

    public static Player playHour() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        return playHour(Integer.parseInt(Hour.getCalendar()), Integer.parseInt(Hour.getMinute()));
    }

    private static InputStream getResourceAsStream(String resource) {
        return new BufferedInputStream(AudioHour.class.getResourceAsStream(AUDIOS_PATH + resource));
    }


    private static int getHour(int hour) {
        if (hour > 12 && hour <= 23) {
            hour = hour - 12;
        }

        if (hour == 0) {
            return 12;
        }

        return hour;
    }

    private static int getNextHour(int hour) {
        if (hour == 23) {
            return 0;
        }

        return hour + 1;
    }
}
