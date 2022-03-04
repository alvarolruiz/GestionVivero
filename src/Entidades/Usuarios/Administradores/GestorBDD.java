package Entidades.Usuarios.Administradores;

import DataAccessLayer.Gestoras.*;
import DataAccessLayer.Listados.ListadosProductos;
import DataAccessLayer.Listados.ListadosUsuarios;
import Entidades.Productos.Producto;
import Entidades.Productos.ProductoJardineria;
import Entidades.Productos.ProductoPlanta;
import Entidades.Usuarios.Cliente;
import Entidades.Usuarios.Persona;
import Vistas.GeneradorTablas;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

// Necesito poder acceder con este usuario y tener todos los permisos en la bdd
public class GestorBDD extends Administrador {


    public GestorBDD(int id, String nombre, String dni, String direccion, int codPostal, String ciudad, int telefono, String correoElectronico, String user, String password) {
        super(id, nombre, dni, direccion, codPostal, ciudad, telefono, correoElectronico, user, password);
    }

    public GestorBDD(Persona persona, String user, String password) {
        super(persona, user, password);
    }

    public GestorBDD(Administrador admin) {
        super(admin);
    }

    // Métodos para obtener listados de todas las tablas
    public void getProductos() throws SQLException {
        ArrayList<Producto> listadoProductos = new ArrayList<>(ListadosProductos.getListaProductos());
        GeneradorTablas.imprimirTablaProductos(listadoProductos);
    }

    public void getClientes() throws SQLException{
        ArrayList<Cliente> listadoClientes = new ArrayList<>(ListadosUsuarios.getListaClientes());
        GeneradorTablas.imprimirTablaClientes(listadoClientes);
    }

    public void getVendedores() throws SQLException {
        ArrayList<Administrador> listadoVendedores = new ArrayList<>(ListadosUsuarios.getListaVendedores());
        GeneradorTablas.imprimirTablaAdministradores(listadoVendedores);
    }

    public void getGestoresBDD() throws SQLException {
        ArrayList<Administrador> listadoGestores = new ArrayList<>(ListadosUsuarios.getListaGestores());
        GeneradorTablas.imprimirTablaAdministradores(listadoGestores);
    }

    // Tabla productos

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


    // Tabla clientes

    public int insertCliente (Cliente cliente) throws SQLException {
        return GestoraClientes.insertarCliente(cliente);
    }
    public int deleteCliente (int idCliente) throws SQLException {
        return GestoraClientes.deleteCliente(idCliente);
    }
    public int updateClient (Cliente cliente) throws SQLException{
        return GestoraClientes.updateCliente(cliente);
    }

    // Tabla Vendedores
    public int insertVendedor (Vendedor vendedor) throws SQLException {
        return GestoraVendedores.insertarVendedor(vendedor);
    }
    public int deleteVendedor (int idVendedor) throws SQLException {
        return GestoraVendedores.deleteVendedor(idVendedor);
    }
    public int updateVendedor (Vendedor vendedor) throws SQLException{
        return GestoraVendedores.updateVendedor(vendedor);
    }

    // Tabla Gestores
    public int insertGestorBDD (GestorBDD gestorBDD) throws SQLException {
        return GestoraGestoresBDD.insertarGestorBDD(gestorBDD);
    }
    public int deleteGestorBDD (int idGestor) throws SQLException {
        return GestoraGestoresBDD.deleteGestorBDD(idGestor);
    }
    public int updateGestorBDD (GestorBDD gestorBDD) throws SQLException{
        return GestoraGestoresBDD.updateGestorBDD(gestorBDD);
    }

    //Tabla Facturas
    public int deleteFactura (int idFactura) throws SQLException {
        return GestoraFacturas.deleteFactura(idFactura);
    }


}




