/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.fighters;

import model.Fighter;
import model.Ship;

public class YWing extends Fighter {
	/**
	 * Constructor de YWing.
	 * @param mother Ship a la que pertenece este tipo de Fighter.
	 */
	public YWing(Ship mother) {
		super(mother);
		this.addVelocity(-20);
		this.addAttack(-10);
		this.addShield(30);
	}
	
	/**
	 * Constructor de copia de YWing.
	 * @param f Fighter a copiar.
	 */
	private YWing(YWing f) {
		super(f);
	}
	
	/**
	 * Realiza la copia de un YWing.
	 */
	@Override
	public Fighter copy() {
		Fighter f = new YWing(this);
		return f;
	}
	
	@Override
	public char getSymbol() {
		return 'Y';
	}
	
	@Override
	public int getDamage(int n,Fighter enemy) {
		int dmg = super.getDamage(n, enemy);
		if((enemy.getType().equals("TIEInterceptor")) || (enemy.getType().equals("TIEFighter"))) {
			dmg = dmg / 3;
		}else if(enemy.getType().equals("TIEBomber")) {
			dmg = dmg / 2;
		}
		return dmg;
	}
}
