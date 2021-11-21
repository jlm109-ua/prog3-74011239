package model.game;

import java.util.ArrayList;
import java.util.List;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Ship;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

public class GameShip extends Ship{
	/**
	 * Constructor de GameShip
	 * @param name Nombre de la nave.
	 * @param side Bando de la nave
	 */
	public GameShip(String name,Side side) {
		super(name,side);
	}
	
	/**
	 * Comprueba si la flota esta destruida.
	 * @return true: Si la flota esta destruida. fals: Si algun caza de la flota no esta destruido.
	 */
	public boolean isFleetDestroyed() { // REVISAR
		if(fleet.equals(null))
			return true;
		for(Fighter f : fleet)
			if(!f.isDestroyed())
				return false;
		return true;
	}
	
	/**
	 * Devuelve el Fighter con el mismo id pasado por parámetro.
	 * @param id Id del Fighter.
	 * @return Fighter con el id id.
	 * @throws WrongFighterIdException Excepcion que se lanza por si no hay Fighter con un id pasado por parametro.
	 */
	private Fighter getFighter(int id) throws WrongFighterIdException{
		for(Fighter f : fleet)
			if(f.getId() == id && !f.isDestroyed())
				return f;
		throw new WrongFighterIdException(id);
	}
	
	/**
	 * Devuelve una lista de ids de Fighters según la condicion pasada por parametro.
	 * @param where String de condicion.
	 * @return List<Integer> ids Lista de ids.
	 */
	public List<Integer> getFightersId(String where){
		List<Fighter> fleet2 = getFleetTest();
		List<Integer> ids = new ArrayList<Integer>();
		
		if(where.equals("board")) {
			for(Fighter f : fleet2) {
				if(f.getPosition() != null && !f.isDestroyed()) {
					ids.add(f.getId());
				}
			}
			return ids;
		}else if(where.equals("ship")) {
			for(Fighter f : fleet2) {
				if(f.getPosition() == null && !f.isDestroyed()) {
					ids.add(f.getId());
				}
			}
			return ids;
		}
		for(Fighter f : fleet2) {
			if(!f.isDestroyed())
				ids.add(f.getId());
		}
		return ids;
	}
	
	/**
	 * Lanza el Fighter con id id al tablero.
	 * @param id id del Fighter a lanzar.
	 * @param c Coordenada del tablero para el Fighter.
	 * @param b Tablero.
	 * @throws WrongFighterIdException Excepcion por si no hay Fighter con la id pasada por parametro.
	 * @throws FighterAlreadyInBoardException Excepcion por si el Fighter ya se encuentra en el tablero.
	 * @throws OutOfBoundsException Excepcion por si la coordenada no se encuentra en el tablero.
	 * @throws RuntimeException
	 */
	public void launch(int id,Coordinate c,Board b) throws WrongFighterIdException, FighterAlreadyInBoardException, OutOfBoundsException, RuntimeException{
		Fighter f = getFighter(id);
		b.launch(c, f);	// ESTÁ BIEN?
	}
	 /**
	  * El Fighter con el id id patrulla por el tablero.
	  * @param id id del Fighter.
	  * @param b Tablero.
	  * @throws WrongFighterIdException Excepcion por si no hay Fighter con la id pasada por parametro.
	  * @throws FighterNotInBoardException Excepcion por si no se encuentra el Fighter en el tablero.
	  * @throws RuntimeException
	  */
	public void patrol(int id,Board b) throws WrongFighterIdException, FighterNotInBoardException, RuntimeException {
		Fighter f = getFighter(id);
		b.patrol(f); // ESTÁ BIEN?
	}
	
	/**
	 * Mejora el Fighter con id id.
	 * @param id id del Fighter.
	 * @param qty Cantidad de ataque (attack) y escudo (shield) a añadir.
	 * @param b Tablero.
	 * @throws WrongFighterIdException Excepcion por si no hay Fighter con la id pasada por parametro.
	 */
	public void improveFighter(int id,int qty,Board b) throws WrongFighterIdException {
		Fighter f = getFighter(id);
		int plus_attack = 0;
		int plus_shield = 0;
		try {
			b.removeFighter(f);
			if(qty%2 == 0) {
				plus_attack = qty/2;
				plus_shield = qty/2;
			}else {
				plus_attack = qty/2;
				plus_shield = qty/2+1;
			}
			f.addAttack(plus_attack);
			f.addShield(plus_shield);
		}catch(FighterNotInBoardException e) {
			e.getMessage();
		}
	}
}
