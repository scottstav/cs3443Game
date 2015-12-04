package cs3443game;

import java.awt.Point;

public class Boss extends Enemy{
	
	private boolean inPosition;
	Point position;
	public Health bossHb;
	
	public Boss(String line, String ship, String explosion, Point pos){
		super(line,pos,ship,explosion);
		position=pos;
		bossHb = new Health(600, 10);
		inPosition=false;
	}
	public boolean inPosition(){
		return inPosition;
	}
	
	public boolean translate(int dir){
		if(inPosition)
			return true;
		position.setLocation(position.getX()+dir, position.getY());
		if(getX()==950){
			inPosition=true;
		}
		return false;


	}
	public Bullet fireCannon0(String line){
		return new Bullet(line, new Point(950,70+225));
	}

	public Bullet fireCannon1(String line){
		return new Bullet(line, new Point(950,70+110));
	}

	public Bullet fireCannon2(String line){
		return new Bullet(line, new Point(950,70+340));
	}
	
	public boolean hasLine(){
		if (getLine().equals(""))
			return false;
		else return true;
	}
	
	

}
