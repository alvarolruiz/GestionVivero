package Entidades.Documentos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Informe {
    private Date fechaInicio;
    private Date fechaFin;
    private List<FilaInforme> elementosInforme;
    private double totalGlobal;

    public Informe(Date fechaInicio, Date fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.elementosInforme = new ArrayList<>();
        totalGlobal = 0;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public List<FilaInforme> getElementosInforme() {
        return elementosInforme;
    }

    public double getTotalGlobal() {
        return totalGlobal;
    }

    public void calcularTotal() {
        double total= 0;
        for (FilaInforme fi: elementosInforme) {
            total+=fi.getTotalGanancias();
        }
        this.totalGlobal = total;
    }

    public void añadirFilaInforme(FilaInforme filaInforme){
        elementosInforme.add(filaInforme);
    }
}
