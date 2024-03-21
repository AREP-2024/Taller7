package edu.escuelaing.arep.ASE.app.servicio;

import edu.escuelaing.arep.ASE.app.persistencia.LoginDAO;
import edu.escuelaing.arep.ASE.app.domain.Login;

public class ServicioGuardar {
    private LoginDAO loginDAO;

    public ServicioGuardar(LoginDAO loginDAO){
        this.loginDAO = loginDAO;
    }

    public void guardar(Login login){
        loginDAO.guardar(login.getUsuario(), login.getContrasena());
    }


    
}
