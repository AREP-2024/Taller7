package edu.escuelaing.arep.ASE.app.servicio;

import edu.escuelaing.arep.ASE.app.persistencia.LoginDAO;
import edu.escuelaing.arep.ASE.app.domain.Login;

public class ServicioGuardar {

    private ServicioEncriptacion servicioEncriptacion;
    private LoginDAO loginDAO;

    public ServicioGuardar(LoginDAO loginDAO, ServicioEncriptacion servicioEncriptacion){
        this.loginDAO = loginDAO;
        this.servicioEncriptacion = servicioEncriptacion;
    }

    public void guardar(Login login){
        String passwordEncriptado = this.servicioEncriptacion.hashString(login.getContrasena());
        loginDAO.guardar(login.getUsuario(), passwordEncriptado);
    }


    
}
