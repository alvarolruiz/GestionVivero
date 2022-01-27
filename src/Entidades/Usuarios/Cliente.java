package Entidades.Usuarios;

public class Cliente extends Persona{
    private double descuentoCliente;

    public Cliente(String nombre, String dni, String direccion, int codPostal, String ciudad, int telefono) {
        super(nombre, dni, direccion, codPostal, ciudad, telefono);
    }

    public Cliente(double descuentoCliente) {
        this.descuentoCliente = descuentoCliente;
    }
}
