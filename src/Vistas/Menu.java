package Vistas;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    public static Scanner tecla = new Scanner(System.in);
    public static final String SEPARATOR = "_____________________";
    public static final String MSG_ERROR_CONTRASENA = "La contraseña que ha introducido es incorrecta";
    public static final String MSG_ERROR_LOGIN_GESTOR = "El gestor no existe";
    public static final String MSG_ERROR_LOGIN_VENDEDOR = "El Vendedor no existe";
    public static final String MSG_ERROR_LOGIN_USUARIO = "El usuario que ha introducido no existe";
    public static final String MSG_INICIAR_VENTA = "Ha iniciado una venta";
    public static final String MSG_PEDIR_DATOS_CLIENTE = "¿Desea introducir el dni o el teléfono del cliente?."+ "\n" +
            "Introduzca D o T:";
    public static final String MSG_PEDIR_DNI_CLIENTE = "Introduce el dni del cliente:";
    public static final String MSG_PEDIR_TELEFONO_CLIENTE = "Introduce el telefono del cliente:";
    public static final String MSG_CLIENTE_NO_REGISTRADO = "Cliente no registrado. No podrá disfrutar de los descuentos" +
            "de afiliado.";
    public static final String MSG_LINEA_FACTURA = "Nueva linea factura" + "\n" +
            SEPARATOR;
    public static final String MSG_COD_PROD = "Código de producto:";
    public static final String MSG_CANTIDAD_PROD = "Cantidad de producto:";
    public static final String MSG_PREG_LINEA_FACTURA = "¿Quieres añadir otra linea a la factura?";
            ;
    public static final String MSG_PREG_SI_NO ="Introduzca S o N:";
    public static final String MSG_PREG_GUARDAR_FACTURA = "Desea guardar o descartar la factura?";
    public static final String MSG_FACTURA_GUARDADA = "La factura se ha guardado.";
    public static final String MSG_FACTURA_CANCELADA = "La factura ha sido eliminada, no se guardaron los cambios.";

    public static final String MSG_NUMBER_FORMAT_EXEPTION ="Ha introducido uno o más caracteres, porfavor introduce un número.";
    public static final String MSG_SQL_EXEPTION ="Se ha producido un error al acceder a la base de datos";
    private static final String MSG_ERROR_ENTRADA = "Opción no válida, vuelve a intentarlo";
    public static final String MENU_INICIO_VENDEDOR = "Menú Inicio"+
                                                       SEPARATOR +
                                                       "1. Iniciar Venta"+
                                                       "0. Cerrar Sesion" +
                                                       SEPARATOR;
    public static final String MSG_INSERCCIÓN_CORRECTA = "Los registros se han guardado correctamente";

    /**
     * Este método devuelve el login en el que los datos del vendedor/gestor validado.
     * @return String []
     */
    public static Map<String,String> showLogin() throws SQLException {
        String usuario = null;
        String contraseña = null;
        char tipoUsuario;
        System.out.println("Iniciar Sesión" +  "\n"
                +  "---------------");
        do{
            System.out.println("Usuario: ");
            usuario = tecla.nextLine();
            tipoUsuario=Validaciones.getTipoAdministrador(usuario);
            if(tipoUsuario=='N'){
                System.out.println(MSG_ERROR_LOGIN_USUARIO);
            }
        }while (tipoUsuario=='N');
        do{
            System.out.println("Contraseña: ");
            contraseña = tecla.nextLine();
        }while (Validaciones.validarCredenciales(usuario, contraseña, tipoUsuario));
        System.out.println("Login correcto. Bienvenido " + usuario);
        return mapDatosLogin(usuario, contraseña, tipoUsuario);
    }

    public static boolean preguntarNuevaLineaFactura(){
        return preguntarSiONo(MSG_PREG_LINEA_FACTURA);
    }

    public static boolean preguntarGuardarFactura(){
        return preguntarSiONo(MSG_PREG_GUARDAR_FACTURA);
    }


    private static boolean preguntarSiONo(String mensaje) {
        String respuesta ="";
        boolean si =false;
        boolean correcto = false;
        do{
            System.out.println(mensaje);
            System.out.println(MSG_PREG_SI_NO);
            respuesta = tecla.nextLine();
            if(respuesta.toUpperCase(Locale.ROOT).equals("S")){
                si=true;
                correcto =true;
            }else if(respuesta.toUpperCase(Locale.ROOT).equals("N")){
                correcto = true;
            }else{
                System.out.println(MSG_ERROR_ENTRADA);
                correcto=false;
            }
        }while (!correcto);
        return si;
    }


    public static int pedirCodigoProducto(){
        int codigoProducto = 0;
        System.out.println(Menu.MSG_COD_PROD);
        try {
            codigoProducto = Integer.parseInt(tecla.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(Menu.MSG_NUMBER_FORMAT_EXEPTION);
        }
        return codigoProducto;
    }

    public static int pedirCantidadProducto(){
        int cantidadProducto = 0;
        System.out.println(Menu.MSG_CANTIDAD_PROD);
        try {
            cantidadProducto = Integer.parseInt(tecla.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(Menu.MSG_NUMBER_FORMAT_EXEPTION);
        }
        return cantidadProducto;
    }

    private static Map mapDatosLogin(String usuario, String contraseña, char tipoUsuario) {
        Map map =  new HashMap<String,String>();
        map.put("usuario", usuario);
        map.put("contraseña", contraseña);
        map.put("tipoUsuario", tipoUsuario);
        return map;
    }

    public static int showMenuInicioVendedor(){
        int opcion=3;
        do{
            System.out.println(MENU_INICIO_VENDEDOR);
            try{
                opcion = Integer.parseInt(tecla.nextLine());
            }catch (NumberFormatException e){
                System.out.println(MSG_NUMBER_FORMAT_EXEPTION);
            }
        }while (Validaciones.validarOpcion(opcion, Constantes.OPCIONES_MENU_VENDEDOR));
        return opcion;
    }


    public static void showMenuInicioGestor() {
    }
}
