/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model;

import model.fighters.*;

public class FighterFactory {
	/**
	 * Crea subclases de Fighter.
	 * @param type Tipo de Fighter
	 * @param mother Ship a la que pertenece el Fighter
	 * @return A: AWing, Y: YWing, X: XWing, b: TIEBomber, f: TIEFighter, i: TIEInterceptor, null: cualquier otro caso.
	 */
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