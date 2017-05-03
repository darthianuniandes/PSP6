package co.com.ism.psp7.services;

import co.com.ism.psp7.model.SimpsonIntegrationPart;

/**
 * @author ian Clase que orquesta el calculo de la integral Es la encargada de
 * iterar el calculo hasta obtener un error menor del definido por
 * defecto
 */
public class SimpsonIntegratorX {

	private double E;
	private Double p;
	private int duff;

	/**
	 * @author ian El constructor recibe los datos principales e inicializa los
	 *         atributos basicos
	 * @param x es el valor limite ingresado en el archivo de entrada
	 * @param duff es el valor dof ingresado en el archivo de entrada
	 */
	public SimpsonIntegratorX(Double p, int duff) {
		this.p = p;
		this.duff = duff;
		E = 0.000001;
	}

	/**
	 * @author ian 
	 * Metodo que se encarga de invocar los calculos de cada parte
	 * del proceso Itera el calculo hasta que obtiene un error menor al
	 * definido por defecto
	 */
	public Double calcularSimpson() {
		double error = 0;
		double errorPrev = 0;
		int num_seg = 0;
		double resultPrev = 0;
		double x = (float) 1.0;
		double d = 0.5;
		double valueIterated = 0;
		do {
			num_seg += 10;
			SimpsonIntegrationPart iterator = new SimpsonIntegrationPart(x,
					duff, num_seg);
			iterator.calcularXi();
			iterator.calcularParte1();
			iterator.calcularParte2();
			iterator.calcularParte3();
			iterator.calcularFX();
			iterator.calcularMultiplier();
			iterator.calcularTerms();
			valueIterated = iterator.calcularResult();
			errorPrev = error;
			error = valueIterated - resultPrev;
			resultPrev = valueIterated;
			if (resultPrev < p) {
				if (Math.signum(error) != Math.signum(errorPrev))
					d = d / 2;
				x = (float) (x + d);
			} else {
				if (Math.signum(error) != Math.signum(errorPrev))
					d = d / 2;
				x = (float) (x - d);
			}
		} while (Math.abs(error) > E);
		num_seg = 0;
		return x;
	}
}
