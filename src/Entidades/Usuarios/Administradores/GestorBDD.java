package Entidades.Usuarios.Administradores;

import DataAccessLayer.Gestoras.GestoraClientes;
import DataAccessLayer.Gestoras.GestoraGestoresBDD;
import DataAccessLayer.Gestoras.GestoraProductos;
import DataAccessLayer.Gestoras.GestoraVendedores;
import DataAccessLayer.Listados.ListadosProductos;
import DataAccessLayer.Listados.ListadosUsuarios;
import Entidades.Productos.Producto;
import Entidades.Productos.ProductoJardineria;
import Entidades.Productos.ProductoPlanta;
import Entidades.Usuarios.Cliente;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

// Necesito poder acceder con este usuario y tener todos los permisos en la bdd
public class GestorBDD extends Vendedor {

    public static final String  SEPARATOR = "-----------------------------------------------------------------------------";

    public GestorBDD(int idVendedor, String nombre, String dni, String direccion, int codPostal, String ciudad, int telefono, String correoElectronico, String user, String password) {
        super(idVendedor, nombre, dni, direccion, codPostal, ciudad, telefono, correoElectronico, user, password);
    }
    // Métodos para obtener listados de todas las tablas
    public void getProductos() throws SQLException {
        ArrayList<Producto> listadoProductos = new ArrayList<>(ListadosProductos.getListaProductos());
        imprimirTablaProductos(listadoProductos);
    }

    public void getClientes() throws SQLException{
        ArrayList<Cliente> listadoClientes = new ArrayList<>(ListadosUsuarios.getListaClientes());
        imprimirTablaClientes(listadoClientes);
    }

    public void getVendedores() throws SQLException {
        ArrayList<Administrador> listadoVendedores = new ArrayList<>(ListadosUsuarios.getListaGestores());
        imprimirTablaAdministradores(listadoVendedores);
    }

    public void getGestoresBDD() throws SQLException {
        ArrayList<Administrador> listadoGestores = new ArrayList<>(ListadosUsuarios.getListaGestores());
        imprimirTablaAdministradores(listadoGestores);
    }

    // Métodos para trabajar con la tabla productos

    public int insertProductoPlanta(ProductoPlanta productoPlanta) throws SQLException {
        return GestoraProductos.insertarProductoPlanta(productoPlanta);
    }

    public int insertProductoJardineria(ProductoJardineria productoJardineria) throws SQLException {
        return GestoraProductos.insertarProductoJardineria(productoJardineria);
    }

    public int deleteProducto(int idProducto) throws SQLException {
        return GestoraProductos.deleteProducto(idProducto);
    }

    public int updateProductoPlanta(ProductoPlanta productoPlanta) throws SQLException {
        return GestoraProductos.updateProductoPlanta(productoPlanta);
    }

    public int updateProductoJardineria(ProductoJardineria productoJardineria) throws SQLException {
        return GestoraProductos.updateProductoJardineria(productoJardineria);
    }


    // Metodos para trabajar con la tabla clientes

    public int insertCliente (Cliente cliente) throws SQLException {
        return GestoraClientes.insertarCliente(cliente);
    }
    public int deleteCliente (int idCliente) throws SQLException {
        return GestoraClientes.deleteCliente(idCliente);
    }
    public int updateClient (Cliente cliente) throws SQLException{
        return GestoraClientes.updateCliente(cliente);
    }

    // Metodos para trabajar con la tabla Vendedores
    public int insertVendedor (Vendedor vendedor) throws SQLException {
        return GestoraVendedores.insertarVendedor(vendedor);
    }
    public int deleteVendedor (int idVendedor) throws SQLException {
        return GestoraVendedores.deleteVendedor(idVendedor);
    }
    public int updateVendedor (Vendedor vendedor) throws SQLException{
        return GestoraVendedores.updateVendedor(vendedor);
    }

    // Metodos para trabajar con la tabla Gestores
    public int insertGestorBDD (GestorBDD gestorBDD) throws SQLException {
        return GestoraGestoresBDD.insertarGestorBDD(gestorBDD);
    }
    public int deleteGestorBDD (int idGestor) throws SQLException {
        return GestoraGestoresBDD.deleteGestorBDD(idGestor);
    }
    public int updateGestorBDD (GestorBDD gestorBDD) throws SQLException{
        return GestoraGestoresBDD.updateGestorBDD(gestorBDD);
    }



    private void imprimirTablaAdministradores(ArrayList<Administrador> listadoAdministradores) {
        System.out.println(SEPARATOR);
        System.out.printf("%5s %15s %15s %10s %5s %20s", "ID", "NOMBRE", "USUARIO", "DNI", "CIUDAD", "TELEFONO");
        System.out.println();
        System.out.println(SEPARATOR);
        for (Administrador  ad: listadoAdministradores) {
            imprimirAdministrador(ad);
        }
        System.out.println(SEPARATOR);
    }

    private void imprimirAdministrador(Administrador ad) {
        System.out.format("%5s %15s %15s %10s %5d %20d",
                ad.getId(), ad.getNombre(), ad.getUser(), ad.getDni(), ad.getCiudad(), ad.getTelefono());
        System.out.println();
    }

    private void imprimirTablaClientes(ArrayList<Cliente> listadoClientes) {
        System.out.println(SEPARATOR);
        System.out.printf("%5s %15s %10s %5s %20s", "ID", "NOMBRE", "DNI", "CIUDAD", "TELEFONO");
        System.out.println();
        System.out.println(SEPARATOR);
        for (Cliente  c: listadoClientes) {
                imprimirCliente(c);
        }
        System.out.println(SEPARATOR);
    }

    private void imprimirCliente(Cliente c) {
        System.out.format("%5s %15s %10s %5d %20d",
                c.getId(), c.getNombre(), c.getDni(), c.getCiudad(), c.getTelefono());
        System.out.println();
    }

    private void imprimirTablaProductos(ArrayList<Producto> listadoProductos) {
        System.out.println(SEPARATOR);
        System.out.printf("%5s %15s %10s %5s %20s", "ID", "DESCRIPCIÓN", "PRECIO", "STOCK", "TIPO");
        System.out.println();
        System.out.println(SEPARATOR);
        for (Producto p : listadoProductos) {
            if (p instanceof ProductoPlanta) {
                imprimirProducto(p, "Planta");
            } else if (p instanceof ProductoJardineria) {
                imprimirProducto(p, "Producto Jardineria");
            }
        }
        System.out.println(SEPARATOR);
    }

    private static void imprimirProducto(Producto p, String tipo) {
        System.out.format("%5s %15s %10s %5d %20s",
                p.getId(), p.getDescripcion(), imprimirDouble(p.getPrecioUnitario()), p.getUnidadesDisponibles(), tipo);
        System.out.println();
    }

    private static String imprimirDouble(double d) {
        DecimalFormat df = new DecimalFormat("#.00");
        String string = df.format(d);
        return string;
    }
}




