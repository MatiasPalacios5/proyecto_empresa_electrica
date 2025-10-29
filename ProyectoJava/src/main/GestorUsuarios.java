package main;

import java.sql.*;
import modelo.*;

/**
 * Clase para gestionar usuarios (insertar personas y empresas)
 */
public class GestorUsuarios {
    
    /**
     * Inserta una persona en la base de datos
     * Primero inserta en usuario, luego en persona
     */
    public static void insertarPersona(Persona persona) {
        Connection connection = null;
        
        try {
            // obtener conexion
            connection = ConexionBD.obtenerConexion();
            
            // desactivar autocommit para manejar transaccion
            connection.setAutoCommit(false);
            
            String queryUsuario = "insert into usuario (direccion, telefono) values (?, ?)";
            PreparedStatement stmtUsuario = connection.prepareStatement(queryUsuario, 
                                            Statement.RETURN_GENERATED_KEYS);
            
            stmtUsuario.setString(1, persona.getDireccion());
            stmtUsuario.setString(2, persona.getTelefono());
            stmtUsuario.executeUpdate();
            
            // obtener el id_usuario generado
            ResultSet rs = stmtUsuario.getGeneratedKeys();
            int idUsuario = 0;
            if (rs.next()) {
                idUsuario = rs.getInt(1);
                persona.setIdUsuario(idUsuario);  // asignar el id al objeto
            }
            
            // paso 2: insertar en tabla persona
            String queryPersona = "insert into persona (id_usuario, dni, nombre, apellido) values (?, ?, ?, ?)";
            PreparedStatement stmtPersona = connection.prepareStatement(queryPersona);
            
            stmtPersona.setInt(1, idUsuario);
            stmtPersona.setInt(2, persona.getDni());
            stmtPersona.setString(3, persona.getNombre());
            stmtPersona.setString(4, persona.getApellido());
            stmtPersona.executeUpdate();
            
            // confirmar transaccion
            connection.commit();
            System.out.println("Persona insertada exitosamente con id: " + idUsuario);
            
        } catch(SQLException sqle) {
            try {
                // si hubo error, deshacer cambios
                if (connection != null) {
                    connection.rollback();
                }
                System.err.println("Error insertando persona: " + sqle);
            } catch(SQLException e) {
                System.err.println("Error en rollback: " + e);
            }
        } finally {
            // cerrar conexion
            if (connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    System.err.println("Error cerrando conexion: " + e);
                }
            }
        }
    }
    
    /**
     * Inserta una empresa en la base de datos
     * Primero inserta en usuario, luego en empresa
     */
    public static void insertarEmpresa(Empresa empresa) {
        Connection connection = null;
        
        try {
            // obtener conexion
            connection = ConexionBD.obtenerConexion();
            
            // desactivar autocommit para manejar transaccion
            connection.setAutoCommit(false);
            
            // paso 1: insertar en tabla usuario
            String queryUsuario = "insert into usuario (direccion, telefono) values (?, ?)";
            PreparedStatement stmtUsuario = connection.prepareStatement(queryUsuario, 
                                                Statement.RETURN_GENERATED_KEYS);
            
            stmtUsuario.setString(1, empresa.getDireccion());
            stmtUsuario.setString(2, empresa.getTelefono());
            stmtUsuario.executeUpdate();
            
            // obtener el id_usuario generado
            ResultSet rs = stmtUsuario.getGeneratedKeys();
            int idUsuario = 0;
            if (rs.next()) {
                idUsuario = rs.getInt(1);
                empresa.setIdUsuario(idUsuario);  // asignar el id al objeto
            }
            
            // paso 2: insertar en tabla empresa
            String queryEmpresa = "insert into empresa (id_usuario, cuit, cap_kw) values (?, ?, ?)";
            PreparedStatement stmtEmpresa = connection.prepareStatement(queryEmpresa);
            
            stmtEmpresa.setInt(1, idUsuario);
            stmtEmpresa.setString(2, empresa.getCuit());
            stmtEmpresa.setDouble(3, empresa.getCapKw());
            stmtEmpresa.executeUpdate();
            
            // confirmar transaccion
            connection.commit();
            System.out.println("Empresa insertada exitosamente con id: " + idUsuario);
            
        } catch(SQLException sqle) {
            try {
                // si hubo error, deshacer cambios
                if (connection != null) {
                    connection.rollback();
                }
                System.err.println("Error insertando empresa: " + sqle);
            } catch(SQLException e) {
                System.err.println("Error en rollback: " + e);
            }
        } finally {
            // cerrar conexion
            if (connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    System.err.println("Error cerrando conexion: " + e);
                }
            }
        }
    }
}