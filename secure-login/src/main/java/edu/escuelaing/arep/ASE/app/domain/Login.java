package edu.escuelaing.arep.ASE.app.domain; 

import com.fasterxml.jackson.annotation.JsonProperty;

public class Login {
    @JsonProperty("usuario")
    private String usuario;

    @JsonProperty("contrasena")
    private String contrasena;

    public Login(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

 

    
}
