/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.lang.String;
import model.exceptions.*;

/**
 * Clase destinada a ser una nave de luchadores.
 */
public class Ship {
	/**
	 * Declaramos todos los atributos privados.
	 * @author Juan Llinares Mauri - 74011239E
	 */
	private String name;
	private Side side;
	private int wins;
	private int losses;
	protected List<Fighter> fleet = new ArrayList<Fighter>();
	
	/**
	 * Constructor de Ship.
	 * @param name Nombre de la nave Ship.
	 * @param side Bando de la nave (IMPERIAL O REBEL).
	 */
	public Ship(String name,Side side) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(side);
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
	 * Anyade Fighter a fleet a partir de una cadena dada.
	 * @param fd Cadena dada.
	 */
	public void addFighters(String fd) {
		Objects.requireNonNull(fd);
		int i = 0;
		String[] fdv = fd.split("[/:]");
		
		do {
			for(int j = 0; j < Integer.parseInt(fdv[i]); j++) {
				Fighter f = FighterFactory.createFighter(fdv[i+1],this);
				if(f != null)
						fleet.add(f);
			}
			i++;i++;
		} while(i<fdv.length);
	}
	
	/**
	 * Actualiza las batallas ganadas y las perdidas a partir de una batalla entre Fighters.
	 * @param r Parametro para determinar quien gano la batalla.
	 * @param f Fighter.
	 */
	public void updateResults(int r,Fighter f) {
			if(r == 1)
				this.wins++;
			if(r == -1)
				this.losses++;
	}
	
	/**
	 * Devuelve el primer Fighter disponible que no est� destruido a partir de una cadena dada. Si la cadena tiene valor null devolver� el primer Fighter no destruido que encuentre.
	 * @param t Cadena dada.
	 * @throws NoFighterAvailableException Excepcion que se lanza cuando no hay ningun Fighter disponible.
	 * @return null: Si no hay Fighters no destruidos disponibles. fleet.get(i): Si el Fighter cumple los requisitos de la cadena y no esta destruido.
	 */
	public Fighter getFirstAvailableFighter(String t) throws NoFighterAvailableException {
		Objects.requireNonNull(t);
		if(fleet.isEmpty()) {
			throw new NoFighterAvailableException(t);
		}
		if(t.equals("AWing") || t.equals("XWing") || t.equals("YWing") || t.equals("TIEBomber") || t.equals("TIEInterceptor") || t.equals("TIEFighter") || t.equals("")) {
			for(int i = 0; i < fleet.size(); i++) {
				if(!fleet.get(i).isDestroyed() && t == ("") && fleet.get(i).getPosition() == null)
					return fleet.get(i);
				if(!fleet.get(i).isDestroyed() && t.equals(fleet.get(i).getType()) && fleet.get(i).getPosition() == null)
					return fleet.get(i);
			}
		}
		throw new NoFighterAvailableException(t);
	}
	
	/**
	 * Limpia la flota de naves destruidas.
	 */
	public void purgeFleet() {
		for(int i = fleet.size()-1;i >= 0;i--) {
			if(fleet.get(i).isDestroyed() == true) {
				fleet.remove(i);
			}
		}
	}
	
	/**
	 * Devuelve una cadena con la informacion de toda la flota.
	 * @return "": Si la flota esta vacia. showFleet: Si hay naves en la flota.
	 */
	public String showFleet() {
		String showFleet = ("");
		if(fleet.size() == 0)
			return("");
		else {
			for(int i = 0;i < fleet.size();i++) {
				if(fleet.get(i).isDestroyed() == true)
					showFleet = showFleet + fleet.get(i).toString() + " (X)\n";
				else
					showFleet = showFleet + fleet.get(i).toString() + "\n";
				
			}
			return showFleet;
		}
	}
	
	/**
	 * Devuelve una cadena con toda la informacion de la flota de forma ordenada con el numero de naves iguales.
	 * @return "": Si la flota esta vacia. myFleet: Si hay naves en la flota.
	 */
	public String myFleet() {
		if (fleet.isEmpty())
			return ("");
		boolean isTheFirst = true;
		
		String myFleet = (""),name;
		List<String> shipNames = new ArrayList<String>();
		for(int i = 0;i < fleet.size();i++) {
			name = fleet.get(i).getType();
			if(!shipNames.contains(name) && !fleet.get(i).isDestroyed()) {
				if(isTheFirst) {
					myFleet = myFleet + checkSameShips(name) + "/" + name;
					isTheFirst = false;
				}else
					myFleet = myFleet + ":" + checkSameShips(name) + "/" + name;
				shipNames.add(name);
			}
		}
		
		return myFleet;
	}
	
	/**
	 * Devuelve el numero de naves iguales.
	 * @param name Tipo de nave a comprobar.
	 * @return count Numero de naves iguales que hay.
	 */
	private int checkSameShips(String name) {
		int count = 0;
		
		for(int j = 0;j < fleet.size();j++) {
			if(name.equals(fleet.get(j).getType()) && !fleet.get(j).isDestroyed())
				count++;
		}
		return count;
	}
	
	@Override
	public String toString() {
		return "Ship [" + name + " " + wins + "/" + losses + "] " + myFleet();
	}
}
