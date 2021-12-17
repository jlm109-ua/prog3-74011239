package model.game.score;

import java.util.SortedSet;
import java.util.TreeSet;

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
	 * @return Primer objeto de scoreSet
	 */
	public ScoreType getWinner() {
		if(!scoreSet.isEmpty())
			return scoreSet.first();
		else
			throw new RuntimeException();
	}
	
	/**
	 * Devuelve una cadena con las puntuaciones de los jugadores.
	 */
	public String toString() {
		return "| " +  + " | " + scoreSet.last() + " |"; //???????????
	}
}
