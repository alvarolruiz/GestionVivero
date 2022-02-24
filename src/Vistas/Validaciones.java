package Vistas;

import DataAccessLayer.gestoraLogIn.GestoraUsuarios;

public class Validaciones {
    //Todo -> Para la validacion habrá que hacer una clase que pueda acceder a la bdd y que obtenga los datos de los usuarios. Comprobar si los recibidos coinciden con
    // algun registro

    public static boolean validarOpcion(int opcion, int[] opcionesMenu) {
        Boolean correcto = false;
        for (int i = 0; i < opcionesMenu.length; i++) {
            if(opcion==opcionesMenu[i]){
            correcto=true;
            }
        }
        return correcto;
    }

    public static char tipoAdministrador(String usuario){
        return GestoraUsuarios.tipoAdministrador(usuario);
    }

    public static boolean validarCredenciales(String usuario, String contraseña, char tipoUsuario) {
        if(tipoUsuario==Constantes.ADMIN_GESTOR){

        }
    }
}
