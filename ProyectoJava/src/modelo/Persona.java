package modelo;

/**
 * Clase Persona - Representa la tabla persona
 * Tabulacion: Persona(id_usuario, dni, nombre, apellido)
 * id_usuario clave foranea a Usuario
 */
public class Persona extends Usuario {
    
    // atributos propios de persona
    private int dni;
    private String nombre;
    private String apellido;
    
    // constructor vacio
    public Persona() {
        super();
    }
    
    // constructor sin id (para insertar nuevas personas)
    public Persona(String direccion, String telefono, int dni, String nombre, String apellido) {
        super(direccion, telefono);
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    // constructor completo (para recuperar de la BD)
    public Persona(int idUsuario, String direccion, String telefono, 
                   int dni, String nombre, String apellido) {
        super(idUsuario, direccion, telefono);
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    // getters
    public int getDni() {
        return dni;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    // setters
    public void setDni(int dni) {
        this.dni = dni;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}