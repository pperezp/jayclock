/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.util;

import cl.hora.Aplicacion;
import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import xml.analizador.dom.JespXML;
import xml.analizador.dom.modelo.Atributo;
import xml.analizador.dom.modelo.CData;
import xml.analizador.dom.modelo.Tag;

/**
 *
 * @author Pato Pérez
 */
public class Actualizar {
    private static Tag tConfig;
    private static Tag tRecordatorios;
    
    public static void opacidad(float nuevoPorcentaje){
        cargarConfig();
        Tag tOpacidad = tConfig.getTagHijo(Tag.CANTIDAD.primeraOcurrencia, "opacidad").get(0);
        tOpacidad.actualizarValorAtributo("nivel", String.valueOf(nuevoPorcentaje));
        escribirConfig();
    }
    
    public static void colorDeComponente(String id, Color color){
        cargarConfig();
        Tag tColor = tConfig.getTagHijo(Tag.CANTIDAD.primeraOcurrencia, "color").get(0);
        tColor.actualizarValorAtributo("escogido", "modificado");
        Tag tModif = tColor.getTagHijo(Tag.CANTIDAD.primeraOcurrencia, "modificado").get(0);
        
        for(Tag comp : tModif.getTagsHijos()){
            if(comp.getValorDeAtributo("id").equalsIgnoreCase(id)){
                comp.actualizarValorAtributo("color", color.getRGB());
                break;
            }
        }
        
        escribirConfig();
    }
    
    public static void dejarTemaPorDefecto(){
        cargarConfig();
        Tag tColor = tConfig.getTagHijo(Tag.CANTIDAD.primeraOcurrencia, "color").get(0);
        tColor.actualizarValorAtributo("escogido", "porDefecto");
        escribirConfig();
    }
    
    public static void addRecordatorio(Recordatorio rec){
        cargarRecordatorios();
        
        Tag recordatorio = new Tag("recordatorio");
        
        Atributo visto = new Atributo("visto", String.valueOf(rec.isVisto()));
        
        Tag fecha, hora, mensaje;
        
        fecha = new Tag("fecha");
        hora = new Tag("hora");
        mensaje = new Tag("mensaje");
        
        fecha.addContenido(rec.getFecha());
        hora.addContenido(rec.getHora());
        mensaje.setValorCdata(new CData(rec.getMensaje()));
        
        recordatorio.addAtributo(visto);
        recordatorio.addTagHijo(fecha);
        recordatorio.addTagHijo(hora);
        recordatorio.addTagHijo(mensaje);
        
        tRecordatorios.addTagHijo(recordatorio);
        escribirRecordatorios();
    }
    
    public static void posicion(Point p){
        cargarConfig();
        Tag tPosicion = tConfig.getTagHijo(Tag.CANTIDAD.primeraOcurrencia, "posicion").get(0);
        tPosicion.actualizarValorAtributo("x", (int)p.getX());
        tPosicion.actualizarValorAtributo("y", (int)p.getY());
        escribirConfig();
    }
    
    public static void siempreEncima(boolean siempreEncima){
        cargarConfig();
        Tag tSiempreEncima = tConfig.getTagHijo(Tag.CANTIDAD.primeraOcurrencia, "siempreEncima").get(0);
        tSiempreEncima.actualizarValorAtributo("valor", String.valueOf(siempreEncima));
        escribirConfig();
    }
    
    private static void cargarConfig(){
        try {
            tConfig = JespXML.leerXML(new File(Ruta.ARCHIVO_CONFIGURACION));
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void escribirConfig(){
        try {
            JespXML.escribirXML(tConfig, new File(Ruta.ARCHIVO_CONFIGURACION));
            Aplicacion.config = new Config();
        } catch (ParserConfigurationException | FileNotFoundException | TransformerException ex) {
            Logger.getLogger(Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void cargarRecordatorios(){
        try {
            tRecordatorios = JespXML.leerXML(new File(Ruta.ARCHIVO_RECORDATORIOS));
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void escribirRecordatorios(){
        try {
            JespXML.escribirXML(tRecordatorios, new File(Ruta.ARCHIVO_RECORDATORIOS));
            Aplicacion.config = new Config();
        } catch (ParserConfigurationException | FileNotFoundException | TransformerException ex) {
            Logger.getLogger(Actualizar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
