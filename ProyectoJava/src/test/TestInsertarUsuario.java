package test;

import main.GestorUsuarios;
import modelo.Persona;
import modelo.Empresa;

public class TestInsertarUsuario {
    public static void main(String[] args) {
        
        // probar insertar persona
        Persona persona = new Persona("Rivadavia 123", "3584999888", 
                                      40123456, "Roberto", "Gomez");
        GestorUsuarios.insertarPersona(persona);
        
        // probar insertar empresa
        Empresa empresa = new Empresa("Colon 456", "3584777666", 
                                      "30-98765432-1", 12000.50);
        GestorUsuarios.insertarEmpresa(empresa);
    }
}
