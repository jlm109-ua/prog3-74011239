/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.fighters;

import model.Fighter;
import model.Ship;
/**
 * Tipo de luchador.
 */
public class AWing extends Fighter {
	/**
	 * Constructor de AWing.
	 * @param mother Ship a la que pertenece este tipo de Fighter.
	 */
	public AWing(Ship mother) {
		super(mother);
		this.addVelocity(40);
		this.addAttack(5);
		this.addShield(-50);
	}
	
	/**
	 * Constructor de copia de AWing.
	 * @param f Fighter a copiar.
	 */
	private AWing(AWing f) {
		super(f);
	}
	 
	/**
	 * Realiza la copia de un AWing.
	 */
	@Override
	public Fighter copy() {
		Fighter f = new AWing(this);
		
		return f;
	}
	
	@Override
	public char getSymbol() {
		return 'A';
	}
	 
	@Override
	public int getDamage(int n,Fighter enemy) {
		int dmg = super.getDamage(n, enemy);
		if(enemy.getType().equals("TIEBomber")) {
			dmg = dmg * 2;
		}
		
		return dmg;
	}
}
