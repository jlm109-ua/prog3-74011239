/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.fighters;

import model.Fighter;
import model.Ship;

public class TIEBomber extends Fighter {
	/**
	 * Constructor de TIEBomber.
	 * @param mother Ship a la que pertenece este tipo de Fighter.
	 */
	public TIEBomber(Ship mother) {
		super(mother);
		this.addVelocity(-30);
		this.addAttack(-50);
		this.addShield(35);		
	}
	
	/**
	 * Constructor de copia de TIEBomber.
	 * @param f Fighter a copiar.
	 */
	private TIEBomber(TIEBomber f) {
		super(f);
	}
	
	/**
	 * Realiza la copia de un TIEBomber.
	 */
	@Override
	public Fighter copy() {
		Fighter f = new TIEBomber(this);
		return(f);
	}
	
	@Override
	public char getSymbol() {
		return 'b';
	}
	
	@Override
	public int getDamage(int n,Fighter enemy) {
		int dmg = super.getDamage(n, enemy);
		if((enemy.getType().equals("XWing")) || (enemy.getType().equals("YWing"))) {
			dmg = dmg / 2;
		}else if(enemy.getType().equals("AWing")) {
			dmg = dmg / 3;
		}
		return dmg;
	}
}
