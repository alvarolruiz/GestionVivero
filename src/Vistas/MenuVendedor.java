package Vistas;

import java.util.Scanner;

public class MenuVendedor {
    private static Scanner tecla = new Scanner(System.in);
    public static final String MENU_INICIO_VENDEDOR =
            "Menú Inicio Vendedor" + "\n" +
                    Mensajes.SEPARATOR + "\n" +
                    "1. Iniciar Venta" + "\n" +
                    "0. Cerrar Sesión" + "\n" +
                    Mensajes.SEPARATOR;

    public static int showMenuInicio() {
        int opcion = -1;
        do {
            System.out.println(MENU_INICIO_VENDEDOR);
            try {
                opcion = Integer.parseInt(tecla.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(Mensajes.MSG_NUMBER_FORMAT_EXEPTION);
            }
        } while (!Validaciones.validarOpcion(opcion, Constantes.OPCIONES_MENU_INICIO_VENDEDOR));
        return opcion;
    }

}
