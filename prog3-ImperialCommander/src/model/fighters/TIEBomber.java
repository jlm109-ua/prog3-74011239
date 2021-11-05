package model.fighters;

import model.Fighter;
import model.Ship;

public class TIEBomber extends Fighter {
	public TIEBomber(Ship mother) {
		super(mother);
		this.addVelocity(-30);
		this.addAttack(-50);
		this.addShield(35);		
	}
	
	private TIEBomber(TIEBomber f) {
		super(f);
	}
	
	public Fighter copy() {
		
	}
	
	@Override
	public char getSymbol() {
		return 'b';
	}
	
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
