package test;

import main.GestorReclamos;
import modelo.Reclamo;
import java.util.ArrayList;

public class TestGestorReclamos {
    public static void main(String[] args) {
        
        System.out.println("=== TEST GESTOR RECLAMOS ===\n");
        
        // test 1: listar reclamos de un usuario
        System.out.println("1. Listando reclamos del usuario 1:");
        ArrayList<Reclamo> reclamos = GestorReclamos.listarReclamosConRellamados(1);
        
        for (Reclamo r : reclamos) {
            System.out.println("  Reclamo #" + r.getNumeroReclamo() + 
                             " | Fecha: " + r.getFechaReclamo() +
                             " | Rellamados: " + r.getCantidadRellamados());
        }
        
        System.out.println("\n2. Listando reclamos del usuario 3:");
        reclamos = GestorReclamos.listarReclamosConRellamados(3);
        
        for (Reclamo r : reclamos) {
            System.out.println("  Reclamo #" + r.getNumeroReclamo() + 
                             " | Fecha: " + r.getFechaReclamo() +
                             " | Rellamados: " + r.getCantidadRellamados());
        }
        
        // test 2: eliminar un reclamo
        System.out.println("\n3. Eliminando reclamo 10:");
        GestorReclamos.eliminarReclamo(10);
        
        // verificar que se elimino
        System.out.println("\n4. Verificando eliminacion (listar reclamos del usuario 5):");
        reclamos = GestorReclamos.listarReclamosConRellamados(5);
        
        for (Reclamo r : reclamos) {
            System.out.println("  Reclamo #" + r.getNumeroReclamo() + 
                             " | Fecha: " + r.getFechaReclamo());
        }
    }
}