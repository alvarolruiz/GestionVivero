package Entidades.Documentos;

public class FilaInforme {
    private int idProducto;
    private double totalGanancias;

    public FilaInforme(int idProducto, double totalGanancias) {
        this.idProducto = idProducto;
        this.totalGanancias = totalGanancias;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public double getTotalGanancias() {
        return totalGanancias;
    }
}
