package Entidades.Documentos;

import Entidades.Usuarios.Administradores.Vendedor;
import Entidades.Usuarios.Cliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Factura {
    private int idFactura;
    private int idCliente;
    private int idVendedor;
    private Date fecha;
    private List<FilaFactura> articulos;
    private double importeTotal;

    public Factura(int idFactura, int idCliente, int idVendedor) {
        this.idFactura = 0;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.fecha = new Date();
        this.articulos = new ArrayList<>();
        this.importeTotal = 0;
    }

    public Factura(int idCliente, int idVendedor) {
        this.idFactura = idFactura;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.fecha = new Date();
        this.articulos = new ArrayList<>();

    }


    public int getIdFactura() {
        return idFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public List<FilaFactura> getArticulos() {
        return articulos;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    //Se podría hacer un carrito de la compra en el que se vayan almacenando los productos que se añaden en el menu,
    // y despues pasarselo a la factura, la cual de los datos simplificados extraera la informacion necesaria y completará la lista de
    // articulos
    public void añadirFilaFactura(FilaFactura fila) {
        articulos.add(fila);
    }

    public void calcularImporteTotal() {
        double total = 0;
        for (FilaFactura ff : articulos) {
            total += ff.getTotalFila();
        }
        this.importeTotal = total;
    }

    public void imprimirFactura() {

    }


}
