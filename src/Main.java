import Controlador.GestoraLogin;
import Controlador.GestoraMenuGestor;
import Controlador.GestoraMenuVendedor;
import Entidades.Usuarios.Administradores.Administrador;
import Entidades.Usuarios.Administradores.GestorBDD;
import Entidades.Usuarios.Administradores.Vendedor;

import java.sql.SQLException;


public class Main {

    public static void main(String[] args) {

        Administrador administradorLogueado = null;
        try{
           administradorLogueado = GestoraLogin.showMenuLogin();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        if(Vendedor.class.isInstance(administradorLogueado)){
            GestoraMenuVendedor gestoraMenuVendedor = new GestoraMenuVendedor((Vendedor) administradorLogueado);
            try {
                gestoraMenuVendedor.showMenuVendedor();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if(GestorBDD.class.isInstance(administradorLogueado)){
            GestoraMenuGestor gestoraMenuGestor = new GestoraMenuGestor((GestorBDD) administradorLogueado);
            try {
                gestoraMenuGestor.showMenuPrincipal();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
