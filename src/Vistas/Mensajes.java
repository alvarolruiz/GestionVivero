package Vistas;

import DataAccessLayer.Listados.ListadosUsuarios;
import Entidades.Productos.Producto;
import Entidades.Usuarios.Administradores.Administrador;
import Entidades.Usuarios.Cliente;
import Entidades.Usuarios.Persona;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Mensajes {
    public static Scanner tecla = new Scanner(System.in);
    public static final String SEPARATOR = "_____________________";
    public static final String MSG_ERROR_CONTRASENA = "La contraseña que ha introducido es incorrecta";
    public static final String MSG_ERROR_LOGIN_GESTOR = "El gestor no existe";
    public static final String MSG_ERROR_LOGIN_VENDEDOR = "El Vendedor no existe";
    public static final String MSG_ERROR_LOGIN_USUARIO = "El usuario que ha introducido no existe";
    public static final String MSG_INICIAR_VENTA = "Ha iniciado una venta";
    public static final String MSG_PEDIR_DATOS_CLIENTE = "¿Desea introducir el dni o el teléfono del cliente?." + "\n" +
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
    public static final String MSG_PREG_SI_NO = "Introduzca S o N:";
    public static final String MSG_PREG_GUARDAR_FACTURA = "Desea guardar o descartar la factura?";
    public static final String MSG_FACTURA_GUARDADA = "La factura se ha guardado.";
    public static final String MSG_FACTURA_CANCELADA = "La factura ha sido eliminada, no se guardaron los cambios.";

    public static final String MSG_NUMBER_FORMAT_EXEPTION = "Ha introducido uno o más caracteres, porfavor introduce un número.";
    public static final String MSG_SQL_EXEPTION = "Se ha producido un error al acceder a la base de datos";
    private static final String MSG_ERROR_ENTRADA = "Opción no válida, vuelve a intentarlo";

    public static final String MSG_ACTUALIZACION_CORRECTA = "Los registros se han actualizado correctamente";
    public static final String MSG_ACTUALIZACION_FALLIDA = "Los registros no se han podido actualizar";
    public static final String MSG_PRODUCTO_NO_EXISTE = "El producto que ha introducido no existe";
    public static final String MSG_SUCCESSFUL_CONNECTION = "Conexión establecida con éxito";
    public static final String MSG_FAILURE_CONNECTION = "No ha sido posible establecer la conexión";
public static final String MSG_AVISO_UPDATE = "Debe introducir un id que coincida con el de un registro para que se modifique la información";

    /**
     * Este método devuelve el login en el que los datos del vendedor/gestor validado.
     *
     * @return String []
     */


    public static boolean preguntarNuevaLineaFactura() {
        return preguntarSiONo(MSG_PREG_LINEA_FACTURA);
    }

    public static boolean preguntarGuardarFactura() {
        return preguntarSiONo(MSG_PREG_GUARDAR_FACTURA);
    }

    private static boolean preguntarSiONo(String mensaje) {
        String respuesta = "";
        boolean si = false;
        boolean correcto = false;
        do {
            System.out.println(mensaje);
            System.out.println(MSG_PREG_SI_NO);
            respuesta = tecla.nextLine();
            if (respuesta.toUpperCase(Locale.ROOT).equals("S")) {
                si = true;
                correcto = true;
            } else if (respuesta.toUpperCase(Locale.ROOT).equals("N")) {
                correcto = true;
            } else {
                System.out.println(MSG_ERROR_ENTRADA);
                correcto = false;
            }
        } while (!correcto);
        return si;
    }

    public static int pedirNumero(String mensaje) {
        int numero = 0;
        System.out.println(mensaje);
        try {
            numero = Integer.parseInt(tecla.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(Mensajes.MSG_NUMBER_FORMAT_EXEPTION);
        }
        return numero;
    }

    public static void showMsgFilasAfectadas(int filasAfectadas) {
        if (filasAfectadas > 0) {
            System.out.println(Mensajes.MSG_ACTUALIZACION_CORRECTA);
            System.out.println(filasAfectadas + " filas se han visto afectadas");
        } else {
            System.out.println(Mensajes.MSG_ACTUALIZACION_FALLIDA);
        }
    }

    public static Cliente preguntarDatosCliente() throws SQLException {
        String dniOTelefono;
        Cliente clienteComprador = null;
        System.out.println(Mensajes.MSG_PEDIR_DATOS_CLIENTE);
        String respuesta = tecla.nextLine();
        if (respuesta.equalsIgnoreCase("D")) {
            System.out.println(Mensajes.MSG_PEDIR_DNI_CLIENTE);
            dniOTelefono = tecla.nextLine();
            clienteComprador = ListadosUsuarios.getClienteDni(dniOTelefono);
            if (clienteComprador.getId() == Constantes.ID_CLIENTE_NO_REGISTRADO) {
                System.out.println(Mensajes.MSG_CLIENTE_NO_REGISTRADO);
            }
        } else if (respuesta.equalsIgnoreCase("T")) {
            System.out.println(Mensajes.MSG_PEDIR_TELEFONO_CLIENTE);
            dniOTelefono = tecla.nextLine();
            clienteComprador = ListadosUsuarios.getClienteTelefono(Integer.parseInt(dniOTelefono));
            if (clienteComprador.getId() == Constantes.ID_CLIENTE_NO_REGISTRADO) {
                System.out.println(Mensajes.MSG_CLIENTE_NO_REGISTRADO);
            }
        }
        return clienteComprador;
    }

    public static Producto pedirDatosProducto (){
        int id = 0;
        String desripcion = "";
        double precioUnitario = 0;
        int udDisponibles = 0;
        try{
            System.out.println("Id producto:");
            id = Integer.parseInt(tecla.nextLine());
            System.out.println("Descripción:");
            desripcion = tecla.nextLine();
            System.out.println("Precio Unitario:");
            precioUnitario = Double.parseDouble(tecla.nextLine());
            System.out.println("Unidades Disponibles:");
            udDisponibles = Integer.parseInt(tecla.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return new Producto(id, desripcion,precioUnitario, udDisponibles);
    }

    public static int pedirTipoPlanta(){
        int idTipoPlanta = 0;
        System.out.println("Tipo Planta: ");
        try{
            idTipoPlanta = Integer.parseInt(tecla.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return idTipoPlanta;
    }

    public static Cliente pedirDatosUsuario(){
        int id = 0;
        String nombre ="";
        String dni = "";
        String direccion = "";
        int codPostal = 0;
        String ciudad= "";
        int telefono = 0;
        String correo = "";
        try {
            System.out.println("Id: ");
            id = Integer.parseInt(tecla.nextLine());
            System.out.println("Nombre: ");
            nombre = tecla.nextLine();
            System.out.println("Dni: ");
            dni = tecla.nextLine();
            System.out.println("Dirección: ");
            direccion = tecla.nextLine();
            System.out.println("Codigo Postal: ");
            codPostal = Integer.parseInt(tecla.nextLine());
            System.out.println("Ciudad: ");
            ciudad = tecla.nextLine();
            System.out.println("Telefono: ");
            telefono = Integer.parseInt(tecla.nextLine());
            System.out.println("Correo: ");
            correo = tecla.nextLine();

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return new Cliente(id,nombre,dni,direccion,codPostal,ciudad,telefono,correo);

    }

    public static String pedirUsername(){
        String user = "";
        System.out.println("Usuario: ");
        user = tecla.nextLine();
        return user;
    }

    public static String pedirContraseña(){
        String contraseña = "";
        System.out.println("Contraseña: ");
        contraseña = tecla.nextLine();
        return contraseña;
    }
}
