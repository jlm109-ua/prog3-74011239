package model.game;

import java.util.Objects;
import model.Side;
import model.exceptions.InvalidSizeException;

public class Game {
	private final int BOARD_SIZE = 10;
	private GameBoard board;
	private IPlayer imperial;
	private IPlayer rebel;
	
	/**
	 * Constructor de Game.
	 * @param imperial IPlayer Jugador imperial.
	 * @param rebel IPlayer Jugador rebelde.
	 */
	public Game(IPlayer imperial,IPlayer rebel) {
		Objects.requireNonNull(imperial);
		Objects.requireNonNull(rebel);
		try {
			board = new GameBoard(BOARD_SIZE);
			this.imperial = imperial;
			this.rebel = rebel;
		}catch(InvalidSizeException e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * Devuelve el tablero.
	 * @return board.
	 */
	public GameBoard getGameBoard() {
		return board;
	}
	
	/**
	 * Controla el juego.
	 * @return Side IMPERIAL: Si gana el jugador imperial, REBEL: Si gana el rebelde.
	 */
	public Side play() {
		boolean endGame = false;
		String whoWon = null;
		imperial.initFighters();
		rebel.initFighters();
		
		do {
			if(!endGame) {
				System.out.println("BEFORE IMPERIAL");
				board.toString();
				getGameInfo();
				if(imperial.isFleetDestroyed()) {
					endGame = true;
					whoWon = "REBEL";
				}else if(rebel.isFleetDestroyed()) {
					endGame = true;
					whoWon = "IMPERIAL";
				}
			}
			if(!endGame) {
				System.out.println("IMPERIAL(" + board.numFighters(Side.IMPERIAL) + "): AFTER IMPERIAL, BEFORE REBEL");
				imperial.nextPlay();
				board.toString();
				getGameInfo();
				if(imperial.isFleetDestroyed()) {
					endGame = true;
					whoWon = "REBEL";
				}else if(rebel.isFleetDestroyed()) {
					endGame = true;
					whoWon = "IMPERIAL";
				}
			}
			if(!endGame) {
				System.out.println("REBEL(" + board.numFighters(Side.REBEL) + "): AFTER REBEL");
				rebel.nextPlay();
				board.toString();
				getGameInfo();
				if(imperial.isFleetDestroyed()) {
					endGame = true;
					whoWon = "REBEL";
				}else if(rebel.isFleetDestroyed()) {
					endGame = true;
					whoWon = "IMPERIAL";
				}
			}
		}while(!endGame);
		
		System.out.println("And the winner is " + whoWon);
		
		if(whoWon.equals("IMPERIAL"))
			return Side.IMPERIAL;
		else
			return Side.REBEL;
	}
	
	/**
	 * Muestra toda la información necesaria para cada turno.
	 */
	private void getGameInfo() {
		board.toString();
		System.out.println(imperial.getGameShip().toString() + "\n" + imperial.getGameShip().showFleet());
		System.out.println(rebel.getGameShip().toString() + "\n" + rebel.getGameShip().showFleet());
	}
}
