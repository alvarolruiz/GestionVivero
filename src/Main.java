import Controlador.GestoraLogin;
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
        Administrador administradorLogueado = null;
        try{
           administradorLogueado = GestoraLogin.showMenuLogin();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(Vendedor.class.isInstance(administradorLogueado)){
            GestoraMenuVendedor gestoraMenuVendedor = new GestoraMenuVendedor((Vendedor) administradorLogueado);
            try {
                gestoraMenuVendedor.showMenu();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if(GestorBDD.class.isInstance(administradorLogueado)){

        }
    }

}
