package DataAccessLayer.Conexion;

import Vistas.Mensajes;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Esta clase se encargará de devolver la cadena de conexión para todas las clases que lo necesiten.
 * Si se desea cambiar algun dato deberá ser modificado en el fichero de propiedades.
 *
 */
public class DatosConexion {
//TODO Conseguir que funcione la conexion. Da un problema con el puerto 1433 y con ssl

    private Properties propiedadesConexion;

    public DatosConexion() {
        propiedadesConexion= new Properties();
        cargarFicheroConfig();
    }

    /**
     * Obtiene una conexión Sql. Si la conexión es correcta se imprimirá en consola un mensaje de
     * éxito, en caso contrario un mensaje de error.
     * @return Connection
     */
   public Connection getConexion(){
       Connection conn = null;
       try{
           conn = DriverManager.getConnection(getConnectionString(), propiedadesConexion.getProperty("Usuario"), propiedadesConexion.getProperty("Clave"));
       }catch (SQLException e) {
           System.out.println(Mensajes.MSG_FAILURE_CONNECTION);
       }
       return conn;

   }

   public void closeConnection (Connection c) throws SQLException {
       c.close();
   }

    /**
     * Carga los datos de un fichero de propiedades en la instancia local de properties
     */

    private void cargarFicheroConfig() {
        try {
            FileInputStream fis  =  new FileInputStream("D:\\IdeaProjects\\GestionVivero\\src\\DataAccessLayer\\Conexion\\ConfiguracionConexion.properties");
            propiedadesConexion.load(fis);
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Devuelve una cadena con los datos de la conexión obtenidos del fichero de propiedades
     * @return String
     */
   private String getConnectionString(){
       String url = propiedadesConexion.getProperty("Url");
       return url;
   }
}
