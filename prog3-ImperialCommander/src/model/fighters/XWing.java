/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.fighters;

import model.Fighter;
import model.Ship;

public class XWing extends Fighter {
	/**
	 * Constructor de XWing.
	 * @param mother Ship a la que pertenece este tipo de Fighter.
	 */
	public XWing(Ship mother) {
		super(mother);
		this.addVelocity(10);
		this.addAttack(20);	
	}
	
	/**
	 * Constructor de copia de XWing.
	 * @param f Fighter a copiar.
	 */
	private XWing(XWing f) {
		super(f);
	}
	
	/**
	 * Realiza la copia de un XWing.
	 */
	@Override
	public Fighter copy() {
		Fighter f = new XWing(this);
		
		return f;
	}
	
	@Override
	public char getSymbol() {
		return 'X';
	}

	@Override
	public int compareTo(Integer o) {
		return 0;
	}
}
