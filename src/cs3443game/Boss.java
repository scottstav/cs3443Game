package cs3443game;

import java.awt.Point;
/**
 * A tougher enemy whose rate of instantiation depends on the
 * player's score. This enemy also has a health bar. 
 * @author Paco
 *
 */
public class Boss extends Enemy{
	/**
	 * determines whether the boss is ready to start shooting
	 */
	private boolean inPosition;
	/**
	 * the current position of the enemy
	 */
	Point position;
	/**
	 * the boss's health
	 */
	public Health bossHb;
	
	/**
	 * constructs the boss using a code line, images for the boss and its explosion, and a 
	 * starting position
	 * @param line the boss's initial code line
	 * @param ship path to the boss's image
	 * @param explosion path to the boss's explosion
	 * @param pos boss's initial position
	 */
	public Boss(String line, String ship, String explosion, Point pos){
		super(line,pos,ship,explosion);
		position=pos;
		bossHb = new Health(600, 10);
		inPosition=false;
	}
	
	/**
	 * 
	 * @return true if boss has reached its resting place on screen, false otherwise
	 */
	public boolean inPosition(){
		return inPosition;
	}
	
	/**
	 * translates the boss's position until the boss reaches a stopping point
	 * on screen. This function makes the boss move left or right only.
	 * @param dir the direction of translation
	 * @return true if boss has reached it's stopping point. False otherwise
	 */
	public boolean translate(int dir){
		if(inPosition)
			return true;
		position.setLocation(position.getX()+dir, position.getY());
		if(getX()==950){
			inPosition=true;
		}
		return false;


	}
	/**
	 * returns a Bullet that is fired from the boss's first cannon
	 * @param line the code line that the bullet will be associated with
	 * @return Bullet to be fired
	 */
	public Bullet fireCannon0(String line){
		return new Bullet(line, new Point(950,70+225));
	}

	/**
	 * returns a Bullet that is fired from the boss's second cannon
	 * @param line the code line that the bullet will be associated with
	 * @return Bullet to be fired
	 */
	public Bullet fireCannon1(String line){
		return new Bullet(line, new Point(950,70+110));
	}

	/**
	 * returns a Bullet that is fired from the boss's third cannon
	 * @param line the code line that the bullet will be associated with
	 * @return Bullet to be fired
	 */
	public Bullet fireCannon2(String line){
		return new Bullet(line, new Point(950,70+340));
	}
	
	/**
	 * boss doesn't always display a line that will cause it to take a hit. 
	 * this method returns true if the boss currently is displaying a line
	 * and false otherwise.
	 */
	public boolean hasLine(){
		if (getLine().equals(""))
			return false;
		else return true;
	}
	
	

}
