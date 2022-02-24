package Entidades.Usuarios.Administradores;

// Necesito poder acceder con este usuario y tener todos los permisos en la bdd
public class GestorBDD extends Vendedor{
    public GestorBDD(int idVendedor, String nombre, String dni, String direccion, int codPostal, String ciudad, int telefono, String correoElectronico, String user, String password) {
        super(idVendedor, nombre, dni, direccion, codPostal, ciudad, telefono, correoElectronico, user, password);
    }
}
