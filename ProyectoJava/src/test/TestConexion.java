package test;

import java.sql.*;

import main.ConexionBD;

public class TestConexion {
    public static void main(String[] args) {
        System.out.println("Probando conexión a la base de datos...");
        
        // intentar conectar
        Connection connection = ConexionBD.obtenerConexion();
        
        // verificar si la conexión se estableció
        if (connection != null) {
            System.out.println("¡Conexión exitosa!");
            
            // cerrar la conexión
            try {
                connection.close();
                System.out.println("Conexión cerrada correctamente");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e);
            }
        } else {
            System.out.println("No se pudo establecer la conexión");
        }
    }
}