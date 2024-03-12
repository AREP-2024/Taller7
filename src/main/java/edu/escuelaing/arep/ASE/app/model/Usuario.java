package edu.escuelaing.arep.ASE.app.model;

import org.eclipse.jetty.server.Authentication.User;

public class Usuario {
    private String userName;
    private String password;

    public Usuario(String userName, String password){
        this.userName = userName;
        this.password = password;

    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }


    
}
