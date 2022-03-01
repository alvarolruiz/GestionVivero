package Entidades.Usuarios;

public abstract class Persona {
    private int id;
    private String nombre;
    private String dni;
    private String direccion;
    private int codPostal;
    private String ciudad;
    private int telefono;
    private String correoElectronico;


    public Persona(int id, String nombre, String dni, String direccion, int codPostal, String ciudad, int telefono, String correoElectronico) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.codPostal = codPostal;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    public Persona (Persona persona){
        this.id = persona.getId();
        this.nombre = persona.getNombre();
        this.dni = persona.getDni();
        this.direccion = persona.getDireccion();
        this.codPostal = persona.getCodPostal();
        this.ciudad = persona.getCiudad();
        this.telefono = persona.getTelefono();
        this.correoElectronico = persona.getCorreoElectronico();
    }


    public Persona(int telefono) {
        this.nombre = null;
        this.dni = null;
        this.direccion = null;
        this.codPostal = 0;
        this.ciudad = null;
        this.telefono = telefono;
        this.correoElectronico = null;
    }

    public Persona() {
    }

    public int getId() {
        return id;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", direccion='" + direccion + '\'' +
                ", codPostal=" + codPostal +
                ", ciudad='" + ciudad + '\'' +
                ", telefono=" + telefono +
                ", correoElectronico='" + correoElectronico + '\'' +
                '}';
    }
}
