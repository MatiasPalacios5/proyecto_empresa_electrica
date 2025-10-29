package modelo;

/**
 * Clase Empresa - Representa la tabla empresa
 * Tabulacion: Empresa(id_usuario, cuit, cap_kw)
 * id_usuario clave foranea a Usuario
 */
public class Empresa extends Usuario {
    
    // atributos propios de empresa
    private String cuit;
    private double capKw;
    
    // constructor vacio
    public Empresa() {
        super();
    }
    
    // constructor sin id (para insertar nuevas empresas)
    public Empresa(String direccion, String telefono, String cuit, double capKw) {
        super(direccion, telefono);
        this.cuit = cuit;
        this.capKw = capKw;
    }
    
    // constructor completo (para recuperar de la BD)
    public Empresa(int idUsuario, String direccion, String telefono, 
                   String cuit, double capKw) {
        super(idUsuario, direccion, telefono);
        this.cuit = cuit;
        this.capKw = capKw;
    }
    
    // getters
    public String getCuit() {
        return cuit;
    }
    
    public double getCapKw() {
        return capKw;
    }
    
    // setters
    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
    
    public void setCapKw(double capKw) {
        this.capKw = capKw;
    }
}