package Controlador;

import Entidades.Documentos.Factura;
import Entidades.Documentos.FilaFactura;
import Entidades.Productos.Producto;
import Entidades.Usuarios.Administradores.Vendedor;
import Entidades.Usuarios.Cliente;
import Vistas.Constantes;
import Vistas.Menu;
import Vistas.Validaciones;

import java.util.Date;
import java.util.Scanner;

public class GestoraMenu {
    private static Scanner tecla = new Scanner(System.in);

    private Vendedor vendedorLogeado;

    public GestoraMenu(Vendedor vendedorLogeado) {
        this.vendedorLogeado = vendedorLogeado;
    }

    public void showMenu() {
        int opcion = 0;
        boolean fin = false;
        do {
            opcion = Menu.showMenuInicioVendedor();
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

    private void iniciarVenta() {
        System.out.println(Menu.MSG_INICIAR_VENTA);
        Cliente clienteComprador = preguntarDatosCliente();
        Factura factura = new Factura(clienteComprador,vendedorLogeado);
        //todo Obtener fecha actual
        boolean nuevaLinea = false;
        do {
            System.out.println(Menu.MSG_LINEA_FACTURA);
            factura.añadirFilaFactura(nuevaLineaFactura());
            nuevaLinea = Menu.preguntarNuevaLineaFactura();
        }while(nuevaLinea);
        System.out.println(Menu.MSG_PREG_ACCION_FACTURA);



    }

    private FilaFactura nuevaLineaFactura() {
        Producto producto = null;
        producto = getProducto(Menu.pedirCodigoProducto());
        int cantidadProducto = Menu.pedirCantidadProducto();
        FilaFactura fila = new FilaFactura(producto,cantidadProducto);
        return fila;
    }

    /**
     * Busca en la base de datos el producto con dicho id y lo devuelve
     * @param codigoProducto
     */

    private Producto getProducto(int codigoProducto) {
        Producto producto= null;
        return producto;
    }


    private Cliente preguntarDatosCliente() {
        String dniOTelefono;
        Cliente clienteComprador = null;
        System.out.println(Menu.MSG_PEDIR_DATOS_CLIENTE);
        String respuesta = tecla.nextLine();
        if (respuesta.equals("D")) {
            System.out.println(Menu.MSG_PEDIR_DNI_CLIENTE);
            dniOTelefono = tecla.nextLine();
            clienteComprador = getCliente(true, dniOTelefono);
            if (clienteComprador == null) {
                clienteComprador = Constantes.CLIENTE_NO_REGISTRADO;
                System.out.println(Menu.MSG_CLIENTE_NO_REGISTRADO);
            }
        } else if (respuesta.equals("T")) {
            System.out.println(Menu.MSG_PEDIR_TELEFONO_CLIENTE);
            dniOTelefono = tecla.nextLine();
            getCliente(false, dniOTelefono);
            if (clienteComprador == null)
                clienteComprador = Constantes.CLIENTE_NO_REGISTRADO;
            System.out.println(Menu.MSG_CLIENTE_NO_REGISTRADO);
        }
        return clienteComprador;
    }



    private Cliente getCliente(boolean dni, String dniOTelefono) {
        Cliente clienteComprador = null;
        if (dni) {
        } else {

        }
    }
}
