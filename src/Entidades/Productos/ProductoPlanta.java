package Entidades.Productos;

public class ProductoPlanta extends Producto{
    private Integer tipoPlanta;

    public int getTipoPlanta() {
        return tipoPlanta;
    }

    public void setTipoPlanta(int tipoPlanta) {
        this.tipoPlanta = tipoPlanta;
    }

    public ProductoPlanta(int codigo, String descripcion, double precioUnitario, int unidadesDisponibles, int tipoPlanta) {
        super(codigo, descripcion, precioUnitario, unidadesDisponibles);
        this.tipoPlanta = tipoPlanta;
    }
}
