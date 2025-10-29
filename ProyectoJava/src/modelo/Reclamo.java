package modelo;

import java.sql.Date;
import java.sql.Time;

/**
 * Clase Reclamo - Representa la tabla reclamo
 * Tabulacion: Reclamo(numero_reclamo, fecha_resolucion, id_usuario, codigo, fecha, hora)
 */
public class Reclamo {
    
    // atributos
    private int numeroReclamo;
    private Date fechaResolucion;
    private int idUsuario;
    private int codigoMotivo;
    private Date fechaReclamo;
    private Time horaReclamo;
    
    // para almacenar datos adicionales al listar
    private int cantidadRellamados;
    
    // constructor vacio
    public Reclamo() {
    }
    
    // constructor sin numero_reclamo (para insertar)
    public Reclamo(Date fechaResolucion, int idUsuario, int codigoMotivo, 
                   Date fechaReclamo, Time horaReclamo) {
        this.fechaResolucion = fechaResolucion;
        this.idUsuario = idUsuario;
        this.codigoMotivo = codigoMotivo;
        this.fechaReclamo = fechaReclamo;
        this.horaReclamo = horaReclamo;
    }
    
    // constructor completo (para recuperar de la BD)
    public Reclamo(int numeroReclamo, Date fechaResolucion, int idUsuario, 
                   int codigoMotivo, Date fechaReclamo, Time horaReclamo) {
        this.numeroReclamo = numeroReclamo;
        this.fechaResolucion = fechaResolucion;
        this.idUsuario = idUsuario;
        this.codigoMotivo = codigoMotivo;
        this.fechaReclamo = fechaReclamo;
        this.horaReclamo = horaReclamo;
    }
    
    // getters
    public int getNumeroReclamo() {
        return numeroReclamo;
    }
    
    public Date getFechaResolucion() {
        return fechaResolucion;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    
    public int getCodigoMotivo() {
        return codigoMotivo;
    }
    
    public Date getFechaReclamo() {
        return fechaReclamo;
    }
    
    public Time getHoraReclamo() {
        return horaReclamo;
    }
    
    public int getCantidadRellamados() {
        return cantidadRellamados;
    }
    
    // setters
    public void setNumeroReclamo(int numeroReclamo) {
        this.numeroReclamo = numeroReclamo;
    }
    
    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public void setCodigoMotivo(int codigoMotivo) {
        this.codigoMotivo = codigoMotivo;
    }
    
    public void setFechaReclamo(Date fechaReclamo) {
        this.fechaReclamo = fechaReclamo;
    }
    
    public void setHoraReclamo(Time horaReclamo) {
        this.horaReclamo = horaReclamo;
    }
    
    public void setCantidadRellamados(int cantidadRellamados) {
        this.cantidadRellamados = cantidadRellamados;
    }
}