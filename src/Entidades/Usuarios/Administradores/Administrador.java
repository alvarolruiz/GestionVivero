package Entidades.Usuarios.Administradores;

import Entidades.Usuarios.Persona;

public class Administrador extends Persona {
    private String user;
    private String password;


    public Administrador(int id, String nombre, String dni, String direccion, int codPostal, String ciudad, int telefono, String correoElectronico, String user, String password) {
        super(id, nombre, dni, direccion, codPostal, ciudad, telefono, correoElectronico);
        this.user = user;
        this.password = password;
    }

    public Administrador(Persona persona, String user, String password) {
        super(persona);
        this.user = user;
        this.password = password;
    }

    public Administrador(Administrador administrador){
        super(administrador);
        this.user = administrador.getUser();
        this.password = administrador.getPassword();
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
