package DataAccessLayer.Conexion;

import Vistas.Constantes;
import Vistas.Mensajes;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Esta clase se encargará de devolver la cadena de conexión para todas las clases que lo necesiten.
 * Si se desea cambiar algun dato deberá ser modificado en el fichero de propiedades.
 */
public class DatosConexion {

    private Properties propiedadesConexion;
    private Scanner tecla = new Scanner(System.in);
    private String nombreBDD = "LaurinoRuiz";

    public DatosConexion() {
        propiedadesConexion = new Properties();
        cargarFicheroConfig();
    }

    public void pedirYValidarPropiedadesConexion() {
        String host, puerto, usuario, contraseña ;
        do{
            System.out.println("Introduzca los datos para la conexión. Solo se le pedirán la primera vez que inicie la " +
                    "aplicación");
            System.out.println("Host: ");
            host = tecla.nextLine();
            System.out.println("Puerto: ");
            puerto = tecla.nextLine();
            System.out.println("Usuario: ");
            usuario = tecla.nextLine();
            System.out.println("Contraseña");
            contraseña = tecla.nextLine();
        }while(!comprobarConexion(host,puerto,usuario,contraseña));
        guardarDatosConfiguracion(host, puerto, usuario, contraseña, true);
    }

    /**
     * Obtiene una conexión Sql.
     * @return Connection
     */

    public Connection getConexion() {
        Connection conn = null;
        try {
            String url = getConnectionUrl( propiedadesConexion.getProperty("Host"),propiedadesConexion.getProperty("Puerto"));
            conn = DriverManager.getConnection(url, propiedadesConexion.getProperty("Usuario"), propiedadesConexion.getProperty("Clave"));
        } catch (SQLException e) {
            System.out.println(Mensajes.MSG_FAILURE_CONNECTION);
        }
        return conn;
    }

    public boolean bddIsCreated(){
        String creada = propiedadesConexion.getProperty("bddCreada");
        boolean isCreada= true;
        if(creada.equals("false")){
            isCreada = false;
        }
        return isCreada;
    }



    private void guardarDatosConfiguracion(String host, String puerto, String usuario,String contraseña, boolean bddCreada) {
        propiedadesConexion.put("Host", host);
        propiedadesConexion.put("Puerto", puerto);
        propiedadesConexion.put("Usuario", usuario);
        propiedadesConexion.put("Clave", contraseña);
        setBddCreated(true);
        escribirPropiedadesEnFichero();
    }

    private void escribirPropiedadesEnFichero(){
        FileOutputStream os = null;
        try {
            os=new FileOutputStream(Constantes.NOMBRE_FICHERO_PROPIEDADES);
            propiedadesConexion.store(os, null);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private boolean comprobarConexion (String host, String puerto, String usuario,String contraseña){
        boolean conexionEstablecida = false;
        try {
            String url = getConnectionUrl(host,puerto);
            Connection conn = DriverManager.getConnection(url,usuario,contraseña);
            conexionEstablecida =true;
        } catch (SQLException e) {
            System.out.println(Mensajes.MSG_FAILURE_CONNECTION);
        }
        return conexionEstablecida;
    }


    private void cargarFicheroConfig() {
        try {
            FileInputStream fis = new FileInputStream(Constantes.NOMBRE_FICHERO_PROPIEDADES);
            propiedadesConexion.load(fis);
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setBddCreated(boolean isCreated){
        if(isCreated){
            propiedadesConexion.put("bddCreada", "true");
        }else{
            propiedadesConexion.put("bddCreada", "false");
        }
    }


    private String getConnectionUrl(String host,String puerto) {
        return "jdbc:sqlserver://"+ host +":"+puerto+";databaseName="+nombreBDD+";trustServerCertificate=true";
    }


}
