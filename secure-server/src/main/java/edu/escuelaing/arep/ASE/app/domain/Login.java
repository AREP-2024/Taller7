package edu.escuelaing.arep.ASE.app.domain; 

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Login {
    @JsonProperty("usuario")
    private String usuario;

    @JsonProperty("contrasena")
    private String contrasena;

    @JsonCreator
    public Login(@JsonProperty("usuario") String usuario, 
                   @JsonProperty("contrasena") String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getUsuario(){
        return usuario;
    }

    public String getContrasena(){
        return contrasena;
    }
    
}
