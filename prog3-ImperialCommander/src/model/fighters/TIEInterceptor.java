package model.fighters;

import model.Fighter;
import model.Ship;

public class TIEInterceptor extends Fighter {
	public TIEInterceptor(Ship mother) {
		super(mother);
		this.addVelocity(45);
		this.addAttack(5);
		this.addShield(-20);		
	}
	
	private TIEInterceptor(TIEInterceptor f) {
		super(f);
	}
	
	public Fighter copy() {
		
		return f;
	}
	
	@Override
	public char getSymbol() {
		return 'i';
	}
	
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
