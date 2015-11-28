package cs3443game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Projectile implements Collidable {

	/**
	 * image icon of the enemy. kept with protected access modifier
	 * to hard code paths to images for now. 
	 */
	protected ImageIcon projectileIcon;
	private ImageIcon explosionIcon;
	private Point position; 
	private boolean isTrash;
	protected BufferedImage bImage;
	private int height;
	private int width;
	private Rectangle bounds;
	private double angle;
	//possibly more instance variables later, although subclasses might have their own traits.

	//sound effects
	private static String LASER = "soundeffects/laser.wav";
	private SoundEffects laserSound = new SoundEffects();

	public Projectile(Point pos, String laser){
		isTrash=false;
		position= pos;
		projectileIcon=new ImageIcon("images/laser.png");
		laserSound.playSound(LASER); //Laser sound effect
		bImage = new BufferedImage(1280,720,BufferedImage.TYPE_INT_RGB);
		height= projectileIcon.getIconHeight();
		width = projectileIcon.getIconWidth();
		bounds = new Rectangle(getX(),getY(), getWidth(), getHeight());
		angle=0d;
	}
	
	public boolean isRotated(){
		if(angle==0d)
			return false;
		else return true;
	}

	public int getX(){
		return (int) position.getX();
	}

	public int getY(){
		return (int) position.getY();
	}

	public void collision(){
		isTrash=true;
	}

	public boolean isTrash()
	{
		return isTrash;
	}

	public Image getImage(){

		return projectileIcon.getImage();
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

	public void calculateBounds(double angle){
		int newWidth;
		int newHeight;
		int newX;
		int newY;
		newWidth = (int) ((height*Math.abs(Math.sin(angle))) + (width * Math.abs(Math.cos(angle))));
		newHeight = (int)((width*Math.abs(Math.sin(angle))) + (height * Math.abs(Math.cos(angle))));
		//System.out.println(angle);
		
		newX= getX()+(width-newWidth)/2;
		newY=getY()+(height-newHeight)/2;
		
		//System.out.println(newWidth);
		bounds.setBounds(newX,newY,newWidth,newHeight);
		

	}
	public Rectangle2D getBounds(){
		return bounds.getBounds2D();
	}

	public int getHeight(){
		return height;
	}

	public int getWidth(){
		return width;
	}

	public void paintToImage(){
		projectileIcon.paintIcon(null, bImage.createGraphics(), getX(), getY());
	}

	public void updateBufferedImage(double angle){
		bImage = new BufferedImage(1280,720,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bImage.createGraphics();
		AffineTransform oldXForm = g2d.getTransform();
		int tx = getX() + getWidth() / 2;
		int ty = getY() + getHeight() / 2;
		g2d.translate(tx, ty);
		g2d.rotate(angle);
		g2d.translate(tx * -1, ty * -1);
		projectileIcon.paintIcon(null, g2d, getX(), getY());
		g2d.setTransform(oldXForm);
	}

	@Override
	public int getRGB(int x, int y) {
		return bImage.getRGB(x, y);
	}
}
