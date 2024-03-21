package edu.escuelaing.arep.ASE.app;

import edu.escuelaing.arep.ASE.app.controlador.Controlador;
import edu.escuelaing.arep.ASE.app.controlador.SparkControlador;
import edu.escuelaing.arep.ASE.app.servicio.ServicioAutenticacion;
import edu.escuelaing.arep.ASE.app.servicio.ServicioGuardar;
import edu.escuelaing.arep.ASE.app.persistencia.LoginDAO;

import static spark.Spark.staticFileLocation;
import static spark.Spark.secure;
import static spark.Spark.port;


public class App {
    public static void main( String[] args ){
        configuracion();
        controladores(); 
    }

    public static void controladores(){
        LoginDAO loginDAO = LoginDAO.getLoginDAO("taller7", "usuarios");

        ServicioAutenticacion servicioAutenticacion = new ServicioAutenticacion(loginDAO);
        ServicioGuardar servicioGuardar = new ServicioGuardar(loginDAO);
        Controlador controlador = new SparkControlador(servicioAutenticacion, servicioGuardar);
        controlador.init();       
    }

    public static void configuracion(){
        staticFileLocation("/public");
        secure("certs/ecikeystore.p12","123456","certs/myTrustStore.p12","123456");
        port(80);     
    }
    
}
