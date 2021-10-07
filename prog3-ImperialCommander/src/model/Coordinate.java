/**
 * @author Juan Llinares Mauri - 74011239E
 */

package model;

public class Coordinate implements Comparable<Coordinate> {
	/**
	 * La componente x del tablero.
	 */
	private int x;
	
	/**
	 * La componente y del tablero.
	 */
	private int y;
	
	/**
	 * Declaramos el constructor. Este hará uso del "this." para
	 * referirse a sus variables "x" e "y" debido a que le 
	 * estamos pidiéndo que cree un objeto pasándole unas 
	 * variables con el mismo nombre. Con este uso del "this." 
	 * cambiará el valor de sus propias variables "x" e "y" y 
	 * creará el nuevo objeto Coordinate con esos nuevos valores.
	 * @param x
	 * @param y
	 */
	public Coordinate(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	/**
	 * Declaramos un nuevo constructor que creará un objeto
	 * Coordinate a partir de otro objeto Coordinate c dado. Aquí
	 * no usamos el "this." ya que las variables del objeto pasado
	 * se declaran diferente que las del nuevo objeto que 
	 * queremos crear.
	 * @param x;
	 * @param y;
	 */
	public Coordinate(Coordinate c) {
		x=c.x;
		y=c.y;
	}
	 /**
	  * Con "getX()" y "getY()" (los getters) conseguimos saber
	  * el valor de las variables del objeto Coordinate que 
	  * queremos.
	  * @return x
	  */
	public int getX() {
		return x;
	}
	 /**
	  * 
	  * @return y
	  */
	public int getY() {
		return y;
	}
	
	/**
	 * Este método es igual que el anterior pero con la
	 * diferencia de que le pasamos directamente el valor 
	 * a sumar. Debemos hacer uso del "this.", al igual que 
	 * en el primer método, porque las variables del propio
	 * objeto y las pasadas se declaran de la misma forma. 
	 * Con el "this." conseguimos que se distingan según 
	 * de qué objeto son.
	 * @param x
	 * @param y
	 * @return new_c
	 */
	public Coordinate add(int x,int y) {
		Coordinate new_c = new Coordinate(this.x+x,this.y+y);
		
		return new_c;
	} 
	
	/** Creamos un método que devuelva un objeto Coordinate las
	  * componentes del cual vienen dadas por:
	  * - "x + c.x" para la variable x
	  * - "y + c.y" para la variable y
	  * Con esto observamos que las nuevas coordenadas son 
	  * simplemente la suma de las coordenadas "x" e "y" que ya
	  * tenemos más las del objeto Coordinate c que le hemos 
	  * pasado al método.
	  * @param c
	  * @return new_c
	  */
	public Coordinate add(Coordinate c) {
		Coordinate new_c = new Coordinate(x+c.x,y+c.y);
		
		return new_c;
	}
	
	/**
	 * Creamos un método String para imprimir por pantalla
	 * los valores "x" e "y" del objeto Coordinate.
	 * @return [x,y]
	 */
	public String toString() {
		return "[" + x + "," + y + "]";
	} 
	
	@Override
	public int hashCode() { /*revisar*/
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	/**
	 * Como en Java la función para igualar dos objetos es
	 * diferente, hacemos uso de un método del propio objeto
	 * con el que compararemos este con el otro objeto obj
	 * y nos devolverá:
	 * - false si:
	 *  · El objeto obj está vacío (null).
	 *  · El objeto obj es diferente en cuanto a valores
	 *    de sus variables "x" e "y".
	 * - true si:
	 * 	· El objeto obj es igual (no tiene porqué ser idéntico)
	 * 	  al de nuestro método equals.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Coordinate))
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}	
	
	/**
	 * Rellenamos el código de compareTo para comparar dos coordenadas.
	 * @param otra
	 * @return
	 */
	public int compareTo(Coordinate otra) {
		if(x<otra.x)
			return (-1);
		if(x>otra.x)
			return 1;
		if(x=otra.x) {
			if(y<otra.y)
				return (-1);
			if(y>otra.y)
				return 1;
			if(y=otra.y)
				return 0;
		}
	}
	
	public TreeSet<Coordinate> getNeighborhood() { /*completar*/
		
	}
}
