package DataAccessLayer.Listados;

import DataAccessLayer.Conexion.DatosConexion;
import Entidades.Usuarios.Cliente;
import Vistas.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListadosFacturas {
    public static DatosConexion datosConexion = new DatosConexion();

    public static int getNextIdFactura() throws SQLException {
        ResultSet result;
        int nextId = 0;
        String sql = "SELECT MAX(idFactura) FROM Facturas";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            result = statement.executeQuery(sql);
            while (result.next()) {
                nextId = result.getInt(1);
            }
        }
        return nextId + 1;
    }
}
