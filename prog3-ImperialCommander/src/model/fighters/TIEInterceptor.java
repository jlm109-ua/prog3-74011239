/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.fighters;

import model.Fighter;
import model.Ship;
/**
 * Tipo de luchador.
 */
public class TIEInterceptor extends Fighter {
	/**
	 * Constructor de TIEInterceptor.
	 * @param mother Ship a la que pertenece este tipo de Fighter.
	 */
	public TIEInterceptor(Ship mother) {
		super(mother);
		this.addVelocity(45);
		this.addAttack(5);
		this.addShield(-20);		
	}
	
	/**
	 * Constructor de copia de TIEInterceptor.
	 * @param f Fighter a copiar.
	 */
	private TIEInterceptor(TIEInterceptor f) {
		super(f);
	}
	
	/**
	 * Realiza la copia de un TIEInterceptor.
	 */
	@Override
	public Fighter copy() {
		Fighter f = new TIEInterceptor(this);
		
		return f;
	}
	
	@Override
	public char getSymbol() {
		return 'i';
	}
	
	@Override
	public int getDamage(int n,Fighter enemy) {
		int dmg = super.getDamage(n, enemy);
		if(enemy.getType().equals("YWing")) {
			dmg = dmg * 2;
		}else if(enemy.getType().equals("AWing")) {
			dmg = dmg / 2;
		}
		
		return dmg;
	}
}
