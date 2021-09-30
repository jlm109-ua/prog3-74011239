/**
 * @author Juan Llinares Mauri - 74011239E
 */

package model;

public class Fighter {
	/**
	 * Declaramos todos los atributos privados.
	 */
	private String type;
	
	private int velocity;
	
	private int attack;
	
	private int shield;
	
	private int id;
	
	private static int nextId=1;
	
	private Coordinate position;
	
	private Ship motherShip;
	
	/**
	 * Declaramos todos los métodos públicos.
	 * @param type
	 * @param mother
	 */
	Fighter(String type, Ship mother) {
		this.velocity = 100;
		this.attack = 80;
		this.shield = 80;
		this.type = type;
		this.position = NULL;
		this.motherShip = mother;
	}
	
	public Fighter(Fighter f) {
		
	}
	
	public void static resetNextId() {
		this.nextId = 1;
	}

	public String getType() {
		return type;
	}

	public int getVelocity() {
		return velocity;
	}

	public int getAttack() {
		return attack;
	}

	public int getShield() {
		return shield;
	}

	public int getId() {
		return id;
	}

	public int getNextId() {
		return nextId;
	}
	
	public Side getSide() {
		return motherShip.side; 
	}
	
	public Coordinate getPosition() {
		return Coordinate;
	}
	
	public Ship getMotherShip() {
		return mother;
	}
	
	public void setPosition(Coordinate p) {
		
	}
	
	public void addAttack(int attack) {
		if(this.attack + attack >= 0)
			this.attack = this.attack + attack;
		else
			this.attack = 0;
	}
	
	public void addVelocity(int velocity) {
		if(this.velocity + velocity >= 0)
			this.velocity  = this.velocity + velocity;
		else
			this.velocity = 0;
	}

	public void addShield(int shield) {
		if(this.shield + shield >= 0)
			this.shield = this.shield + shield;
		else
			this.shield = 0;
	}

	public boolean isDestroyed() {
		if(shield <= 0) 
			return true;
		else 
			return false;
	}
	
	public int getDamage(int n,Fighter enemy) {
		int damage = (n*this.attack)/300;
		return damage;
	}
	
	public int fight(Fighter enemy) {
		
	}

	@Override
	public String toString() {
		return "(" + type + " " + id + " " + motherShip.side + "" + shield
				+ ", id=" + id + ", nextId=" + nextId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Fighter))
			return false;
		Fighter other = (Fighter) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
