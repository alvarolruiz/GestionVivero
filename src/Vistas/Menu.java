package Vistas;

import java.util.Scanner;

public class Menu {
    public static final String MSG_ERROR_LOGIN = "El usuario y/o contraseña introducidos son incorrectos";
    public static final String MENU_INICIO_VENDEDOR = "Menú Inicio"+
                                                       "_____________________" +
                                                       "1. Iniciar Venta"+
                                                       "0. Finalizar Sesion" +
                                                       "_____________________";
    public static final String MSG_PEDIR_DATOS = "Introduce los datos del cliente";
    public static final String MSG_PEDIR_PRODUCTOS = "Introduce los productos a facturar";
    public static final String MSG_PREG_PRODUCTOS = "¿Quieres añadir otro producto?";

    public static Scanner tecla = new Scanner(System.in);
    public static final String =
    /**
     * Este método devuelve el login en el que los datos del vendedor/gestor validado.
     * @return String []
     */
    public static String[] showLogin(){
        String usuario = null;
        String contraseña = null;
        do{
            System.out.println("Iniciar Sesion" +
                               "______________" +
                               "Usuario: "
            );
            usuario = tecla.nextLine();
            System.out.println("Contraseña: ");
            contraseña = tecla.nextLine();
        }while (Validaciones.validarCredenciales(usuario,contraseña));
        System.out.println("Login correcto. Bienvenido " + usuario);
        return arrayDatosLogin(usuario, contraseña);
    }

    private static String [] arrayDatosLogin(String usuario, String contraseña) {
        String [] login = new String[2];
        login[0] = usuario;
        login[1] = contraseña;
        return login;
    }

    public static int showMenuInicioVendedor(){
        String opcion;
        do{
            System.out.println(MENU_INICIO_VENDEDOR);
            opcion = tecla.nextLine();
        }while (Validaciones.validarOpcion(opcion, Constantes.OPCIONES_MENU_VENDEDOR));
        return Integer.parseInt(opcion);
    }

    public static boolean iniciarVenta(){

    }
}
