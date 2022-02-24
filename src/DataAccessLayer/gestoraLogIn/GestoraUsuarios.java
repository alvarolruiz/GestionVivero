package DataAccessLayer.gestoraLogIn;


import DataAccessLayer.Conexion.DatosConexion;
import Entidades.Usuarios.Administradores.GestorBDD;
import Entidades.Usuarios.Administradores.Vendedor;
import Entidades.Usuarios.Cliente;
import Vistas.Constantes;
import Vistas.Menu;

import java.sql.*;

public class GestoraUsuarios {
    /*
    Clase terminada. Posee metodos publicos para devolver un cliente, un gestor, y un vendedor. Además tambien contiene
    Métodos que devolverán booleanos que simplemente comrpobarán si existe el usuario en la base de datos. Estos métodos
    Serán llamados desde la clase validaciones
    */

    public static DatosConexion datosConexion = new DatosConexion();

    public static Cliente getClienteDni(String dni) throws SQLException {
        ResultSet result;
        Cliente cliente = null;
        String sql = "SELECT * FROM Clientes Where dni = ?";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setString(1, dni);
            result = statement.executeQuery(sql);
            if (result.isBeforeFirst()) {
                cliente = getDatosDeCliente(result);
            } else {
                sql = "Select * FROM Clientes WHERE idCliente =?";
                statement = c.prepareStatement(sql);
                statement.setString(1, String.valueOf(Constantes.ID_CLIENTE_NO_REGISTRADO);
                result = statement.executeQuery(sql);
                cliente = getDatosDeCliente(result);
            }
        }
        return cliente;
    }

    public static Cliente getClienteTelefono(int telefono) {
        Cliente cliente = null;
        ResultSet result = null;
        String sql = "SELECT * FROM Clientes Where telefonoContacto = ?";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setString(1, String.valueOf(telefono);
            result = statement.executeQuery(sql);
            if (result.isBeforeFirst()) {
                cliente = getDatosDeCliente(result);
            } else {
                sql = "Select * FROM Clientes WHERE idCliente =?";
                statement = c.prepareStatement(sql);
                statement.setString(1, String.valueOf(Constantes.ID_CLIENTE_NO_REGISTRADO);
                result = statement.executeQuery();
                cliente = getDatosDeCliente(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public static char tipoAdministrador(String usuario) {
        char tipoUsuario = Constantes.ADMIN_NULO;
        if (isAdmin(usuario)) {
            tipoUsuario = Constantes.ADMIN_GESTOR;
        } else if (isSeller(usuario)) {
            tipoUsuario = Constantes.ADMIN_VENDEDOR;
        }
        return tipoUsuario;
    }

    private static boolean isAdmin(String usuario) {
        String sql = "SELECT * FROM Gestores Where usuario = ?";
        return isRegistered(usuario, sql);
    }

    private static boolean isSeller(String usuario) {
        String sql = "SELECT * FROM Vendedores Where usuario = ?";
        return isRegistered(usuario, sql);
    }

    private static boolean isRegistered(String usuario, String sql) {
        boolean exists = false;
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setString(1, usuario);
            ResultSet result = statement.executeQuery(sql);
            if (result.isBeforeFirst()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public static GestorBDD getGestorBDD(String usuario, String constraseña) throws SQLException {
        GestorBDD gestorBDD = null;
        ResultSet resultSet;
        String sql = "SELECT * FROM Gestores Where usuario = ? AND contraseña = ?";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setString(1, usuario);
            statement.setString(2, constraseña);
            resultSet = statement.executeQuery(sql);
            if (resultSet.isBeforeFirst()) {
                gestorBDD = (GestorBDD) getDatosGestor(resultSet);
            } else {
                System.out.println(Menu.MSG_ERROR_LOGIN_GESTOR);
            }
        }
        return gestorBDD;
    }

    public static Vendedor getVendedor(String usuario, String constraseña) throws SQLException {
        Vendedor vendedor = null;
        ResultSet resultSet;
        String sql = "SELECT * FROM Vendedores Where usuario = ? AND contraseña = ?";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setString(1, usuario);
            statement.setString(2, constraseña);
            resultSet = statement.executeQuery(sql);
            if (resultSet.isBeforeFirst()) {
                vendedor = (Vendedor) getDatosVendedor(resultSet);
            } else {
                System.out.println(Menu.MSG_ERROR_LOGIN_GESTOR);
            }
        }
        return vendedor;
    }

    private static Vendedor getDatosVendedor(ResultSet resultSet) throws SQLException {
        Vendedor vendedor = null;
        while (resultSet.next()) {
            int idVendedor = resultSet.getInt(1);
            String nombreVendedor = resultSet.getString(2);
            String dniVendedor = resultSet.getString(3);
            String direccionVendedor = resultSet.getString(4);
            int codPostalVendedor = resultSet.getInt(5);
            String ciudadVendedor = resultSet.getString(6);
            int telefonoVendedor = resultSet.getInt(7);
            String correoVendedor = resultSet.getString(8);
            String usuarioVendedor = resultSet.getString(9);
            String contraseñaVendedor = resultSet.getString(10);
            vendedor = new Vendedor(idVendedor, nombreVendedor, dniVendedor, direccionVendedor, codPostalVendedor, ciudadVendedor, telefonoVendedor, correoVendedor, usuarioVendedor, contraseñaVendedor);
        }
        return vendedor;
    }

    public static GestorBDD getDatosGestor(ResultSet resultSet) throws SQLException {
        GestorBDD gestorBDD = null;
        while (resultSet.next()) {
            int idGestor = resultSet.getInt(1);
            String nombreGestor = resultSet.getString(2);
            String dniGestor = resultSet.getString(3);
            String direccionGestor = resultSet.getString(4);
            int codPostalGestor = resultSet.getInt(5);
            String ciudadGestor = resultSet.getString(6);
            int telefonoGestor = resultSet.getInt(7);
            String correoGestor = resultSet.getString(8);
            String usuarioGestor = resultSet.getString(9);
            String contraseñaGestor = resultSet.getString(10);
            gestorBDD = new GestorBDD(idGestor, nombreGestor, dniGestor, direccionGestor, codPostalGestor, ciudadGestor, telefonoGestor, correoGestor, usuarioGestor, contraseñaGestor);
        }
        return gestorBDD;
    }


    private static Cliente getDatosDeCliente(ResultSet resultSet) throws SQLException {
        Cliente cliente = null;
        while (resultSet.next()) {
            int idCliente = resultSet.getInt(1);
            String nombreCliente = resultSet.getString(2);
            String dniCliente = resultSet.getString(3);
            String direccionCliente = resultSet.getString(4);
            int codPostalCliente = resultSet.getInt(5);
            String ciudadCliente = resultSet.getString(6);
            int telefonoCliente = resultSet.getInt(7);
            String correoCliente = resultSet.getString(8);
            cliente = new Cliente(idCliente, nombreCliente, dniCliente, direccionCliente, codPostalCliente, ciudadCliente, telefonoCliente, correoCliente);
        }
        return cliente;
    }
}
