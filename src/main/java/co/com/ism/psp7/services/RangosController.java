package co.com.ism.psp7.services;

import java.util.List;

import co.com.ism.psp7.model.Regression;
import co.com.ism.psp7.model.TableIntegrate;

/**
 * @author ian 
 * Clase que se encarga de gestionar el calculo de los rangos y la significancia
 */
public class RangosController {
	
	private SimpsonIntegratorX simpsonIntegratorX; 
	private SimpsonIntegratorP simpsonIntegratorP; 
	private TableIntegrate table;
	private Regression regression;
	private double constantP;
	private double significance;
	private double range;
	private double UPI;
	private double LPI;
	private int n;
	
	/**
	 * @author ian 
	 * El constructor inicializa los atributos de la clase
	 */
	public RangosController(double p, int duff, Regression regression){
		table = new TableIntegrate();
		this.table.setDuff(duff);
		this.regression = regression;
		this.constantP = p;
		this.n = regression.getDatosX().size();
	}
	
	/**
	 * @author ian 
	 * El metodo que se encarga de llamar los calculos de significancia y rangos
	 */	
	public void calcular(){
		calcularSignificance();
		calcularRange();
		calcularPIs();
	}
	
	/**
	 * @author ian 
	 * Metodo que imprime los atributos que contienen los resultados de cada test ejecutado
	 */
	public void imprimirResultado(int i) {
		StringBuilder reporte = new StringBuilder();
		reporte.append("| Test "+i+"   |	  r       | "+regression.getR()+" |\n");
		reporte.append("|          |     r^2      | "+regression.getR2()+ " |\n");
		reporte.append("|          | significance | "+significance+" |\n");
		reporte.append("|          |	  B0      | "+regression.getB0()+" |\n");
		reporte.append("|          |	  B1      | "+regression.getB1()+" |\n");
		reporte.append("|          |	  Yk      | "+regression.getyK()+" |\n");
		reporte.append("|          |	Range     | "+range+ " |\n");
		reporte.append("|          |   UPI(70%)-  | "+UPI+ " |\n");
		reporte.append("|          |   LPI(70%)	  | "+LPI+ " |\n");
		reporte.append("________________________________________________");
		System.out.println(reporte.toString());
	}
	
	/**
	 * @author ian
	 * Metodo que hace el calculo de la integral para cada registro ingresado en el archivo de entrada
	 */
	private void calcularSignificance(){
		float x = (float) ((Math.abs(regression.getR()) * Math.sqrt(n - 2)) / 
				Math.sqrt (1.f-regression.getR2() ) );
		simpsonIntegratorP = new SimpsonIntegratorP(x, n - 2);
		table.setP(simpsonIntegratorP.calcularSimpson());
		significance = 1 - 2 * table.getP();
	}
	
	private void calcularRange(){
		simpsonIntegratorX = new SimpsonIntegratorX(constantP, table.getDuff());
		table.setX(simpsonIntegratorX.calcularSimpson());
		range = table.getX() * calcularSigma() * Math.sqrt( 1 + (1.f/n) +
				( Math.pow(regression.getxK() - promedio(regression.getDatosX()), 2) / 
				( sumatoria2(regression.getDatosX()) ) ) );
	}
	
	private void calcularPIs(){
		UPI = regression.getyK() + range;
		LPI = regression.getyK() - range;
	}

	private double calcularSigma(){
		double sigma = 0;
		double sum = 0;
		int index = 0;
		double estimate = 0;
		for(float iterator : regression.getDatosX()){
			estimate =regression.getDatosY().get(index) - regression.getB0() 
					- regression.getB1() * iterator;
			sum = sum + Math.pow(estimate, 2);
			index++;
		}
		Double part = (double) (1.f / (n - 2));
		sigma = Math.sqrt( part * sum);
		return sigma;
	}
	
	/**
	 * @author ian
	 * Metodo que calcula la sumatoria de la multiplicacion de los elementos de dos arreglos dados 
	 * @param arreglo recibe un arreglo y devuelve la sumatoria de sus elementos
	 */
	private Double sumatoria2(List<Float> arreglo) {
		Double sum = 0.0;
		Double promedio = promedio(arreglo);
		for (Float iterator : arreglo) {
			sum = sum + Math.pow(iterator - promedio, 2);
		}
		return sum;
	}

	/**
	 * @author ian
	 * Metodo que calcula el valor promedio de los elementos de un arreglo dado 
	 * @param arreglo recibe un arreglo y devuelve la sumatoria de sus elementos
	 */
	private Double promedio(List<Float> arreglo) {
		Double promedio = 0.0;
		promedio = sumatoria(arreglo) / arreglo.size();
		return promedio;
	}
	
	private Double sumatoria(List<Float> arreglo) {
		Double sum = 0.0;
		for (Float i : arreglo) {
			sum = i + sum;
		}
		return sum;
	}
}
