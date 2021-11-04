package model.exceptions;

import model.Fighter;

public class FighterAlreadyInBoardException extends Exception{
	private Fighter f;
	
	public FighterAlreadyInBoardException(Fighter f){
		
	}
	
	public String getMessage() {
		
	}
}