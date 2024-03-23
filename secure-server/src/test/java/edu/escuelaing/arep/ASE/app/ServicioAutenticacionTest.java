package edu.escuelaing.arep.ASE.app;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import edu.escuelaing.arep.ASE.app.servicio.ServicioAutenticacion;
import edu.escuelaing.arep.ASE.app.servicio.ServicioEncriptacion;
import edu.escuelaing.arep.ASE.app.persistencia.LoginDAO;
import edu.escuelaing.arep.ASE.app.domain.Login;
import org.bson.Document;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioAutenticacionTest {
    private ServicioAutenticacion servicioAutenticacion;
    private ServicioEncriptacion servicioEncriptacion;
    private LoginDAO loginDAO;

    @Before
    public void setUp(){
        loginDAO = mock(LoginDAO.class);
        servicioEncriptacion = mock(ServicioEncriptacion.class);
        servicioAutenticacion = new ServicioAutenticacion(loginDAO, servicioEncriptacion);
    }

    @Test
    public void  deberiaAutenticar(){
        Login login = new Login("admin", "admin");
        when(servicioEncriptacion.hashString(login.getContrasena())).thenReturn("admin");
        when(loginDAO.buscar(login.getUsuario())).thenReturn(new Document("_id", login.getUsuario())
                                                .append("password", login.getContrasena()));
        boolean result = servicioAutenticacion.autenticar(login);
        assertTrue(result);                                     

    }

    @Test
    public void  noDeberiaAutenticar(){
        Login login = new Login("", "admin");
        when(servicioEncriptacion.hashString(login.getContrasena())).thenReturn("admin");
        when(loginDAO.buscar(login.getUsuario())).thenReturn(null);
        boolean result = servicioAutenticacion.autenticar(login);
        assertFalse(result);                                     

    }

    @Test
    public void  noDeberiaAutenticarContrasenaIncorrecta(){
        Login login = new Login("admin", "admin");
        Login auxLogin = new Login("admin", "admin1");

        when(servicioEncriptacion.hashString(login.getContrasena())).thenReturn("admin");
        when(loginDAO.buscar(login.getUsuario())).thenReturn(new Document("_id", login.getUsuario())
                                                .append("password", auxLogin.getContrasena()));
        boolean result = servicioAutenticacion.autenticar(login);
        assertFalse(result);                                    

    }
    
}
