package Entidades.Usuarios;

import Vistas.Constantes;

public class Cliente extends Persona{
    private double descuentoCliente;

    public Cliente(String nombre, String dni, String direccion, int codPostal, String ciudad, int telefono, String correoElectronico) {
        super(nombre, dni, direccion, codPostal, ciudad, telefono, correoElectronico);
        setDescuentoCliente();
    }

    public Cliente(String dni) {
        super(dni);
    }

    public Cliente(int telefono) {
        super(telefono);
    }

    public Cliente(){

    }

    public void setDescuentoCliente() {
        this.descuentoCliente = Constantes.DESCUENTO_CLIENTES;
    }
}
