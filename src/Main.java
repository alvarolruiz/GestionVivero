import Controlador.GestoraMenuVendedor;
import DataAccessLayer.Listados.ListadosUsuarios;
import Entidades.Usuarios.Administradores.Administrador;
import Entidades.Usuarios.Administradores.GestorBDD;
import Entidades.Usuarios.Administradores.Vendedor;
import Vistas.Constantes;
import Vistas.Menu;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) {
        Map<String, String> mapDatosLogin = new HashMap<>();
        String usuario;
        String contraseña;
        String tipoUsuario ="N";
        Administrador administradorLogueado = null;
        try {
            mapDatosLogin = Menu.showLogin();
            usuario = mapDatosLogin.get("usuario");
            contraseña = mapDatosLogin.get("contraseña");
            tipoUsuario = mapDatosLogin.get("tipoUsuario");
            if (tipoUsuario.equals(Constantes.ADMIN_GESTOR)) {
                administradorLogueado = (GestorBDD) ListadosUsuarios.getGestorBDD(usuario, contraseña);
            } else if (tipoUsuario.equals(Constantes.ADMIN_VENDEDOR)) {
                administradorLogueado = (Vendedor) ListadosUsuarios.getVendedor(usuario, contraseña);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(tipoUsuario.equals(Constantes.ADMIN_VENDEDOR)){
            GestoraMenuVendedor gestoraMenuVendedor = new GestoraMenuVendedor((Vendedor) administradorLogueado);
            try {
                gestoraMenuVendedor.showMenu();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if(tipoUsuario.equals(Constantes.ADMIN_GESTOR)){

        }
    }

}
