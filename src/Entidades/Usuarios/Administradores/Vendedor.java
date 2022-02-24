package Entidades.Usuarios.Administradores;

import Entidades.Usuarios.Persona;

public class Vendedor extends Persona {
    private String user;
    private String password;

    protected Vendedor(String nombre, String dni, String direccion, int codPostal, String ciudad, int telefono, String user, String password) {
        super(nombre, dni, direccion, codPostal, ciudad, telefono);
        this.user = user;
        this.password = password;
    }

    protected String getUserName() {
        return user;
    }

}
