package co.com.ism.psp7.services;

import java.util.LinkedList;
import java.util.List;
import java.text.DecimalFormat;

import co.com.ism.psp7.model.Regression;

/**
 * @author ian
 * Clase que gestiona los datos de la regresion
 * Solo esta clase puede manipular los datos a traves de los metodos de la clase que los encapsula
 */
public class RegressionService {
	
	private Regression regression;
	private Float xK;
	private LinkedList<Float> listaX;
	private LinkedList<Float> listaY;
	
	/**
	 * @author ian
	 * El constructor de la clase inicializa los atributos, sin obtenerlos de manera externa
	 */
	public RegressionService() {
		super();
		this.regression = new Regression();
		this.listaX = new LinkedList<Float>();
		this.listaY = new LinkedList<Float>();
	}

	/**
	 * @author ian
	 * Crea las listas logicas en el objeto que encapsula la informacion de la regresion 
	 * @param stringXk es el valor aproximado dado
	 * @param listaStringX es la primer lista de datos fisicos dada 
	 * @param listaStringY es la segunda lista de datos fisicos dada
	 */
	public void crearListas(String stringXk, List<String> listaStringX, List<String> listaStringY) {
		castearLista(listaStringX, listaX);
		castearLista(listaStringY, listaY);
		try {
			xK = Float.valueOf(stringXk);
		} catch (NumberFormatException e) {
			xK = Float.valueOf(0);
		}		
		almacenarListas();
	}
	
	/**
	 * @author ian
	 * Almacena las listas logicas en el objeto que encapsula la informacion de la regresion 
	 */
	public void almacenarListas() {
		regression.almacenarListas(xK, listaX, listaY);
	}
	
	/**
	 * @author ian
	 * Invoca los metodos de calculo de los coeficientes 
	 */
	public void calcularCoeficientes() {
		regression.calcularNs();
		regression.caltularCoeficientesB();
		regression.caltularCoeficientesR();
	}
	
	/**
	 * @author ian
	 * Invoca el metodo de calculo del mejor valor calculado
	 */
	public Regression calcularYk() {
		regression.calcularYk();
		return regression;
	}
	
	/**
	 * @author ian
	 * Imprime los resultados de los calculos de una regresion
	 * @param testNumber es el contador de los tests dados en el documento de entrada
	 */
	public void imprimirData(int testNumber) {
		StringBuilder reporte = new StringBuilder();
		DecimalFormat four = new DecimalFormat("#0.0000");
		reporte.append("_______________________________________________\n");
		reporte.append("|Test "+testNumber+"|"+four.format(regression.getB0()) +"|"+four.format(regression.getB1())+"|"
		+four.format(regression.getR())+"|"+four.format(regression.getR2())+"|"+four.format(regression.getyK())+"|\n");
		reporte.append("_______________________________________________\n");
		System.out.println(reporte.toString());
	}

	/**
	 * @author ian
	 * Caste los datos de las listas ingresadas por el usuario en datos logicos para su posterior tratamiento
	 * @param listaFuente es la lista de datos fisicos
	 * @param listaDestino es la lista de datos logicos obtenidos de la lista de datos fisicos
	 */
	private void castearLista(List<String> listaFuente, LinkedList<Float> listaDestino){
		for(String i : listaFuente){
			try{
				listaDestino.add(Float.valueOf(i));
			} catch (NumberFormatException nfe){
//				listaNumeros.add((float) 0.0);
				//En este caso el valor detectado en el archivo no es valido 
				//por lo tanto no se tiene en cuenta
			}
		}
	}
	
	/**
	 * @author ian
	 * Devuelve la primer lista de numeros 
	 */
	public List<Float> getListaX() {
		return listaX;
	}

	/**
	 * @author ian
	 * Devuelve la segunda lista de numeros 
	 */
	public List<Float> getListaY() {
		return listaY;
	}
}
