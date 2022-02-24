package Vistas;

import Entidades.Usuarios.Cliente;

public class Constantes {
    public static final char ADMIN_NULO = 'N';
    public static final char ADMIN_GESTOR = 'G';
    public static final char ADMIN_VENDEDOR= 'V';
    public static int [] OPCIONES_MENU_VENDEDOR = {0,1};
    public static double DESCUENTO_CLIENTES =0.05;
    public static final String MSG_SUCCESSFUL_CONNECTION = "Conexión establecida con éxito";
    public static final String MSG_FAILURE_CONNECTION = "No ha sido posible establecer la conexión";
    public static final String NOMBRE_FICHERO_PROPIEDADES = "ConexionConfig.properties";
    public static final int ID_CLIENTE_NO_REGISTRADO = 1;
}
