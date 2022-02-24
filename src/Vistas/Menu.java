package Vistas;

import java.util.Locale;
import java.util.Scanner;

public class Menu {
    public static final String MSG_ERROR_LOGIN = "El usuario y/o contraseña introducidos son incorrectos";
    public static final String SEPARATOR = "_____________________";
    public static final String MENU_INICIO_VENDEDOR = "Menú Inicio"+
                                                       SEPARATOR +
                                                       "1. Iniciar Venta"+
                                                       "0. Cerrar Sesion" +
                                                       SEPARATOR;


    public static final String MSG_INICIAR_VENTA = "Ha iniciado una venta";
    public static final String MSG_PEDIR_DATOS_CLIENTE = "¿Desea introducir el dni o el teléfono del cliente?."+ "\n" +
                                                         "Introduzca D o T";
    public static final String MSG_PEDIR_DNI_CLIENTE = "Introduce el dni del cliente:";
    public static final String MSG_PEDIR_TELEFONO_CLIENTE = "Introduce el telefono del cliente:";
    public static final String MSG_CLIENTE_NO_REGISTRADO = "Cliente no registrado. No podrá disfrutar de los descuentos" +
            "de afiliado.";

    public static final String MSG_LINEA_FACTURA = "Nueva linea factura" + "\n" +
                                                    SEPARATOR;
    public static final String MSG_COD_PROD = "Código de producto:";
    public static final String MSG_CANTIDAD_PROD = "Cantidad de producto:";
    public static final String MSG_PREG_LINEA_FACTURA = "¿Quieres añadir otra linea a la factura?" +"\n"+
                                                    "Introduzca S o N";
    public static final String MSG_PREG_ACCION_FACTURA = "Desea guardar o descartar la factura?";
    public static final String MSG_FACTURA_GUARDADA = "La factura se ha guardado.";
    public static final String MSG_FACTURA_CANCELADA = "La factura ha sido eliminada, no se guardaron los cambios.";
    public static final String NUMBER_FORMAT_EXEPTION ="Ha introducido uno o más caracteres, porfavor introduce un número.";
    private static final String MSG_ERROR_ENTRADA = "Opción no válida, vuelve a intentarlo";

    public static Scanner tecla = new Scanner(System.in);
    /**
     * Este método devuelve el login en el que los datos del vendedor/gestor validado.
     * @return String []
     */
    public static String[] showLogin(){
        String usuario = null;
        String contraseña = null;
        do{
            System.out.println("Iniciar Sesion" +  "\n"
                            +  "---------------" +  "\n"
                            +  "Usuario: "
            );
            usuario = tecla.nextLine();
            System.out.println("Contraseña: ");
            contraseña = tecla.nextLine();
        }while (Validaciones.validarCredenciales("admin",usuario,contraseña));
        System.out.println("Login correcto. Bienvenido " + usuario);
        return arrayDatosLogin(usuario, contraseña);
    }

    public static boolean preguntarNuevaLineaFactura(){
        String respuesta = "";
        boolean nuevaLinea = false;
        boolean correcto =true;
        do{
            System.out.println(Menu.MSG_PREG_LINEA_FACTURA);
            respuesta = tecla.nextLine();
            if(respuesta.toUpperCase(Locale.ROOT).equals("S")){
                nuevaLinea=true;
            }else if(respuesta.toUpperCase(Locale.ROOT).equals("N")){
            }else{
                System.out.println(MSG_ERROR_ENTRADA);
                correcto=false;
            }
        }while (!correcto);
        return nuevaLinea;
    }

    public static int pedirCodigoProducto(){
        int codigoProducto = 0;
        System.out.println(Menu.MSG_COD_PROD);
        try {
            codigoProducto = Integer.parseInt(tecla.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(Menu.NUMBER_FORMAT_EXEPTION);
        }
        return codigoProducto;
    }

    public static int pedirCantidadProducto(){
        int cantidadProducto = 0;
        System.out.println(Menu.MSG_CANTIDAD_PROD);
        try {
            cantidadProducto = Integer.parseInt(tecla.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(Menu.NUMBER_FORMAT_EXEPTION);
        }
        return cantidadProducto;
    }

    private static String [] arrayDatosLogin(String usuario, String contraseña) {
        String [] login = new String[2];
        login[0] = usuario;
        login[1] = contraseña;
        return login;
    }

    public static int showMenuInicioVendedor(){
        int opcion=3;
        do{
            System.out.println(MENU_INICIO_VENDEDOR);
            try{
                opcion = Integer.parseInt(tecla.nextLine());
            }catch (NumberFormatException e){
                System.out.println(NUMBER_FORMAT_EXEPTION);
            }
        }while (Validaciones.validarOpcion(opcion, Constantes.OPCIONES_MENU_VENDEDOR));
        return opcion;
    }


}
