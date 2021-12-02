package model.game;

import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;
import java.util.List;
import java.util.Objects;
import model.Coordinate;
import model.RandomNumber;

public class PlayerRandom implements IPlayer {
	private int numFighters;
	private GameShip ship;
	private GameBoard board;
	
	/**
	 * Constructor de PlayerRandom.
	 * @param side Side, Bando de la nave.
	 * @param numFighters Int, Número de Fighters.
	 */
	public PlayerRandom(Side side,int numFighters) {
		Objects.requireNonNull(side);
		Objects.requireNonNull(numFighters);
		ship = new GameShip("PlayerRandom " + side + " Ship",side);
		this.numFighters = numFighters;
	}

	@Override
	public void setBoard(GameBoard gb) {
		Objects.requireNonNull(gb);
		board = gb;
	}
	
	@Override
	public GameShip getGameShip() {
		return ship;
	}
	
	@Override
	public void initFighters() {
		String fleetString = ("");
		int n = 0;
		
		if(ship.getSide().equals(Side.REBEL)) {
			for(int i = 0;i < 3;i++) {
				switch(i) {
					case 0:
						n = RandomNumber.newRandomNumber(numFighters);
						if(n != 0)
							fleetString += n + "/XWing:";
					case 1:
						n = RandomNumber.newRandomNumber(numFighters);
						if(n != 0)
							fleetString += n + "/YWing:";
					case 2:
						n = RandomNumber.newRandomNumber(numFighters);
						if(n != 0)
							fleetString += n + "/AWing";
				}
			}
		}else if(ship.getSide().equals(Side.IMPERIAL)){
			for(int i = 0;i < 3;i++) {
				switch(i) {
					case 0:
						n = RandomNumber.newRandomNumber(numFighters);
						if(n != 0)
							fleetString += n + "/TIEFighter:";
					case 1:
						n = RandomNumber.newRandomNumber(numFighters);
						if(n != 0)
							fleetString += n + "/TIEBomber:";
					case 2:
						n = RandomNumber.newRandomNumber(numFighters);
						if(n != 0)
							fleetString += n + "/TIEInterceptor";
				}
			}
		}
		if(!fleetString.equals(null)) {
			ship.addFighters(fleetString);
		}
	}
	
	@Override
	public boolean isFleetDestroyed() {
		return ship.isFleetDestroyed();
	}
	
	@Override
	public String showShip() {
		String shipString = ("");
			shipString += ship.getName() + "\n";
			shipString += ship.showFleet();
		
		return shipString;
	}
	
	@Override
	public void purgeFleet() {
		ship.purgeFleet();
	}
	
	@Override
	public boolean nextPlay() {
		int option = RandomNumber.newRandomNumber(100);
		int pos = 0;
		
		if(option == 99) {
			return false;
		}else {
			List<Integer> ids = ship.getFightersId("board");
			pos = RandomNumber.newRandomNumber(ids.size());
			int id = ids.get(pos);
			
			if(!ids.equals(null)) {
				if(option >= 85 && option <= 98) {
					try {
						ship.improveFighter(id,option,board);
					} catch (WrongFighterIdException e) {}
				}else if(option >= 25 && option <= 84) {
					Coordinate c = new Coordinate(RandomNumber.newRandomNumber(board.getSize()),RandomNumber.newRandomNumber(board.getSize()));
					try {
						ship.launch(id, c, board);
					} catch (WrongFighterIdException e) {
						e.getMessage();
						throw new RuntimeException(e);
					} catch (FighterAlreadyInBoardException e) {
						e.getMessage();
						throw new RuntimeException(e);
					} catch (OutOfBoundsException e) {
						e.getMessage();
						throw new RuntimeException(e);
					}
				}else if(option >= 0 && option <= 24) {
					try {
						ship.patrol(id, board);
					}catch(WrongFighterIdException e) {
						e.getMessage();
						throw new RuntimeException(e);
					}catch(FighterNotInBoardException e) {
						e.getMessage();
						throw new RuntimeException(e);
					}
				}
			}else {
				System.err.println("ERROR: No Fighters found.");
			}
		}
		
		return true;
	}
}
