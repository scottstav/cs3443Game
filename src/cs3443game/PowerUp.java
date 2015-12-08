package cs3443game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.Timer;
/**
 * power ups that help the player clear the screen of enemies
 * @author Paco
 *
 */
public class PowerUp implements Collidable{
	/**
	 * image of power up
	 */
	protected ImageIcon powerUpIcon;
	/**
	 * position of power up
	 */
	private Point position; 
	/**
	 * denotes whether power up is ready to leave screen
	 */
	private boolean isTrash;
	/**
	 * power up's buffered image
	 */
	protected BufferedImage bImage;
	/**
	 * height of power up
	 */
	private int height;
	/**
	 * width of power up
	 */
	private int width;
	/**
	 * bounds of power up
	 */
	private Rectangle bounds;
	/**
	 * current angle of rotation of power up
	 */
	private double angle;
	/**
	 * denotes whether power up has reached resting place
	 */
	public boolean inPosition;
	/**
	 * dictates power up rotation
	 */
	private Timer rotationTimer;
	/**
	 * direction of rotation
	 */
	private boolean reverse;

	/**
	 * creates a power up from a path to a power up image
	 * @param file
	 */
	public PowerUp(String file){
		powerUpIcon = new ImageIcon(file);
		bImage = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_RGB);
		height = powerUpIcon.getIconHeight();
		width = powerUpIcon.getIconWidth();
		position = new Point(-450,0);
		bounds = new Rectangle(getX(),getY(), getWidth(), getHeight());
		inPosition=false;
		angle=0d;
		reverse=false;
		rotationTimer  = new Timer(30, new ActionListener(){

			public void actionPerformed(ActionEvent e){
				PowerUp.this.rotate();				

			}
		});
	}
	/**
	 * returns the power up's image
	 * @return
	 */
	public Image getImage(){
		return powerUpIcon.getImage();
	}

	/**
	 * rotates the power up
	 */
	public void rotate(){
		double delta=.01d;
		if(angle>Math.PI/4)
			reverse=true; 
		if(angle<-1*Math.PI/4)
			reverse=false;
		if(reverse)
			angle = angle-delta;
		else
			angle = angle+delta;
		calculateBounds(angle);
		updateBufferedImage(angle);
	}
	/**
	 * repaints the power up to its buffered image for collision purposes
	 * @param angle
	 */
	public void updateBufferedImage(double angle){
		bImage = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bImage.createGraphics();
		AffineTransform oldXForm = g2d.getTransform();
		int tx = getX() + getWidth() / 2;
		int ty = getY() + getHeight() / 2;
		g2d.translate(tx, ty);
		g2d.rotate(angle);
		g2d.translate(tx * -1, ty * -1);
		powerUpIcon.paintIcon(null, g2d, getX(), getY());
		g2d.setTransform(oldXForm);
	}
	/**
	 * when the power up rotates, its bounds change. This method recalculates those bounds. 
	 * @param angle angle of rotation
	 */
	public void calculateBounds(double angle){
		int newWidth;
		int newHeight;
		int newX;
		int newY;
		newWidth = (int) ((powerUpIcon.getIconHeight()*Math.abs(Math.sin(angle))) + (powerUpIcon.getIconWidth() * Math.abs(Math.cos(angle))));
		newHeight = (int)((powerUpIcon.getIconWidth()*Math.abs(Math.sin(angle))) + (powerUpIcon.getIconHeight() * Math.abs(Math.cos(angle))));
		//System.out.println(angle);

		newX= (int) position.getX()+(powerUpIcon.getIconWidth()-newWidth)/2;
		newY=(int) position.getY()+(powerUpIcon.getIconHeight()-newHeight)/2;

		//System.out.println(newWidth);
		bounds.setBounds(newX,newY,newWidth,newHeight);

	}
	/**
	 * 
	 * @return true if boss has reached its resting position
	 */
	public boolean inPosition(){
		return inPosition;
	}

	/**
	 * translates the power up on screen
	 * @param dir direction of translation
	 */
	public void translate(int dir){

		if(dir == -1)
		{
			powerUpIcon = new ImageIcon("images/powerUpShip.png");
			inPosition = false;
		}
		if(inPosition && (dir == 1))
			return;

		position.setLocation(position.getX()+dir, position.getY());
		if((getX()==-50) && (dir == 1)){
			inPosition=true;
			beginRotation();
		}
		else if((getX() == -500) && (dir == -1))
		{
			inPosition = true;
		}


	}
	/**
	 * returns the power up's current rotation angle
	 * @return
	 */
	public double getAngle(){
		return angle;
	}

	/**
	 * after the power up reaches its resting place, this causes the power up
	 * to begin firing and rotating
	 */
	public void beginRotation(){
		powerUpIcon = new ImageIcon("images/powerUpShipFire.png");
		rotationTimer.start();

	}
	@Override
	/**
	 * gets the power ups height
	 * @return height in pixels
	 */
	public int getHeight() {

		return height;
	}

	@Override
	/**
	 * gets the power up's width
	 * @return width in pixels
	 */
	public int getWidth() {

		return width;
	}

	@Override
	/**
	 * gets the power up's x coordinate
	 * @return x coordinate
	 */
	public int getX() {

		return (int) position.getX();
	}

	@Override
	/**
	 * gets the power up's y coordinate
	 * @return y coordinate
	 */
	public int getY() {

		return (int) position.getY();
	}

	@Override
	/**
	 * gets RGB value of power up at specified pixel
	 * @param x x coordinate of specified pixel
	 * @param y y coordinate of specified pixel
	 * @return RGB value of pixel
	 */
	public int getRGB(int x, int y) {
		return bImage.getRGB(x, y);
	}

	@Override
	/**
	 * bounds of power up as a rectangle2D object
	 * @return bounds of power up
	 */
	public Rectangle2D getBounds() {

		return bounds.getBounds2D();
	}

	@Override
	/**
	 * unused, power ups are invincible
	 */
	public void collision() {
		// TODO Auto-generated method stub

	}
	/**
	 * 
	 * @return true if power up is rotated, false otherwise
	 */
	public boolean isRotated(){
		if(angle==0d)
			return false;
		else return true;
	}

}
