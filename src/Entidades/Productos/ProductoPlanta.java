package Entidades.Productos;

public abstract class ProductoPlanta extends Producto{

    private String tipoPlanta;

    public ProductoPlanta(int codigo, String descripcion, double precioUnitario, int unidadesDisponibles, String tipoPlanta) {
        super(codigo, descripcion, precioUnitario, unidadesDisponibles);
        this.tipoPlanta = tipoPlanta;
    }
}
