package cs3443game;
import java.awt.geom.Rectangle2D;
/**
 * contains all the methods required to calculate a collision
 * @author Paco
 *
 */
public interface Collidable {
	/**
	 * gets the height of the collidable object
	 * @return height in pixels
	 */
	public int getHeight();
	/**
	 * gets the width of the collidable object
	 * @return width in pixels
	 */
	public int getWidth();
	/**
	 * gets the x coordinate of the collidable object
	 * @return x coordinate
	 */
	public int getX();
	/**
	 * gets the y coordinate of the collidable object
	 * @return y coordinate
	 */
	public int getY();
	/**
	 * gets the RGB value of the collidable object's specified pixel
	 * @param x x coordinate of specified pixel
	 * @param y y coordinate of specified pixel
	 * @return RGB value of pixel
	 */
	public int getRGB(int x, int y);
	/**
	 * gets the bounds of the collidable object
	 * @return
	 */
	public Rectangle2D getBounds();
	/**
	 * processes a collision
	 */
	public void collision();
}
