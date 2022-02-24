package Controlador;

import Entidades.Usuarios.Administradores.GestorBDD;
import Entidades.Usuarios.Persona;
import Vistas.Menu;
import Vistas.Validaciones;

public class GestoraLogin {

    public Persona showMenuLogin(){
        String [] credenciales = new String[2];
        boolean fin = false;
        do{
            credenciales = Menu.showLogin();
        }while(Validaciones.validarCredenciales());
    }

}
