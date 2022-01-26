package Entidades.Productos;

public abstract class Producto {
    private int codigo;
    private String descripcion;
    private double precioUnitario;
    private int unidadesDisponibles;

    public Producto(int codigo, String descripcion, double precioUnitario, int unidadesDisponibles) {
        codigo = codigo;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        codigo = codigo;
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

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }


}
