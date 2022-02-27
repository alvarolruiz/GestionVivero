package Vistas;

import DataAccessLayer.Listados.ListadosUsuarios;

import java.sql.SQLException;

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

    public static char getTipoAdministrador(String usuario) throws SQLException,NullPointerException{
        return ListadosUsuarios.tipoAdministrador(usuario);
    }

    public static boolean validarCredenciales(String usuario, String contraseña, char tipoUsuario) throws SQLException {
         return ListadosUsuarios.isValidLogin(usuario,contraseña,tipoUsuario);
    }
}
