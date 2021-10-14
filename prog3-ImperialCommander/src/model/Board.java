package model;

import java.util.Map;
import java.util.Objects;

public class Board {
	/**
	 * Declaramos todos los atributos privados.
	 * @author Juan Llinares Mauri - 74011239E
	 */
	private int size;
	
	private Map<Coordinate,Fighter> board = new HashMap<Coordinate,Fighter>();
	
	public Board(int size) {
		
	}
	
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		
		Fighter f = board.get(c);
		
		return f;
	}
	
	public int getSize() {
		return board.size();
	}
	
	public boolean removeFighter(Fighter f) {
		Objects.requireNonNull(f);
		
		Fighter f2 = board.get(f.getPosition());
		
		if(f2.equals(f)) {
			f2 = board.remove(f.getPosition());
			return true;
		}else if(f2.equals(null))
			return false;
		else
			return false;
	}
	
	public boolean inside(Coordinate c) {
		Objects.requireNonNull(c);
		
		if(c.equals(null))
			return false;
		else if(c.getX() >= 0 && c.getX() < board.size()-1 && c.getY() >= 0 && c.getY() < board.size()-1 )
			return true;
		else 
			return false;
		
	}
	
	public TreeSet<Coordinate> getNeighborhood(Coordinate c) {
		c.getNeighborhood();
		
		/*recorrer TreeSet y acabarlo*/
	}
	
	public int launch(Coordinate c,Fighter f) {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		
		if(inside(c)) {
			Fighter f2 = board.get(c);
			
			if(f2.equals(null)) {
				board.put(c,f);
				f.setPosition(c);
				return 0;
			}else if(f2.getSide() != f.getSide()) {
				int combat = f.fight(f2);
				if(combat == 1) {
					Ship motherShip = f.getMotherShip();
					motherShip.updateResults(combat);
					removeFighter(f2);
					board.put(c,f);
					f.setPosition(c);
					return combat;
				}else {
					Ship motherShip = f2.getMotherShip();
					motherShip.updateResults(combat);
					return combat;
				}
					
			}
		}else
			return 0;
	}
	
	public void patrol(Fighter f) {
		Objects.requireNonNull(f);
		
		
	}
}
