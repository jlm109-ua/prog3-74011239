package model.fighters;

import model.Fighter;
import model.Ship;

public class XWing extends Fighter {
	public XWing(Ship mother) {
		super(mother);
		this.addVelocity(10);
		this.addAttack(20);	
	}
	
	private XWing(XWing f) {
		super(f);
	}
	
	public Fighter copy() {
		
	}
	
	@Override
	public char getSymbol() {
		return 'X';
	}
}
