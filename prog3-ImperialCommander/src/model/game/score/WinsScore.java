/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.game.score;

import model.Side;

public class WinsScore extends Score<Integer>{
	/**
	 * Constructor de WinsScore.
	 * @param side
	 */
	public WinsScore(Side side) {
		super(side);
	}
	
	/**
	 * Augmenta el atributo score de la superclase.
	 * @param w
	 */
	@Override
	public void score(Integer w) {
		if(w.equals(1)) {
			score += 1;
		}
	}
}