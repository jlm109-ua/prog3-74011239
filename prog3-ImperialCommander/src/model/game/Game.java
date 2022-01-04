/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.game;

import java.util.Objects;
import model.Side;
import model.exceptions.InvalidSizeException;
import model.game.score.DestroyedFightersScore;
import model.game.score.Ranking;
import model.game.score.WinsScore;
/**
 * Clase destinada a ser la que controla el juego. 
 */
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
			imperial.setBoard(board);
			this.rebel = rebel;
			rebel.setBoard(board);
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
		int count = 0;
		imperial.initFighters();
		rebel.initFighters();
		
		do {
			if(!endGame && count == 0) {
				printRankings();
				System.out.print("BEFORE IMPERIAL");
				getGameInfo();
			}
			if(!endGame && count != 0) {
				System.out.print("\n");
				printRankings();
				System.out.print("BEFORE IMPERIAL");
				getGameInfo();
			}
			if(!endGame) {
				System.out.print("\nIMPERIAL(" + board.numFighters(Side.IMPERIAL) + "):");
				if(!imperial.nextPlay()) {
					endGame = true;
					whoWon = "REBEL";
					break;
				}
				System.out.print("AFTER IMPERIAL, BEFORE REBEL");
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
				System.out.print("\nREBEL(" + board.numFighters(Side.REBEL) + "):");
				if(!rebel.nextPlay()) {
					endGame = true;
					whoWon = "IMPERIAL";
					break;
				}
				System.out.print("AFTER REBEL");
				getGameInfo();
				imperial.purgeFleet();
				rebel.purgeFleet();
				if(imperial.isFleetDestroyed()) {
					endGame = true;
					whoWon = "REBEL";
				}else if(rebel.isFleetDestroyed()) {
					endGame = true;
					whoWon = "IMPERIAL";
				}
			}
			count++;
		}while(!endGame);
		
		imperial.purgeFleet();
		rebel.purgeFleet();
		printRankings();
		if(whoWon.equals("IMPERIAL"))
			return Side.IMPERIAL;
		else
			return Side.REBEL;
	}
	
	/**
	 * Muestra toda la informacion necesaria para cada turno.
	 */
	private void getGameInfo() {
		System.out.println("\n" + board.toString() + "\n");
		System.out.println(imperial.getGameShip().toString() + "\n" + imperial.getGameShip().showFleet());
		System.out.println(rebel.getGameShip().toString());
		if(rebel.getGameShip().getFleetTest().size() != 0)
			System.out.print(rebel.getGameShip().showFleet());
	}
	
	private void printRankings() {
		Ranking<WinsScore> rw = new Ranking<>();
		Ranking<DestroyedFightersScore> rd = new Ranking<>();
		
		rw.addScore(imperial.getGameShip().getWinsScore());
		rw.addScore(rebel.getGameShip().getWinsScore());
		rd.addScore(imperial.getGameShip().getDestroyedFightersScore());
		rd.addScore(rebel.getGameShip().getDestroyedFightersScore());
		System.out.println("RANKING WINS: " + rw.toString());
		System.out.println("RANKING DESTROYED: " + rd.toString());
	}
}
