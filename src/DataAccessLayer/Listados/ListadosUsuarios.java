package DataAccessLayer.Listados;


import DataAccessLayer.Conexion.DatosConexion;
import Entidades.Productos.Producto;
import Entidades.Productos.ProductoJardineria;
import Entidades.Productos.ProductoPlanta;
import Entidades.Usuarios.Administradores.Administrador;
import Entidades.Usuarios.Administradores.GestorBDD;
import Entidades.Usuarios.Administradores.Vendedor;
import Entidades.Usuarios.Cliente;
import Vistas.Constantes;
import Vistas.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListadosUsuarios {
    /*
    Clase terminada. Posee metodos publicos para devolver un cliente, un gestor, y un vendedor. Además tambien contiene
    Métodos que devolverán booleanos que simplemente comrpobarán si existe el usuario en la base de datos. Estos métodos
    Serán llamados desde la clase validaciones
    */

    private static DatosConexion datosConexion = new DatosConexion();

    public static List<Cliente> getListaClientes() throws SQLException {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        Cliente cliente;
        ResultSet resultSet;
        String sql = "SELECT * FROM Clientes";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    cliente = getDatosDeCliente(resultSet);
                    listaClientes.add(cliente);
                }
            } else {
                System.out.println(Menu.MSG_SQL_EXEPTION);
            }
        }
        return listaClientes;
    }

    public static List<Vendedor> getListaVendedores() throws SQLException {
        ArrayList<Vendedor> listaClientes = new ArrayList<>();
        Vendedor vendedor;
        ResultSet resultSet;
        String sql = "SELECT * FROM Vendedores";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    vendedor = (Vendedor) getDatosAdmin(resultSet);
                    listaClientes.add(vendedor);
                }
            } else {
                System.out.println(Menu.MSG_SQL_EXEPTION);
            }
        }
        return listaClientes;
    }

    public static List<GestorBDD> getListaGestores() throws SQLException {
        ArrayList<GestorBDD> listaGestores = new ArrayList<>();
        GestorBDD gestor;
        ResultSet resultSet;
        String sql = "SELECT * FROM Gestores";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    gestor = (GestorBDD) getDatosAdmin(resultSet);
                    listaGestores.add(gestor);
                }
            } else {
                System.out.println(Menu.MSG_SQL_EXEPTION);
            }
        }
        return listaGestores;
    }

    public static Cliente getClienteDni(String dni) throws SQLException {
        ResultSet result;
        Cliente cliente;
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
                statement.setString(1, String.valueOf(Constantes.ID_CLIENTE_NO_REGISTRADO));
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
            statement.setString(1, String.valueOf(telefono));
            result = statement.executeQuery(sql);
            if (result.isBeforeFirst()) {
                cliente = getDatosDeCliente(result);
            } else {
                sql = "Select * FROM Clientes WHERE idCliente =?";
                statement = c.prepareStatement(sql);
                statement.setString(1, String.valueOf(Constantes.ID_CLIENTE_NO_REGISTRADO));
                result = statement.executeQuery();
                cliente = getDatosDeCliente(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
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
                gestorBDD = (GestorBDD) getDatosAdmin(resultSet);
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
                vendedor = (Vendedor) getDatosAdmin(resultSet);
            } else {
                System.out.println(Menu.MSG_ERROR_LOGIN_GESTOR);
            }
        }
        return vendedor;
    }

    public static boolean isValidLogin(String usuario, String constraseña, char tipoAdmin) throws SQLException, NullPointerException {
        boolean isValidLogin = false;
        ResultSet resultSet;
        String sql = "";
        if (tipoAdmin == Constantes.ADMIN_VENDEDOR) {
            sql = "SELECT * FROM Vendedores Where usuario = ? AND contraseña = ?";
        } else if (tipoAdmin == Constantes.ADMIN_GESTOR) {
            sql = "SELECT * FROM Gestores Where usuario = ? AND contraseña = ?";
        }
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setString(1, usuario);
            statement.setString(2, constraseña);
            resultSet = statement.executeQuery(sql);
            if (resultSet.isBeforeFirst()) {
                isValidLogin = true;
            } else {
                System.out.println(Menu.MSG_ERROR_LOGIN_GESTOR);
            }
        }
        return isValidLogin;
    }

    public static char tipoAdministrador(String usuario) throws SQLException, NullPointerException {
        char tipoUsuario = Constantes.ADMIN_NULO;
        if (isGestor(usuario)) {
            tipoUsuario = Constantes.ADMIN_GESTOR;
        } else if (isVendedor(usuario)) {
            tipoUsuario = Constantes.ADMIN_VENDEDOR;
        }
        return tipoUsuario;
    }

    private static boolean isGestor(String usuario) throws SQLException, NullPointerException {
        String sql = "SELECT * FROM Gestores Where usuario = ?";
        return isRegistered(usuario, sql);
    }

    private static boolean isVendedor(String usuario) throws SQLException, NullPointerException {
        String sql = "SELECT * FROM Vendedores Where usuario = ?";
        return isRegistered(usuario, sql);
    }

    private static boolean isRegistered(String usuario, String sql) throws SQLException, NullPointerException {
        boolean exists = false;
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setString(1, usuario);
            ResultSet result = statement.executeQuery(sql);
            if (result.isBeforeFirst()) {
                exists = true;
            }
        }
        return exists;
    }

    private static Administrador getDatosAdmin(ResultSet resultSet) throws SQLException {
        Administrador admin = null;
        int idAdministrador = resultSet.getInt(1);
        String nombreAdministrador = resultSet.getString(2);
        String dniAdministrador = resultSet.getString(3);
        String direccionAdministrador = resultSet.getString(4);
        int codPostalAdministrador = resultSet.getInt(5);
        String ciudadAdministrador = resultSet.getString(6);
        int telefonoAdministrador = resultSet.getInt(7);
        String correoAdministrador = resultSet.getString(8);
        String usuarioAdministrador = resultSet.getString(9);
        String contraseñaAdministrador = resultSet.getString(10);
        admin = new Administrador(idAdministrador, nombreAdministrador, dniAdministrador, direccionAdministrador,
                codPostalAdministrador, ciudadAdministrador, telefonoAdministrador, correoAdministrador,
                usuarioAdministrador, contraseñaAdministrador);

        return admin;
    }


    private static Cliente getDatosDeCliente(ResultSet resultSet) throws SQLException {
        Cliente cliente = null;
        int idCliente = resultSet.getInt(1);
        String nombreCliente = resultSet.getString(2);
        String dniCliente = resultSet.getString(3);
        String direccionCliente = resultSet.getString(4);
        int codPostalCliente = resultSet.getInt(5);
        String ciudadCliente = resultSet.getString(6);
        int telefonoCliente = resultSet.getInt(7);
        String correoCliente = resultSet.getString(8);
        cliente = new Cliente(idCliente, nombreCliente, dniCliente, direccionCliente, codPostalCliente, ciudadCliente,
                telefonoCliente, correoCliente);
        return cliente;
    }
}
