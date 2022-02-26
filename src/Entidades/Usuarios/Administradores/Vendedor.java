package Entidades.Usuarios.Administradores;

import Entidades.Usuarios.Persona;

public class Vendedor extends Administrador {
    public Vendedor(int id, String nombre, String dni, String direccion, int codPostal, String ciudad, int telefono, String correoElectronico, String user, String password) {
        super(id, nombre, dni, direccion, codPostal, ciudad, telefono, correoElectronico, user, password);
    }

    public void iniciarVenta(){

    }
}
