package edu.escuelaing.arep.ASE.app;

import edu.escuelaing.arep.ASE.app.integracion.ClienteHttp;

import edu.escuelaing.arep.ASE.app.servicios.ServicioAutenticacion;
import edu.escuelaing.arep.ASE.app.domain.Login;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class ServicioAutenticacionTest {

    @Test
    public void deberiaAutenticar(){

        ClienteHttp cliente = Mockito.mock(ClienteHttp.class);
        when(cliente.hacerLlamado(anyString(), any(Login.class))).thenReturn("true");
        ServicioAutenticacion servicio = new ServicioAutenticacion("http://localhost:10000/login", cliente);
        boolean result = servicio.autenticar(new Login("admin", "admin"));
        assertTrue(result);
    }

    @Test
    public void noDeberiaAutenticarConLoginVacio(){
        
        ClienteHttp cliente = Mockito.mock(ClienteHttp.class);
        when(cliente.hacerLlamado(anyString(), any(Login.class))).thenReturn("false");
        ServicioAutenticacion servicio = new ServicioAutenticacion("http://localhost:10000/login", cliente);
        boolean result = servicio.autenticar(new Login("", ""));
        assertFalse(result);
    }

    @Test
    public void noDeberiaAutenticarConUsuarioVacio(){
        
        ClienteHttp cliente = Mockito.mock(ClienteHttp.class);
        when(cliente.hacerLlamado(anyString(), any(Login.class))).thenReturn("false");
        ServicioAutenticacion servicio = new ServicioAutenticacion("http://localhost:10000/login", cliente);
        boolean result = servicio.autenticar(new Login("", "admin"));
        assertFalse(result);
    }
    
    @Test
    public void noDeberiaAutenticarConContrasenaVacio(){
        
        ClienteHttp cliente = Mockito.mock(ClienteHttp.class);
        when(cliente.hacerLlamado(anyString(), any(Login.class))).thenReturn("false");
        ServicioAutenticacion servicio = new ServicioAutenticacion("http://localhost:10000/login", cliente);
        boolean result = servicio.autenticar(new Login("admin", ""));
        assertFalse(result);
    }

    @Test
    public void noDeberiaAutenticarConLoginNull(){
        
        ClienteHttp cliente = Mockito.mock(ClienteHttp.class);
        when(cliente.hacerLlamado(anyString(), any(Login.class))).thenReturn("false");
        ServicioAutenticacion servicio = new ServicioAutenticacion("http://localhost:10000/login", cliente);
        boolean result = servicio.autenticar(new Login(null, null));
        assertFalse(result);
    }

    @Test
    public void noDeberiaAutenticarConUsuarioNull(){
        
        ClienteHttp cliente = Mockito.mock(ClienteHttp.class);
        when(cliente.hacerLlamado(anyString(), any(Login.class))).thenReturn("false");
        ServicioAutenticacion servicio = new ServicioAutenticacion("http://localhost:10000/login", cliente);
        boolean result = servicio.autenticar(new Login(null, "admin"));
        assertFalse(result);
    }
    
    @Test
    public void noDeberiaAutenticarConContrasenaNull(){
        
        ClienteHttp cliente = Mockito.mock(ClienteHttp.class);
        when(cliente.hacerLlamado(anyString(), any(Login.class))).thenReturn("false");
        ServicioAutenticacion servicio = new ServicioAutenticacion("http://localhost:10000/login", cliente);
        boolean result = servicio.autenticar(new Login("admin", null));
        assertFalse(result);
    }
}
