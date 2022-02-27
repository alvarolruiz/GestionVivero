package DataAccessLayer.Listados;

import DataAccessLayer.Conexion.DatosConexion;
import Entidades.Productos.Producto;
import Entidades.Productos.ProductoJardineria;
import Entidades.Productos.ProductoPlanta;
import Entidades.Usuarios.Administradores.GestorBDD;
import Vistas.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListadosProductos {

    private static DatosConexion datosConexion = new DatosConexion();

    public static Producto getProducto(int idProducto) throws SQLException {
        Producto producto = null;
        ResultSet resultSet;
        String sql = "SELECT * FROM Productos Where id = ?";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, idProducto);
            resultSet = statement.executeQuery(sql);
            if (resultSet.isBeforeFirst()) {
                producto = getDatosProducto(resultSet);
            } else {
                System.out.println(Menu.MSG_PRODUCTO_NO_EXISTE);
            }
        }
        return producto;
    }

    /**
     * Accede a la base de datos y devuelve una lista de productos.
     * @return List<Producto>
     * @throws SQLException
     */
    public static List<Producto> getListaProductos() throws SQLException {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        ProductoPlanta productoPlanta;
        ProductoJardineria productoJardineria;
        boolean isPlanta = true;
        ResultSet resultSet;
        String sql = "SELECT * FROM view_ProductosTipo";
        try (Connection c = datosConexion.getConexion()) {
            PreparedStatement statement = c.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    if (isProductoPlanta(resultSet)) {
                        productoPlanta = getDatosProductoPlanta(resultSet);
                        listaProductos.add(productoPlanta);
                    }else{
                        productoJardineria = (ProductoJardineria) getDatosProducto(resultSet);
                        listaProductos.add(productoJardineria);
                    }
                }
            } else {
                System.out.println(Menu.MSG_SQL_EXEPTION);
            }
        }
        return listaProductos;
    }

    private static Producto getDatosProducto(ResultSet resultSet) throws SQLException {
        Producto producto = null;
        int id = resultSet.getInt(1);
        String descripcion = resultSet.getString(2);
        double precioUnitario = resultSet.getDouble(3);
        int unidadesDisponibles = resultSet.getInt(4);
        producto = new Producto(id, descripcion, precioUnitario, unidadesDisponibles);

        return producto;
    }

    private static ProductoPlanta getDatosProductoPlanta(ResultSet resultSet) throws SQLException {
        ProductoPlanta productoPlanta = null;
        int id = resultSet.getInt(1);
        String descripcion = resultSet.getString(2);
        double precioUnitario = resultSet.getDouble(3);
        int unidadesDisponibles = resultSet.getInt(4);
        int tipoPlanta = resultSet.getInt(5);
        productoPlanta = new ProductoPlanta(id, descripcion, precioUnitario, unidadesDisponibles, tipoPlanta);
        return productoPlanta;
    }

    private static boolean isProductoPlanta(ResultSet resultSet) throws SQLException {
        boolean isPlanta = true;
        if (Integer.valueOf(resultSet.getString(5)) == null) {
            isPlanta = false;
        }
        return isPlanta;
    }
}
