package edu.escuelaing.arep.ASE.app;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import edu.escuelaing.arep.ASE.app.servicio.ServicioEncriptacion;

public class ServicioEncriptacionTest {    

    @Test
    public void deberiahashString(){
        String contrasena = "admin";
        String contrasenaEncriptada = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";
        ServicioEncriptacion servicioEncriptacion = new ServicioEncriptacion();
        String aux = servicioEncriptacion.hashString(contrasena);
        assertEquals(contrasenaEncriptada, aux);
    }

    @Test
    public void noDeberiahashString(){
        String contrasena = "admin";
        String contrasenaEncriptada = "8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F";
        ServicioEncriptacion servicioEncriptacion = new ServicioEncriptacion();
        String aux = servicioEncriptacion.hashString(contrasena);
        assertNotEquals(contrasenaEncriptada, aux);
    }
    
}
