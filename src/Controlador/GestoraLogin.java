package Controlador;

import Entidades.Usuarios.Administradores.Administrador;
import Entidades.Usuarios.Administradores.GestorBDD;
import Entidades.Usuarios.Persona;
import Vistas.Constantes;
import Vistas.Menu;
import Vistas.Validaciones;

import java.sql.SQLException;

public class GestoraLogin {

    public Administrador showMenuLogin(){
        String [] credenciales = new String[2];
        try {
            credenciales = Menu.showLogin();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(credenciales[2].equals(Constantes.ADMIN_GESTOR)){

        }
    }

}
