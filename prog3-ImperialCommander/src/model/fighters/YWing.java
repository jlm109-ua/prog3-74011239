package model.fighters;

import model.Fighter;
import model.Ship;

public class YWing extends Fighter {
	public YWing(Ship mother) {
		super(mother);
		this.addVelocity(-20);
		this.addAttack(-10);
		this.addShield(30);
	}
	
	private YWing(YWing f) {
		super(f);
	}
	
	public Fighter copy() {
		
	}
	
	@Override
	public char getSymbol() {
		return 'Y';
	}
	
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
