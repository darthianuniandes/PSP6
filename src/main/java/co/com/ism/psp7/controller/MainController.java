package co.com.ism.psp7.controller;

import co.com.ism.psp7.services.MainService;

/**
 * @author ian 
 * Clase principal que se encarga de capturar la entrada del usuario y da inicio al calculo de los tests
 */
public class MainController {
	
	static private MainService mainService;
    
	public static void main( String[] args ) {
    	mainService = new MainService();
    	mainService.leerArchivo(args);
    	mainService.calcular();    	
    }
}
