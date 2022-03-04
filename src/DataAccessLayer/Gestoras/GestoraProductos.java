package DataAccessLayer.Gestoras;

import DataAccessLayer.Conexion.DatosConexion;
import DataAccessLayer.Listados.ListadosFacturas;
import DataAccessLayer.Listados.ListadosProductos;
import Entidades.Productos.ProductoJardineria;
import Entidades.Productos.ProductoPlanta;
import Vistas.Mensajes;

import java.sql.*;

public class GestoraProductos {
    private static DatosConexion datosConexion = new DatosConexion();

    public static int insertarProductoPlanta(ProductoPlanta productoPlanta) throws SQLException {
        int filasAfectadas = 0;
        try (Connection c = datosConexion.getConexion()) {
            String sql = "EXEC sp_InsertProductoPlanta @descripcion = ?, @precioUnitario =  ?, @unidadesDisponibles =  ?, @idTipoPlanta = ?";
            CallableStatement cst = c.prepareCall(sql);
            cst.setString(1, productoPlanta.getDescripcion());
            cst.setDouble(2, productoPlanta.getPrecioUnitario());
            cst.setInt(3, productoPlanta.getUnidadesDisponibles());
            cst.setInt(4, productoPlanta.getTipoPlanta());
            filasAfectadas = cst.executeUpdate();
            Mensajes.showMsgFilasAfectadas(filasAfectadas);
        }
        return filasAfectadas;
    }

    public static int insertarProductoJardineria(ProductoJardineria productoJardineria) throws SQLException {
        int filasAfectadas = 0;
        String sql = "EXEC sp_InsertProductoJardineria @descripcion = ?, @precioUnitario =  ?, @unidadesDisponibles =  ?";
        try (Connection c = datosConexion.getConexion()) {
            CallableStatement cst = c.prepareCall(sql);
            cst.setString(1, productoJardineria.getDescripcion());
            cst.setDouble(2, productoJardineria.getPrecioUnitario());
            cst.setInt(3, productoJardineria.getUnidadesDisponibles());
            filasAfectadas = cst.executeUpdate();
            Mensajes.showMsgFilasAfectadas(filasAfectadas);
        }
        return filasAfectadas;
    }

    public static int updateProductoPlanta(ProductoPlanta productoPlanta) throws SQLException {
        int filasAfectadas = 0;
        String sql = "EXEC sp_actualizarProductoPlanta @id = ?, @descripcion =  ?, @precioUnitario = ?, @unidadesDisponibles =  ?, @idTipoPlanta = ?";
        try (Connection c = datosConexion.getConexion()) {
            CallableStatement cst = c.prepareCall(sql);
            cst.setInt(1, productoPlanta.getId());
            cst.setString(2, productoPlanta.getDescripcion());
            cst.setDouble(3, productoPlanta.getPrecioUnitario());
            cst.setInt(4, productoPlanta.getUnidadesDisponibles());
            cst.setInt(5, productoPlanta.getTipoPlanta());
            filasAfectadas = cst.executeUpdate();
            Mensajes.showMsgFilasAfectadas(filasAfectadas);
        }
        return filasAfectadas;
    }

    public static int updateProductoJardineria(ProductoJardineria productoJardineria) throws SQLException {
        int filasAfectadas = 0;
        String sql = "UPDATE Productos SET descripcion = ?, precioUnitario = ?, unidadesDisponibles = ? WHERE id = ?;";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareCall(sql);
            statement.setString(1, productoJardineria.getDescripcion());
            statement.setDouble(2, productoJardineria.getPrecioUnitario());
            statement.setInt(3, productoJardineria.getUnidadesDisponibles());
            statement.setInt(4, productoJardineria.getId());
            statement.executeUpdate();
        }
        return filasAfectadas;
    }

//TODO comprobar que dicho producto no exista en ninguna factura. En el caso de que exista no borrar.
    public static int deleteProducto(int idProducto) throws SQLException {
        int filasAfectadas = 0;
        String sql = "DELETE FROM Productos WHERE id = ?";
        if(!ListadosFacturas.existeFacturaConProducto(idProducto)){
            try (Connection c = datosConexion.getConexion()) {
                PreparedStatement statement = c.prepareStatement(sql);
                statement.setInt(1, idProducto);
                filasAfectadas = statement.executeUpdate();
                Mensajes.showMsgFilasAfectadas(filasAfectadas);
            }
        }
        return filasAfectadas;
    }

    public static int updateStockProducto(int cantidad, int idProducto) throws SQLException {
        int filasAfectadas = 0;
        String sql = "UPDATE Productos SET unidadesDisponibles = unidadesDisponibles-? WHERE id = ?;";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, cantidad);
            statement.setInt(2, idProducto);
            filasAfectadas = statement.executeUpdate();
            Mensajes.showMsgFilasAfectadas(filasAfectadas);
        }
        return filasAfectadas;
    }




}

