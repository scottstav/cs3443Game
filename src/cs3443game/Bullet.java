package cs3443game;

import java.awt.Point;

/**
 * class that is used to generate bullets shot by the game's boss
 * @author Paco
 *
 */
public class Bullet extends Enemy{
/**
 * creates boss bullet
 * @param line code line associated with the created bullet
 * @param pos position of the bullet
 */
	public Bullet(String line, Point pos){
		super(line, pos, "images/bossBullet.png", "");
		
	}
}
