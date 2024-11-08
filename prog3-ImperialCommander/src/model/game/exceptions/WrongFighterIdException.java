/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.game.exceptions;

/**
 * Excepcion para cuando un Fighter contiene una Id erronea.
 */
@SuppressWarnings("serial")
public class WrongFighterIdException extends Exception{
	private int id;
	
	/**
	 * Excepcion para cuando un Fighter contiene una Id erronea.
	 * @param id int
	 */
	public WrongFighterIdException(int id) {
		super();
		this.id = id;
	}
	
	/**
	 * Obtiene el mensaje de la excepcion.
	 * @return String Error por el que ha sido causada la excepcion.
	 */
	public String getMessage() {
		return "ERROR: The id (" + id + ") of the Fighter is wrong.";
	}
}