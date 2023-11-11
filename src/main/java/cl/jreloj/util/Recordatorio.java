/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.jreloj.util;

/**
 *
 * @author Pato Pérez
 */
public class Recordatorio {
    private String fecha;
    private String mensaje; 
    private String hora;
    private boolean visto;

    public Recordatorio(String fecha, String mensaje, String hora, boolean visto) {
        this.fecha = fecha;
        this.mensaje = mensaje;
        this.hora = hora;
        this.visto = visto;
    }

    public Recordatorio(String fecha, String mensaje, String hora) {
        this.fecha = fecha;
        this.mensaje = mensaje;
        this.hora = hora;
        visto = false;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }
    
    
}
