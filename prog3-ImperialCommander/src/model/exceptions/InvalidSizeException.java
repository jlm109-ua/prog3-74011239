/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.exceptions;

@SuppressWarnings("serial")
public class InvalidSizeException extends Exception{
	private int size;
	
	/**
	 * Excepción para cuando el tamaño del tablero es incorrecto.
	 * @param size int
	 */
	public InvalidSizeException(int size){
		super();
		this.size = size;
	}
	
	public String getMessage() {
		return "ERROR: Invalid size " + size;
	}
}
