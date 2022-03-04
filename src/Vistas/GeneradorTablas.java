package Vistas;

import Entidades.Documentos.FilaInforme;
import Entidades.Documentos.Informe;
import Entidades.Productos.Producto;
import Entidades.Productos.ProductoJardineria;
import Entidades.Productos.ProductoPlanta;
import Entidades.Usuarios.Administradores.Administrador;
import Entidades.Usuarios.Cliente;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GeneradorTablas {

    public static final String  SEPARATOR = "-----------------------------------------------------------------------------";
    public static final String  SMALL_SEPARATOR = "----------------------------------";

    public static void imprimirTablaAdministradores(ArrayList<Administrador> listadoAdministradores) {
        System.out.println(SEPARATOR);
        System.out.printf("%5s %15s %15s %10s %15s %10s", "ID", "NOMBRE", "USUARIO", "DNI", "CIUDAD", "TELEFONO");
        System.out.println();
        System.out.println(SEPARATOR);
        for (Administrador  ad: listadoAdministradores) {
            imprimirAdministrador(ad);
        }
        System.out.println(SEPARATOR);
    }

    public static void imprimirAdministrador(Administrador ad) {
        System.out.format("%5d %15s %15s %10s %15s %10d",
                ad.getId(), ad.getNombre(), ad.getUser(), ad.getDni(), ad.getCiudad(), ad.getTelefono());
        System.out.println();
    }

    public static void imprimirTablaClientes(ArrayList<Cliente> listadoClientes) {
        System.out.println(SEPARATOR);
        System.out.printf("%5s %20s %10s %5s %20s", "ID", "NOMBRE", "DNI", "CIUDAD", "TELEFONO");
        System.out.println();
        System.out.println(SEPARATOR);
        for (Cliente  c: listadoClientes) {
            imprimirCliente(c);
        }
        System.out.println(SEPARATOR);
    }

    public static void imprimirCliente(Cliente c) {
        System.out.format("%5s %20s %10s %5s %20d",
                c.getId(), c.getNombre(), c.getDni(), c.getCiudad(), c.getTelefono());
        System.out.println();
    }

    public static void imprimirTablaProductos(ArrayList<Producto> listadoProductos) {
        System.out.println(SEPARATOR);
        System.out.printf("%5s %15s %10s %5s %20s", "ID", "DESCRIPCIÓN", "PRECIO", "STOCK", "TIPO");
        System.out.println();
        System.out.println(SEPARATOR);
        for (Producto p : listadoProductos) {
            if (p instanceof ProductoPlanta) {
                imprimirProducto(p, "Planta");
            } else if (p instanceof ProductoJardineria) {
                imprimirProducto(p, "Producto Jardineria");
            }
        }
        System.out.println(SEPARATOR);
    }

    public static void imprimirProducto(Producto p, String tipo) {
        System.out.format("%5s %15s %10s %5d %20s",
                p.getId(), p.getDescripcion(), imprimirDouble(p.getPrecioUnitario()), p.getUnidadesDisponibles(), tipo);
        System.out.println();
    }

    public static String imprimirDouble(double d) {
        DecimalFormat df = new DecimalFormat("#.00");
        String string = df.format(d);
        return string;
    }

    public static void imprimirInforme(Informe informe) {
        System.out.println(SMALL_SEPARATOR);
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyy");
        System.out.println("Fecha inicio: " + formater.format(informe.getFechaInicio()));
        System.out.println("Fecha final: " + formater.format(informe.getFechaFin()));
        System.out.println("Total global: " + informe.getTotalGlobal());
        System.out.println(SMALL_SEPARATOR);
        System.out.printf("%10s %10s", "ID_PRODUCTO", "TOTAL");
        System.out.println();
        System.out.println(SMALL_SEPARATOR);
        if(!informe.getElementosInforme().isEmpty()){
            for (FilaInforme fi: informe.getElementosInforme()) {
                imprimirFilaInforme(fi);
            }
        }else{
            System.out.println("No hay ventas.");
        }

        System.out.println(SMALL_SEPARATOR);
    }

    public static void imprimirFilaInforme(FilaInforme fi) {
        System.out.format("%10s %10s",
            fi.getIdProducto(), imprimirDouble(fi.getTotalGanancias()));
        System.out.println();
    }
}
