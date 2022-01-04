/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.game.score;

import java.util.SortedSet;
import java.util.TreeSet;
/**
 * Clase destindada a ser un marcador de WinsScore o DestroyedFightersScore.
 * @param <ScoreType> Tipo de marcador.
 */
public class Ranking<ScoreType extends Score<?>> {
	private SortedSet<ScoreType> scoreSet;
	/**
	 * Constructor de Ranking.
	 */
	public Ranking() {
		scoreSet = new TreeSet<>();
	}
	 /**
	  * Anyade al conjunto scoreSet el objeto pasado como parametro.
	  * @param st ScoreType
	  */
	public void addScore(ScoreType st) {
		scoreSet.add(st);
		
	}
	
	/**
	 * Getter de scoreSet.
	 * @return scoreSet
	 */
	public SortedSet<ScoreType> getSortedRanking(){
		return scoreSet;
	}
	
	/**
	 * Devuelve el primer objeto del conjunto scoreSet. Si el conjunto esta vacio lanzara una RuntimeException.
	 * @return Score
	 */
	public ScoreType getWinner() {
		if(!scoreSet.isEmpty())
			return scoreSet.first();
		else
			throw new RuntimeException();
	}
	
	/**
	 * Devuelve una cadena con las puntuaciones de los jugadores.
	 * @return String Contenido del marcador.
	 */
	public String toString() {
		String scoreToString = "";
		
		for(Score<?> s : scoreSet) {
			scoreToString += "|" + s.toString();
		}
		scoreToString += "|";
		
		return scoreToString;
	}
}
