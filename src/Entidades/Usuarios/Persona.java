package Entidades.Usuarios;

public abstract class Persona {
    private String nombre;
    private String dni;
    private String direccion;
    private int codPostal;
    private String ciudad;
    private int telefono;


    public Persona(String nombre, String dni, String direccion, int codPostal, String ciudad, int telefono) {
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.codPostal = codPostal;
        this.ciudad = ciudad;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre ='" + nombre + '\'' +
                ", dni ='" + dni + '\'' +
                ", direccion ='" + direccion + '\'' +
                ", codPostal =" + codPostal +
                ", ciudad ='" + ciudad + '\'' +
                ", telefono =" + telefono +
                '}';
    }
}
