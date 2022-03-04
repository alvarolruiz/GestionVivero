package DataAccessLayer.Gestoras;

import DataAccessLayer.Conexion.DatosConexion;
import Entidades.Usuarios.Cliente;
import Vistas.Mensajes;

import java.sql.*;

public class GestoraClientes {

    public static DatosConexion datosConexion = new DatosConexion();

    public static int insertarCliente(Cliente cliente) throws SQLException {
        int filasAfectadas = 0;
        String sql = "Insert Into Clientes Values (?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement = setStatementParameters(cliente, statement);
            filasAfectadas = statement.executeUpdate();
            Mensajes.showMsgFilasAfectadas(filasAfectadas);
        }
        return filasAfectadas;
    }

    public static int updateCliente(Cliente cliente) throws SQLException {
        int filasAfectadas = 0;
        String sql = "UPDATE Clientes SET nombre = ? , dni = ?, direccion = ?, codigoPostal = ?, ciudad = ?, telefonoContacto = ?, correoElectronico = ? WHERE idCliente = ?";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement = setStatementParameters(cliente, statement);
            statement.setInt(8, cliente.getId());
            filasAfectadas = statement.executeUpdate();
            Mensajes.showMsgFilasAfectadas(filasAfectadas);
        }
        return filasAfectadas;
    }

    public static int deleteCliente(int idCliente) throws SQLException {
        int filasAfectadas = 0;
        String sql = "DELETE FROM Clientes WHERE idCliente = ?";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, idCliente);
            filasAfectadas = statement.executeUpdate();
            Mensajes.showMsgFilasAfectadas(filasAfectadas);
        }
        return filasAfectadas;
    }

    /**
     * Setea los parámetros de un cliente para una inserción. No tiene en cuenta el id del cliente.
     */
    private static PreparedStatement setStatementParameters(Cliente cliente, PreparedStatement statement) throws SQLException {
        statement.setString(1, cliente.getNombre());
        statement.setString(2, cliente.getDni());
        statement.setString(3, cliente.getDireccion());
        statement.setInt(4, cliente.getCodPostal());
        statement.setString(5, cliente.getCiudad());
        statement.setInt(6, cliente.getTelefono());
        statement.setString(7, cliente.getCorreoElectronico());
        return statement;
    }
}
