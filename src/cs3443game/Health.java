package cs3443game;

import java.awt.Color;
import java.awt.Graphics;

public class Health {

	int health;
	int maxHealth;
	int hbX, hbY, hbWidth, hbHeight;
	Color hbColor;
	
	public Health(Collidable mortal) {
		mortal.;
		
	}

	/**
	 * redraws the health bar 
	 * @param g
	 */
	public void draw(Graphics g)
	{
	    float hbScale = health / maxHealth;
	    hbColor = determineColor(hbScale);
	    g.setColor(hbColor);
	   // g.fillRect(healthBarX, healthBarY, healthBarWidth * healthScale, healthBarHeight);
	}

	/**
	 * health bar changes from red -> green -> yellow
	 * here we determine that color based on ccurrent health / maxhealth
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
}
