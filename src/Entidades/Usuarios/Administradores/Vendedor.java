package Entidades.Usuarios.Administradores;

import Entidades.Usuarios.Persona;

public class Vendedor extends Persona {
    private int idVendedor;
    private String user;
    private String password;

    public Vendedor(int idVendedor, String nombre, String dni, String direccion, int codPostal, String ciudad, int telefono, String correoElectronico, String user, String password) {
        super(nombre, dni, direccion, codPostal, ciudad, telefono, correoElectronico);
        this.idVendedor = idVendedor;
        this.user = user;
        this.password = password;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public String getUser() {
        return user;
    }

}
