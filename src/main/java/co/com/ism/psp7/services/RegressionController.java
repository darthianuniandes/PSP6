package co.com.ism.psp7.services;

import java.util.List;

import co.com.ism.psp7.model.Regression;

/**
 * @author ian
 * Clase que se encarga de leer los datos ingresados por el usuario y orquestar la optencion de los resultados de las regresiones 
 */
public class RegressionController {

	private RegressionService regressionService;
	private Regression regression;
	private List<String> listaX;
	private List<String> listaY;
	private String Xk;

	/**
	 * @author ian
	 * El constructor de la clase inicializa los atributos, sin obtenerlos de manera externa
	 */
	public RegressionController(String Xk, List<String> listaX, List<String> listaY) {
		super();
		this.regressionService = new RegressionService();
		this.Xk = Xk;
		this.listaX = listaX;
		this.listaY = listaY;
	}
	
	/**
	 * @author ian
	 * Metodo que separa la informacion almacenada en el archivo dado para analizar
	 * Se hace la separacion de la informacion segun un formato especifico
	 */
	public Regression calcularRegression() {
		regressionService = new RegressionService();
		regressionService.crearListas(Xk, listaX, listaY);
		regressionService.calcularCoeficientes();
		regression = regressionService.calcularYk();
		return regression;
	}	
}
