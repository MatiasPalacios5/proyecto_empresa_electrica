package modelo;

/**
 * Clase Motivo - Representa la tabla motivo
 * Tabulacion: Motivo(codigo, descripcion)
 */
public class Motivo {
    
    // atributos
    private int codigo;
    private String descripcion;
    
    // constructor vacio
    public Motivo() {
    }
    
    // constructor sin codigo (para insertar, aunque no lo usaremos mucho)
    public Motivo(String descripcion) {
        this.descripcion = descripcion;
    }
    
    // constructor completo (para recuperar de la BD)
    public Motivo(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }
    
    // getters
    public int getCodigo() {
        return codigo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    // setters
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}