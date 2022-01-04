/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.exceptions;

import model.Coordinate;

@SuppressWarnings("serial")
/**
 * Excepcion para cuando una Coordinate se encuentra fuera de los limites del tablero.
 */
public class OutOfBoundsException extends Exception{
	private Coordinate c;
	
	/**
	 * Excepcion para cuando una Coordinate se encuentra fuera de los limites del tablero.
	 * @param c Coordinate
	 */
	public OutOfBoundsException(Coordinate c) {
		super();
		this.c = c;
	}
	
	/**
	 * Obtiene el mensaje de la excepcion.
	 */
	public String getMessage() {
		return "ERROR: Coordinate " + c.toString() + " out of bounds.";
	}
}
