package Vistas;

import Entidades.Usuarios.Cliente;

public class Constantes {
    public static final char ADMIN_NULO = 'N';
    public static final char ADMIN_GESTOR = 'G';
    public static final char ADMIN_VENDEDOR= 'V';
    public static int [] OPCIONES_MENU_INICIO_VENDEDOR = {0,1};
    public static int [] OPCIONES_MENU_INICIO_GESTOR = {0,3};
    public static int [] OPCIONES_MENU_LISTAR_GESTOR = {0,4};
    public static int [] OPCIONES_MENU_EDITAR_GESTOR = {0,4};
    public static int [] OPCIONES_MENU_CRUD_GESTOR = {0,3};
    public static int [] OPCIONES_MENU_AÑADIR_PRODUCTO_GESTOR = {0,2};
    public static int [] OPCIONES_MENU_UPDATE_PRODUCTO_GESTOR = {0,2};
    public static double DESCUENTO_CLIENTES =0.05;
    public static final String NOMBRE_FICHERO_PROPIEDADES = "Conexion/ConfiguracionConexion.properties";
    public static final int ID_CLIENTE_NO_REGISTRADO = 1;
}
