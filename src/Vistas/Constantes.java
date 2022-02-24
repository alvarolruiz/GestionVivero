package Vistas;

import Entidades.Usuarios.Cliente;

public class Constantes {
    public static int [] OPCIONES_MENU_VENDEDOR = {0,1};
    public static double DESCUENTO_CLIENTES =0.05;
    public static final String MSG_SUCCESSFUL_CONNECTION = "Conexión establecida con éxito";
    public static final String MSG_FAILURE_CONNECTION = "No ha sido posible establecer la conexión";
    public static final String NOMBRE_FICHERO_PROPIEDADES = "ConexionConfig.properties";
    public static final Cliente CLIENTE_NO_REGISTRADO = new Cliente(000000000);
}
