/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.game;

import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;
/**
 * Interfaz de los jugadores.
 */
public interface IPlayer {
	/**
	 * Asigna el tablero pasado como parametro.
	 * @param gb Tablero.
	 */
	public void setBoard(GameBoard gb);
	/**
	 * Devuelve la nave GameShip.
	 * @return GameShip.
	 */
	public GameShip getGameShip();
	/**
	 * Devuelve las victorias de la nave.
	 * @return WinsScore
	 */
	public WinsScore getWinsScore();
	/**
	 * Devuelve la puntuaci�n de los Fighter destruidos por la nave.
	 * @return DestroyedFightersScore
	 */
	public DestroyedFightersScore getDestroyedFightersScore();
	/**
	 * Obtiene una cadena de Fighter y los inicializa.
	 */
	public void initFighters();
	/**
	 * Comprueba si la flota esta destruida.
	 * @return true: Si la flota esta destruida. false: Si hay algun caza que no est� destruido en la flota.
	 */
	public boolean isFleetDestroyed();
	/**
	 * Devuelve la informacion de la nave.
	 * @return String shipstring Informacion de la nave.
	 */
	public String showShip();
	/**
	 * Elimina los cazas destruidos de la nave.
	 */
	public void purgeFleet();
	/**
	 * Realiza la siguiente jugada del jugador.
	 * @return false: Si el jugador abandona el juego. true: Si sigue jugando
	 */
	public boolean nextPlay();
}
