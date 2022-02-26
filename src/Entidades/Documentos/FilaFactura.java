package Entidades.Documentos;

import Entidades.Productos.Producto;

public class FilaFactura {
    private int idFactura;
    private int idProducto;
    private int cantidad;
    private double precioUnitario;
    private double totalFila;

    public FilaFactura(int idFactura, Producto producto, int cantidad) {
        this.idFactura = idFactura;
        this.idProducto = producto.getId();
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecioUnitario();
        this.totalFila = calcularTotal();
    }

    public int getIdFactura() {
        return idFactura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getTotalFila() {
        return totalFila;
    }

    private double calcularTotal() {
        return precioUnitario*cantidad;
    }
}
