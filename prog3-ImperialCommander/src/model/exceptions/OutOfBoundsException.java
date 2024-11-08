/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.exceptions;

import model.Coordinate;

/**
 * Excepcion para cuando una Coordinate se encuentra fuera de los limites del tablero.
 */
@SuppressWarnings("serial")
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
	 * @return String Error por el que ha sido causada la excepcion.
	 */
	public String getMessage() {
		return "ERROR: Coordinate " + c.toString() + " out of bounds.";
	}
}
