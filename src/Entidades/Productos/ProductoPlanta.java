package Entidades.Productos;

public class ProductoPlanta extends Producto{
    private String tipoPlanta;

    public String getTipoPlanta() {
        return tipoPlanta;
    }

    public void setTipoPlanta(String tipoPlanta) {
        this.tipoPlanta = tipoPlanta;
    }

    public ProductoPlanta(int codigo, String descripcion, double precioUnitario, int unidadesDisponibles, String tipoPlanta) {
        super(codigo, descripcion, precioUnitario, unidadesDisponibles);
        this.tipoPlanta = tipoPlanta;
    }
}
