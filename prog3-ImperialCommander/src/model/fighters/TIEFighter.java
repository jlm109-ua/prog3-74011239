/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.fighters;

import model.Fighter;
import model.Ship;

public class TIEFighter extends Fighter {
	/**
	 * Constructor de TIEFighter.
	 * @param mother Ship a la que pertenece este tipo de Fighter.
	 */
	public TIEFighter(Ship mother) {
		super(mother);
		this.addVelocity(10);
		this.addAttack(5);
		this.addShield(-10);
	}
	
	/**
	 * Constructor de copia de TIEFighter.
	 * @param f Fighter a copiar.
	 */
	private TIEFighter(TIEFighter f) {
		super(f);
	}
	
	/**
	 * Realiza la copia de un TIEFighter.
	 */
	@Override
	public Fighter copy() {
		Fighter f = new TIEFighter(this);
		
		return f;
	}
	
	@Override
	public char getSymbol() {
		return 'f';
	}

	@Override
	public int compareTo(Integer o) {
		return 0;
	}
}
