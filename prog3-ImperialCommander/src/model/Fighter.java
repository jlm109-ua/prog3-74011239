/**
 * @author Juan Llinares Mauri - 74011239E
 */

package model;

public class Fighter {
	/**
	 * Declaramos todos los atributos privados.
	 * @author Juan Llinares Mauri - 74011239E
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
		this.position = null;
		this.motherShip = mother;
	}
	
	public Fighter(Fighter f) {
		this.velocity = f.getVelocity();
		this.attack = f.getAttack();
		this.shield = f.getShield();
		this.type = f.getType();
		this.position = f.getPosition();
		this.motherShip = f.getMotherShip();
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
		return motherShip.getSide(); 
	}
	
	public Coordinate getPosition() {
		return position;
	}
	
	public Ship getMotherShip() {
		return motherShip;
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
	
	/**
	 * 
	 * @param n
	 * @param enemy
	 * @return
	 */
	public int getDamage(int n,Fighter enemy) {
		int damage = (n*this.attack)/300;
		return damage;
	}
	
	public int fight(Fighter enemy) {
		do {
			int n ;
			RandomNumber rndm;
			setRandom(n,rndm);
			
			int threshold = 100*getVelocity()/(getVelocity()+enemy.getVelocity());
			
			if(threshold <= n) {
				enemy.addShield(-getDamage(n,this));
			}else {
				addShield(-getDamage(100-n,enemy));
			}
		}while(isDestroyed() == false && enemy.isDestroyed() == false);
		
		if(isDestroyed() == true) {
			return -1;
		}else {
			return 1;
		}	
	}

	public static void setRandom(int n,RandomNumber rndm) {
		n = rndm.newRandomNumber(100);
	}
	
	@Override
	public String toString() {
		if(getPosition() != null) {
			return "(" + type + " " + id + " " + motherShip.getSide() +	"[" + position.getX()
					+ "," + position.getY() + "] {" + velocity + "," + attack + 
					"," + shield + "})";
		}else {
			return "(" + type + " " + id + " " + motherShip.getSide() +	"null {" 
					+ velocity + "," + attack + "," + shield + "})";
		}
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
