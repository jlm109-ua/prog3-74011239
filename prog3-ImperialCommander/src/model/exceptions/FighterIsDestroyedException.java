/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.exceptions;

import model.Fighter;

@SuppressWarnings("serial")
/**
 * Excepcion para cuando un Fighter ya esta destruido.
 */
public class FighterIsDestroyedException extends Exception{
	private Fighter f;
	
	/**
	 * Excepcion para cuando un Fighter ya esta destruido.
	 * @param f Fighter
	 */
	public FighterIsDestroyedException(Fighter f){
		super();
		this.f = f;
	}
	
	/**
	 * Obtiene el mensaje de la excepcion.
	 * @return String Error por el que ha sido causada la excepcion.
	 */
	public String getMessage() {
		return "ERROR: Fighter " + f.toString() + " is destroyed.";
	}
}