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
    //TODO Aplicar descuento de cliente y avisar al usuario del descuento generado
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
        Factura factura = new Factura(idFactura, clienteComprador.getId(),vendedorLogeado.getId());
        // Clase factura tiene como fecha java.util.date
        //java.sql.Timestamp fechaSql = new java.sql.Timestamp(new Date().getTime());
        boolean nuevaLinea = false;
        do {
            System.out.println(Mensajes.MSG_LINEA_FACTURA);
            factura.añadirFilaFactura(nuevaLineaFactura(idFactura));
            nuevaLinea = Mensajes.preguntarNuevaLineaFactura();
        }while(nuevaLinea);
        factura.setImporteTotal();
        vendedorLogeado.imprimirFactura(factura);
        if(Mensajes.preguntarGuardarFactura()){
            vendedorLogeado.guardarFactura(factura);
        }
    }

    private FilaFactura nuevaLineaFactura(int idFactura) throws SQLException {
        Producto producto = null;
        FilaFactura fila = null;
        do {
            producto = ListadosProductos.getProducto(Mensajes.pedirNumero(Mensajes.MSG_COD_PROD));
            if (producto != null) {
                System.out.println(producto.getDescripcion());
                int cantidadProducto = Mensajes.pedirNumero(Mensajes.MSG_CANTIDAD_PROD);
                fila = new FilaFactura(idFactura, producto, cantidadProducto);
            } else {
                System.out.println("Producto no existe");
            }
        }while (fila==null);
        return fila;
    }



}
