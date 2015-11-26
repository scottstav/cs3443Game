package cs3443game;
import java.awt.geom.Rectangle2D;

public interface Collidable {
	  	
    public int getHeight();
    
    public int getWidth();
    
    public int getX();
	
	public int getY();
	
	public int getRGB(int x, int y);
	
	public Rectangle2D getBounds();
	
	public void collision();
}
