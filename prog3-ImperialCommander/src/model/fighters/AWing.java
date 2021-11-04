package model.fighters;

import model.Fighter;
import model.Ship;

public class AWing extends Fighter {
	public AWing(Ship mother) {
		super(mother);
		this.addVelocity(40);
		this.addAttack(5);
		this.addShield(-50);
	}
	
	private AWing(AWing f) {
		
	}
	 
	public Fighter copy() {
		
	}
	
	@Override
	public char getSymbol() {
		return 'A';
	}
	
	public int getDamage(int n,Fighter enemy) {
		int dmg = super.getDamage(n, enemy);
		if(enemy.getType().equals("TIEBomber")) {
			dmg = dmg * 2;
		}
		return dmg;
	}
}
