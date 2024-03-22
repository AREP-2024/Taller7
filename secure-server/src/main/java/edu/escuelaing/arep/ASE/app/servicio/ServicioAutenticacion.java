package edu.escuelaing.arep.ASE.app.servicio;

import edu.escuelaing.arep.ASE.app.persistencia.LoginDAO;
import org.bson.Document;
import edu.escuelaing.arep.ASE.app.domain.Login;

public class ServicioAutenticacion {

    private ServicioEncriptacion servicioEncriptacion;   
    private LoginDAO loginDAO;

    public ServicioAutenticacion(LoginDAO loginDAO, ServicioEncriptacion servicioEncriptacion) {
        this.loginDAO = loginDAO;
        this.servicioEncriptacion = servicioEncriptacion;
    }

    public boolean autenticar (Login login){
        String passwordEncriptado = this.servicioEncriptacion.hashString(login.getContrasena());
        Document documento =  this.loginDAO.buscar(login.getUsuario());
        return documento != null && documento.getString("password").equals(passwordEncriptado);
    }


    
}
