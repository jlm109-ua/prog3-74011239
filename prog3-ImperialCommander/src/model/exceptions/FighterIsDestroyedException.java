/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.exceptions;

import model.Fighter;

@SuppressWarnings("serial")
public class FighterIsDestroyedException extends Exception{
	private Fighter f;
	
	public FighterIsDestroyedException(Fighter f){
		super();
		this.f = f;
	}
	
	public String getMessage() {
		return "ERROR: Fighter " + f.toString() + " is destroyed.";
	}
}