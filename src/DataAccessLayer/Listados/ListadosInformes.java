package DataAccessLayer.Listados;

import DataAccessLayer.Conexion.DatosConexion;
import Entidades.Documentos.FilaInforme;
import Entidades.Documentos.Informe;
import Vistas.Mensajes;
import org.jetbrains.annotations.NotNull;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ListadosInformes {

    public static DatosConexion datosConexion = new DatosConexion();

    /**
     * Generar un informe con el total de ventas de un mes indicando la fecha. Este informe incluirá las ventas totales por cada tipo de producto y el total global.
     * Generar un informe de ventas de to do un año, con los mismos datos que el anterior.
     */

    public static Informe generarInformeMensual(Date fechaInicio) throws SQLException {
        FilaInforme fila = null;
        Date fechaIni = fechaInicio;
        Date fechaFinal = getFechaMasMes(fechaInicio);
        return getInforme(fechaIni, fechaFinal);
    }

    public static Informe generarInformeAnual(Date fechaInicio) throws SQLException {
        ResultSet resultSet;
        FilaInforme fila = null;
        Date fechaFinal = getFechaMasAño(fechaInicio);
        return getInforme(fechaInicio, fechaFinal);
    }

    @NotNull
    private static Informe getInforme(Date fechaInicio, Date fechaFinal) throws SQLException {
        ResultSet resultSet;
        FilaInforme fila;
        String sql = "EXEC sp_GenerarInforme @fechaInicio = ?, @fechaFinal = ?";
        Informe informe = new Informe(fechaInicio, fechaFinal);
        try (Connection c = datosConexion.getConexion()) {
            CallableStatement cst = c.prepareCall(sql);
            cst.setDate(1, new java.sql.Date(informe.getFechaInicio().getTime()));
            cst.setDate(2, new java.sql.Date(informe.getFechaFin().getTime()));
            resultSet = cst.executeQuery();
                while (resultSet.next()) {
                    fila = new FilaInforme(resultSet.getInt(1), resultSet.getDouble(2));
                    informe.añadirFilaInforme(fila);
                }
                informe.calcularTotal();
            }
        return informe;
    }


    private static Date getFechaMasMes(Date fechaInicio) {
        Calendar c1 = GregorianCalendar.getInstance();
        Date fechaFin = new Date();
        try {
            c1.setTime(fechaInicio);
            c1.add(Calendar.MONTH, 1);
            fechaFin= c1.getTime();
        } catch (Exception e) {
            return null;
        }
        return fechaFin;
    }

    private static Date getFechaMasAño(Date fechaInicio) {
        Calendar c1 = GregorianCalendar.getInstance();
        Date fechaFin = new Date();
        try {
            c1.setTime(fechaInicio);
            c1.add(Calendar.YEAR, 1);
            fechaFin= c1.getTime();
        } catch (Exception e) {
            return null;
        }
        return fechaFin;
    }
}
