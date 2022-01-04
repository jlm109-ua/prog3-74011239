/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.game.score;

import model.Side;
/**
 * Clase destinada a ser un marcador.
 * @param <T> Implementa Comparable consigo mismo.
 */
public abstract class Score<T> implements Comparable<Score<T>>{
	int score;
	private Side side;
	
	/**
	 * Constructor de Score.
	 * @param side Side.
	 */
	public Score(Side side) {
		this.score = 0;
		this.side = side;
	}
	
	/**
	 * Getter de Score.
	 * @return score 
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Compara dos Score y devuelve 1 o -1 si el valor del Score pasado como par�metro es menor o mayor, respectivamente.
	 * Si resultan ser iguales acude al compareTo de los side para detereminar si devolver 1 o -1 (IMPERIAL > REBEL). 
	 * @param other Score<T>
	 * @return 1: this > other, -1: other > this.
	 */
	public int compareTo(Score<T> other) {
		if(this.score == other.score)
			return side.compareTo(other.side);
		else if(this.score > other.score)
			return -1;
		else
			return 1;
	}
	
	/**
	 * Devuelve la cadena ""Player " + side + ": " + score".
	 * @return String Contenido del marcador.
	 */
	public String toString() {
		return "Player " + side + ": " + score;
	}
	
	/**
	 * Añade puntuaciones.
	 * @param sc Tipo de marcador.
	 */
	public abstract void score(T sc);
}
