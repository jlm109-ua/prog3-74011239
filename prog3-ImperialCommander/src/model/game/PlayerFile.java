package model.game;

import java.io.BufferedReader;
import java.util.Objects;

import model.Coordinate;
import model.Side;
import model.game.exceptions.WrongFighterIdException;

public class PlayerFile implements IPlayer {
	private BufferedReader br;
	private GameShip ship;
	private GameBoard board;
	
	/**
	 * Constructor de PlayerFile.
	 * @param side
	 * @param br
	 */
	public PlayerFile(Side side,BufferedReader br) {
		Objects.requireNonNull(side);
		Objects.requireNonNull(br);
		ship = new GameShip("PlayerFile " + side + " Ship",side);
		this.br = br;
	}
	
	@Override
	public void setBoard(GameBoard g) {
		Objects.requireNonNull(g);
		board = g;
	}

	@Override
	public GameShip getGameShip() {
		return ship;
	}

	@Override
	public void initFighters() {
		try {
			String fleetString = br.readLine();
			ship.addFighters(fleetString);
		}catch(Exception e) {
			throw new RuntimeException();
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
		try {
			String move = br.readLine();
			
			if(move.startsWith("exit") || move.startsWith("improve") || move.startsWith("patrol") || move.startsWith("launch")) {
				if(move.startsWith("exit")) {
					return false; // REVISAR
				}
				if(move.startsWith("improve")) {
					String[] moveImprove = move.split(" ");
					
					if(moveImprove.length == 3) {
						int id = Integer.parseInt(moveImprove[1]);
						int qty = Integer.parseInt(moveImprove[2]);
						
						if(qty < 100) {
							try {
								ship.improveFighter(id, qty, board);
							}catch(WrongFighterIdException e){
								System.out.println(e.getMessage());
							}
						}else
							System.out.println("ERROR: Quantity value too high.");
					}else
						System.out.println("ERROR: No matching moves found.");
				}
				if(move.startsWith("patrol")) {
					String[] movePatrol = move.split("[ ]");
					
					if(movePatrol.length == 2) {
						int id = Integer.parseInt(movePatrol[1]);
						try {
							ship.patrol(id, board);
						}catch(Exception e) {
							System.out.println(e.getMessage());
						}
					}else
						System.out.println("ERROR: No matching moves found.");
				}
				if(move.startsWith("launch")) {
					try {
						String[] moveLaunch = move.split("[ ]");
						
						if(moveLaunch.length == 3) {
							int x = Integer.parseInt(moveLaunch[1]);
							int y = Integer.parseInt(moveLaunch[2]);
							
							ship.launch(ship.getFirstAvailableFighter("").getId(),new Coordinate(x,y),board);
						}else if(moveLaunch.length == 4) {
							String mL3 = moveLaunch[3];
							int x = Integer.parseInt(moveLaunch[1]);
							int y = Integer.parseInt(moveLaunch[2]);
							
							try {
								int id = Integer.parseInt(mL3);
								ship.launch(id,new Coordinate(x,y), board);
							}catch(NumberFormatException e) {
								ship.launch(ship.getFirstAvailableFighter(mL3).getId(),new Coordinate(x,y), board);
							}catch(Exception e) {
								System.out.println(e.getMessage());
							}
						}else
							System.out.println("ERROR: No matching moves found.");
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}else 
				System.out.println("ERROR: No matching moves found.");
			return true;
		}catch(Exception e) {
			throw new RuntimeException();
		}
	}
}
