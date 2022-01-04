/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.game.exceptions;

@SuppressWarnings("serial")
/**
 * Excepcion para cuando un Fighter contiene una Id erronea.
 */
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
	 */
	public String getMessage() {
		return "ERROR: The id (" + id + ") of the Fighter is wrong.";
	}
}