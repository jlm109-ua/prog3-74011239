/**
 * @author Juan Llinares Mauri - 74011239E
 */

package model;

import java.util.Objects;
import model.exceptions.*;

public abstract class Fighter {
	/**
	 * Declaramos todos los atributos privados.
	 * @author Juan Llinares Mauri - 74011239E
	 */
	private int velocity;
	private int attack;
	private int shield;
	private int id;
	private static int nextId=1;
	private Coordinate position;
	private Ship motherShip;
	
	/**
	 * Constructor de Fighter.
	 * @param type Tipo de Fighter.
	 * @param mother Nave (Ship) a la que pertenece el Fighter.
	 */
	protected Fighter(Ship mother) {
		Objects.requireNonNull(mother);
		this.velocity = 100;
		this.attack = 80;
		this.shield = 80;
		this.position = null;
		this.motherShip = mother;
		this.id=nextId;
		nextId++;
	}
	
	/**
	 * Constructor de copia de Fighter.
	 * @param f Fighter
	 */
	protected Fighter(Fighter f) {
		Objects.requireNonNull(f);
		this.velocity = f.getVelocity();
		this.attack = f.getAttack();
		this.shield = f.getShield();
		this.position = f.getPosition();
		this.motherShip = f.getMotherShip();
		this.id=f.getId();
	}
	
	/**
	 * Resetea el nextId a 1.
	 */
	public static void resetNextId() {
		nextId = 1;
	}
	
	/**
	 * Getter de type.
	 * @return type
	 */
	public String getType() {
		return getClass().getSimpleName();
	}

	/**
	 * Getter de velocity.
	 * @return velocity
	 */
	public int getVelocity() {
		return velocity;
	}

	/**
	 * Getter de attack.
	 * @return attack
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * Getter de shield.
	 * @return shield
	 */
	public int getShield() {
		return shield;
	}
	
	/**
	 * Getter de id.
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Getter de nextId.
	 * @return nextId
	 */
	public int getNextId() {
		return nextId;
	}
	
	/**
	 * Getter de side.
	 * @return side
	 */
	public Side getSide() {
		return motherShip.getSide(); 
	}
	
	/**
	 * Getter de position.
	 * @return position
	 */
	public Coordinate getPosition() {
		return position;
	}
	
	/**
	 * Getter de motherShip.
	 * @return motherShip
	 */
	public Ship getMotherShip() {
		return motherShip;
	}
	
	 /**
	  * Devuelve el simbolo del Fighter dependiendo de su tipo.
	  * @return
	  */
	public abstract char getSymbol();
	
	 /**
	  * 
	  * @return
	  */
	public abstract Fighter copy();
	
	/**
	 * Setter de position.
	 * @param p Coordenada nueva.
	 */
	public void setPosition(Coordinate p) {
		this.position = p;
	}
	
	/**
	 * Anyade puntos de ataque al Fighter.
	 * @param attack Puntos de ataque a anyadir.
	 */
	public void addAttack(int attack) {
		if(this.attack + attack >= 0)
			this.attack = this.attack + attack;
		else
			this.attack = 0;
	}
	
	/**
	 * Anyade puntos de velocidad al Fighter.
	 * @param velocity Puntos de velocidad a anyadir.
	 */
	public void addVelocity(int velocity) {
		if(this.velocity + velocity >= 0)
			this.velocity  = this.velocity + velocity;
		else
			this.velocity = 0;
	}

	/**
	 * Anyade puntos de escudo al Fighter.
	 * @param shield Puntos de escudo a anyadir.
	 */
	public void addShield(int shield) {
			this.shield = this.shield + shield;
	}

	/**
	 * Comprueba si el caza esta destruido.
	 * @return true: Si lo esta. false: En cualquier otro caso.
	 */
	public boolean isDestroyed() {
		if(shield <= 0) 
			return true;
		else 
			return false;
	}
	
	/**
	 * Calcula el danyo que hace un Fighter.
	 * @param n Numero aleatorio.
	 * @param enemy Fighter enemigo.
	 * @return damage Danyo hecho.
	 */
	public int getDamage(int n,Fighter enemy) {
		Objects.requireNonNull(n);
		Objects.requireNonNull(enemy);
		int damage = (n*this.attack)/300;
		
		return damage;
	}
	
	/**
	 * Simula la lucha entre dos Fighters.
	 * @param enemy Fighter enemigo.
	 * @return -1: Si el caza amigo ha sido destruido. 1: Si el caza enemigo ha sido destruido.
	 */
	public int fight(Fighter enemy) throws FighterIsDestroyedException { 
		Objects.requireNonNull(enemy);
		if(this.isDestroyed()){
			throw new FighterIsDestroyedException(this);
		}else if(enemy.isDestroyed()) {
			throw new FighterIsDestroyedException(enemy);
		}else {
			do {
				int n = RandomNumber.newRandomNumber(100);
				
				int threshold = 100*getVelocity()/(getVelocity()+enemy.getVelocity());
				
				if(threshold <= n) {
					enemy.addShield(-getDamage(n,this));
				}else {
					addShield(-enemy.getDamage(100-n,enemy));
				}
			}while(isDestroyed() == false && enemy.isDestroyed() == false);
			
			if(isDestroyed() == true) {
				return -1;
			}else {
				return 1;
			}
		}
	}
	
	@Override
	public String toString() {
		if(getPosition() != null) {
			return "(" + getType() + " " + id + " " + motherShip.getSide() +	" [" + position.getX()
					+ "," + position.getY() + "] {" + velocity + "," + attack + 
					"," + shield + "})";
		}else {
			return "(" + getType() + " " + id + " " + motherShip.getSide() +	" null {" 
					+ velocity + "," + attack + "," + shield + "})";
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fighter other = (Fighter) obj;
		
		return id == other.id;
	}
}
