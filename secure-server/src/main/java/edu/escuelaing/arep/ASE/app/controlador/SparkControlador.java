package edu.escuelaing.arep.ASE.app.controlador;


import static spark.Spark.port;
import static spark.Spark.post;
import java.security.PublicKey;

import edu.escuelaing.arep.ASE.app.domain.Login;
import edu.escuelaing.arep.ASE.app.servicio.ServicioGuardar;
import edu.escuelaing.arep.ASE.app.servicio.ServicioAutenticacion;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SparkControlador implements Controlador {
    private ServicioAutenticacion servicioAutenticacion;
    private ServicioGuardar servicioGuardar;

    public SparkControlador(ServicioAutenticacion servicioAutenticacion, ServicioGuardar servicioGuardar){
        this.servicioAutenticacion = servicioAutenticacion;
        this.servicioGuardar = servicioGuardar;
    }

    @Override
    public void init() {
        
        post("/login", (req,res)->{
             
            try{
                String cuerpo = req.body();
                ObjectMapper mapeador= new ObjectMapper();
                Login lecturaMapeador= mapeador.readValue(cuerpo, Login.class);
                return servicioAutenticacion.autenticar(lecturaMapeador);           
                
            }catch(Exception e){
                return false;
               
            }      

        });

        post("/user", (req, res)->{
            try{
                String cuerpo = req.body();
                ObjectMapper mapeador= new ObjectMapper();
                Login lecturaMapeador= mapeador.readValue(cuerpo, Login.class);           
                servicioGuardar.guardar(lecturaMapeador);
                return "Usuario Creado";
            }catch(Exception e){
                return "Usuario no creado";
            }

        });

        
        
        

    }


}