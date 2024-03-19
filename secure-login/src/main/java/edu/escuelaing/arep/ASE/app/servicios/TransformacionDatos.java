package edu.escuelaing.arep.ASE.app.servicios; 

import edu.escuelaing.arep.ASE.app.domain.Login;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransformacionDatos {

    public Login stringLogin(String informacion){
        ObjectMapper mapeador= new ObjectMapper();
        Login lecturaMapeador=mapeador.readValue(login, Login.class);
        return lecturaMapeador;

    }
    
}
