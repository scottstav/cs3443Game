package cs3443game;

import java.awt.Color;
import java.awt.Graphics;

public class Health {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public double health;
	public double hbScale;
	public double maxHealth;
	public int hbX, hbY, hbWidth, hbHeight;
	private Graphics g;
	
	public Health(int x, int y) {
		// potentially, use collidable to determine health bar type and location
		maxHealth = 100;
		health = 100;
		hbX = x;
		hbY = y;
		hbWidth = 350;
		hbHeight = 30;
		hbScale = 1;
	}

	
	/**
	 * health bar changes from red -> green -> yellow
	 * here we determine that color based on current health / maxhealth
	 * @param hbScale
	 * @return
	 */
	private Color determineColor() {
		
		if(hbScale > .55)
			return Color.green;
		else if(hbScale > .30)
			return Color.yellow;
		else
			return Color.red;
	}
	
	public int getHeight()
	{
		return this.hbHeight;
	}
	
	public double getWidth()
	{
		return (hbWidth * (hbScale));
	}
	
	public int getX()
	{
		return this.hbX;
	}
	
	public int getY()
	{
		return this.hbY;
	}
	
	public Color getColor()
	{
		return determineColor();
	
	}
	
	/**
	 * 
	 * @param hitSz - hits can deal variable damage
	 * @return 
	 */
	public void hit(int d)
	{
		// temporarily replacing with 99 so one hit kills for testing
		health -= 100;
		hbScale = health / maxHealth;
	}
}
