/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model;

import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.HashMap;
import model.exceptions.*;

public class Board {
	/**
	 * Declaramos todos los atributos privados.
	 * @author Juan Llinares Mauri - 74011239E
	 */
	private int size;
	private Map<Coordinate,Fighter> board;
	
	/**
	 * Constructor del tablero.
	 * @param size Tamanyo del tablero (size*size).
	 */
	public Board(int size) throws InvalidSizeException {
		if(size < 5)
			throw new InvalidSizeException(size);
		else{
			this.size = size;
			this.board = new HashMap<Coordinate,Fighter>();
		}		
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
			Fighter f = FighterFactory.createFighter(board.get(c).getType(),board.get(c).getMotherShip());
			return f;
		}
	}
	 
	/**
	 * Getter de size.
	 * @return Tamanyo del tablero.
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Borra Fighters del tablero en caso de que sean iguales.
	 * @param f Fighter.
	 * @return true: Si son iguales. false: En cualquier otro caso.
	 */
	public boolean removeFighter(Fighter f) throws FighterNotInBoardException {
		Objects.requireNonNull(f);
		
		if(f.getPosition() == null) {
			throw new FighterNotInBoardException(f);
		}else {
			Fighter f2 = board.get(f.getPosition());
			
			if(f2 != null) {
				if(f2.equals(f)) {
					f2 = board.remove(f.getPosition());
					return true;
				}else if(f2.equals(null))
					return false;
				else
					return false;
			}
		}
		
		return false;
	}
	
	/**
	 * Comprueba si la coordenada pasada se encuentra dentro del tablero.
	 * @param c Coordenada dada.
	 * @return true: Si lo esta. false: En cualquier otro caso.
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
	 * Devuelve las coordenadas validas dentro del tablero.
	 * @param c Coordenada de la nave actual.
	 * @return valid_pos Coordenadas validas que puede tomar la nave.
	 */
	public TreeSet<Coordinate> getNeighborhood(Coordinate c) throws OutOfBoundsException {
		if(inside(c)) {
			TreeSet<Coordinate> valid_pos = new TreeSet<Coordinate>();
			
			for(Coordinate c2 : c.getNeighborhood()) {
				if(inside(c2)) {
					valid_pos.add(c2);
				}
			}
			
			return valid_pos;
		}else {
			throw new OutOfBoundsException(c);
		}
	}
	
	/**
	 * Comprueba si en la coordenada dada hay un enemigo y mueve a los Fighters que luchan.
	 * @param c Coordenada dada.
	 * @param f Fighter amigo.
	 * @return combat(-1): Si el enemigo gana. 0: Si no hay enemigo en c. combat(1): Si el amigo gana.
	 */
	public int launch(Coordinate c,Fighter f) throws FighterAlreadyInBoardException, OutOfBoundsException{
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		int combat = 0;
		
		if(f.getPosition() == null) {
			if(inside(c)) {
				Fighter f2 = board.get(c);
				
				if(f2 == null) {
					board.put(c,f);
					f.setPosition(c);
					return 0;
				}else if(f2.getSide() != f.getSide()) {
						try {
							combat = f.fight(f2);
						} catch (FighterIsDestroyedException e) {
							e.getMessage();
						}
					if(combat == 1) {
						f.getMotherShip().updateResults(combat);
						f2.getMotherShip().updateResults(-combat);
						try {
							removeFighter(f2);
						}catch(FighterNotInBoardException e) {
							e.getMessage();
						}
						board.put(c,f);
						f.setPosition(c);
						return combat;
					}else if(combat == -1){
						f.getMotherShip().updateResults(combat);
						f2.getMotherShip().updateResults(-combat);
						
						return combat;
					}
				}
			}else {
				throw new OutOfBoundsException(c);
			}
		}else {
			throw new FighterAlreadyInBoardException(f);
		}
		
		return 0;	
	}
	
	/**
	 * Recorre las posiciones vecinas validas y lucha contra los Fighter enemigos que encuentre.
	 * @param f Fighter amigo.
	 */
	public void patrol(Fighter f) throws FighterNotInBoardException{
		Objects.requireNonNull(f);
		int combat = 0;
		
		if(f.getPosition().equals(null))
			throw new FighterNotInBoardException(f);
		else {
			for(Coordinate c : f.getPosition().getNeighborhood()) {
				Fighter f2 = getFighter(c);
				
				if(f2 != null) {
					if(f2.getSide() != f.getSide()) {
						try {
							combat = f.fight(f2);
						}catch(FighterIsDestroyedException e){
							e.getMessage();
						}
						if(combat == 1) {
							f.getMotherShip().updateResults(combat);
							f2.getMotherShip().updateResults(-combat);
							for(int i = 0;i < f2.getMotherShip().getFleetTest().size();i++) {
								if(f2.equals(f2.getMotherShip().getFleetTest().get(i)))
									f2.getMotherShip().getFleetTest().set(i,f2);
							}
							try {
								removeFighter(f2);
							}catch(FighterNotInBoardException e) {
								e.getMessage();
							}
							f2.setPosition(null);
						}else {
							f.getMotherShip().updateResults(combat);
							f2.getMotherShip().updateResults(-combat);
							try {
								removeFighter(f);
							}catch(FighterNotInBoardException e) {
								e.getMessage();
							}
							f.setPosition(null);
						}
					}
				}
			}
		}
	}
}