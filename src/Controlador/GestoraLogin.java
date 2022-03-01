package Controlador;

import DataAccessLayer.Listados.ListadosUsuarios;
import Entidades.Usuarios.Administradores.Administrador;
import Entidades.Usuarios.Administradores.GestorBDD;
import Entidades.Usuarios.Administradores.Vendedor;
import Vistas.Constantes;
import Vistas.Mensajes;
import Vistas.MenuLogin;

import java.sql.SQLException;
import java.util.Map;

public class GestoraLogin {

    public static Administrador showMenuLogin() throws SQLException {
        Map<String, String> mapDatosLogin;
        Administrador administradorLogueado = null;
        mapDatosLogin = MenuLogin.showLogin();
        String usuario = mapDatosLogin.get("usuario");
        String contraseña = mapDatosLogin.get("contraseña");
        String tipoUsuario = mapDatosLogin.get("tipoUsuario");
        if (tipoUsuario.equals(String.valueOf(Constantes.ADMIN_GESTOR))) {
            administradorLogueado = (GestorBDD) ListadosUsuarios.getGestorBDD(usuario, contraseña);
        } else if (tipoUsuario.equals(String.valueOf(Constantes.ADMIN_VENDEDOR))) {
            administradorLogueado = (Vendedor) ListadosUsuarios.getVendedor(usuario, contraseña);
        }
        return administradorLogueado;
    }

}
