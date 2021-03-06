package Entidades.Productos;

public class Producto {
    private int id;
    private String descripcion;
    private double precioUnitario;
    private int unidadesDisponibles;

    public Producto(int id, String descripcion, double precioUnitario, int unidadesDisponibles) {
        this.id = id;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public Producto (Producto producto){
        this.id = producto.getId();
        this.descripcion = producto.getDescripcion();
        this.precioUnitario = producto.getPrecioUnitario();
        this.unidadesDisponibles = producto.getUnidadesDisponibles();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }


}
