/**
 * @author Juan Llinares Mauri - 74011239E
 */
package model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class FighterFactory {
	/**
	 * Crea subclases de Fighter.
	 * @param type Tipo de Fighter
	 * @param mother Ship a la que pertenece el Fighter
	 * @return f: Fighter creado a partir del parametro type, null: Si no encuentra Fighter con nombre de type o ha ocurrido algun otro error en la creacion del Fighter.
	 */
	public static Fighter createFighter(String type,Ship mother) {
		Objects.requireNonNull(type);
		Objects.requireNonNull(mother);
		try {
			Class<?> newFighter = Class.forName("model.fighters." + type);
			Constructor c = newFighter.getDeclaredConstructor(Ship.class);
			Fighter f = (Fighter) c.newInstance(mother);
			return f;
		} catch (NoClassDefFoundError | ClassNotFoundException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
		}
		
		return null;
	}
}