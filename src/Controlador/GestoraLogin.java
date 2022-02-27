package Controlador;

import DataAccessLayer.Listados.ListadosUsuarios;
import Entidades.Usuarios.Administradores.Administrador;
import Entidades.Usuarios.Administradores.GestorBDD;
import Entidades.Usuarios.Administradores.Vendedor;
import Entidades.Usuarios.Persona;
import Vistas.Constantes;
import Vistas.Menu;
import Vistas.Validaciones;

import java.sql.SQLException;
import java.util.Map;

public class GestoraLogin {

    public static Administrador showMenuLogin() throws SQLException {
        Map<String, String> mapDatosLogin;
        Administrador administradorLogueado = null;
        mapDatosLogin = Menu.showLogin();
        String usuario = mapDatosLogin.get("usuario");
        String contraseña = mapDatosLogin.get("contraseña");
        String tipoUsuario = mapDatosLogin.get("tipoUsuario");
        if (tipoUsuario.equals(Constantes.ADMIN_GESTOR)) {
            administradorLogueado = (GestorBDD) ListadosUsuarios.getGestorBDD(usuario, contraseña);
        } else if (tipoUsuario.equals(Constantes.ADMIN_VENDEDOR)) {
            administradorLogueado = (Vendedor) ListadosUsuarios.getVendedor(usuario, contraseña);
        }
        return administradorLogueado;
    }

}
