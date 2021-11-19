package model.game;

import java.util.List;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Ship;
import model.Side;
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
		
	}
	
	public void launch(int id,Coordinate c,Board b){
		
	}
	
	public void patrol(int id,Board b) {
		
	}
	
	public void imporveFighter(int id,int qty,Board b) {
		
	}
}
