package model;

import model.fighters.*;

public class FighterFactory {
	public static Fighter createFighter(String type,Ship mother) {
		switch(type) {
			case "AWing":
				Fighter A = new AWing(mother);
				return A;
			case "YWing":
				Fighter Y = new YWing(mother);
				return Y;
			case "XWing":
				Fighter X = new XWing(mother);
				return X;
			case "TIEBomber":
				Fighter b = new TIEBomber(mother);
				return b;
			case "TIEFighter":
				Fighter f = new TIEFighter(mother);
				return f;
			case "TIEInterceptor":
				Fighter i = new TIEInterceptor(mother);
				return i;
		}
		return null;
	}
}