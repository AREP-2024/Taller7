package edu.escuelaing.arep.ASE.app;

import org.junit.Test;
import static org.junit.Assert.fail;
import edu.escuelaing.arep.ASE.app.servicio.ServicioGuardar;
import edu.escuelaing.arep.ASE.app.domain.Login;
import edu.escuelaing.arep.ASE.app.servicio.ServicioEncriptacion;
import edu.escuelaing.arep.ASE.app.persistencia.LoginDAO;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioGuardarTest {

    public static void assertDoesNotThrow(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @Test
    public void deberiaGuardar() {
        Login login = new Login("admin", "admin");
        LoginDAO loginDAO = mock(LoginDAO.class);
        ServicioEncriptacion servicioEncriptacion = mock(ServicioEncriptacion.class);
        ServicioGuardar servicioGuardar = new ServicioGuardar(loginDAO, servicioEncriptacion);
        
        assertDoesNotThrow(()->{
            servicioGuardar.guardar(login);
        });        

    }


    
}
