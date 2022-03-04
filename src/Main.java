import Controlador.GestoraLogin;
import Controlador.GestoraMenuGestor;
import Controlador.GestoraMenuVendedor;
import DataAccessLayer.Conexion.DatosConexion;
import Entidades.Usuarios.Administradores.Administrador;
import Entidades.Usuarios.Administradores.GestorBDD;
import Entidades.Usuarios.Administradores.Vendedor;
import Vistas.Constantes;

import java.sql.SQLException;


public class Main {

    public static void main(String[] args) {

        DatosConexion datosConexion = new DatosConexion();
        Administrador administradorLogueado = null;

        if (!datosConexion.bddIsCreated()) {
            crearDB();
            datosConexion.pedirYValidarPropiedadesConexion();
        }
        try {
            administradorLogueado = GestoraLogin.showMenuLogin();
            if (Vendedor.class.isInstance(administradorLogueado)) {
                GestoraMenuVendedor gestoraMenuVendedor = new GestoraMenuVendedor((Vendedor) administradorLogueado);
                gestoraMenuVendedor.showMenuVendedor();
            } else if (GestorBDD.class.isInstance(administradorLogueado)) {
                GestoraMenuGestor gestoraMenuGestor = new GestoraMenuGestor((GestorBDD) administradorLogueado);
                gestoraMenuGestor.showMenuGestor();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    private static void crearDB() {
        String ejecucion = "sqlcmd -i"+Constantes.RUTA_SCRIPT+ Constantes.SCRIPTCREABASEDATOS;
        try {
            Process p = Runtime.getRuntime().exec(ejecucion);
        } catch (Exception e) {
            System.out.println("Se ha producido una excepción");
        }
    }

}
