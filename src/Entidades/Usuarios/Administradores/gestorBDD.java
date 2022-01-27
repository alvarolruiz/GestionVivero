package Entidades.Usuarios.Administradores;

// Necesito poder acceder con este usuario y tener todos los permisos en la bdd
public class gestorBDD extends Vendedor{

    protected gestorBDD(String nombre, String dni, String direccion, int codPostal, String ciudad, int telefono, String user, String password) {
        super(nombre, dni, direccion, codPostal, ciudad, telefono, user, password);
    }
}
