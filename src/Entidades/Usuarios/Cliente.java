package Entidades.Usuarios;

import Vistas.Constantes;

public class Cliente extends Persona{
    private double descuentoCliente;

    public Cliente(int id, String nombre, String dni, String direccion, int codPostal, String ciudad, int telefono, String correoElectronico) {
        super(id, nombre, dni, direccion, codPostal, ciudad, telefono, correoElectronico);
        setDescuentoCliente();
    }

    public Cliente(Persona persona) {
        super(persona);
        setDescuentoCliente();
    }

    public Cliente(){

    }

    public double getDescuentoCliente() {
        return descuentoCliente;
    }
    private void setDescuentoCliente() {
        if(this.getId()!=Constantes.ID_CLIENTE_NO_REGISTRADO){
            this.descuentoCliente = Constantes.DESCUENTO_CLIENTES;
        }else{
            this.descuentoCliente = 0;
        }
    }


}
