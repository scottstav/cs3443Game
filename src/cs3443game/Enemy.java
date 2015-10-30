package cs3443game;

import java.awt.Point;
import java.awt.Image;
import javax.swing.ImageIcon;



/**
 * Super class for enemies. Fairly basic at the moment, 
 * but the idea is to have the basic enemy traits here.
 * Small enemies and bosses will extend from this class,
 * and will perhaps have more fields of their own. 
 * The idea is to replace the onScreenLines ArrayList
 * with an ArrayList of this class, so that every kind of 
 * enemy can be added to that list through polymorphism. Meteors
 * should be able to extend this class as well.
 * @author Paco
 *
 */

public class Enemy{

	/**
	 * image icon of the enemy. kept with protected access modifier
	 * to hard code paths to images for now. 
	 */
	protected ImageIcon enemyIcon;
	private String codeLine;
	private Point position; 
	//possibly more instance variables later, although subclasses might have their own traits.
	
	//public enemy haha get it?
	
	public Enemy(String line, Point pos){
		codeLine = line;
		position= pos;
		
	}
	
	public int getX(){
		return (int) position.getX();
	}
	
	public int getY(){
		return (int) position.getY();
	}
	
	public Image getImage(){
		return enemyIcon.getImage();
	}
	
	/**
	 * translates the enemy by the specified amount for 
	 * x and for y. Parameter values are added, not subtracted
	 * from current coordinate point, so ensure to enter negative
	 * x offset for left screen movement. 
	 * @param x offset for x
	 * @param y offset for y
	 */
	public void translate(double x, double y){
		position.setLocation(position.getX()+x, position.getY()+y);
	}
	
	//public void paintComponent(Graphics g){
		//super.paintComponent(g);
		//g.drawString(codeLine, 200, 200);
		//g.drawImage(enemyIcon,(int) position.getX(), (int) position.getY(),null);
	
}
