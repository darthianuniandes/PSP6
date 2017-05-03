package co.com.ism.psp7;

import java.util.LinkedList;
import java.util.List;

import co.com.ism.psp7.model.Regression;
import co.com.ism.psp7.model.SimpsonIntegrationPart;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName ) {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testGammaEntero() {
    	assertEquals(gamma(5),24.0);
		
    }
    
    public void testGammFraccion() {
    	assertEquals(gamma(9/2.0), 11.6317, 4);
    }
    
    public void testResult(){
    	SimpsonIntegrationPart iterator = new SimpsonIntegrationPart((float) 1.1, 9, 10);
    	iterator.calcularXi();
		iterator.calcularParte1();
		iterator.calcularParte2();
		iterator.calcularParte3();
		iterator.calcularFX();
		iterator.calcularMultiplier();
		iterator.calcularTerms();
		assertEquals(iterator.calcularResult(), 0.35006, 5);		
    }
    
    public void testParte1(){
    	SimpsonIntegrationPart iterator = new SimpsonIntegrationPart((float) 1.1, 9, 10);
    	iterator.calcularXi();
		iterator.calcularParte1();		
		assertEquals(iterator.getParte1().get(1), 1.00134, 5);
		
    }
    
    public void testParte2(){
    	SimpsonIntegrationPart iterator = new SimpsonIntegrationPart((float) 1.1, 9, 10);
    	iterator.calcularXi();
		iterator.calcularParte1();
		iterator.calcularParte2();		
		assertEquals(iterator.getParte2().get(1), 0.9933, 4);
		
    }
    
    public void testParte3(){
    	SimpsonIntegrationPart iterator = new SimpsonIntegrationPart((float) 1.1, 9, 10);
    	iterator.calcularXi();
		iterator.calcularParte1();
		iterator.calcularParte2();
		iterator.calcularParte3();		
		assertEquals(iterator.getParte3(), 0.388035, 6);		
    }
    
    public void testTerms(){
    	SimpsonIntegrationPart iterator = new SimpsonIntegrationPart((float) 1.1, 9, 10);
    	iterator.calcularXi();
		iterator.calcularParte1();
		iterator.calcularParte2();
		iterator.calcularParte3();
		iterator.calcularFX();
		iterator.calcularMultiplier();
		iterator.calcularTerms();
		assertEquals(iterator.getTerms().get(1), 0.05653, 5);		
    }
    
    /**
     * Rigourous Test :-)
     */
    public void testTamanoListas() {
    	Regression reg = new Regression();
    	LinkedList<Float> listaX = new LinkedList<Float>();
    	listaX.add(Float.valueOf("4"));
    	listaX.add(Float.valueOf("9"));
    	
    	LinkedList<Float> listaY = new LinkedList<Float>();
    	listaY.add(Float.valueOf("10"));
    	
    	reg.almacenarListas(Float.valueOf("386"), listaX, listaY);
    	reg.calcularNs();
    	
    	assertEquals(2, reg.getnX());
    	assertEquals(2, reg.getnY());
    }
    
    /**
     * Rigourous Test :-)
     */
    public void testSumatoria() {
    	Regression reg = new Regression();
    	LinkedList<Float> listaX = new LinkedList<Float>();
    	listaX.add(Float.valueOf("1"));
    	listaX.add(Float.valueOf("1"));
    	
    	LinkedList<Float> listaY = new LinkedList<Float>();
    	listaY.add(Float.valueOf("2"));
    	listaY.add(Float.valueOf("1"));
    	
    	reg.almacenarListas(Float.valueOf("386"), listaX, listaY);
    	reg.calcularNs();
    	
    	assertEquals(2.0, sumatoria(reg.getDatosX()));
    	assertEquals(3.0, sumatoria(reg.getDatosY()));
    }
    
    /**
     * Rigourous Test :-)
     */
    public void testSumatoria2() {
    	Regression reg = new Regression();
    	LinkedList<Float> listaX = new LinkedList<Float>();
    	listaX.add(Float.valueOf("1"));
    	listaX.add(Float.valueOf("1"));
    	
    	LinkedList<Float> listaY = new LinkedList<Float>();
    	listaY.add(Float.valueOf("2"));
    	listaY.add(Float.valueOf("2"));
    	
    	reg.almacenarListas(Float.valueOf("386"), listaX, listaY);
    	reg.calcularNs();
    	
    	assertEquals(2.0, sumatoria2(reg.getDatosX(),reg.getDatosX()));
    	assertEquals(4.0, sumatoria2(reg.getDatosY(),reg.getDatosX()));
    }
    
    /**
     * Rigourous Test :-)
     */
    public void testPromedio() {
    	Regression reg = new Regression();
    	LinkedList<Float> listaX = new LinkedList<Float>();
    	listaX.add(Float.valueOf("1"));
    	listaX.add(Float.valueOf("1"));
    	
    	LinkedList<Float> listaY = new LinkedList<Float>();
    	listaY.add(Float.valueOf("2"));
    	listaY.add(Float.valueOf("2"));
    	
    	reg.almacenarListas(Float.valueOf("386"), listaX, listaY);
    	reg.calcularNs();
    	
    	assertEquals(1.0, promedio(reg.getDatosX()));
    	assertEquals(2.0, promedio(reg.getDatosY()));
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
}
