/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.game.score;

import model.Fighter;
import model.Side;
/**
 * Clase destinada a ser un marcador de luchadores destruidos.
 */
public class DestroyedFightersScore extends Score<Fighter>{
	/**
	 * Constructor de DestroyedFightersScore
	 * @param side Side.
	 */
	public DestroyedFightersScore(Side side) {
		super(side);
	}
	
	/**
	 * Aumenta el atributo score de la superclase.
	 * @param f
	 */
	@Override
	public void score(Fighter f) {
		if(f != null) {
			score += f.getValue();
		}
	}
}
