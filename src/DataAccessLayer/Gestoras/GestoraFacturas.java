package DataAccessLayer.Gestoras;

import DataAccessLayer.Conexion.DatosConexion;
import Entidades.Documentos.Factura;
import Entidades.Documentos.FilaFactura;
import Vistas.Menu;

import java.rmi.MarshalException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class GestoraFacturas {
    /**
     * Esta clase me permitirá añadir facturas y filas de factura
     */
    public static DatosConexion datosConexion = new DatosConexion();

    public static int añadirFactura(Factura factura) throws SQLException {
        int filasAfectadas = 0;
        try (Connection c = datosConexion.getConexion()) {
            String sql = "INSERT INTO Facturas VALUES (?, ?, ?, ?)";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, factura.getIdCliente());
            statement.setInt(2, factura.getIdVendedor());
            statement.setDate(3, new java.sql.Date(factura.getFecha().getTime()));
            statement.setDouble(4, factura.getImporteTotal());
            filasAfectadas = statement.executeUpdate();
        }
        return filasAfectadas;
    }

    public static int añadirFilasFactura(List<FilaFactura> listaArticulos) throws SQLException {
        int nFilasAñadidas = 0;
        for (FilaFactura ff : listaArticulos) {
            nFilasAñadidas = añadirFilaFactura(ff);
        }
        return nFilasAñadidas;
    }

    private static int añadirFilaFactura(FilaFactura filaFactura) throws SQLException {
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
