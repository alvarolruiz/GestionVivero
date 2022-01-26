package Entidades.Documentos;

import Entidades.Productos.Producto;
import Entidades.Productos.ProductoPlanta;

public class FilaFactura {
    Producto producto;
    double precioUnitario;
    int cantidad;
    double total;

    public FilaFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.precioUnitario = this.producto.getPrecioUnitario();
        this.cantidad = cantidad;
        this.total = calcularTotal();
    }

    private double calcularTotal() {
        return precioUnitario*cantidad;
    }
}
