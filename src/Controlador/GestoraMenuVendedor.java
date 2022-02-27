package Controlador;

import DataAccessLayer.Gestoras.GestoraFacturas;
import DataAccessLayer.Listados.ListadosFacturas;
import DataAccessLayer.Listados.ListadosUsuarios;
import Entidades.Documentos.Factura;
import Entidades.Documentos.FilaFactura;
import Entidades.Productos.Producto;
import Entidades.Usuarios.Administradores.Vendedor;
import Entidades.Usuarios.Cliente;
import Vistas.Constantes;
import Vistas.Menu;

import java.sql.SQLException;
import java.util.Scanner;

public class GestoraMenuVendedor {
    private static Scanner tecla = new Scanner(System.in);

    private Vendedor vendedorLogeado;



    public GestoraMenuVendedor(Vendedor vendedorLogeado) {
        this.vendedorLogeado = vendedorLogeado;
    }

    public void showMenuVend() throws SQLException {
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

    private void iniciarVenta() throws SQLException {
        System.out.println(Menu.MSG_INICIAR_VENTA);
        Cliente clienteComprador = preguntarDatosCliente();
        int idFactura = ListadosFacturas.getNextIdFactura();
        Factura factura = new Factura(idFactura, clienteComprador.getId(),vendedorLogeado.getId());
        // Clase factura tiene como fecha java.util.date
        //java.sql.Timestamp fechaSql = new java.sql.Timestamp(new Date().getTime());
        boolean nuevaLinea = false;
        do {
            System.out.println(Menu.MSG_LINEA_FACTURA);
            factura.añadirFilaFactura(nuevaLineaFactura(idFactura));
            nuevaLinea = Menu.preguntarNuevaLineaFactura();
        }while(nuevaLinea);
        if(Menu.preguntarGuardarFactura()){
            guardarFactura(factura);
        }else{
            factura = null;
        }
    }

    private FilaFactura nuevaLineaFactura(int idFactura) {
        Producto producto = null;
        producto = getProducto(Menu.pedirCodigoProducto());
        int cantidadProducto = Menu.pedirCantidadProducto();
        FilaFactura fila = new FilaFactura(idFactura,producto,cantidadProducto);
        return fila;
    }

    /**
     * Este metodo guardara la factura en la base de datos. Guardará los datos de la factura primero y después
     * irá llamando a otro método guardar lineaFactura por cada una de entrads que tiene la lista de facturas
     * @param factura
     */
    private void guardarFactura(Factura factura) throws SQLException {
        int filasAfectadasFacturas = 0;
        int filasAfectadasLineas = 0;
        filasAfectadasFacturas = GestoraFacturas.añadirFactura(factura);
        filasAfectadasLineas = GestoraFacturas.añadirFilasFactura(factura.getArticulos());
        if(filasAfectadasFacturas == 1 && filasAfectadasLineas >=1){
            System.out.println(Menu.MSG_ACTUALIZACION_CORRECTA);
        }
    }



    /**
     * Busca en la base de datos el producto con dicho id y lo devuelve
     * @param codigoProducto
     */

    private Producto getProducto(int codigoProducto) {

        Producto producto= null;
        return producto;
    }


    private Cliente preguntarDatosCliente() throws SQLException {
        String dniOTelefono;
        Cliente clienteComprador = null;
        System.out.println(Menu.MSG_PEDIR_DATOS_CLIENTE);
        String respuesta = tecla.nextLine();
        if (respuesta.equalsIgnoreCase("D")) {
            System.out.println(Menu.MSG_PEDIR_DNI_CLIENTE);
            dniOTelefono = tecla.nextLine();
            clienteComprador = getCliente(true, dniOTelefono);
            if (clienteComprador.getId()== Constantes.ID_CLIENTE_NO_REGISTRADO) {
                System.out.println(Menu.MSG_CLIENTE_NO_REGISTRADO);
            }
        } else if (respuesta.equalsIgnoreCase("T")) {
            System.out.println(Menu.MSG_PEDIR_TELEFONO_CLIENTE);
            dniOTelefono = tecla.nextLine();
            clienteComprador = getCliente(false, dniOTelefono);
            if (clienteComprador.getId() == Constantes.ID_CLIENTE_NO_REGISTRADO) {
                System.out.println(Menu.MSG_CLIENTE_NO_REGISTRADO);
            }
        }
        return clienteComprador;
    }

    private Cliente getCliente(boolean dni, String dniOTelefono) throws SQLException {
        Cliente clienteComprador = null;
        if (dni) {
           clienteComprador =  ListadosUsuarios.getClienteDni(dniOTelefono);
        } else {
            clienteComprador = ListadosUsuarios.getClienteTelefono(Integer.parseInt(dniOTelefono));
        }
        return clienteComprador;
    }
}
