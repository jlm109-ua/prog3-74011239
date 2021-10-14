package model;

import java.util.ArrayList;
import java.util.List;

public class Ship {
	
	/**
	 * Declaramos sus variables privadas.
	 * @author Juan Llinares Mauri - 74011239E
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
				new Fighter(fdv[i+1],this); /*???*/
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
		if(fleet.isEmpty())
			return null;
		
		for(int i = 0; i < fleet.size(); i++) {
			if(fleet.get(i).isDestroyed() == false && t == null)
				return fleet.get(i);
			if(fleet.get(i).isDestroyed() == false && fleet.get(i).getType() == t)
				return fleet.get(i);
		}
		return null;
	}
	
	public void purgeFleet() {
		for(int i = 0;i < fleet.size();i++) {
			if(fleet.get(i).isDestroyed() == true) {
				fleet.remove(i);
			}
		}
	}
	
	public String showFleet() {
		String showFleet = null;
		if(fleet.size() == 0)
			return("");
		else {
			for(int i = 0;i < fleet.size();i++) {
				if(fleet.get(i).isDestroyed() == true)
					showFleet = showFleet + fleet.get(i).toString() + "(X)\n";
				else
					showFleet = showFleet + fleet.get(i).toString() + "\n";
				
			}
			return showFleet;
		}
	}
	
	public String myFleet() {
		if (fleet.isEmpty())
			return ("");
		
		String myFleet = null,name;
		List<String> shipNames = new ArrayList<String>();
		for(int i = 0;i < fleet.size();i++) {
			name = fleet.get(i).getType();
			if(shipNames.contains(name)) {
				myFleet = myFleet + checkSameShips(name) + "/" + name;
				shipNames.add(name);
			}
			if(checkOtherShips(name,shipNames)) {
				myFleet += ":";
			}
		}
		return myFleet;
	}
	
	/**
	 * Devuelve el número de naves iguales.
	 * @param name
	 * @return count
	 */
	private int checkSameShips(String name) {
		int count = 0;
		
		for(int j = 0;j < fleet.size();j++) {
			if(name == fleet.get(j).getType())
				count++;
		}
		return count;
	}
	
	/**
	 * Devuelve True si hay naves diferentes que no hayan sido contadas ya. False si no las hay.
	 * @param name
	 * @param shipNames
	 * @return True o False.
	 */
	private boolean checkOtherShips(String name,List<String> shipNames) {
		for(int l = 0;l < fleet.size();l++) {
			if(shipNames.contains(fleet.get(l).getType()))
				return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Ship [" + name + " " + wins + "/" + losses + "]  " + myFleet();
	}
}
