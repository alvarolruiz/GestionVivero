package Vistas;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuLogin {

    public static Scanner tecla = new Scanner(System.in);

    public static Map<String, String> showLogin() throws SQLException {
        String usuario = null;
        String contraseña = null;
        char tipoUsuario;
        System.out.println("Iniciar Sesión" + "\n"
                + "---------------");
        do {
            System.out.println("Usuario: ");
            usuario = tecla.nextLine();
            tipoUsuario = Validaciones.getTipoAdministrador(usuario);
            if (tipoUsuario == 'N') {
                System.out.println(Mensajes.MSG_ERROR_LOGIN_USUARIO);
            }
        } while (tipoUsuario == 'N');
        do {
            System.out.println("Contraseña: ");
            contraseña = tecla.nextLine();
        } while (!Validaciones.validarCredenciales(usuario, contraseña, tipoUsuario));
        System.out.println("Login correcto. Bienvenido " + usuario);
        return mapDatosLogin(usuario, contraseña, tipoUsuario);
    }

    private static Map mapDatosLogin(String usuario, String contraseña, char tipoUsuario) {
        Map map = new HashMap<String, String>();
        map.put("usuario", usuario);
        map.put("contraseña", contraseña);
        map.put("tipoUsuario", String.valueOf(tipoUsuario));
        return map;
    }
}
