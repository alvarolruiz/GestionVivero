package Entidades.Usuarios.Administradores;

import Entidades.Usuarios.Persona;

public class Administrador extends Persona {
    private int id;
    private String user;
    private String password;


    public Administrador(int id, String nombre, String dni, String direccion, int codPostal, String ciudad, int telefono, String correoElectronico, String user, String password) {
        super(nombre, dni, direccion, codPostal, ciudad, telefono, correoElectronico);
        this.id = id;
        this.user = user;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
