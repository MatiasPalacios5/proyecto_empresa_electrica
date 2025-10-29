package main;

import java.io.*;
import modelo.*;
import java.util.ArrayList;

/**
 * Programa principal con menu interactivo
 */
public class Main {
    
    public static void main(String[] args) {
        
        // para leer desde teclado
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion = 0;
        
        // ciclo del menu hasta que el usuario elija salir
        do {            
            try {
                // mostrar el menu
                System.out.println("\n=== SISTEMA DE GESTION - EMPRESA ELECTRICA ===");
                System.out.println("1. Insertar Usuario (Persona)");
                System.out.println("2. Insertar Usuario (Empresa)");
                System.out.println("3. Eliminar Reclamo");
                System.out.println("4. Listar Reclamos de un Usuario");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opcion: ");
                
                // leer la opcion del usuario
                opcion = Integer.parseInt(br.readLine());
                
                // ejecutar la accion segun la opcion elegida
                if (opcion == 1) {
                    insertarPersona(br);
                } else if (opcion == 2) {
                    insertarEmpresa(br);
                } else if (opcion == 3) {
                    eliminarReclamo(br);
                } else if (opcion == 4) {
                    listarReclamos(br);
                } else if (opcion == 5) {
                    System.out.println("Saliendo del sistema...");
                } else {
                    System.out.println("Opcion invalida, intente de nuevo");
                }
                
            } catch(Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
            
        } while(opcion != 5);
    }
    
    /**
     * Pide los datos y crea una persona nueva
     */
    private static void insertarPersona(BufferedReader br) throws IOException {
        System.out.println("\n--- INSERTAR PERSONA ---");
        
        // pedir cada dato al usuario
        System.out.print("Direccion: ");
        String direccion = br.readLine();
        
        System.out.print("Telefono: ");
        String telefono = br.readLine();
        
        System.out.print("DNI: ");
        int dni = Integer.parseInt(br.readLine());
        
        System.out.print("Nombre: ");
        String nombre = br.readLine();
        
        System.out.print("Apellido: ");
        String apellido = br.readLine();
        
        // crear el objeto persona con los datos ingresados
        Persona persona = new Persona(direccion, telefono, dni, nombre, apellido);
        
        // llamar al gestor para insertarla en la base de datos
        GestorUsuarios.insertarPersona(persona);
    }
    
    /**
     * Pide los datos y crea una empresa nueva
     */
    private static void insertarEmpresa(BufferedReader br) throws IOException {
        System.out.println("\n--- INSERTAR EMPRESA ---");
        
        // pedir cada dato al usuario
        System.out.print("Direccion: ");
        String direccion = br.readLine();
        
        System.out.print("Telefono: ");
        String telefono = br.readLine();
        
        System.out.print("CUIT: ");
        String cuit = br.readLine();
        
        System.out.print("Capacidad instalada (Kw): ");
        double capKw = Double.parseDouble(br.readLine());
        
        // crear el objeto empresa con los datos ingresados
        Empresa empresa = new Empresa(direccion, telefono, cuit, capKw);
        
        // llamar al gestor para insertarla en la base de datos
        GestorUsuarios.insertarEmpresa(empresa);
    }
    
    /**
     * Pide el numero de reclamo y lo elimina
     */
    private static void eliminarReclamo(BufferedReader br) throws IOException {
        System.out.println("\n--- ELIMINAR RECLAMO ---");
        
        // pedir el numero de reclamo
        System.out.print("Numero de reclamo: ");
        int numeroReclamo = Integer.parseInt(br.readLine());
        
        // llamar al gestor para eliminarlo
        // el trigger se va a encargar de guardar la info en auditoria
        GestorReclamos.eliminarReclamo(numeroReclamo);
    }
    
    /**
     * Muestra todos los reclamos de un usuario con sus rellamados
     */
    private static void listarReclamos(BufferedReader br) throws IOException {
        System.out.println("\n--- LISTAR RECLAMOS ---");
        
        // pedir el id del usuario
        System.out.print("ID de usuario: ");
        int idUsuario = Integer.parseInt(br.readLine());
        
        // obtener los reclamos del usuario desde la base de datos
        ArrayList<Reclamo> reclamos = GestorReclamos.listarReclamosConRellamados(idUsuario);
        
        // mostrar los resultados
        if (reclamos.isEmpty()) {
            System.out.println("No se encontraron reclamos para el usuario " + idUsuario);
        } else {
            System.out.println("\nReclamos del usuario " + idUsuario + ":");
            System.out.println("-----------------------------------------------");
            
            // recorrer cada reclamo y mostrarlo
            for (Reclamo r : reclamos) {
                System.out.println("Reclamo #" + r.getNumeroReclamo());
                System.out.println("  Fecha: " + r.getFechaReclamo() + " " + r.getHoraReclamo());
                System.out.println("  Motivo: " + r.getCodigoMotivo());
                System.out.println("  Fecha resolucion: " + r.getFechaResolucion());
                System.out.println("  Cantidad de rellamados: " + r.getCantidadRellamados());
                System.out.println("-----------------------------------------------");
            }
        }
    }
}