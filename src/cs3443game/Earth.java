package cs3443game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
/**
 * Class used to instantiate an earth object. 
 * this is the main object to defend in the game. 
 * Enemies collide with this object, causing the earth's 
 * health bar to go down. 
 * @author Paco
 *
 */
public class Earth extends BufferedImage implements Collidable {
	private ImageIcon imgEarth;
	public Health hbEarth;
	
	/**
	 * constructs earth using a BufferedImage for collision detection.
	 * Also constructs earth's health bar. 
	 */
	public Earth(){
		super(1280,720, BufferedImage.TYPE_INT_RGB);
		imgEarth = new ImageIcon("images/earth.png");
		imgEarth.paintIcon(null, this.createGraphics(), 0, 0);
		hbEarth = new Health(10, 10);
	}

/**
 * returns the width of earth in pixels
 */
public int getWidth(){
	return imgEarth.getIconWidth();
}
/**
 * returns the height of earth in pixels
 */
public int getHeight(){
	return imgEarth.getIconHeight();
}
/**
 * returns earth's image for painting onto the screen
 * @return earth's image
 */
public Image getImage(){
	return imgEarth.getImage();
}

/**
 * returns the current bounds of earth in the form of a rectangle. 
 * This rectangle is just big enough to contain earth inside it. 
 * this is used for collision detection. 
 */
public Rectangle2D getBounds(){
	Rectangle r = new Rectangle(0,0,getWidth(), getHeight());
    return r.getBounds2D();
}


@Override
/**
 * returns the earth's x coordinate
 */
public int getX() {
	return 0;
}

@Override
/**
 * returns the earth's y coordinate
 */
public int getY() {
	return 0;
}


@Override

/**
 * when a collision with earth occurs, this method 
 * is called to lower earth's health
 */
public void collision() {
	//get type of object collision to determine damage amount
	//for now just call 10 for testing
	System.out.println("earth was struck, taking damage!");
	hbEarth.hit(10);
	
}



}
