/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.game.score;

import model.Side;
/**
 * Clase destinada a ser un marcador de victorias.
 */
public class WinsScore extends Score<Integer>{
	/**
	 * Constructor de WinsScore.
	 * @param side Side.
	 */
	public WinsScore(Side side) {
		super(side);
	}
	
	/**
	 * Aumenta el atributo score de la superclase.
	 * @param w
	 */
	@Override
	public void score(Integer w) {
		if(w != null) {
			if(w.equals(1)) {
				score += 1;
			}
		}
	}
}
