package Controlador;

import Entidades.Productos.Producto;
import Entidades.Productos.ProductoJardineria;
import Entidades.Productos.ProductoPlanta;
import Entidades.Usuarios.Administradores.GestorBDD;
import Entidades.Usuarios.Administradores.Vendedor;
import Entidades.Usuarios.Cliente;
import Entidades.Usuarios.Persona;
import Vistas.Mensajes;
import Vistas.MenuGestor;

import java.sql.SQLException;

import static Vistas.MenuGestor.showMenuEditarProductos;

public class GestoraMenuGestor {
    GestorBDD gestorLogueado;

    public GestoraMenuGestor(GestorBDD gestorLogueado) {
        this.gestorLogueado = gestorLogueado;
    }

    public void showMenuPrincipal() throws SQLException {
        int opcion = 0;
        boolean fin = false;
        do {
            opcion = MenuGestor.showMenuInicio();
            switch (opcion) {
                case 0:
                    fin = true;
                    break;
                case 1:
                    showMenuListar();
                    break;
                case 2:
                    showMenuEditar();
                    break;
                case 3:
                    showMenuInformes();
            }
        } while (!fin);
    }


    private void showMenuListar() throws SQLException {
        int opcion = 0;
        boolean fin = false;
        do {
            opcion = MenuGestor.showMenuListar();
            switch (opcion) {
                case 0:
                    fin = true;
                    break;
                case 1:
                    listarProductos();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    listarVendedores();
                    break;
                case 4:
                    listarGestores();
                    break;
            }
        } while (!fin);
    }

    private void listarProductos() throws SQLException {
        gestorLogueado.getProductos();
    }

    private void listarClientes() throws SQLException {
        gestorLogueado.getClientes();
    }

    private void listarVendedores() throws SQLException {
        gestorLogueado.getVendedores();
    }

    private void listarGestores() throws SQLException {
        gestorLogueado.getGestoresBDD();
    }

    private void showMenuEditar() throws SQLException {
        int opcion = 0;
        boolean fin = false;
        do {
            opcion = MenuGestor.showMenuEditar();
            switch (opcion) {
                case 0:
                    fin = true;
                    break;
                case 1:
                    showMenuEditarProductos();
                    break;
                case 2:
                    showMenuEditarClientes();
                    break;
                case 3:
                    showMenuEditarVendedores();
                    break;
                case 4:
                    showMenuEditarGestores();
                    break;
            }
        } while (!fin);
    }


    private void showMenuEditarProductos() throws SQLException {
        int opcion = 0;
        boolean fin = false;
        do {
            opcion = MenuGestor.showMenuEditarProductos();
            switch (opcion) {
                case 0:
                    fin = true;
                    break;
                case 1:
                    showMenuAñadirProducto();
                    break;
                case 2:
                    showMenuUpdateProducto();
                    break;
                case 3:
                    deleteProducto();
            }
        } while (!fin);
    }

    private void showMenuAñadirProducto() throws SQLException {
        int opcion = 0;
        boolean fin = false;
        do {
            opcion = MenuGestor.showMenuAñadirProducto();
            switch (opcion) {
                case 0:
                    fin = true;
                    break;
                case 1:
                    añadirProductoJardineria();
                    break;
                case 2:
                    añadirProductoPlanta();
            }
        } while (!fin);
    }

    private void añadirProductoJardineria() throws SQLException {
        ProductoJardineria productoJardineria = new ProductoJardineria(Mensajes.pedirDatosProducto());
        gestorLogueado.insertProductoJardineria(productoJardineria);
    }

    private void añadirProductoPlanta() throws SQLException {
        Producto producto = Mensajes.pedirDatosProducto();
        int idTipoPlanta = Mensajes.pedirTipoPlanta();
        ProductoPlanta productoPlanta = new ProductoPlanta(producto, idTipoPlanta);
        gestorLogueado.insertProductoPlanta(productoPlanta);
    }

    private void showMenuUpdateProducto() throws SQLException {
        int opcion = 0;
        boolean fin = false;
        do {
            opcion = MenuGestor.showMenuUpdateProducto();
            switch (opcion) {
                case 0:
                    fin = true;
                    break;
                case 1:
                    updateProductoJardineria();
                    break;
                case 2:
                    updateProductoPlanta();
            }
        } while (!fin);
    }

    private void updateProductoJardineria() throws SQLException {
        ProductoJardineria productoJardineria = (ProductoJardineria) Mensajes.pedirDatosProducto();
        gestorLogueado.updateProductoJardineria(productoJardineria);
    }

    private void updateProductoPlanta() throws SQLException {
        Producto producto = Mensajes.pedirDatosProducto();
        int idTipoPlanta = Mensajes.pedirTipoPlanta();
        ProductoPlanta productoPlanta = new ProductoPlanta(producto, idTipoPlanta);
        gestorLogueado.updateProductoPlanta(productoPlanta);
    }

    private void deleteProducto() throws SQLException {
        int idProducto = Mensajes.pedirNumero("Id: ");
        gestorLogueado.deleteProducto(idProducto);
    }

    private void showMenuEditarClientes() throws SQLException {
        int opcion = 0;
        boolean fin = false;
        do {
            opcion = MenuGestor.showMenuEditarClientes();
            switch (opcion) {
                case 0:
                    fin = true;
                    break;
                case 1:
                    añadirCliente();
                    break;
                case 2:
                    updateCliente();
                    break;
                case 3:
                    deleteCliente();
                    break;
            }
        } while (!fin);
    }
    private void añadirCliente() throws SQLException {
        Cliente cliente = Mensajes.pedirDatosUsuario();
        gestorLogueado.insertCliente(cliente);
    }

    private void updateCliente() throws SQLException {
        Cliente cliente = Mensajes.pedirDatosUsuario();
        gestorLogueado.insertCliente(cliente);
    }

    private void deleteCliente() throws SQLException {
        int idCliente = Mensajes.pedirNumero("Id: ");
        gestorLogueado.deleteCliente(idCliente);
    }

    private void showMenuEditarVendedores() throws SQLException {
        int opcion = 0;
        boolean fin = false;
        do {
            opcion = MenuGestor.showMenuEditarVendedores();
            switch (opcion) {
                case 0:
                    fin = true;
                    break;
                case 1:
                    añadirVendedor();
                    break;
                case 2:
                    updateVendedor();
                    break;
                case 3:
                    deleteVendedor();
                    break;
            }
        } while (!fin);
    }
    private void añadirVendedor() throws SQLException {
        Persona persona = Mensajes.pedirDatosUsuario();
        String usuario = Mensajes.pedirUsername();
        String contraseña = Mensajes.pedirContraseña();
        Vendedor vendedor = new Vendedor(persona,usuario,contraseña);
        gestorLogueado.insertVendedor(vendedor);
    }

    private void updateVendedor() throws SQLException {
        System.out.println(Mensajes.MSG_AVISO_UPDATE);
        Persona persona = Mensajes.pedirDatosUsuario();
        String usuario = Mensajes.pedirUsername();
        String contraseña = Mensajes.pedirContraseña();
        Vendedor vendedor = new Vendedor(persona,usuario,contraseña);
        gestorLogueado.updateVendedor(vendedor);
    }

    private void deleteVendedor() throws SQLException {
        int idVendedor = Mensajes.pedirNumero("Id: ");
        gestorLogueado.deleteVendedor(idVendedor);
    }

    private void showMenuEditarGestores() throws SQLException {
        int opcion = 0;
        boolean fin = false;
        do {
            opcion = MenuGestor.showMenuEditarGestores();
            switch (opcion) {
                case 0:
                    fin = true;
                    break;
                case 1:
                    añadirGestor();
                    break;
                case 2:
                    updateGestor();
                    break;
                case 3:
                    deleteGestor();
                    break;
            }
        } while (!fin);
    }
    private void añadirGestor() throws SQLException {
        Persona persona = Mensajes.pedirDatosUsuario();
        String usuario = Mensajes.pedirUsername();
        String contraseña = Mensajes.pedirContraseña();
        GestorBDD vendedor = new GestorBDD(persona,usuario,contraseña);
        gestorLogueado.insertGestorBDD(vendedor);
    }

    private void updateGestor() throws SQLException {
        System.out.println(Mensajes.MSG_AVISO_UPDATE);
        Persona persona = Mensajes.pedirDatosUsuario();
        String usuario = Mensajes.pedirUsername();
        String contraseña = Mensajes.pedirContraseña();
        GestorBDD vendedor = new GestorBDD(persona,usuario,contraseña);
        gestorLogueado.updateGestorBDD(vendedor);
    }

    private void deleteGestor() throws SQLException {
        int idGestor = Mensajes.pedirNumero("Id: ");
        gestorLogueado.deleteGestorBDD(idGestor);
    }

    private void showMenuInformes() {

    }








}
