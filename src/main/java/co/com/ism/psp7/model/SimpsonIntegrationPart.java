package co.com.ism.psp7.model;

import java.util.LinkedList;
import java.util.List;
/**
 * @author ian
 * La clase que se encarga de calcular las partes que componen el calculo de la integracion 
 */
public class SimpsonIntegrationPart {

	private int numSeg;
	private Float w;
	private int duff;
	private double x;
	private List<Float> xi;
	private List<Double> parte1;
	private List<Double> parte2;
	private Double parte3;
	private List<Double> fX;
	private List<Integer> multiplier;
	private List<Double> terms;
	private Double result;
	
	/**
	 * @author ian
	 * El constructor de la clase se encarga de inicializar los atributos basicos para cada iteracion
	 * @param x es el valor limite ingresado por el usuario en el archivo de entrada
	 * @param duff es el valor dof ingresado por el usuario en el archivo de entrada
	 * @param numSeg es el numero de segmentos que se van a calcular por cada iteracion, va incrementando conforme aumentan las iteraciones del calculo
	 */
	public SimpsonIntegrationPart(double x, int duff, int numSeg){
		this.x = x;
		this.duff = duff;
		this.numSeg = numSeg;
		w = (float) (this.x / this.numSeg);
	}
	
	/**
	 * @author ian
	 * Calcula los rangos de X segun el numero de segmentos 
	 */
	public void calcularXi(){
		xi = new LinkedList<Float>();
		for (int i = 0; i <= numSeg; i++) {
			xi.add(w * i);
		}	
	}
	
	/**
	 * @author ian
	 * Calcula la primer parte de la integral para cada segmento de X 
	 */
	public void calcularParte1() {
		Double calc;
		parte1 = new LinkedList<Double>();
		for(Float xIterator : xi){
			calc = 1 + (Math.pow(xIterator, 2) / duff);
			parte1.add(calc);
		}
	}
	
	/**
	 * @author ian
	 * Calcula la segunda parte de la iteracion para cada segmento de x 
	 */
	public void calcularParte2() {
		Double calc;
		parte2 = new LinkedList<Double>();
		for(Double xIterator : parte1){
			calc = (double) ((duff + 1) /2.0);
			calc = Math.pow(xIterator, -calc);  
			parte2.add(calc);
		}
	}
	/**
	 * @author ian
	 * Calcula la constante usando la funcion Gamma 
	 */
	public void calcularParte3() {
		Double calc = gamma( ((duff + 1) / 2.0) ) / ( Math.sqrt(duff * Math.PI) * gamma((duff / 2.0)) );
		parte3 = calc;
	}
	/**
	 * @author ian
	 * Calcula la funcion para cada valor de X utilizando las partes anteriores 
	 */
	public void calcularFX() {
		fX = new LinkedList<Double>();
		for(Double xParte : parte2) {
			fX.add(xParte * parte3);
		}
	}
	/**
	 * @author ian
	 * Crea un arreglo para almacenar los multiplicadores segun el numero de segmentos creados para cada iteracion 
	 */
	public void calcularMultiplier() {
		Boolean flag = true;
		multiplier = new LinkedList<Integer>();
		for(Double iterator : fX){
			if(flag){
				multiplier.add(2);
				flag = false;
			}
			else{
				multiplier.add(4);
				flag = true;
			}
		}
		multiplier.set(0, 1);
		multiplier.set(multiplier.size() - 1, 1);
	}
	/**
	 * @author ian
	 * Calcula los terminos para cada segmento utilizando todas las partes calculadas anteriormente 
	 */
	public void calcularTerms() {
		terms = new LinkedList<Double>();
		int i = 0;
		for(Double iterator : fX){
			terms.add((w/3.0) * multiplier.get(i) * iterator);
			i++;
		}
	}
	/**
	 * @author ian
	 * Suma los terminos anteriores para obtener un resultado para cada iteracion 
	 */
	public Double calcularResult() {
		result = (double) 0;
		for(Double iterator : terms){
			result = result + iterator;
		}
		return result;
	}

	private double gamma(double num){
    	double fact = 0;
		if (num % 1 == 0){
			fact = factorial(num - 1, 1);
			return fact;
		}
		else {
			fact = factorial(num - 1, 1/2.0);
			return fact * Math.sqrt(Math.PI);
		} 
	}
    
    private double factorial(double num, double limit){
    	if (num <= limit){
    		return limit;
 		}
 		else{
 			return num * factorial(num - 1, limit); 			
 		}
    }

	public int getNumSeg() {
		return numSeg;
	}

	public Float getW() {
		return w;
	}

	public int getDuff() {
		return duff;
	}

	public double getX() {
		return x;
	}

	public List<Float> getXi() {
		return xi;
	}

	public List<Double> getParte1() {
		return parte1;
	}

	public List<Double> getParte2() {
		return parte2;
	}

	public Double getParte3() {
		return parte3;
	}

	public List<Double> getfX() {
		return fX;
	}

	public List<Integer> getMultiplier() {
		return multiplier;
	}

	public List<Double> getTerms() {
		return terms;
	}

	public Double getResult() {
		return result;
	}

}
