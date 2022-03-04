package Vistas;

import java.util.Scanner;

public class MenuGestor {
    private static Scanner tecla = new Scanner(System.in);
    public static final String MENU_INICIO =
            "Menú Inicio Gestor" + "\n" +
                    Mensajes.SEPARATOR + "\n" +
                    "1. Listar" + "\n" +
                    "2. Editar" + "\n" +
                    "3. Generar Informes" + "\n" +
                    "0. Cerrar Sesión" + "\n" +
                    Mensajes.SEPARATOR;

    public static final String MENU_LISTAR =
            "Menú Listar" + "\n" +
                    Mensajes.SEPARATOR + "\n" +
                    "1. Listar Productos" + "\n" +
                    "2. Listar Clientes" + "\n" +
                    "3. Listar Vendedores" + "\n" +
                    "4. Listar Gestores" + "\n" +
                    "0. Volver" + "\n" +
                    Mensajes.SEPARATOR;

    public static final String MENU_EDITAR =
            "Menú Editar" + "\n" +
                    Mensajes.SEPARATOR + "\n" +
                    "1. Productos" + "\n" +
                    "2. Clientes" + "\n" +
                    "3. Vendedores" + "\n" +
                    "4. Gestores" + "\n" +
                    "5. Facturas" + "\n" +
                    "0. Volver" + "\n" +
                    Mensajes.SEPARATOR;

    public static final String MENU_INFORMES =
            "Menú Editar" + "\n" +
                    Mensajes.SEPARATOR + "\n" +
                    "1. Generar Informe mensual" + "\n" +
                    "2. Generar Informe anual" + "\n" +
                    "0. Volver" + "\n" +
                    Mensajes.SEPARATOR;

    public static final String MENU_EDITAR_PRODUCTOS =
            "Menú Editar Productos " + "\n" +
                    Mensajes.SEPARATOR + "\n" +
                    "1. Añadir Producto" + "\n" +
                    "2. Actualizar Producto" + "\n" +
                    "3. Eliminar Procucto" + "\n" +
                    "0. Volver" + "\n" +
                    Mensajes.SEPARATOR;

    public static final String MENU_AÑADIR_PRODUCTO =
            "Menú Añadir Producto" + "\n" +
                    Mensajes.SEPARATOR + "\n" +
                    "1. Añadir Producto Jardinería" + "\n" +
                    "2. Añadir Producto Planta" + "\n" +
                    "0. Volver" + "\n" +
                    Mensajes.SEPARATOR;

    public static final String MENU_UPDATE_PRODUCTO =
            "Menú Actualizar Producto" + "\n" +
                    Mensajes.SEPARATOR + "\n" +
                    "1. Actualizar Producto Jardinería" + "\n" +
                    "2. Actualizar Producto Planta" + "\n" +
                    "0. Volver" + "\n" +
                    Mensajes.SEPARATOR;

    public static final String MENU_EDITAR_CLIENTES =
            "Menú Editar Clientes" + "\n" +
                    Mensajes.SEPARATOR + "\n" +
                    "1. Añadir Cliente" + "\n" +
                    "2. Actualizar Cliente" + "\n" +
                    "3. Eliminar Cliente" + "\n" +
                    "0. Volver" + "\n" +
                    Mensajes.SEPARATOR;

    public static final String MENU_EDITAR_VENDEDORES =
            "Menú Editar Vendedores" + "\n" +
                    Mensajes.SEPARATOR + "\n" +
                    "1. Añadir Vendedor" + "\n" +
                    "2. Actualizar Vendedor" + "\n" +
                    "3. Eliminar Vendedor" + "\n" +
                    "0. Volver" + "\n" +
                    Mensajes.SEPARATOR;

    public static final String MENU_EDITAR_GESTORES =
            "Menú Editar Gestores" + "\n" +
                    Mensajes.SEPARATOR + "\n" +
                    "1. Añadir Gestor" + "\n" +
                    "2. Actualizar Gestor" + "\n" +
                    "3. Eliminar Gestor" + "\n" +
                    "0. Volver" + "\n" +
                    Mensajes.SEPARATOR;

    public static final String MENU_EDITAR_FACTURAS =
            "Menú Editar Facturas" + "\n" +
                    Mensajes.SEPARATOR + "\n" +
                    "1. Eliminar Factura" + "\n" +
                    "0. Volver" + "\n" +
                    Mensajes.SEPARATOR;

    public static int showMenuInicio() {
        return showMenu(MENU_INICIO, Constantes.OPCIONES_MENU_INICIO_GESTOR);
    }

    public static int showMenuListar() { return showMenu(MENU_LISTAR, Constantes.OPCIONES_MENU_LISTAR_GESTOR); }

    public static int showMenuEditar() { return showMenu(MENU_EDITAR, Constantes.OPCIONES_MENU_EDITAR_GESTOR); }

    public static int showMenuAñadirProducto() {
        return showMenu(MENU_AÑADIR_PRODUCTO, Constantes.OPCIONES_MENU_AÑADIR_PRODUCTO_GESTOR);
    }

    public static int showMenuUpdateProducto() {
        return showMenu(MENU_UPDATE_PRODUCTO, Constantes.OPCIONES_MENU_UPDATE_PRODUCTO_GESTOR);
    }

    public static int showMenuEditarProductos() {
        return showMenu(MENU_EDITAR_PRODUCTOS, Constantes.OPCIONES_MENU_CRUD_GESTOR);
    }

    public static int showMenuEditarClientes() {
        return showMenu(MENU_EDITAR_CLIENTES, Constantes.OPCIONES_MENU_CRUD_GESTOR);
    }

    public static int showMenuEditarVendedores() {
        return showMenu(MENU_EDITAR_VENDEDORES, Constantes.OPCIONES_MENU_CRUD_GESTOR);
    }

    public static int showMenuEditarGestores() {
        return showMenu(MENU_EDITAR_GESTORES, Constantes.OPCIONES_MENU_CRUD_GESTOR);
    }

    public static int showMenuEditarFacturas() {
        return showMenu(MENU_EDITAR_FACTURAS, Constantes.OPCIONES_MENU_CRUD_FACTURAS);
    }

    public static int showMenuInformes() {
        return showMenu(MENU_INFORMES, Constantes.OPCIONES_MENU_INFORMES);
    }

    private static int showMenu(String menu, int[] opcionesMenu) {
        int opcion = -1;
        do {
            System.out.println(menu);
            try {
                opcion = Integer.parseInt(tecla.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(Mensajes.MSG_NUMBER_FORMAT_EXEPTION);
            }
        } while (!Validaciones.validarOpcion(opcion, opcionesMenu));
        return opcion;
    }


}
