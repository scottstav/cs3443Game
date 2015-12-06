package cs3443game;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import cs3443game.Collidable;



/**
 * Super class for enemies. Contains the basic enemy traits.
 * Small enemies and bosses will extend from this class,
 * and will perhaps have more fields of their own. 
 * @author Paco
 *
 */


public class Enemy implements Collidable{

	/**
	 * image icon of the enemy. 
	 */
	protected ImageIcon enemyIcon;
	/**
	 * explosion icon for enemy
	 */
	protected ImageIcon explosionIcon;
	/**
	 * enemy's code line
	 */
	private String codeLine;
	/**
	 * enemy's position
	 */
	private Point position; 
	/**
	 * denotes whether the enemy is ready to be cleaned up
	 */
	private boolean isTrash;
	/**
	 * denotes how long the enemy's explosion will last
	 */
	private Timer explosionTimer;
	/**
	 * denotes whether the enemy is currently exploding or not
	 */
	private boolean exploded;
	/**
	 * enemy's buffered image used for collision detection
	 */
	protected BufferedImage bImage;
	

	
	//sound effects
	private static String EXPLOSION = "soundeffects/ship_explosion.wav";
	private SoundEffects explosion = new SoundEffects();
	
	
	/**
	 * instantiates an enemy
	 * @param line enemy's code line
	 * @param pos enemy's position
	 * @param ship enemy's image path
	 * @param explosion enemy's explosion image path
	 */
	
	public Enemy(String line, Point pos, String ship, String explosion){
		codeLine = line;
		position= pos;
		isTrash=false;
		exploded=false;
		
		explosionIcon = new ImageIcon(explosion);
		bImage = new BufferedImage(1280,720,BufferedImage.TYPE_INT_RGB);
		enemyIcon = new ImageIcon(ship);
		
		
		paintToImage();
		
		explosionTimer= new Timer(2000, new ActionListener(){

			public void actionPerformed(ActionEvent e){
				Enemy.this.isTrash=true;
			}
		});
		
	}
	
	/**
	 * returns the RGB value of the enemy's specified pixel
	 */
	public int getRGB(int x, int y){
		return bImage.getRGB(x,y);
	}
	
	/**
	 * paints the enemy's image to the buffered image
	 */
	public void paintToImage(){
		enemyIcon.paintIcon(null, bImage.createGraphics(), getX(), getY());
	}
	
	/**
	 * returns the enemy's x coordinate
	 */
	public int getX(){
		return (int) position.getX();
		
	}
	/**
	 * returns the enemy's y coordinate
	 */
	public int getY(){
		return (int) position.getY();
	}
	
	/**
	 * processes a collision for the enemy (makes it explode)
	 */
	public void collision(){
		
		enemyIcon = explosionIcon;
	    startExplosion();
	}
	
	/**
	 * 
	 * @return true if enemy is ready to be cleaned up, false otherwise
	 */
	public boolean isTrash()
	{
		return isTrash;
	}
	
	/**
	 * gets the enemy's image
	 * @return enemy's image
	 */
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
		if(!exploded)
			position.setLocation(position.getX()+x, position.getY()+y);
	}
	
	/**
	 * returns the bounds of the enemy's image as a rectangle object
	 */
    public Rectangle2D getBounds(){
    	Rectangle r = new Rectangle(getX(),getY(), getWidth()*2, getHeight()*2);
        return r.getBounds2D();
    }
    
    /**
     * returns the enemy's height in pixels
     */
    public int getHeight(){
    	return enemyIcon.getIconHeight();
    }
    
    /**
     * returns the enemy's width in pixels
     */
    public int getWidth(){
    	return enemyIcon.getIconWidth();
    }
    
    /**
     * paints enemy to its buffered image at a specific point
     * @param x x coordinate to begin painting at
     * @param y y coordinate to begin painting at
     * @param g graphics object used to paint to buffered image
     */
    public void bufferedImagePaint(int x, int y, Graphics2D g){
    	enemyIcon.paintIcon(null, g, x, y);
    	
    }
    
    /**
     * gets the enemy's code line
     * @return enemy's code line
     */
    public String getLine(){
    	return codeLine;
    }
    /**
     * makes the enemy explode
     */
    public void startExplosion(){
    	explosion.playSound(EXPLOSION);
    	explosionTimer.start();
    	exploded=true;
    	codeLine="";
    }
    /**
     * tells whether the enemy has exploded
     * @return true if exploded, false otherwise
     */
    public boolean exploded(){
    	return exploded;
    }
    /**
     * sets a code line for the enemy
     * @param s
     */
    public void setLine(String s){
		codeLine = s;
	}
}
