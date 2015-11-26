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

public class Enemy implements Collidable{

	/**
	 * image icon of the enemy. kept with protected access modifier
	 * to hard code paths to images for now. 
	 */
	protected ImageIcon enemyIcon;
	private String codeLine;
	private Point position; 
	private boolean isTrash;
	private Timer explosionTimer;
	private boolean exploded;
	protected BufferedImage bImage;
	//possibly more instance variables later, although subclasses might have their own traits.
	
	//public enemy haha get it?
	
	public Enemy(String line, Point pos, String ship, String explosion){
		codeLine = line;
		position= pos;
		isTrash=false;
		exploded=false;
		bImage = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_RGB);
		enemyIcon = new ImageIcon("images/blueShip.png");
		paintToImage();
		
		explosionTimer= new Timer(2000, new ActionListener(){

			public void actionPerformed(ActionEvent e){
				Enemy.this.isTrash=true;
			}
		});
		
	}
	public int getRGB(int x, int y){
		return bImage.getRGB(x,y);
	}
	public void paintToImage(){
		enemyIcon.paintIcon(null, bImage.createGraphics(), getX(), getY());
	}
	
	public int getX(){
		return (int) position.getX();
		
	}
	
	public int getY(){
		return (int) position.getY();
	}
	
	public void  collision(){
		enemyIcon= new ImageIcon("images/smallExplosion.png");
	    startExplosion();
	}
	
	
	public boolean isTrash()
	{
		return isTrash;
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
		if(!exploded)
		position.setLocation(position.getX()+x, position.getY()+y);
	}
	
    public Rectangle2D getBounds(){
    	Rectangle r = new Rectangle(getX(),getY(), getWidth()*2, getHeight()*2);
        return r.getBounds2D();
    }
    
    public int getHeight(){
    	return enemyIcon.getIconHeight();
    }
    
    public int getWidth(){
    	return enemyIcon.getIconWidth();
    }
    
    public void bufferedImagePaint(int x, int y, Graphics2D g){
    	enemyIcon.paintIcon(null, g, x, y);
    	
    }
    
    
    public String getLine(){
    	return codeLine;
    }
    
    public void startExplosion(){
    	explosionTimer.start();
    	exploded=true;
    	codeLine="";
    }
    public boolean exploded(){
    	return exploded;
    }
	
	//public void paintComponent(Graphics g){
		//super.paintComponent(g);
		//g.drawString(codeLine, 200, 200);
		//g.drawImage(enemyIcon,(int) position.getX(), (int) position.getY(),null);
	
}
