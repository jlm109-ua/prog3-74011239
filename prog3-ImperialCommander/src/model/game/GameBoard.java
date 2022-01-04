/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model.game;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Side;
import model.exceptions.InvalidSizeException;
/**
 * Clase destinada a ser la que controle el tablero.
 */
public class GameBoard extends Board{
	
	/**
	 * Constructor de GameBoard.
	 * @param size Tamanyo del tablero.
	 * @throws InvalidSizeException Excepcion que se lanza cuando el tamanyo del tablero no es correcto.
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
		
		for(Fighter f : board.values()) {
			if(f.getSide().equals(side))
				count++;
		}
		
		return count;
	}
	
	/**
	 * Devuelve una String del tablero con sus respectivos Fighter.
	 * @return String Cadena que muestra el tablero de forma gráfica.
	 */
	public String toString() { // REVISAR X = COLUMNA & Y = FILA
		String boardString = "  ";
		
		for(int i = 0;i < getSize();i++) { 
			boardString += i;
		}

		boardString += "\n" + "  "; 
		for(int i = 0;i < getSize();i++) {
			boardString += "-";
		}

		boardString += "\n";
		for(int i = 2;i < getSize()+2;i++) {
			if(i-2 < getSize()) {
				boardString += i-2 + "|";
				for(int j = 2;j < getSize()+2;j++) {
					if(board.get(new Coordinate(j-2,i-2)) != null)
						boardString += board.get(new Coordinate(j-2,i-2)).getSymbol();
					else
						boardString += " ";
				}
				if(i<getSize()+1)
					boardString += "\n";
			}
		}
		
		return boardString;
	}
}
