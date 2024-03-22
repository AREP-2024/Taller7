package edu.escuelaing.arep.ASE.app.servicios; 

import edu.escuelaing.arep.ASE.app.domain.Login;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransformacionDatos {

    public Login stringLogin(String informacion) throws IOException{
        System.out.println("info :)"+informacion);
        ObjectMapper mapeador= new ObjectMapper();
        Login lecturaMapeador= mapeador.readValue(informacion, Login.class);
        return lecturaMapeador;
    }
    
}
