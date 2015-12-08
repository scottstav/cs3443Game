package cs3443game;

import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.ImageIcon;



/**
 *Grunt enemy class. used to instantiate small enemies.
 * @author Paco
 *
 */
public class EnemyGrunt extends Enemy {

	public EnemyGrunt(String line, Point pos){
		
		
		super(line, pos, "images/blueShip.png", "images/smallExplosion.png");
		
	}
	
}
