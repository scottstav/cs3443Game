package cs3443game;

import java.awt.Point;

public class Bullet extends Projectile{

	private String codeLine;
	public Bullet(String line, Point pos, String bullet, String explosion){
		super(pos, bullet);
		codeLine = line;
	}
	 public String getLine(){
	    	return codeLine;
	    }
}
