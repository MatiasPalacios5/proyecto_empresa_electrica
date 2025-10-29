
package main;

import java.sql.*;
/**
 * Clase para manejar la conexión a la base de datos
 */
public class ConexionBD {
    
    /**
     * Obtiene una conexión a la base de datos
     */
    public static Connection obtenerConexion() {
        Connection connection = null;
        
        try {
            // Datos de conexion
            String driver = "com.mysql.cj.jdbc.Driver";                     // Driver de MySQL
            String url = "jdbc:mysql://localhost:3306/empresa_electrica";   // URL de la base de datos
            String username = "root";                                       // Usuario de la base de datos
            String password = "Olivia@2022";                                // Contraseña de la base de datos
            
            // Cargar el driver de mysql
            Class.forName(driver);
            
            // Establecer conexion a la base de datos
            connection = DriverManager.getConnection(url, username, password);
            
        } catch(ClassNotFoundException cnfe) {
            System.err.println("Error cargando el driver: " + cnfe);           // Manejar error de driver no encontrado
        } catch(SQLException sqle) {
            System.err.println("Error conectando a la base de datos: " + sqle); // Manejar error de SQL
        }
        
        return connection;
    }
}