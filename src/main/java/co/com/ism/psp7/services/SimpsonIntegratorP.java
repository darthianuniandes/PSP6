package co.com.ism.psp7.services;

import co.com.ism.psp7.model.SimpsonIntegrationPart;


/**
 * @author ian
 * Clase que orquesta el calculo de la integral 
 * Es la encargada de iterar el calculo hasta obtener un error menor del definido por defecto 
 */
public class SimpsonIntegratorP {

	private double E;
	private Float x;
	private int duff;
	
	/**
	 * @author ian
	 * El constructor recibe los datos principales e inicializa los atributos basicos 
	 * @param x es el valor limite ingresado en el archivo de entrada
	 * @param duff es el valor dof ingresado en el archivo de entrada
	 */
	public SimpsonIntegratorP(Float x, int duff){
		this.x = x;
		this.duff = duff;
		E = 0.000001;
	}
	/**
	 * @author ian
	 * Metodo que se encarga de invocar los calculos de cada parte del proceso
	 * Itera el calculo hasta que obtiene un error menor al definido por defecto 
	 */
	public Double calcularSimpson(){
		double error = 0;
		int num_seg = 0;
		double resultPrev = 0;
		do {
			num_seg += 10;
			SimpsonIntegrationPart iterator = new SimpsonIntegrationPart(x, duff, num_seg);
			iterator.calcularXi();
			iterator.calcularParte1();
			iterator.calcularParte2();
			iterator.calcularParte3();
			iterator.calcularFX();
			iterator.calcularMultiplier();
			iterator.calcularTerms();
			error = iterator.calcularResult() - resultPrev;
			resultPrev = iterator.calcularResult();
		} while (Math.abs(error) > E);
		num_seg = 0;
		return resultPrev;
	}
}
