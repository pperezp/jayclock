/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.jreloj.util.hora;

import cl.jreloj.jaudio.basico.AudioFile;
import cl.jreloj.jaudio.basico.Reproductor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Pato Pérez
 */
public class HoraAudio {

    public static String RUTA_AUDIOS = "/sounds/";

    public static Reproductor decirHora(int hora, int minuto) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        String primeraParte = "son_las";
        String jornada;
        String min = Integer.toString(minuto);
        boolean horaNormal = true;

        switch (minuto) {
            case 15: {
                min = "y_cuarto";
                break;
            }
            case 30: {
                min = "y_media";
                break;
            }
            case 35: {
                horaNormal = false;
                primeraParte = "faltan";
                if (hora == 0) {
                    min = "25_para_la";
                } else {
                    min = "25_para_las";
                }
                break;
            }
            case 40: {
                horaNormal = false;
                primeraParte = "faltan";
                if (hora == 0) {
                    min = "20_para_la";
                } else {
                    min = "20_para_las";
                }
                break;
            }
            case 45: {
                horaNormal = false;
                primeraParte = "son";
                if (hora == 0) {
                    min = "un_cuarto_para_la";
                } else {
                    min = "un_cuarto_para_las";
                }
                break;
            }
            case 50: {
                horaNormal = false;
                primeraParte = "faltan";
                if (hora == 0) {
                    min = "10_para_la";
                } else {
                    min = "10_para_las";
                }
                break;
            }
            case 55: {
                horaNormal = false;
                primeraParte = "faltan";
                if (hora == 0) {
                    min = "5_para_la";
                } else {
                    min = "5_para_las";
                }
                break;
            }
        }

        if (!horaNormal) {
            hora = getProximaHora(hora);
        }

        if (hora >= 0 && hora <= 6) {
            jornada = "de_la_madrugada";
        } else if (hora >= 7 && hora <= 11) {
            jornada = "de_la_mañana";
        } else if (hora >= 12 && hora <= 20) {
            jornada = "de_la_tarde";
        } else {
            jornada = "de_la_noche";
        }

        hora = getHora(hora);

        AudioFile fpp, fhora, fmin = null, fjorn;

        fpp = new AudioFile(getResourceAsStream(primeraParte + ".wav"));
        fhora = new AudioFile(getResourceAsStream(hora + ".wav"));
        if (hora == 1) {
            fhora = new AudioFile(getResourceAsStream("una.wav"));
        }
        if (!min.equalsIgnoreCase("0")) {
            fmin = new AudioFile(getResourceAsStream(min + ".wav"));
        }

        fjorn = new AudioFile(getResourceAsStream(jornada + ".wav"));

        Reproductor hr;
        if (min.equalsIgnoreCase("0")) {
            hr = new Reproductor(fpp, fhora, fjorn, new AudioFile(getResourceAsStream("en_punto.wav")));
        } else if (horaNormal) {
            hr = new Reproductor(fpp, fhora, fmin, fjorn);
        } else {
            hr = new Reproductor(fpp, fmin, fhora, fjorn);
        }
        hr.play();
        return hr;
    }

    public static Reproductor decirHora() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        return decirHora(Integer.parseInt(Hora.getHora()), Integer.parseInt(Hora.getMinuto()));
    }

    private static InputStream getResourceAsStream(String resource) {
        return new BufferedInputStream(HoraAudio.class.getResourceAsStream(RUTA_AUDIOS + resource));
    }


    private static int getHora(int hora) {
        if (hora > 12 && hora <= 23) {
            hora = hora - 12;
        }
        if (hora == 0) {
            return 12;
        }
        return hora;
    }

    private static int getProximaHora(int hora) {
        if (hora == 23) {
            return 0;
        }
        return hora + 1;
    }
}
