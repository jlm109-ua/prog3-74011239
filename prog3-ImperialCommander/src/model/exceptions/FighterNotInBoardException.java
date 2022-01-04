/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.exceptions;

import model.Fighter;

@SuppressWarnings("serial")
/**
 * Excepcion para cuando un Fighter no se encuentra en el tablero.
 */
public class FighterNotInBoardException extends Exception{
	private Fighter f;
	
	/**
	 * Excepcion para cuando un Fighter no se encuentra en el tablero.
	 * @param f Fighter
	 */
	public FighterNotInBoardException(Fighter f){
		super();
		this.f = f;
	}
	
	/**
	 * Obtiene el mensaje de la excepcion.
	 * @return String Error por el que ha sido causada la excepcion.
	 */
	public String getMessage() {
		return "ERROR: Cannot find Fighter " + f.toString() + " in Board.";
	}
}
