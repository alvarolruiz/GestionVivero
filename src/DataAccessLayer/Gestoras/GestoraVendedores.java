package DataAccessLayer.Gestoras;

import DataAccessLayer.Conexion.DatosConexion;
import Entidades.Usuarios.Administradores.Vendedor;
import Vistas.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GestoraVendedores {
    public static DatosConexion datosConexion = new DatosConexion();

    public static int insertarVendedor(Vendedor vendedor) throws SQLException {
        int filasAfectadas = 0;
        String sql = "Insert Into Gestores Values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement = setParametersForInsertVendedor(vendedor, statement);
            filasAfectadas = statement.executeUpdate();
            Menu.showMsgFilasAfectadas(filasAfectadas);
        }
        return filasAfectadas;
    }
    public static int deleteVendedor(int idVendedor) throws SQLException {
        int filasAfectadas = 0;
        String sql = "DELETE FROM Vendedores WHERE idVendedor = ?";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, idVendedor);
            filasAfectadas = statement.executeUpdate();
            Menu.showMsgFilasAfectadas(filasAfectadas);
        }
        return filasAfectadas;
    }

    public static int updateVendedor(Vendedor vendedor) throws SQLException {
        int filasAfectadas = 0;
        String sql = "UPDATE Vendedores SET nombre = ? , dni = ?, direccion = ?, codigoPostal = ?, ciudad = ?, telefonoContacto = ?, correoElectronico = ?, usuario = ?, contraseña = ? WHERE idVendedor = ?";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement = setParametersForInsertVendedor(vendedor, statement);
            statement.setInt(10, vendedor.getId());
            filasAfectadas = statement.executeUpdate();
            Menu.showMsgFilasAfectadas(filasAfectadas);
        }
        return filasAfectadas;
    }

    /**
     * Setea los parámetros de un gestor para una inserción. No tiene en cuenta el id del gestor.
     */
    private static PreparedStatement setParametersForInsertVendedor(Vendedor vendedor, PreparedStatement statement) throws SQLException {
        statement.setString(1, vendedor.getNombre());
        statement.setString(2, vendedor.getDni());
        statement.setString(3, vendedor.getDireccion());
        statement.setInt(4, vendedor.getCodPostal());
        statement.setString(5, vendedor.getCiudad());
        statement.setInt(6, vendedor.getTelefono());
        statement.setString(7, vendedor.getCorreoElectronico());
        statement.setString(8, vendedor.getUser());
        statement.setString(9, vendedor.getPassword());
        return statement;
    }
}
