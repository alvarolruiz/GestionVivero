package Entidades.Usuarios.Administradores;

import DataAccessLayer.Gestoras.GestoraFacturas;
import DataAccessLayer.Gestoras.GestoraProductos;
import Entidades.Documentos.Factura;
import Entidades.Documentos.FilaFactura;
import Entidades.Usuarios.Cliente;
import Entidades.Usuarios.Persona;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Administrador {

    public static final String  SEPARATOR = "-----------------------------------------------------------------------------";

    public Vendedor(int id, String nombre, String dni, String direccion, int codPostal, String ciudad, int telefono, String correoElectronico, String user, String password) {
        super(id, nombre, dni, direccion, codPostal, ciudad, telefono, correoElectronico, user, password);
    }

    public Vendedor(Persona persona, String user, String password) {
        super(persona, user, password);
    }

    public Vendedor(Administrador administrador) {
        super(administrador);
    }

    public void guardarFactura(Factura factura) throws SQLException {
        GestoraFacturas.insertFactura(factura);
        GestoraFacturas.insertFilasFactura(factura.getArticulos());
        actualizarStockProductosFactura(factura.getArticulos());
    }

    private void actualizarStockProductosFactura(List<FilaFactura> articulos) throws SQLException {
        for (FilaFactura ff: articulos) {
            GestoraProductos.updateStockProducto(ff.getCantidad(),ff.getIdProducto());
        }
    }

    public void imprimirFactura(Factura factura){
        System.out.println(String.format("Id Factura: %d",factura.getIdFactura()));
        System.out.println(String.format("Id Vendedor: %d",factura.getIdVendedor()));
        System.out.println(String.format("Id Cliente: %d",factura.getIdCliente()));
        System.out.println(String.format("Total: %f",factura.getImporteTotal()));
        imprimirTablaLineasFactura(factura.getArticulos());
    }

    public void imprimirTablaLineasFactura(List<FilaFactura> articulos){
        System.out.println("Artículos");
        System.out.println(SEPARATOR);
        System.out.printf("%15s %20s %15s %15s", "ID_PRODUCTO", "CANTIDAD", "PRECIO", "TOTAL");
        System.out.println();
        System.out.println(SEPARATOR);
        for (FilaFactura ff: articulos) {
            imprimirLineaFactura(ff);
        }
        System.out.println(SEPARATOR);
    }

    private void imprimirLineaFactura(FilaFactura filaFactura) {
        System.out.printf("%15d %20d %15f %15f",
                filaFactura.getIdProducto(), filaFactura.getCantidad(), filaFactura.getPrecioUnitario(), filaFactura.getTotalFila());
        System.out.println();
    }

    private void imprimirTablaClientes(ArrayList<Cliente> listadoClientes) {
        System.out.println(SEPARATOR);
        System.out.printf("%5s %20s %10s %5s %20s", "ID", "NOMBRE", "DNI", "CIUDAD", "TELEFONO");
        System.out.println();
        System.out.println(SEPARATOR);
        for (Cliente  c: listadoClientes) {
            imprimirCliente(c);
        }
        System.out.println(SEPARATOR);
    }

    private void imprimirCliente(Cliente c) {
        System.out.format("%5s %20s %10s %5s %20d",
                c.getId(), c.getNombre(), c.getDni(), c.getCiudad(), c.getTelefono());
        System.out.println();
    }
}
