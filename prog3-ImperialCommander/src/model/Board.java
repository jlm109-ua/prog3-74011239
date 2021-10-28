package model;

import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.HashMap;

public class Board {
	/**
	 * Declaramos todos los atributos privados.
	 * @author Juan Llinares Mauri - 74011239E
	 */
	private int size;
	private Map<Coordinate,Fighter> board;
	
	/**
	 * Constructor del tablero.
	 * @param size Tamaño del tablero (size*size).
	 */
	public Board(int size) {
		this.size = size;
		this.board = new HashMap<Coordinate,Fighter>(); 
	}
	
	/**
	 * Metodo para obtener el Fighter que se encuentra en la coordenada dada.
	 * @param Coordenada dada.
	 * @return Fighter.
	 */
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		
		if(board.get(c) == null)
			return null;
		else {
			Fighter f = new Fighter(board.get(c));
			return f;
		}
	}
	 
	/**
	 * Getter de size.
	 * @return Tamaño del tablero.
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Borra Fighters del tablero en caso de que sean iguales.
	 * @param f Fighter.
	 * @return true: Si son iguales. false: En cualquier otro caso.
	 */
	public boolean removeFighter(Fighter f) {
		Objects.requireNonNull(f);
		
		Fighter f2 = board.get(f.getPosition());
		
		if(f2 != null && f2.getPosition() != null) {
			if(f2.equals(f)) {
				f2 = board.remove(f.getPosition());
				return true;
			}else if(f2.equals(null))
				return false;
			else
				return false;
		}else
			return false;
	}
	
	/**
	 * Comprueba si la coordenada pasada se encuentra dentro del tablero.
	 * @param c Coordenada dada.
	 * @return true: Si lo está. false: En cualquier otro caso.
	 */
	public boolean inside(Coordinate c) {
		Objects.requireNonNull(c);
		
		if(c.equals(null))
			return false;
		else if(c.getX() >= 0 && c.getX() <= getSize()-1 && c.getY() >= 0 && c.getY() <= getSize()-1 )
			return true;
		else 
			return false;
		
	}
	
	/**
	 * Devuelve las coordenadas válidas dentro del tablero.
	 * @param c Coordenada de la nave actual.
	 * @return valid_pos Coordenadas válidas que puede tomar la nave.
	 */
	public TreeSet<Coordinate> getNeighborhood(Coordinate c) {
		TreeSet<Coordinate> valid_pos = new TreeSet<Coordinate>();
		
		for(Coordinate c2 : c.getNeighborhood()) {
			if(inside(c2)) {
				valid_pos.add(c2);
			}
		}
		
		return valid_pos;
	}
	
	/**
	 * Comprueba si en la coordenada dada hay un enemigo y mueve a los Fighters que luchan.
	 * @param c Coordenada dada.
	 * @param f Fighter amigo.
	 * @return combat(-1): Si el enemigo gana. 0: Si no hay enemigo en c. combat(1): Si el amigo gana.
	 */
	public int launch(Coordinate c,Fighter f) {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		
		if(inside(c)) {
			Fighter f2 = getFighter(c);
			
			if(f2 == null) {
				board.put(c,f);
				f.setPosition(c);
				return 0;
			}else if(f2.getSide() != f.getSide()) {
				int combat = f.fight(f2);
				for(int i = 0;i < f2.getMotherShip().getFleetTest().size();i++) {
					if(f2.equals(f2.getMotherShip().getFleetTest().get(i)))
						f2.getMotherShip().getFleetTest().set(i,f2);
				}
				if(combat == 1) {
					f.getMotherShip().updateResults(combat);
					f2.getMotherShip().updateResults(-combat);
					removeFighter(f2);
					board.put(c,f);
					f.setPosition(c);
					return combat;
				}else if(combat == -1){
					f.getMotherShip().updateResults(-combat);
					f2.getMotherShip().updateResults(combat);
					return combat;
				}
					
			}
		}
		return 0;	
	}
	
	/**
	 * Recorre las posiciones vecinas válidas y lucha contra los Fighter enemigos que encuentre.
	 * @param f Fighter amigo.
	 */
	public void patrol(Fighter f) {
		Objects.requireNonNull(f);
		
		for(Coordinate c : f.getPosition().getNeighborhood()) {
			Fighter f2 = getFighter(c);
			
			if(f2 != null) {
				if(f2.getSide() != f.getSide()) {
					int combat = f.fight(f2);
					if(combat == 1) {
						f.getMotherShip().updateResults(combat);
						f2.getMotherShip().updateResults(-combat);
						for(int i = 0;i < f2.getMotherShip().getFleetTest().size();i++) {
							if(f2.equals(f2.getMotherShip().getFleetTest().get(i)))
								f2.getMotherShip().getFleetTest().set(i,f2);
						}
						removeFighter(f2);
						f2.setPosition(null);
					}else {
						f.getMotherShip().updateResults(combat);
						f2.getMotherShip().updateResults(-combat);
						removeFighter(f);
						f.setPosition(null);
					}
				}
			}
		}
	}
}