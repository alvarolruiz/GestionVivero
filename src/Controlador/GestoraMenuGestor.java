package Controlador;

import DataAccessLayer.Listados.ListadosInformes;
import Entidades.Documentos.Informe;
import Entidades.Productos.Producto;
import Entidades.Productos.ProductoJardineria;
import Entidades.Productos.ProductoPlanta;
import Entidades.Usuarios.Administradores.GestorBDD;
import Entidades.Usuarios.Administradores.Vendedor;
import Entidades.Usuarios.Cliente;
import Entidades.Usuarios.Persona;
import Vistas.GeneradorTablas;
import Vistas.Mensajes;
import Vistas.MenuGestor;

import java.sql.SQLException;
import java.util.Date;

public class GestoraMenuGestor {
    GestorBDD gestorLogueado;

    public GestoraMenuGestor(GestorBDD gestorLogueado) {
        this.gestorLogueado = gestorLogueado;
    }

    public void showMenuGestor() throws SQLException {
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
                case 5:
                    showMenuEditarFacturas();
                    break;
            }
        } while (!fin);
    }

    private void showMenuEditarFacturas() throws SQLException {
        int opcion = 0;
        boolean fin = false;
        do {
            opcion = MenuGestor.showMenuEditarFacturas();
            switch (opcion) {
                case 0:
                    fin = true;
                    break;
                case 1:
                    eliminarFactura();
                    break;
            }
        } while (!fin);
    }

    private void eliminarFactura() throws SQLException {
        int idFactura = Mensajes.pedirNumero("Id de la facura que desea borrar: ");
        gestorLogueado.deleteFactura(idFactura);
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
                    showMenuA??adirProducto();
                    break;
                case 2:
                    showMenuUpdateProducto();
                    break;
                case 3:
                    deleteProducto();
            }
        } while (!fin);
    }

    private void showMenuA??adirProducto() throws SQLException {
        int opcion = 0;
        boolean fin = false;
        do {
            opcion = MenuGestor.showMenuA??adirProducto();
            switch (opcion) {
                case 0:
                    fin = true;
                    break;
                case 1:
                    a??adirProductoJardineria();
                    break;
                case 2:
                    a??adirProductoPlanta();
            }
        } while (!fin);
    }

    private void a??adirProductoJardineria() throws SQLException {
        ProductoJardineria productoJardineria = new ProductoJardineria(Mensajes.pedirDatosProducto());
        gestorLogueado.insertProductoJardineria(productoJardineria);
    }

    private void a??adirProductoPlanta() throws SQLException {
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
        ProductoJardineria productoJardineria = new ProductoJardineria(Mensajes.pedirDatosProducto());
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
                    a??adirCliente();
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
    private void a??adirCliente() throws SQLException {
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
                    a??adirVendedor();
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
    private void a??adirVendedor() throws SQLException {
        Persona persona = Mensajes.pedirDatosUsuario();
        String usuario = Mensajes.pedirUsername();
        String contrase??a = Mensajes.pedirContrase??a();
        Vendedor vendedor = new Vendedor(persona,usuario,contrase??a);
        gestorLogueado.insertVendedor(vendedor);
    }

    private void updateVendedor() throws SQLException {
        System.out.println(Mensajes.MSG_AVISO_UPDATE);
        Persona persona = Mensajes.pedirDatosUsuario();
        String usuario = Mensajes.pedirUsername();
        String contrase??a = Mensajes.pedirContrase??a();
        Vendedor vendedor = new Vendedor(persona,usuario,contrase??a);
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
                    a??adirGestor();
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
    private void a??adirGestor() throws SQLException {
        Persona persona = Mensajes.pedirDatosUsuario();
        String usuario = Mensajes.pedirUsername();
        String contrase??a = Mensajes.pedirContrase??a();
        GestorBDD vendedor = new GestorBDD(persona,usuario,contrase??a);
        gestorLogueado.insertGestorBDD(vendedor);
    }

    private void updateGestor() throws SQLException {
        System.out.println(Mensajes.MSG_AVISO_UPDATE);
        Persona persona = Mensajes.pedirDatosUsuario();
        String usuario = Mensajes.pedirUsername();
        String contrase??a = Mensajes.pedirContrase??a();
        GestorBDD vendedor = new GestorBDD(persona,usuario,contrase??a);
        gestorLogueado.updateGestorBDD(vendedor);
    }

    private void deleteGestor() throws SQLException {
        int idGestor = Mensajes.pedirNumero("Id: ");
        gestorLogueado.deleteGestorBDD(idGestor);
    }

    private void showMenuInformes() throws SQLException {
        int opcion = 0;
        boolean fin = false;
        do {
            opcion = MenuGestor.showMenuInformes();
            switch (opcion) {
                case 0:
                    fin = true;
                    break;
                case 1:
                    generarInformeMensual();
                    break;
                case 2:
                    generarInformeAnual();
                    break;
            }
        } while (!fin);
    }

    private void generarInformeMensual() throws SQLException {
        Date fechaInicial = Mensajes.pedirFecha();
        Informe informe = ListadosInformes.generarInformeMensual(fechaInicial);
        System.out.println("Informe Mensual");
        GeneradorTablas.imprimirInforme(informe);
    }

    private void generarInformeAnual() throws SQLException {
        Date fechaInicial = Mensajes.pedirFecha();
        Informe informe = ListadosInformes.generarInformeAnual(fechaInicial);
        System.out.println("Informe Anual");
        GeneradorTablas.imprimirInforme(informe);
    }

}
