package DataAccessLayer.Gestoras;

import DataAccessLayer.Conexion.DatosConexion;
import Entidades.Productos.Producto;
import Entidades.Productos.ProductoJardineria;
import Entidades.Productos.ProductoPlanta;
import Vistas.Menu;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GestoraProductos {
    private static DatosConexion datosConexion = new DatosConexion();

    public static int insertarProductoPlanta(ProductoPlanta productoPlanta) throws SQLException {
        int filasAfectadas = 0;
        try (Connection c = datosConexion.getConexion()) {
            String sql = "{call sp_InsertProductoPlanta @descripcion = ?, @precioUnitario =  ?, @unidadesDisponibles =  ?, @idTipoPlanta = ?";
            CallableStatement cst = c.prepareCall(sql);
            cst.setString(1, productoPlanta.getDescripcion());
            cst.setDouble(2, productoPlanta.getPrecioUnitario());
            cst.setInt(3, productoPlanta.getUnidadesDisponibles());
            cst.setInt(4, productoPlanta.getTipoPlanta());
            filasAfectadas = cst.executeUpdate();
            Menu.showMsgFilasAfectadas(filasAfectadas);
        }
        return filasAfectadas;
    }

    public static int insertarProductoJardineria(ProductoJardineria productoJardineria) throws SQLException {
        int filasAfectadas = 0;
        String sql = "{call sp_InsertProductoJardineria @descripcion = ?, @precioUnitario =  ?, @unidadesDisponibles =  ?}";
        try (Connection c = datosConexion.getConexion()) {
            CallableStatement cst = c.prepareCall(sql);
            cst.setString(1, productoJardineria.getDescripcion());
            cst.setDouble(2, productoJardineria.getPrecioUnitario());
            cst.setInt(3, productoJardineria.getUnidadesDisponibles());
            filasAfectadas = cst.executeUpdate();
            Menu.showMsgFilasAfectadas(filasAfectadas);
        }
        return filasAfectadas;
    }

    // TODO Si el cst.executeUpdate no funciona cambiarlo por execute
    public static int updateProductoPlanta(ProductoPlanta productoPlanta) throws SQLException {
        int filasAfectadas = 0;
        String sql = "{call sp_actualizarProductoPlanta @id = ?, @descripcion =  ?, @precioUnitario = ?, @unidadesDisponibles =  ?, @tipoPlanta = ?}";
        try (Connection c = datosConexion.getConexion()) {
            CallableStatement cst = c.prepareCall(sql);
            cst.setInt(1, productoPlanta.getId());
            cst.setString(2, productoPlanta.getDescripcion());
            cst.setDouble(3, productoPlanta.getPrecioUnitario());
            cst.setInt(4, productoPlanta.getUnidadesDisponibles());
            cst.setInt(5, productoPlanta.getTipoPlanta());
            filasAfectadas = cst.executeUpdate();
            Menu.showMsgFilasAfectadas(filasAfectadas);
        }
        return filasAfectadas;
    }

    public static int updateProductoJardineria(ProductoJardineria productoJardineria) throws SQLException {
        int filasAfectadas = 0;
        String sql = "UPDATE Productos SET descripcion = ?, precioUnitario = ?, unidadesDisponibles = ? WHERE id = @id;";
        try (Connection c = datosConexion.getConexion()) {
            CallableStatement cst = c.prepareCall(sql);
            cst.setString(1, productoJardineria.getDescripcion());
            cst.setDouble(2, productoJardineria.getPrecioUnitario());
            cst.setInt(3, productoJardineria.getUnidadesDisponibles());
        }
        return filasAfectadas;
    }

//TODO comprobar que dicho producto no exista en ninguna factura. En el caso de que exista no borrar.
    public static int deleteProducto(int idProducto) throws SQLException {
        int filasAfectadas = 0;
        String sql = "DELETE FROM Productos WHERE id = ?";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, idProducto);
            filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println(Menu.MSG_ACTUALIZACION_CORRECTA);
            } else {
                System.out.println(Menu.MSG_ACTUALIZACION_FALLIDA);
            }
        }
        return filasAfectadas;
    }


}

