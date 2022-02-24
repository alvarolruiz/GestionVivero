package Controlador;

import Vistas.Menu;
import Vistas.Validaciones;

public class GestoraLogin {
    public void showMenu(){
        String [] credenciales = new String[2];
        boolean fin = false;
        do{
            credenciales = Menu.showLogin();
        }while(Validaciones.validarCredenciales());

    }

}
