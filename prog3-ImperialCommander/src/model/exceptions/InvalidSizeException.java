/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.exceptions;

@SuppressWarnings("serial")
/**
 * Excepcion para cuando el tamanyo del tablero es incorrecto.
 */
public class InvalidSizeException extends Exception{
	private int size;
	
	/**
	 * Excepcion para cuando el tamanyo del tablero es incorrecto.
	 * @param size int
	 */
	public InvalidSizeException(int size){
		super();
		this.size = size;
	}
	
	/**
	 * Obtiene el mensaje de la excepcion.
	 */
	public String getMessage() {
		return "ERROR: Invalid size " + size;
	}
}
