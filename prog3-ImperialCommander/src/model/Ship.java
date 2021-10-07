package model;

import java.util.ArrayList;
import java.util.List;

public class Ship {
	
	/**
	 * Declaramos sus variables privadas.
	 */
	private String name;
	
	private Side side;
	
	private int wins;
	
	private int losses;
	
	private List<Fighter> fleet = new ArrayList<Fighter>();
	
	public Ship(String name,Side side) {
		this.name = name;
		this.side = side;
		this.wins = 0;
		this.losses = 0;
	}

	public String getName() {
		return name;
	}

	public Side getSide() {
		return side;
	}

	public int getWins() {
		return wins;
	}

	public int getLosses() {
		return losses;
	}
	
	public List<Fighter> getFleetTest(){
		return fleet;
	}
	
	public void addFighters(String fd) {
		int i = 0;
		String[] fdv = fd.split("[/:]");
		
		do {
			for(int j = 0; j < Integer.parseInt(fdv[i]); j++) {
				Fighter f = new Fighter(fdv[i+1],this);
			}
			i++;i++;
		} while(i<fdv.length);
	}
	
	public void updateResults(int r) {
			if(r == 1)
				this.wins++;
			if(r == -1)
				this.losses++;
	}
	
	public Fighter getFirstAvailableFighter(String t) {
		if(fleet.size() == 0)
			return null;
		
		for(int i = 0; i < fleet.size(); i++) {
			if(fleet[i].isDestroyed() == false && t == null)
				return fleet[i];
			if(fleet[i].isDestroyed() == false && fleet[i].getName() == t)
				return fleet[i];
		}
		return null;
	}
	
	public void purgeFleet() {
		
	}
	
	public String showFleet() {
		
	}
	
	public String myFleet() {
		
	}

	@Override
	public String toString() {
		return "Ship [name=" + name + ", side=" + side + ", wins=" + wins + ", losses=" + losses + ", fleet=" + fleet
				+ "]";
	}
}
