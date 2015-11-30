package cs3443game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Earth extends BufferedImage implements Collidable {
	private ImageIcon imgEarth;
	public Health hbEarth;
	
	public Earth(){
		super(1280,720, BufferedImage.TYPE_INT_RGB);
		imgEarth = new ImageIcon("images/earth.png");
		imgEarth.paintIcon(null, this.createGraphics(), 0, 0);
		hbEarth = new Health(10, 10);
	}


public int getWidth(){
	return imgEarth.getIconWidth();
}

public int getHeight(){
	return imgEarth.getIconHeight();
}

public Image getImage(){
	return imgEarth.getImage();
}

public Rectangle2D getBounds(){
	Rectangle r = new Rectangle(0,0,getWidth(), getHeight());
    return r.getBounds2D();
}


@Override
public int getX() {
	return 0;
}

@Override
public int getY() {
	return 0;
}


@Override
public void collision() {
	//get type of object collision to determine damage amount
	//for now just call 10 for testing
	System.out.println("earth was struck, taking damage!");
	hbEarth.hit(10);
	
}



}
