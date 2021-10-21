package model;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class Ship {
	/**
	 * Declaramos todos los atributos privados.
	 * @author Juan Llinares Mauri - 74011239E
	 */
	private String name;
	private Side side;
	private int wins;
	private int losses;
	private List<Fighter> fleet = new ArrayList<Fighter>();
	
	/**
	 * Constructor de Ship.
	 * @param name Nombre de la nave Ship.
	 * @param side Bando de la nave (IMPERIAL O REBEL).
	 */
	public Ship(String name,Side side) {
		this.name = name;
		this.side = side;
		this.wins = 0;
		this.losses = 0;
	}
	
	/**
	 * Getter de name.
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter de side.
	 * @return side
	 */
	public Side getSide() {
		return side;
	}

	/**
	 * Getter de wins.
	 * @return wins
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * Getter de losses.
	 * @return losses
	 */
	public int getLosses() {
		return losses;
	}
	
	/**
	 * Getter de fleet. Devuelve toda la flota de Fighters de la nave.
	 * @return fleet
	 */
	public List<Fighter> getFleetTest(){
		return fleet;
	}
	
	/**
	 * Añade Fighter a fleet a partir de una cadena dada.
	 * @param fd Cadena dada.
	 */
	public void addFighters(String fd) {
		int i = 0;
		String[] fdv = fd.split("[/:]");
		
		do {
			for(int j = 0; j < Integer.parseInt(fdv[i]); j++) {
				Fighter f = new Fighter(fdv[i+1],this);
				fleet.add(f);
			}
			i++;i++;
		} while(i<fdv.length);
	}
	
	/**
	 * Actualiza las batallas ganadas y las perdidas a partir de una batalla entre Fighters.
	 * @param r Parámetro para determinar quién ganó la batalla.
	 */
	public void updateResults(int r) {
			if(r == 1)
				this.wins++;
			if(r == -1)
				this.losses++;
	}
	
	/**
	 * Devuelve el primer Fighter disponible que no esté destruido a partir de una cadena dada. Si la cadena tiene valor null devolverá el primer Fighter no destruido que encuentre.
	 * @param t Cadena dada.
	 * @return null: Si no hay Fighters no destruidos disponibles. fleet.get(i): Si el Fighter cumple los requisitos de la cadena y no está destruido.
	 */
	public Fighter getFirstAvailableFighter(String t) {
		if(fleet.isEmpty())
			return null;
		
		for(int i = 0; i < fleet.size(); i++) {
			if(fleet.get(i).isDestroyed() == false && t.equals(null))
				return fleet.get(i);
			if(fleet.get(i).isDestroyed() == false && t.equals(fleet.get(i).getType()))
				return fleet.get(i);
		}
		return null;
	}
	
	/**
	 * Limpia la flota de naves destruidas.
	 */
	public void purgeFleet() {
		for(int i = 0;i < fleet.size();i++) {
			if(fleet.get(i).isDestroyed() == true) {
				fleet.remove(i);
			}
		}
	}
	
	/**
	 * Devuelve una cadena con la información de toda la flota.
	 * @return "": Si la flota está vacía. showFleet: Si hay naves en la flota.
	 */
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
	
	/**
	 * Devuelve una cadena con toda la información de la flota de forma ordenada con el número de naves iguales.
	 * @return "": Si la flota está vacía. myFleet: Si hay naves en la flota.
	 */
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
	 * @param name Tipo de nave a comprobar.
	 * @return count Número de naves iguales que hay.
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
	 * Comprueba si hay Fighter diferentes a la pasada por la cadena name.
	 * @param name Cadena para comprobar si hay Fighter diferentes.
	 * @param shipNames Lista de Fighter que ya se ha comprobado que están en la flota.
	 * @return true: Si hay naves diferentes que no hayan sido contadas ya. false: Si no las hay.
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
