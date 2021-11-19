/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.exceptions;

@SuppressWarnings("serial")
public class WrongFighterIdException extends Exception{
	private int id;
	
	/**
	 * Excepcion para cuando un Fighter contiene una Id errónea.
	 * @param id int
	 */
	public WrongFighterIdException(int id) {
		super();
		this.id = id;
	}
	
	public String getMessage() {
		return "ERROR: The id (" + id + ") of the Fighter is wrong.";
	}
}