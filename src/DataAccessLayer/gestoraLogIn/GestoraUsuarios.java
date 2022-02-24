package DataAccessLayer.gestoraLogIn;


import DataAccessLayer.Conexion.DatosConexion;
import Entidades.Usuarios.Cliente;

import java.sql.*;

public class GestoraUsuarios {
    public static DatosConexion datosConexion = new DatosConexion();
/*todo --> Esta clase tendrá un objeto Connection, mediante el cual se haran las consultas a la base de datos de
    administradores*/

    /*todo --> Este metodo será llamado por la clase validaciones, y comprobará que una coonsulta a la tabla clientes
    con dichas credenciales existe
    */
    public static Cliente getClienteDni(String dni){
        /*TODO Instalar la base de datos la primera vez que se ejecute la aplicacion
        Comprobar si la bdd ha sido instalada cuando se ejecute la app, si no es asi hacerlo
         */
       Cliente cliente = null;
       String sql = "SELECT * FROM Clientes Where dni = ?";
       try(Connection c = datosConexion.getConexion()) {
           PreparedStatement statement = c.prepareStatement(sql);
           statement.setString(1,dni);
           ResultSet result = statement.executeQuery(sql);
           if(result.isBeforeFirst()){
               while(result.next()){
                   String nombreCliente = result.getString(1);
                   String  dniCliente = result.getString(2);
                   String  direccionCliente = result.getString(3);
                   int  codPostalCliente = result.getInt(4);
                   String  ciudadCliente = result.getString(5);
                   int  telefonoCliente = result.getInt(6);
                   String correoCliente = result.getString(7);
                   cliente = new Cliente(nombreCliente,dniCliente,direccionCliente,codPostalCliente,ciudadCliente,telefonoCliente,correoCliente);
               }
           }else{
               cliente=new Cliente(dni);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } ;
      //if cliente existe
        //cliente correcto
        // else cliente base no reg
        return cliente;
        }



    public static Cliente getClienteTelefono(String telefono){
        Cliente cliente = null;
        String sql = "SELECT * FROM Clientes Where telefonoContacto = ?";
        try(Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setString(1,telefono);
            ResultSet result = statement.executeQuery(sql);
            if(result.isBeforeFirst()){
                while(result.next()){
                    String nombreCliente = result.getString(1);
                    String  dniCliente = result.getString(2);
                    String  direccionCliente = result.getString(3);
                    int  codPostalCliente = result.getInt(4);
                    String  ciudadCliente = result.getString(5);
                    int  telefonoCliente = result.getInt(6);
                    String correoCliente = result.getString(7);
                    cliente = new Cliente(nombreCliente,dniCliente,direccionCliente,codPostalCliente,ciudadCliente,telefonoCliente,correoCliente);
                }
            }else{
                cliente=new Cliente(telefono);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public static boolean isAdmin(String usuario, String contrasena){

        String sql = "SELECT * FROM Gestores Where usuario = ? AND contraseña = ?";
        try(Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setString(1,telefono);
            ResultSet result = statement.executeQuery(sql);
            if(result.isBeforeFirst()){
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
