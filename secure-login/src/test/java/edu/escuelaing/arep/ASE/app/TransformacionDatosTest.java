package edu.escuelaing.arep.ASE.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import java.io.IOException;

import edu.escuelaing.arep.ASE.app.servicios.TransformacionDatos;
import edu.escuelaing.arep.ASE.app.domain.Login;

public class TransformacionDatosTest {

    @Test
    public void deberiaStringLogin() throws IOException{
        TransformacionDatos transformacion = new TransformacionDatos();
        String informacion = "{\"usuario\":\"admin\",\"contrasena\":\"admin\"}";
        Login lecturaMapeador = transformacion.stringLogin(informacion);
        assertEquals("admin", lecturaMapeador.getUsuario());
        assertEquals("admin", lecturaMapeador.getContrasena());
    }
    
}
