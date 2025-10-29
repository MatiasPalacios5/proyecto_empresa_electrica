package main;

import java.sql.*;
import modelo.Reclamo;
import java.util.ArrayList;

/**
 * Clase para gestionar reclamos (eliminar y listar)
 */
public class GestorReclamos {
    
    /**
     * Elimina un reclamo por su numero
     * El trigger se encarga de guardar en auditoria automaticamente
     */
    public static void eliminarReclamo(int numeroReclamo) {
        Connection connection = null;
        
        try {
            // obtener conexion
            connection = ConexionBD.obtenerConexion();
            
            // desactivar autocommit
            connection.setAutoCommit(false);
            
            // preparar query de eliminacion
            String query = "delete from reclamo where numero_reclamo = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, numeroReclamo);
            
            // ejecutar
            int filasAfectadas = statement.executeUpdate();
            
            if (filasAfectadas > 0) {
                connection.commit();
                System.out.println("Reclamo " + numeroReclamo + " eliminado exitosamente");
            } else {
                System.out.println("No se encontro el reclamo " + numeroReclamo);
            }
            
        } catch(SQLException sqle) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
                System.err.println("Error eliminando reclamo: " + sqle);
            } catch(SQLException e) {
                System.err.println("Error en rollback: " + e);
            }
        } finally {
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
     * Lista los reclamos de un usuario con la cantidad de rellamados
     */
    public static ArrayList<Reclamo> listarReclamosConRellamados(int idUsuario) {
        Connection connection = null;
        ArrayList<Reclamo> reclamos = new ArrayList<Reclamo>();
        
        try {
            // obtener conexion
            connection = ConexionBD.obtenerConexion();
            
            // query con left join para contar rellamados
            String query = "select r.numero_reclamo, r.fecha_resolucion, r.id_usuario, " +
                          "r.codigo_mot, r.fecha_reclamo, r.hora_reclamo, " +
                          "count(rel.numero_llamado) as cantidad_rellamados " +
                          "from reclamo r " +
                          "left join rellamado rel on r.numero_reclamo = rel.numero_reclamo " +
                          "where r.id_usuario = ? " +
                          "group by r.numero_reclamo";
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUsuario);
            
            // ejecutar query
            ResultSet rs = statement.executeQuery();
            
            // recorrer resultados
            while (rs.next()) {
                Reclamo reclamo = new Reclamo();
                reclamo.setNumeroReclamo(rs.getInt("numero_reclamo"));
                reclamo.setFechaResolucion(rs.getDate("fecha_resolucion"));
                reclamo.setIdUsuario(rs.getInt("id_usuario"));
                reclamo.setCodigoMotivo(rs.getInt("codigo_mot"));
                reclamo.setFechaReclamo(rs.getDate("fecha_reclamo"));
                reclamo.setHoraReclamo(rs.getTime("hora_reclamo"));
                reclamo.setCantidadRellamados(rs.getInt("cantidad_rellamados"));
                
                reclamos.add(reclamo);
            }
            
            System.out.println("Se encontraron " + reclamos.size() + " reclamos");
            
        } catch(SQLException sqle) {
            System.err.println("Error listando reclamos: " + sqle);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    System.err.println("Error cerrando conexion: " + e);
                }
            }
        }
        
        return reclamos;
    }
}