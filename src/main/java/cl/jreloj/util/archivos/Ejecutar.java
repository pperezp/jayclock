/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.jreloj.util.archivos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 *
 * @author Administrador
 */
public class Ejecutar {
    private static String salida = "";
    private static String error;
    private static Process proceso;

    public static void ejecutarArchivo(String programa, String ruta){
        error = "";

        try{
            proceso = Runtime.getRuntime().exec("cmd.exe /C "+programa+" "+ruta+"");

            BufferedReader brStdOut = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            BufferedReader brStdErr = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
            String str=null;

            while ((str = brStdOut.readLine())!=null){
                salida = salida + str + "\n";
            }
            while ((str = brStdErr.readLine())!=null){
                error = error + str + "\n";
            }


            brStdOut.close();
            brStdErr.close();
            proceso.destroy();
        }catch(Exception e){
        }
    }

    public static String ejecutarComandoLinux(String comando){
        error = "";
        try{
            Process p;
            String s = "";
            p = Runtime.getRuntime().exec(comando);

            BufferedReader brStdOut = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader brStdErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String str=null;

            while ((str = brStdOut.readLine())!=null){
                s = s + str + "\n";
            }
            while ((str = brStdErr.readLine())!=null){
                error = error + str + "\n";
            }
            

            brStdOut.close();
            brStdErr.close();
            p.destroy();
            p = null;
            return s.split(":")[3].substring(1, 3);
        }catch(Exception e){
            return null;
        }
    }

    public void crearCarpeta(String ruta){
        try{
            proceso = Runtime.getRuntime().exec("cmd.exe /C mkdir "+ruta);

            BufferedReader brStdOut = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            BufferedReader brStdErr = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
            String str=null;

            while ((str = brStdOut.readLine())!=null){
                salida = salida + str + "\n";
            }
            while ((str = brStdErr.readLine())!=null){
                error = error + str + "\n";
            }


            brStdOut.close();
            brStdErr.close();

            proceso.destroy();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
