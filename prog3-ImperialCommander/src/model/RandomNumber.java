package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * clase para generar n�meros aleatorios
 * @author paco
 *
 */
public class RandomNumber {

	/**
	 * generador de n�meros aleatorios
	 */
	private static Random generator = new Random(1L);
	
	/**
	 * lista de n�meros generados (para debug)
	 */
	private static List<Integer> list = new ArrayList<Integer>();
	
	/**
	 * genera un n�mero aleatorio entre 0 y max-1
	 * @param max indica el m�ximo valor (no incluido)
	 * @return n�mero aleatorio entre 0 y max-1
	 */
	public static int newRandomNumber(int max) {
		int r = generator.nextInt(max);
		list.add(r);
		return r;
	}
	
	/**
	 * getter (debug)
	 * @return lista de n�meros generados
	 */
	public static List<Integer> getRandomNumberList() {
		return list;
	}
	
	/**
	 * reinicializa el generador y la lista, para las pruebas unitarias
	 */
	public static void resetRandomCounter() {
        list.clear();
        generator = new Random(1L);
    } 
}