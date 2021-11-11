/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.exceptions;

@SuppressWarnings("serial")
public class NoFighterAvailableException extends Exception{
	private String type;
	
	/**
	 * Excepción para cuando no se encuentran Fighter disponibles.
	 * @param type String
	 */
	public NoFighterAvailableException(String type){
		super();
		this.type = type;
	}
	
	public String getMessage() {
		return "ERROR: No " + type + "Fighter available.";
	}
}