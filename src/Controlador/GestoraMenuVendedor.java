package Controlador;

import DataAccessLayer.Gestoras.GestoraProductos;
import DataAccessLayer.Listados.ListadosFacturas;
import DataAccessLayer.Listados.ListadosProductos;
import Entidades.Documentos.Factura;
import Entidades.Documentos.FilaFactura;
import Entidades.Productos.Producto;
import Entidades.Usuarios.Administradores.Vendedor;
import Entidades.Usuarios.Cliente;
import Vistas.Mensajes;
import Vistas.MenuVendedor;

import java.sql.SQLException;
import java.util.Scanner;

public class GestoraMenuVendedor {

    private static Scanner tecla = new Scanner(System.in);

    private Vendedor vendedorLogeado;

    public GestoraMenuVendedor(Vendedor vendedorLogeado) {
        this.vendedorLogeado = vendedorLogeado;
    }

    public void showMenuVendedor() throws SQLException {
        int opcion = 0;
        boolean fin = false;
        do {
            opcion = MenuVendedor.showMenuInicio();
            switch (opcion) {
                case 0:
                    fin = true;
                    break;
                case 1:
                    iniciarVenta();
                    break;
            }
        } while (!fin);

    }

    private void iniciarVenta() throws SQLException {
        System.out.println(Mensajes.MSG_INICIAR_VENTA);
        Cliente clienteComprador = Mensajes.preguntarDatosCliente();
        int idFactura = ListadosFacturas.getNextIdFactura();
        Factura factura = new Factura(idFactura, clienteComprador.getId(), vendedorLogeado.getId());
        boolean nuevaLinea = false;
        do {
            System.out.println(Mensajes.MSG_LINEA_FACTURA);
            factura.añadirFilaFactura(nuevaLineaFactura(idFactura));
            nuevaLinea = Mensajes.preguntarNuevaLineaFactura();
        } while (nuevaLinea);
        factura.calcularImporteTotal();
        if (clienteComprador.getId() != 1) {
            aplicarDescuento(factura, clienteComprador.getDescuentoCliente());
        }
        vendedorLogeado.imprimirFactura(factura);
        if (Mensajes.preguntarGuardarFactura()) {
            vendedorLogeado.guardarFactura(factura);
        }
    }

    private void aplicarDescuento(Factura factura, double descuentoCliente) {
        double descuento = factura.getImporteTotal() * descuentoCliente;
        System.out.println(String.format("Ha ahorrado %f euros", descuento));
        factura.setImporteTotal(factura.getImporteTotal() - descuento);
    }

    private FilaFactura nuevaLineaFactura(int idFactura) throws SQLException {
        Producto producto = null;
        int cantidadProducto = 0;
        FilaFactura fila = null;
        do {
            producto = ListadosProductos.getProducto(Mensajes.pedirNumero(Mensajes.MSG_COD_PROD));
            if (producto != null && producto.getUnidadesDisponibles() > 0) {
                System.out.println(String.format(" %s, %d Unidades Disponibles.", producto.getDescripcion(), producto.getUnidadesDisponibles()));
                cantidadProducto = Mensajes.pedirNumero(Mensajes.MSG_CANTIDAD_PROD);
                fila = new FilaFactura(idFactura, producto, cantidadProducto);
            } else {
                System.out.println("Producto no existe o no hay stock");
            }
        } while (fila == null);
        return fila;
    }


}
