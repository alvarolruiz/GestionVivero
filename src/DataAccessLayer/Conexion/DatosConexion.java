package DataAccessLayer.Conexion;

import Vistas.Constantes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
           conn = DriverManager.getConnection(getConnectionString());
           if(conn!=null){
               System.out.println(Constantes.MSG_SUCCESSFUL_CONNECTION);
           }
       }catch (SQLException e){
           System.out.println(Constantes.MSG_FAILURE_CONNECTION);
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
            propiedadesConexion.load(new FileInputStream(new File(Constantes.NOMBRE_FICHERO_PROPIEDADES)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Devuelve una cadena con los datos de la conexión obtenidos del fichero de propiedades
     * @return String
     */
   private String getConnectionString(){
        return propiedadesConexion.getProperty("Url") + propiedadesConexion.getProperty("Usuario")
                + propiedadesConexion.getProperty("Clave");
   }
}
