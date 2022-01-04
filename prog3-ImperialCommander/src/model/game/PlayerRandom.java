package model.game;

import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;
import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;
import java.util.List;
import java.util.Objects;
import model.Coordinate;
import model.RandomNumber;
/**
 * Clase destinada a ser un jugador que juega mediante posibilidades.
 */
public class PlayerRandom implements IPlayer {
	private int numFighters;
	private GameShip ship;
	private GameBoard board;
	
	/**
	 * Constructor de PlayerRandom.
	 * @param side Side, Bando de la nave.
	 * @param numFighters Int, Numero de Fighters.
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
						break;
					case 1:
						n = RandomNumber.newRandomNumber(numFighters);
						if(n != 0)
							fleetString += n + "/YWing:";
						break;
					case 2:
						n = RandomNumber.newRandomNumber(numFighters);
						if(n != 0)
							fleetString += n + "/AWing";
						break;
				}
			}
		}else if(ship.getSide().equals(Side.IMPERIAL)){
			for(int i = 0;i < 3;i++) {
				switch(i) {
					case 0:
						n = RandomNumber.newRandomNumber(numFighters);
						if(n != 0)
							fleetString += n + "/TIEFighter:";
						break;
					case 1:
						n = RandomNumber.newRandomNumber(numFighters);
						if(n != 0)
							fleetString += n + "/TIEBomber:";
						break;
					case 2:
						n = RandomNumber.newRandomNumber(numFighters);
						if(n != 0)
							fleetString += n + "/TIEInterceptor";
						break;
				}
			}
		}
		if(!fleetString.equals("")) {
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
		shipString += ship.toString();
		
		if(ship.getFleetTest().size() != 0)
			shipString += "\n" + ship.showFleet();
		
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
			if(option >= 85 && option <= 98) {
				try {
					List<Integer> ids = ship.getFightersId("");
					if(ids.size() != 0) {
						pos = RandomNumber.newRandomNumber(ids.size());
						int id = ids.get(pos);
						ship.improveFighter(id,option,board);
					}else
						System.out.println("ERROR: No Fighters found.");
				} catch (WrongFighterIdException e) {}
			}else if(option >= 25 && option <= 84) {
				try {
					List<Integer> ids = ship.getFightersId("ship");
					if(ids.size() != 0) {
						pos = RandomNumber.newRandomNumber(ids.size());
						int id = ids.get(pos);
						Coordinate c = new Coordinate(RandomNumber.newRandomNumber(board.getSize()),RandomNumber.newRandomNumber(board.getSize()));
						ship.launch(id, c, board);
					}else
						System.out.println("ERROR: No Fighters found.");
				} catch (WrongFighterIdException e) {
					System.out.print(e.getMessage());
					throw new RuntimeException(e);
				} catch (FighterAlreadyInBoardException e) {
					System.out.print(e.getMessage());
					throw new RuntimeException(e);
				} catch (OutOfBoundsException e) {
					System.out.print(e.getMessage());
					throw new RuntimeException(e);
				}
			}else if(option >= 0 && option <= 24) {
				try {
					List<Integer> ids = ship.getFightersId("board");
					if(ids.size() != 0) {
						pos = RandomNumber.newRandomNumber(ids.size());
						int id = ids.get(pos);
						ship.patrol(id, board);
					}else
						System.out.println("ERROR: No Fighters found.");
				}catch(WrongFighterIdException e) {
					System.out.print(e.getMessage());
					throw new RuntimeException(e);
				}catch(FighterNotInBoardException e) {
					System.out.print(e.getMessage());
					throw new RuntimeException(e);
				}
			}
		}
	
		return true;
	}

	@Override
	public WinsScore getWinsScore() {
		return ship.getWinsScore();
	}

	@Override
	public DestroyedFightersScore getDestroyedFightersScore() {
		return ship.getDestroyedFightersScore();
	}
}
