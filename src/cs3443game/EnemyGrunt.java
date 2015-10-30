package cs3443game;

import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.ImageIcon;



/**
 * This is meant for intermediate enemies. 
 * Instances of this class will eventually be 
 * initialized from an array list of code lines
 * of appropriate size for this class. Let's 
 * get some ideas going for other grunt attributes
 * to add to this class. 
 * @author Paco
 *
 */
public class EnemyGrunt extends Enemy {

	public EnemyGrunt(String line, Point pos){
		
		super(line, pos);
		enemyIcon = new ImageIcon("images/testship.png");
		
	}
	
}
