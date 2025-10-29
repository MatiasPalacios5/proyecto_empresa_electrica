package modelo;

/**
 * Clase Usuario - Representa la tabla usuario
 * Tabulacion: Usuario(id_usuario, direccion, telefono)
 */
public class Usuario {
    
    // atributos
    private int idUsuario;
    private String direccion;
    private String telefono;
    
    // constructor vacio
    public Usuario() {
    }
    
    // constructor con parametros (sin id porque es auto_increment)
    public Usuario(String direccion, String telefono) {
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    // constructor completo (con id, para cuando recuperamos de la BD)
    public Usuario(int idUsuario, String direccion, String telefono) {
        this.idUsuario = idUsuario;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    // getters
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    // setters
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}