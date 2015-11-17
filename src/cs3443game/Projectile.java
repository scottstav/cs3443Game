package cs3443game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Projectile implements Collidable {

	/**
	 * image icon of the enemy. kept with protected access modifier
	 * to hard code paths to images for now. 
	 */
	protected ImageIcon projectileIcon;
	private Point position; 
	private boolean isTrash;
	protected BufferedImage bImage;
	//possibly more instance variables later, although subclasses might have their own traits.


	public Projectile(Point pos){
		isTrash=false;
		position= pos;
		projectileIcon=new ImageIcon("images/laser.png");
		bImage = new BufferedImage(1280,720,BufferedImage.TYPE_INT_RGB);

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

	public Rectangle2D getBounds(){
		Rectangle r = new Rectangle(getX(),getY(), getWidth(), getHeight());
		return r.getBounds2D();
	}

	public int getHeight(){
		return projectileIcon.getIconHeight();
	}

	public int getWidth(){
		return projectileIcon.getIconWidth();
	}

	public void paintToImage(){
		projectileIcon.paintIcon(null, bImage.createGraphics(), getX(), getY());
	}

	@Override
	public int getRGB(int x, int y) {
		return bImage.getRGB(x, y);
	}
}
