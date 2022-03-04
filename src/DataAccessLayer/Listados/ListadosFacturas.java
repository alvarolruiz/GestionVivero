package DataAccessLayer.Listados;

import DataAccessLayer.Conexion.DatosConexion;
import Entidades.Usuarios.Cliente;
import Vistas.Constantes;
import Vistas.Mensajes;

import java.sql.*;

public class ListadosFacturas {

    public static DatosConexion datosConexion = new DatosConexion();

    public static int getNextIdFactura() throws SQLException {
        ResultSet result;
        int nextId = 0;
        String sql = "SELECT MAX(idFactura) FROM Facturas";
        try (Connection c = datosConexion.getConexion()) {
            Statement statement = c.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()) {
                nextId = result.getInt(1);
            }
        }
        return nextId + 1;
    }

    public static boolean existeFacturaConProducto(int idProducto) throws SQLException {
        ResultSet result;
        boolean existe = false;
        String sql = "SELECT Count(* )FROM FilasFactura WHERE idProducto = ?";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, idProducto);
            result = statement.executeQuery();
            result.next();
            if (result.getInt(1) > 0) {
                existe = true;
            }
        }
        return existe;
    }


}
