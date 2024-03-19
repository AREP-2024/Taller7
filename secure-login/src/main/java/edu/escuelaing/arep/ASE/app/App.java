package edu.escuelaing.arep.ASE.app;

import edu.escuelaing.arep.ASE.app.controlador.Controlador;
import edu.escuelaing.arep.ASE.app.controlador.SparkControlador;
import edu.escuelaing.arep.ASE.app.servicios.ServicioAutenticacion;

import static spark.Spark.staticFileLocation;
import static spark.Spark.secure;
import static spark.Spark.port;

public class App 
{
    public static void main( String[] args ){
        configuracion();
        controladores();
        
    }

    public static void controladores(){
        ServicioAutenticacion servicioAutenticacion = new ServicioAutenticacion("http://localhost:10000/login", new ClienteHttp());
        Controlador controlador = new SparkControlador(servicioAutenticacion);
        controlador.init();
        

    }

    public static void configuracion(){
        staticFileLocation("/public");
        secure("certs/ecikeystore.p12","123456","certs/myTrustStore.p12","123456");
        port(80);     
    }
}
