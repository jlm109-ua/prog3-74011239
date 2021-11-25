package model.game;

import java.util.Objects;

import model.exceptions.InvalidSizeException;

public class Game {
	private final int BOARD_SIZE = 10;
	private GameBoard board;
	private IPlayer imperial;
	private IPlayer rebel;
	
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
	
	public GameBoard getGameBoard() {
		return board;
	}
	
	public Side play() {
		boolean endGame = false;
		imperial.initFighters();
		rebel.initFighters();
		
		do {
			// HACER IFS (if(!endGame)) PARA COMPROBAR QUE NO SE HA TERMINADO EL JUEGO Y NO PEDIR MOVIMIENTOS A LOS JUGADORES
			// CUANDO SON INNECESARIOS
			
			// TERMINAR
		}while(endGame = true);
	}
}
