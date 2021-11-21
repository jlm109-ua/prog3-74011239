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
	public GameShip(String name,Side side) {
		super(name,side);
	}
	
	public boolean isFleetDestroyed() { // REVISAR
		if(fleet.equals(null))
			return true;
		for(Fighter f : fleet)
			if(!f.isDestroyed())
				return false;
		return true;
	}
	
	private Fighter getFighter(int id) throws WrongFighterIdException{
		for(Fighter f : fleet)
			if(f.getId() == id && !f.isDestroyed())
				return f;
		throw new WrongFighterIdException(id);
	}
	
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
	
	public void launch(int id,Coordinate c,Board b) throws WrongFighterIdException, FighterAlreadyInBoardException, OutOfBoundsException, RuntimeException{
		Fighter f = getFighter(id);
		b.launch(c, f);	// ESTÁ BIEN?
	}
	
	public void patrol(int id,Board b) throws WrongFighterIdException, FighterNotInBoardException, RuntimeException {
		Fighter f = getFighter(id);
		b.patrol(f); // ESTÁ BIEN?
	}
	
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
