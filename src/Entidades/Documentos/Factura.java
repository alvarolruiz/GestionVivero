package Entidades.Documentos;

import Entidades.Usuarios.Administradores.Vendedor;
import Entidades.Usuarios.Cliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Factura {
    // En la bdd se almacenara el dni de cliente y vendedor
    private Cliente cliente;
    private Vendedor vendedor;
    private Date fecha;
    private List<FilaFactura> articulos;
    private double importeTotal;

    public Factura(Date fecha, Cliente cliente, Vendedor vendedor) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.fecha = fecha;
        this.articulos = new ArrayList<>();
    }

    public Factura(Cliente cliente, Vendedor vendedor) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.fecha = fecha;
        this.articulos = new ArrayList<>();

    }
    //Se podría hacer un carrito de la compra en el que se vayan almacenando los productos que se añaden en el menu,
    // y despues pasarselo a la factura, la cual de los datos simplificados extraera la informacion necesaria y completará la lista de
    // articulos
    public void añadirFilaFactura(FilaFactura fila){
        articulos.add(fila);
    }

    public void imprimirFactura(){

    }


}
