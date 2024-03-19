package edu.escuelaing.arep.ASE.app.controlador;
import edu.escuelaing.arep.ASE.app.servicio.APPLBRoundRobin;

import static spark.Spark.port;
import static spark.Spark.get;
import java.security.PublicKey;


public class SparkControlador implements Controlador {
    private ServicioAutenticacion servicioAutenticacion;

    public SparkControlador(ServicioAutenticacion servicioAutenticacion){
        this.servicioAutenticacion = servicioAutenticacion;
    }

    @Override
    public void init() {
        
        get("/login", (req,res)->{
            String cuerpo = req.body();
            return servicioAutenticacion.autenticar(cuerpo);        

        });


    }


}
