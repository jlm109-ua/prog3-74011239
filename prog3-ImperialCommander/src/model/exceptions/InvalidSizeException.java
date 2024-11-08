/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.exceptions;

/**
 * Excepcion para cuando el tamanyo del tablero es incorrecto.
 */
@SuppressWarnings("serial")
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
	 * @return String Error por el que ha sido causada la excepcion.
	 */
	public String getMessage() {
		return "ERROR: Invalid size " + size;
	}
}
