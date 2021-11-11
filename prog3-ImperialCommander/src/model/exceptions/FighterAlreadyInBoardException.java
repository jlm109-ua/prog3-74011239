/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.exceptions;

import model.Fighter;

@SuppressWarnings("serial")
public class FighterAlreadyInBoardException extends Exception{
	private Fighter f;
	
	/**
	 * Excepción para cuando un Fighter ya se encuentra en el tablero.
	 * @param f Fighter
	 */
	public FighterAlreadyInBoardException(Fighter f){
		super();
		this.f = f;
	}
	
	public String getMessage() {
		return "ERROR: Fighter " + f.toString() + " is already in Board.";
	}
}