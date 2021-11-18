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
			case "AWing" : return new AWing(mother);
			case "YWing": return new YWing(mother);
			case "XWing": return new XWing(mother);
			case "TIEBomber": return new TIEBomber(mother);
			case "TIEFighter": return new TIEFighter(mother);
			case "TIEInterceptor": return new TIEInterceptor(mother);
		}
		return null;
	}
}