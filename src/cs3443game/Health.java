package cs3443game;

import java.awt.Color;
import java.awt.Graphics;

public class Health {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int health, hbScale;
	int maxHealth;
	int hbX, hbY, hbWidth, hbHeight;
	Color hbColor;
	Graphics g;
	
	public Health(Collidable col) {
		// potentially, use collidable to determine health bar type and location
		maxHealth = 100;
		health = 100;
		hbX = 10;
		hbY = 10;
		hbWidth = 350;
		hbHeight = 30;
		hbColor = Color.green;
		hbScale = 1;
	}

	
	/**
	 * health bar changes from red -> green -> yellow
	 * here we determine that color based on current health / maxhealth
	 * @param hbScale
	 * @return
	 */
	private Color determineColor(float hbScale) {
		
		if(hbScale > .40)
			return Color.green;
		else if(hbScale > .20)
			return Color.yellow;
		else
			return Color.red;
	}
	
	public int getHeight()
	{
		return this.hbHeight;
	}
	
	public int getWidth()
	{
		return this.hbWidth;
	}
	
	public int getX()
	{
		return this.hbX;
	}
	
	public int getY()
	{
		return this.hbY;
	}
	
	/**
	 * 
	 * @param hitSz - hits can deal variable damage
	 * @return 
	 */
	public void hit(int damage)
	{
		
		health-=damage;
	    hbScale = health / maxHealth;
		hbColor = determineColor(hbScale);
	}
}
