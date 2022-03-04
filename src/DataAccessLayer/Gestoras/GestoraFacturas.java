package DataAccessLayer.Gestoras;

import DataAccessLayer.Conexion.DatosConexion;
import Entidades.Documentos.Factura;
import Entidades.Documentos.FilaFactura;
import Vistas.Mensajes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class GestoraFacturas {

    public static DatosConexion datosConexion = new DatosConexion();

    public static int insertFactura(Factura factura) throws SQLException {
        int filasAfectadas = 0;
        try (Connection c = datosConexion.getConexion()) {
            String sql = "INSERT INTO Facturas VALUES (?, ?, ?, ?)";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, factura.getIdCliente());
            statement.setInt(2, factura.getIdVendedor());
            statement.setDate(3, new java.sql.Date(factura.getFecha().getTime()));
            statement.setDouble(4, factura.getImporteTotal());
            filasAfectadas = statement.executeUpdate();
            Mensajes.showMsgFilasAfectadas(filasAfectadas);
        }
        return filasAfectadas;
    }

    public static int deleteFactura (int idFactura) throws SQLException {
        int filasAfectadas = 0;
        String sql = "DELETE FROM Facturas WHERE idFactura = ?";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, idFactura);
            filasAfectadas = statement.executeUpdate();
            Mensajes.showMsgFilasAfectadas(filasAfectadas);
        }
        return filasAfectadas;
    }

    public static int insertFilasFactura(List<FilaFactura> listaArticulos) throws SQLException {
        int nFilasAñadidas = 0;
        for (FilaFactura ff : listaArticulos) {
            nFilasAñadidas += insertFilaFactura(ff);
        }
        return nFilasAñadidas;
    }

    private static int insertFilaFactura(FilaFactura filaFactura) throws SQLException {
        int filasAfectadas = 0;
        try (Connection c = datosConexion.getConexion()) {
            String sql = "INSERT INTO FilasFactura VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, filaFactura.getIdFactura());
            statement.setInt(2, filaFactura.getIdProducto());
            statement.setInt(3, filaFactura.getCantidad());
            statement.setDouble(4, filaFactura.getPrecioUnitario());
            statement.setDouble(5, filaFactura.getTotalFila());
            filasAfectadas = statement.executeUpdate();
        }
        return filasAfectadas;
    }
}
