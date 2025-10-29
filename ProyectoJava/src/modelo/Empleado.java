package modelo;

/**
 * Clase Empleado - Representa la tabla empleado
 * Tabulacion: Empleado(id_usuario, sueldo)
 * id_usuario clave foranea a Persona
 */
public class Empleado extends Persona {
    
    // atributo propio de empleado
    private double sueldo;
    
    // constructor vacio
    public Empleado() {
        super();
    }
    
    // constructor sin id (para insertar nuevos empleados)
    public Empleado(String direccion, String telefono, int dni, 
                    String nombre, String apellido, double sueldo) {
        super(direccion, telefono, dni, nombre, apellido);
        this.sueldo = sueldo;
    }
    
    // constructor completo (para recuperar de la BD)
    public Empleado(int idUsuario, String direccion, String telefono, 
                    int dni, String nombre, String apellido, double sueldo) {
        super(idUsuario, direccion, telefono, dni, nombre, apellido);
        this.sueldo = sueldo;
    }
    
    // getter
    public double getSueldo() {
        return sueldo;
    }
    
    // setter
    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
}