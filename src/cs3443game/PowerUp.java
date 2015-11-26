package cs3443game;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

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


	public PowerUp(String file){
		powerUpIcon = new ImageIcon(file);
		bImage = new BufferedImage(1280,720,BufferedImage.TYPE_INT_RGB);
		height = powerUpIcon.getIconHeight();
		width = powerUpIcon.getIconWidth();
		position = new Point(0,0);
		bounds = new Rectangle(getX(),getY(), getWidth(), getHeight());
		inPosition=false;
		angle=0d;
	}
	public void rotate(){
		double delta=.01d;
		if(angle>Math.PI/4 || angle< (-1*Math.PI)/4)
			delta=delta*-1;
		angle = angle+delta;
		calculateBounds(angle);
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
	public void translate(){
		if(!inPosition){
			position.setLocation(position.getX()+1, position.getY());
			if(getX()==150)
				inPosition=true;
		}
	}
	@Override
	public int getHeight() {
		if(isRotated())
			return (int) bounds.getHeight();
		else
			return height;
	}

	@Override
	public int getWidth() {
		if(isRotated())
			return (int) bounds.getWidth();
		else
			return width;
	}

	@Override
	public int getX() {
		if(isRotated())
			return (int) bounds.getX();
		else
			return (int) position.getX();
	}

	@Override
	public int getY() {
		if(isRotated())
			return (int) bounds.getY();
		else
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
