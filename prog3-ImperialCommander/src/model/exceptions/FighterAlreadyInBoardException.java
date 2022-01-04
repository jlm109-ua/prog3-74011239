/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.exceptions;

import model.Fighter;

/**
 * Excepcion para cuando un Fighter ya se encuentra en el tablero.
 */
@SuppressWarnings("serial")
public class FighterAlreadyInBoardException extends Exception{
	private Fighter f;
	
	/**
	 * Excepcion para cuando un Fighter ya se encuentra en el tablero.
	 * @param f Fighter
	 */
	public FighterAlreadyInBoardException(Fighter f){
		super();
		this.f = f;
	}
	
	/**
	 * Obtiene el mensaje de la excepcion.
	 * @return String Error por el que ha sido causada la excepcion.
	 */
	public String getMessage() {
		return "ERROR: Fighter " + f.toString() + " is already in Board.";
	}
}