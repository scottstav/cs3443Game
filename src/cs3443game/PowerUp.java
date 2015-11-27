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

public class PowerUp implements Collidable{

	protected ImageIcon powerUpIcon;
	private Point position; 
	private boolean isTrash;
	protected BufferedImage bImage;
	private int height;
	private int width;
	private Rectangle bounds;
	private double angle;
	private boolean inPosition;
	private Timer rotationTimer;
	private boolean reverse;


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

	public Image getImage(){
		return powerUpIcon.getImage();
	}
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
	public boolean inPosition(){
		return inPosition;
	}
	public void translate(){
		if(inPosition)
			return;
		position.setLocation(position.getX()+1, position.getY());
		if(getX()==-50){
			inPosition=true;
			beginRotation();
		}


	}
	public double getAngle(){
		return angle;
	}
	public void beginRotation(){
		powerUpIcon = new ImageIcon("images/powerUpShipFire.png");
		//width= powerUpIcon.getIconWidth();
		//height = powerUpIcon.getIconHeight();
		rotationTimer.start();

	}
	@Override
	public int getHeight() {
		//if(isRotated())
		//	return (int) bounds.getHeight();
		//else
		return height;
	}

	@Override
	public int getWidth() {
		//if(isRotated())
		//	return (int) bounds.getWidth();
		//	else
		return width;
	}

	@Override
	public int getX() {
		//if(isRotated())
		//	return (int) bounds.getX();
		//else
		return (int) position.getX();
	}

	@Override
	public int getY() {
		//if(isRotated())
		//	return (int) bounds.getY();
		//else
		return (int) position.getY();
	}

	@Override
	public int getRGB(int x, int y) {
		return bImage.getRGB(x, y);
	}

	@Override
	public Rectangle2D getBounds() {

		return bounds.getBounds2D();
	}

	@Override
	public void collision() {
		// TODO Auto-generated method stub

	}

	public boolean isRotated(){
		if(angle==0d)
			return false;
		else return true;
	}

}
