/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.util;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import xml.analizador.dom.JespXML;
import xml.analizador.dom.modelo.Tag;

/**
 *
 * @author Pato Pérez
 */
public class Config {

    /*Si quieres agragar una nueva configuracion, haz lo siguiente:
        1.- Crea el campo y los metodos en Config.java
        2.- Crea el metodo para actualizar el xml en Actualizar.java
        3.- Modifica el metodo crearXMLConfiguracion() y agrega la configuracion creada*/
    private float opacidad;
    private List<Recordatorio> recordatorios;
    //el prefijo c quiere decir Color
    private Color c_Hora;
    private Color c_Fecha;
    private Color c_Fondo;
    private Color c_Titulo;
    private Color c_HoraBack;
    private Color c_MinutoBack;
    private Color c_HoraFront;
    private Color c_MinutoFront;
    private Color c_SegundoFront;
    private Color c_SegundoBack;
    private Color c_SistemaOperativo;
    private Color c_Gpu;
    //posicion de la ventana principal
    private double x, y;
    private boolean siempreEncima;

    public Config() {
        try {
            Tag tConfig = JespXML.leerXML(new File(Ruta.ARCHIVO_CONFIGURACION));
            Tag tRecordatorios = JespXML.leerXML(new File(Ruta.ARCHIVO_RECORDATORIOS));


            //ciclo para rescatar los colores del XML
            for (Tag t : tConfig.getTagsHijos()) {
                if (t.getNombre().equalsIgnoreCase("color")) {
                    Tag colores = t.getTagHijo(Tag.CANTIDAD.primeraOcurrencia,
                            t.getValorDeAtributo("escogido")).get(0);

                    guardarColores(colores);
                } else if (t.getNombre().equalsIgnoreCase("opacidad")) {
                    opacidad = Float.parseFloat(t.getValorDeAtributo("nivel"));
                } else if(t.getNombre().equalsIgnoreCase("posicion")){
                    x = Double.parseDouble(t.getValorDeAtributo("x"));
                    y = Double.parseDouble(t.getValorDeAtributo("y"));
                } else if(t.getNombre().equalsIgnoreCase("siempreEncima")){
                    siempreEncima = Boolean.valueOf(t.getValorDeAtributo("valor"));
                }
            }
            
            //ciclo para rescatar los recordatorios del XML
            recordatorios = new ArrayList<>();
            for(Tag rec : tRecordatorios.getTagsHijos()){
                Tag tFecha = rec.getTagHijo(Tag.CANTIDAD.primeraOcurrencia, "fecha").get(0);
                Tag tHora = rec.getTagHijo(Tag.CANTIDAD.primeraOcurrencia, "hora").get(0);
                Tag tMensaje = rec.getTagHijo(Tag.CANTIDAD.primeraOcurrencia, "mensaje").get(0);
                boolean visto = Boolean.valueOf(rec.getValorDeAtributo("visto"));
                
                recordatorios.add(new Recordatorio(tFecha.getContenido(), tMensaje.getContenido(), tHora.getContenido(), visto));
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isSiempreEncima() {
        return siempreEncima;
    }

    public void setSiempreEncima(boolean siempreEncima) {
        this.siempreEncima = siempreEncima;
    }
    
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public Color getColorFecha() {
        return c_Fecha;
    }

    public Color getColorFondo() {
        return c_Fondo;
    }

    public Color getColorGpu() {
        return c_Gpu;
    }

    public Color getColorHora() {
        return c_Hora;
    }

    public Color getColorHoraBack() {
        return c_HoraBack;
    }

    public Color getColorHoraFront() {
        return c_HoraFront;
    }

    public Color getColorMinutoBack() {
        return c_MinutoBack;
    }

    public Color getColorMinutoFront() {
        return c_MinutoFront;
    }

    public Color getColorSegundoFront() {
        return c_SegundoFront;
    }

    public Color getColorSistemaOperativo() {
        return c_SistemaOperativo;
    }

    public Color getColorTitulo() {
        return c_Titulo;
    }

    public float getOpacidad() {
        return opacidad;
    }

    public List<Recordatorio> getRecordatorios(boolean soloNoVistos) {
        if(!soloNoVistos)return recordatorios;
        else{
            List<Recordatorio> l = new ArrayList<>();
            for(Recordatorio r: recordatorios){
                if(!r.isVisto()){
                    l.add(r);
                }
            }
            return l;
        }
    }

    public Color getColorSegundoBack() {
        return c_SegundoBack;
    }

    private void guardarColores(Tag colores) {
        for (Tag comp : colores.getTagsHijos()) {
            switch (comp.getValorDeAtributo("id")) {
                case "fondo": {
                    c_Fondo = new Color(Integer.parseInt(comp.getValorDeAtributo("color")));
                    break;
                }
                case "hora": {
                    c_Hora = new Color(Integer.parseInt(comp.getValorDeAtributo("color")));
                    break;
                }
                case "fecha": {
                    c_Fecha = new Color(Integer.parseInt(comp.getValorDeAtributo("color")));
                    break;
                }
                case "titulo": {
                    c_Titulo = new Color(Integer.parseInt(comp.getValorDeAtributo("color")));
                    break;
                }
                case "segundoFront": {
                    c_SegundoFront = new Color(Integer.parseInt(comp.getValorDeAtributo("color")));
                    break;
                }
                case "segundoBack": {
                    c_SegundoBack = new Color(Integer.parseInt(comp.getValorDeAtributo("color")));
                    break;
                }
                case "horaFront": {
                    c_HoraFront = new Color(Integer.parseInt(comp.getValorDeAtributo("color")));
                    break;
                }
                case "horaBack": {
                    c_HoraBack = new Color(Integer.parseInt(comp.getValorDeAtributo("color")));
                    break;
                }
                case "minutoFront": {
                    c_MinutoFront = new Color(Integer.parseInt(comp.getValorDeAtributo("color")));
                    break;
                }
                case "minutoBack": {
                    c_MinutoBack = new Color(Integer.parseInt(comp.getValorDeAtributo("color")));
                    break;
                }
                case "sistemaOperativo": {
                    c_SistemaOperativo = new Color(Integer.parseInt(comp.getValorDeAtributo("color")));
                    break;
                }
                case "gpu": {
                    c_Gpu = new Color(Integer.parseInt(comp.getValorDeAtributo("color")));
                    break;
                }
            }
        }
    }
}
