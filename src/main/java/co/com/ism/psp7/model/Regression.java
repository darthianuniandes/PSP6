package co.com.ism.psp7.model;

import java.util.LinkedList;
import java.util.List;
import static java.lang.Math.*;

/**
 * @author ian
 * Clase que abstrae todo el comportamiento del modelo de regresion 
 */
public class Regression {

	private int nX;
	private int nY;
	private Double B0;
	private Double B1;
	private Double r;
	private Double r2;
	private float xK;
	private Double yK;
	private List<Float> datosX;
	private List<Float> datosY;
	
	/**
	 * @author ian
	 * El constructor de la clase inicializa los atributos, sin obtenerlos de manera externa
	 * @param nombre
	 * @param begin
	 * @param end
	 */
	public Regression() {
		super();
		this.nX = 0;
		this.nY = 0;
		B0 = 0.0;
		B1 = 0.0;
		this.r = 0.0;
		this.r2 = 0.0;
		this.xK = 0;
		this.yK = 0.0;
		this.datosX = new LinkedList<Float>();
		this.datosY = new LinkedList<Float>();
	}

	/**
	 * @author ian
	 * Metodo que asigna los datos logicos de entrada. Las listas de datos y el valor estimado 
	 * @param xK
	 * @param listaX
	 * @param listaY
	 */
	public void almacenarListas(Float xK, LinkedList<Float> listaX, LinkedList<Float> listaY) {
		datosX = listaX;
		datosY = listaY;
		this.xK = xK;
	}

	/**
	 * @author ian
	 * Metodo que calcula los tamaños de las listas de datos y se asegura que ambas listas queden con el mismo tamaño
	 * Esto con fines de poder hacer los calculos correctamente
	 * Si una lista tiene menos elementos que la otra esta es llenada con valores en cero	 
	 */
	public void calcularNs() {
		nX = datosX.size();
		nY = datosY.size();
		if (nX != nY) {
			if (nX > nY) {
				for (int i = 0; i < nX - nY; i++) {
					datosY.add((float) 0);
				}
			} else {
				for (int i = 0; i < nY - nX; i++) {
					datosX.add((float) 0);
				}
			}
		}
		nX = datosX.size();
		nY = datosY.size();
	}

	/**
	 * @author ian
	 * Metodo que calcula los coeficientes Beta de la regresion 
	 */
	public void caltularCoeficientesB() {
		B1 = (sumatoria2(datosX, datosY) - (datosX.size() * promedio(datosX) * promedio(datosY))) 
				/ (sumatoria2(datosX, datosX) - (datosX.size() * pow(promedio(datosX), 2)));
		B0 = promedio(datosY) - (B1 * promedio(datosX));
	}

	/**
	 * @author ian
	 * Metodo que calcula el coeficiente de correlacion 
	 */
	public void caltularCoeficientesR() {
		r = ((datosX.size() * sumatoria2(datosX, datosY)) - (sumatoria(datosX) * sumatoria(datosY))) 
				/ sqrt(((datosX.size() * sumatoria2(datosX, datosX)) - pow(sumatoria(datosX), 2))*((datosY.size() * sumatoria2(datosY, datosY)) - pow(sumatoria(datosY), 2)));
		r2 = pow(r, 2);
	}

	/**
	 * @author ian
	 * Metodo que calcula el mejor valor aproximado utilizando el valor estimado y los coeficientes de regresion  
	 */
	public void calcularYk() {
		yK = B0 + B1 * xK;
	}

	/**
	 * @author ian
	 * Metodo que calcula la sumatoria de los elementos de un arreglo dado 
	 * @param arreglo
	 */
	private Double sumatoria(List<Float> arreglo) {
		Double sum = 0.0;
		for (Float i : arreglo) {
			sum = i + sum;
		}
		return sum;
	}

	/**
	 * @author ian
	 * Metodo que calcula la sumatoria de la multiplicacion de los elementos de dos arreglos dados 
	 * @param arreglo
	 * @param arreglo1
	 */
	private Double sumatoria2(List<Float> arreglo, List<Float> arreglo1) {
		Double sum = 0.0;
		int i = 0;
		for (Float iterator : arreglo) {
			sum = sum + iterator * arreglo1.get(i);
			i++;
		}
		return sum;
	}

	/**
	 * @author ian
	 * Metodo que calcula el valor promedio de los elementos de un arreglo dado 
	 * @param arreglo
	 */
	private Double promedio(List<Float> arreglo) {
		Double promedio = 0.0;
		promedio = sumatoria(arreglo) / arreglo.size();
		return promedio;
	}

	/**
	 * @author ian
	 * Metodo que devuelve el tamaño de la primer lista logica 
	 */
	public int getnX() {
		return nX;
	}
	
	/**
	 * @author ian
	 * Metodo que devuelve el tamaño de la segunda lista logica 
	 */
	public int getnY() {
		return nY;
	}

	/**
	 * @author ian
	 * Metodo que devuelve el tamaño del primer coeficiente 
	 */
	public Double getB0() {
		return B0;
	}

	/**
	 * @author ian
	 * Metodo que devuelve el tamaño del segundo coeficiente 
	 */
	public Double getB1() {
		return B1;
	}

	/**
	 * @author ian
	 * Metodo que devuelve el valor base del coeficiente de correlacion 
	 */
	public Double getR() {
		return r;
	}
	
	/**
	 * @author ian
	 * Metodo que devuelve el valor del coeficiente de correlacion 
	 */
	public Double getR2() {
		return r2;
	}
	
	/**
	 * @author ian
	 * Metodo que devuelve el valor estimado 
	 */
	public float getxK() {
		return xK;
	}

	/**
	 * @author ian
	 * Metodo que devuelve el mejor valor calculado 
	 */
	public Double getyK() {
		return yK;
	}

	/**
	 * @author ian
	 * Metodo que devuelve la primer lista de elementos 
	 */
	public List<Float> getDatosX() {
		return datosX;
	}
	
	/**
	 * @author ian
	 * Metodo que devuelve la segunda lista de elementos 
	 */
	public List<Float> getDatosY() {
		return datosY;
	}

}
