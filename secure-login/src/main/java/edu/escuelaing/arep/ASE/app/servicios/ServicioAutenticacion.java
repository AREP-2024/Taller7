package edu.escuelaing.arep.ASE.app.servicios; 

import java.io.IOException;

import edu.escuelaing.arep.ASE.app.domain.Login;
import edu.escuelaing.arep.ASE.app.integracion.ClienteHttp;

public class ServicioAutenticacion {
    private String url;
    private ClienteHttp cliente;
    private TransformacionDatos transformacion;

    public ServicioAutenticacion(String url, ClienteHttp cliente) {
        this.url = url;
        this.cliente = cliente;
        transformacion = new TransformacionDatos();
    }

    public boolean autenticar (Login login){
        return this.cliente.hacerLlamado(this.url, login).equals("true");
    }

    public boolean autenticar (String login) throws IOException{  
            
        return this.autenticar(transformacion.stringLogin(login));
    }   
        
}
