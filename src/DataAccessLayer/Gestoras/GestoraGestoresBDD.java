package DataAccessLayer.Gestoras;

import DataAccessLayer.Conexion.DatosConexion;
import Entidades.Usuarios.Administradores.GestorBDD;
import Vistas.Mensajes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GestoraGestoresBDD {

    public static DatosConexion datosConexion = new DatosConexion();

    public static int insertarGestorBDD(GestorBDD gestorBDD) throws SQLException {
        int filasAfectadas = 0;
        String sql = "Insert Into Gestores Values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement = setParametersForInsertGestor(gestorBDD, statement);
            filasAfectadas = statement.executeUpdate();
            Mensajes.showMsgFilasAfectadas(filasAfectadas);
        }
        return filasAfectadas;
    }

    public static int deleteGestorBDD(int idGestor) throws SQLException {
        int filasAfectadas = 0;
        String sql = "DELETE FROM Gestores WHERE idGestor = ?";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, idGestor);
            filasAfectadas = statement.executeUpdate();
            Mensajes.showMsgFilasAfectadas(filasAfectadas);
        }
        return filasAfectadas;
    }

    public static int updateGestorBDD(GestorBDD gestorBDD) throws SQLException {
        int filasAfectadas = 0;
        String sql = "UPDATE Gestores SET nombre = ? , dni = ?, direccion = ?, codigoPostal = ?, ciudad = ?, telefonoContacto = ?, correoElectronico = ?, usuario = ?, contraseña = ? WHERE idCliente = ?";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement = setParametersForInsertGestor(gestorBDD, statement);
            statement.setInt(10, gestorBDD.getId());
            filasAfectadas = statement.executeUpdate();
            Mensajes.showMsgFilasAfectadas(filasAfectadas);
        }
        return filasAfectadas;
    }

    /**
     * Setea los parámetros de un gestor para una inserción. No tiene en cuenta el id del gestor.
     */
    private static PreparedStatement setParametersForInsertGestor(GestorBDD gestorBDD, PreparedStatement statement) throws SQLException {
        statement.setString(1, gestorBDD.getNombre());
        statement.setString(2, gestorBDD.getDni());
        statement.setString(3, gestorBDD.getDireccion());
        statement.setInt(4, gestorBDD.getCodPostal());
        statement.setString(5, gestorBDD.getCiudad());
        statement.setInt(6, gestorBDD.getTelefono());
        statement.setString(7, gestorBDD.getCorreoElectronico());
        statement.setString(8, gestorBDD.getUser());
        statement.setString(9, gestorBDD.getPassword());
        return statement;
    }
}
