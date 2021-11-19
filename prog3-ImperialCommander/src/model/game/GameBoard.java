package model.game;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Side;
import model.exceptions.InvalidSizeException;

public class GameBoard extends Board{
	
	/**
	 * Constructor de GameBoard.
	 * @param size Tamanyo del tablero.
	 * @throws InvalidSizeException
	 */
	public GameBoard(int size) throws InvalidSizeException {
		super(size);
	}
	 /**
	  * Devuelve el numero de Fighters de un bando en el tablero.
	  * @param side Bando del que queremos saber los Fighter.
	  * @return count Numero de Fighters.
	  */
	public int numFighters(Side side) {
		int count=0;
		
		for(Fighter f : board) { // CÓMO LO HAGO?
			if(f.getSide().equals(side))
				count++;
		}
		return count;
	}
	
	/**
	 * Devuelve una String del tablero con sus respectivos Fighter.
	 */
	public String toString() { // REVISAR
		String boardString = "  ";
		
		for(int i = 0;i < getSize();i++) { 
			boardString += i;
		}
		//"  0123456789"
		boardString += "\n" + "  "; 
		/*"  0123456789"
		"  "*/
		for(int i = 0;i < getSize();i++) {
			boardString += "-";
		}
		/*
		"  0123456789"
		"  ----------"
		*/
		boardString += "\n";
		for(int i = 0;i < getSize()+2;i++) { // Horizontal
			if(i < getSize()) {
				boardString += i + "|";
				for(int j = 0;j < getSize();i++) { // Vertical
					if(board.get(new Coordinate(i,j)) != null)
						boardString += board.get(new Coordinate(i,j)).getSymbol();
					else
						boardString += " ";
				}
				boardString += "\n";
			}
		}
		return boardString;
	}
}
