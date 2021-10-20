/**
 * @author Juan Llinares Mauri - 74011239E
 */

package model;

import java.util.TreeSet;

public class Coordinate implements Comparable<Coordinate> {
	/**
	 * Declaramos todos los atributos privados.
	 * @author Juan Llinares Mauri - 74011239E
	 */
	private int x;
	private int y;
	
	/**
	 * Constructor de Coordinate.
	 * @param x Coordenada x.
	 * @param y Coordenada y.
	 */
	public Coordinate(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	/**
	 * Constructor de copia.
	 * @param x Coordenada x.
	 * @param y Coordenada y.
	 */
	public Coordinate(Coordinate c) {
		x=c.x;
		y=c.y;
	}
	 /**
	  * Getter de x.
	  * @return x
	  */
	public int getX() {
		return x;
	}
	 /**
	  * Getter de y.
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
	
	/** Suma las coordenadas actual y dada.
	  * @param c Coordenada dada.
	  * @return new_c Coordenada resultado.
	  */
	public Coordinate add(Coordinate c) {
		Coordinate new_c = new Coordinate(x+c.x,y+c.y);
		return new_c;
	}
	
	/**
	 * Imprime las componentes de la coordenada.
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
	 * Compara dos coordenadas.
	 * @param otra
	 * @return 
	 * -1: Si la "x" o la "y" es menor que la "x" o la "y" de la coordenada que queremos comparar.
	 * 0: Si la "x" y la "y" es igual a la "x" y la "y" de la coordenada que queremos comparar.
	 * 1: Si la "x" o la "y" es mayor que la "x" o la "y" de la coordenada que queremos comparar.
	 */
	public int compareTo(Coordinate otra) {
		if(x<otra.x)
			return (-1);
		if(x>otra.x)
			return 1;
		if(x==otra.x) {
			if(y<otra.y)
				return (-1);
			if(y>otra.y)
				return 1;
			if(y==otra.y)
				return 0;
		}
		return 0;
	}
	
	/**
	 * Devuelve un TreeSet de todas las coordenadas adyacentes a la coordenada actual.
	 * @return neighborhood TreeSet de coordenadas vecinas.
	 */
	public TreeSet<Coordinate> getNeighborhood() {
		TreeSet<Coordinate> neighborhood = new TreeSet<Coordinate>();
		
		neighborhood.add(new Coordinate(this.x-1,this.y));
		neighborhood.add(new Coordinate(this.x-1,this.y+1));
		neighborhood.add(new Coordinate(this.x,this.y+1));
		neighborhood.add(new Coordinate(this.x+1,this.y+1));
		neighborhood.add(new Coordinate(this.x+1,this.y));
		neighborhood.add(new Coordinate(this.x+1,this.y-1));
		neighborhood.add(new Coordinate(this.x,this.y-1));
		neighborhood.add(new Coordinate(this.x-1,this.y-1));
		
		return neighborhood;
	}
}
