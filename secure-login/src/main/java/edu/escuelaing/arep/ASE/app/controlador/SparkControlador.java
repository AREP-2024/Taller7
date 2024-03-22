package edu.escuelaing.arep.ASE.app.controlador;

import edu.escuelaing.arep.ASE.app.servicios.ServicioAutenticacion;

import static spark.Spark.port;
import static spark.Spark.post;
import java.security.PublicKey;
import java.io.IOException;


public class SparkControlador implements Controlador {
    private ServicioAutenticacion servicioAutenticacion;

    public SparkControlador(ServicioAutenticacion servicioAutenticacion){
        this.servicioAutenticacion = servicioAutenticacion;
    }

    @Override
    public void init() {
        
        post("/login", (req,res)->{
            try{
                String cuerpo = req.body();
                System.out.println("cuerpo :)"+cuerpo);
                return servicioAutenticacion.autenticar(cuerpo); 

            }catch(IOException e){
                return "Error del servidor";
            }      

        });


    }


}
