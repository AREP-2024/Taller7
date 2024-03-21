package edu.escuelaing.arep.ASE.app.servicio;

import edu.escuelaing.arep.ASE.app.persistencia.LoginDAO;
import org.bson.Document;
import edu.escuelaing.arep.ASE.app.domain.Login;


public class ServicioAutenticacion {
   
    private LoginDAO loginDAO;

    public ServicioAutenticacion(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    public boolean autenticar (Login login){
        Document documento =  this.loginDAO.buscar(login.getUsuario());

        return documento != null && documento.getString("password").equals(login.getContrasena());
    }


    
}
