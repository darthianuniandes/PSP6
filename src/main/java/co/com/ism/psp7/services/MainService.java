package co.com.ism.psp7.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import co.com.ism.psp7.model.Regression;

/**
 * @author ian 
 * El servicio principal se encarga de leer el archivo de entrada y ejectua un test por cada
 * entrada de datos que este en el documento 
 */
public class MainService {

	private List<String> listaX;
	private List<String> listaY;
	private String numeros;
	private Regression regression;
	private RangosController rangos;

	/**
	 * @author ian El constructor de la clase inicializa los atributos, sin
	 *         obtenerlos de manera externa
	 */
	public MainService() {
		super();
		this.listaX = new LinkedList<String>();
		this.listaY = new LinkedList<String>();
	}

	/**
	 * @author ian Se obtiene la informacion del archivo dado Si no se tiene un
	 *         archivo dado por el usuario se usa el archivo por defecto
	 */
	public void leerArchivo(String[] archivo) {
		if (archivo != null && archivo.length > 0) {
			leerArchivo(archivo[0]);
		} else {
			leerArchivo("./numeros.txt");
		}
	}

	/**
	 * @author ian Metodo que separa la informacion almacenada en el archivo
	 *         dado para analizar Se hace la separacion de la informacion segun
	 *         un formato especifico
	 */
	public void calcular() {
		List<String> tests = Arrays.asList(numeros.split(";"));
		imprimirCabecera();
		int i = 0;
		for (String testIterator : tests) {
			List<String> arrayXk = Arrays.asList(testIterator.split("&"));
			if (arrayXk != null && arrayXk.size() > 1) {
				List<String> arrayNs = Arrays.asList(arrayXk.get(1).split(","));
				listaX = Arrays.asList(arrayNs.get(0).split(" "));
				listaY = Arrays.asList(arrayNs.get(1).split(" "));
				RegressionController regressionController = new RegressionController(
						arrayXk.get(0), listaX, listaY);
				regression = regressionController.calcularRegression();
				rangos = new RangosController(0.35, listaX.size() - 2,
						regression);				
				rangos.calcular();
				rangos.imprimirResultado(++i);
			}
		}
	}

	/**
	 * @author ian Metodo que imprime los resultados de las diferentes
	 *         regresiones que se vayan calculando segun como se ingresen estas
	 *         en el archivo dado Imprime la cabecera una vez y procede a hacer
	 *         la impresion de cada uno de los resultados de cada test
	 * @param testNumber es el contador de los tests dados en el documento de entrada
	 */
	public void imprimirCabecera() {
		StringBuilder reporte = new StringBuilder();
		reporte.append("________________________________________________\n");
		reporte.append("| Test     | Parameter    | Expected Values    |\n");
		reporte.append("________________________________________________");
		System.out.println(reporte.toString());
	}

	/**
	 * @author ian Metodo que hace la lectura del archivo dado por el usuario
	 * @param archivo es el directorio del archivo que va a ser analizado
	 */
	private void leerArchivo(String archivo) {
		BufferedReader in = null;
		FileInputStream fis = null;
		StringBuilder lectura;

		try {
			fis = new FileInputStream(archivo);
			in = new BufferedReader(new InputStreamReader(fis));

			lectura = new StringBuilder();
			String sCurrentLine;

			while ((sCurrentLine = in.readLine()) != null) {
				lectura.append(sCurrentLine);
			}
			numeros = lectura.toString();
		} catch (IOException e) {
			System.out.println("El directorio ingresado es invalido");
			System.exit(0);
		} finally {
			try {
				if (in != null)
					in.close();
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				System.out
						.println("Hubo un error al intentar cerrar los recursos abiertos");
				ex.printStackTrace();
			}
		}
	}

	/**
	 * @author ian Metodo que devuelve la primer lista de datos fisicos
	 */
	public List<String> getListaX() {
		return listaX;
	}

	/**
	 * @author ian Metodo que devuelve la segunda lista de datos fisicos
	 */
	public List<String> getListaY() {
		return listaY;
	}

}
