package Entidades.Usuarios;

import Vistas.Constantes;

public class Cliente extends Persona{
    private int id;
    private double descuentoCliente;

    public Cliente(int id, String nombre, String dni, String direccion, int codPostal, String ciudad, int telefono, String correoElectronico) {
        super(nombre, dni, direccion, codPostal, ciudad, telefono, correoElectronico);
        this.id = id;
        setDescuentoCliente();
    }


    public Cliente(){

    }

    public int getId() {
        return id;
    }

    public double getDescuentoCliente() {
        return descuentoCliente;
    }
    private void setDescuentoCliente() {
        if(this.id !=Constantes.ID_CLIENTE_NO_REGISTRADO){
            this.descuentoCliente = Constantes.DESCUENTO_CLIENTES;
        }else{
            this.descuentoCliente = 0;
        }
    }


}
