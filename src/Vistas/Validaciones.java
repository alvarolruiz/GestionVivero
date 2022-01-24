package Vistas;

public class Validaciones {
    //Todo -> Para la validacion habrá que hacer una clase que pueda acceder a la bdd y que obtenga los datos de los usuarios. Comprobar si los recibidos coinciden con
    // algun registro
    public static boolean validarCredenciales(String nombre, String contraseña){
        boolean correcto=false;
        return correcto;
    }

    public static boolean validarOpcion(String opcion, String[] opcionesMenu) {
        Boolean correcto = false;
        for (int i = 0; i < opcionesMenu.length; i++) {
            if(opcion.equals(opcionesMenu[i])){

            }
        }
    }
}
