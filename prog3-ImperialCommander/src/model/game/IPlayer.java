package model.game;

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
	 * Obtiene una cadena de Fighter y los inicializa.
	 */
	public void initFighters();
	/**
	 * Comprueba si la flota esta destruida.
	 * @return true: Si la flota esta destruida. false: Si hay algun caza que no esté destruido en la flota.
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
	 * @return 
	 */
	public boolean nextPlay();
}
