/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.exceptions;

import model.Fighter;

@SuppressWarnings("serial")
public class FighterNotInBoardException extends Exception{
	private Fighter f;
	
	/**
	 * Excepción para cuando un Fighter no se encuentra en el tablero.
	 * @param f Fighter
	 */
	public FighterNotInBoardException(Fighter f){
		super();
		this.f = f;
	}
	
	public String getMessage() {
		return "ERROR: Cannot find Fighter " + f.toString() + " in Board.";
	}
}
