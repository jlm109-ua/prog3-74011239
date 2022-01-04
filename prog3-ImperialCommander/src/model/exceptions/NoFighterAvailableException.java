/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.exceptions;

/**
 * Excepcion para cuando no se encuentran Fighter disponibles.
 */
@SuppressWarnings("serial")
public class NoFighterAvailableException extends Exception{
	private String type;
	
	/**
	 * Excepcion para cuando no se encuentran Fighter disponibles.
	 * @param type String
	 */
	public NoFighterAvailableException(String type){
		super();
		this.type = type;
	}
	
	/**
	 * Obtiene el mensaje de la excepcion.
	 * @return String Error por el que ha sido causada la excepcion.
	 */
	public String getMessage() {
		return "ERROR: No " + type + "Fighter available.";
	}
}