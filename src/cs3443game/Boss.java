package cs3443game;

import java.awt.Point;

public class Boss extends Enemy{
	private boolean inPosition;
	Point position;
	public Boss(String line, String ship, String explosion, Point pos){
		super(line,pos,ship,explosion);
		position=pos;
		inPosition=false;
	}
	public boolean inPosition(){
		return inPosition;
	}
	public boolean translate(){
		if(inPosition)
			return true;
		position.setLocation(position.getX()-1, position.getY());
		if(getX()==950){
			inPosition=true;
		}
		return false;


	}
	public Bullet fireCannon0(){
		return new Bullet("code", new Point(950,70+225));
	}

	public Bullet fireCannon1(){
		return new Bullet("code", new Point(950,70+110));
	}

	public Bullet fireCannon2(){
		return new Bullet("code li", new Point(950,70+340));
	}
	public boolean hasLine(){
		if (getLine().equals(""))
			return false;
		else return true;
	}
}
