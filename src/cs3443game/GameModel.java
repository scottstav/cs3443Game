package cs3443game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class GameModel {
    /**
     * Contains lines of code to be put on screen
     */
	private ArrayList<String> codeLineDB;
	
	/**
	 * Collection of lines that are currently being displayed.
	 * This is treated as a queue. As of now, the player must always
	 * target the first line that appears on the screen.  
	 * 
	 */
	private ArrayList<String> onScreenLines;
	
	/**
	 * Collection of the current (x,y) points of all lines on screen
	 * index of onScreenLines corresponds to index of this variable
	 */
	private ArrayList<Point> screenLinePointDB;
	
	/**
	 * Used to generate random starting points
	 */
	private Random random;
	
	/**
	 * put three arbitrary code lines into codeLineDB
	 */
	public GameModel(){
		codeLineDB = new ArrayList<String>();
		onScreenLines = new ArrayList<String>();
		screenLinePointDB = new ArrayList<Point>();
		codeLineDB.add("System.out.println();");
		codeLineDB.add("ArrayList<String>;");
		codeLineDB.add("int count;");
		random = new Random();
		
	}
	
	/**
	 * gets a code line from codeLineDB at specified index
	 * @param index code line to be returned
	 * @return code line
	 */
	public String getCodeLine(int index){
		return codeLineDB.get(index % codeLineDB.size());
	}
	
	/**
	 * gets a random Point
	 * @return an (x,y) point
	 */
	public Point getRandomPoint(){
		int y =random.nextInt();
		if(y<0)
			y=y*-1;
		y = y % 650;
		return new Point(700, y);
	}
	/**
	 * gets a screen line from onScreenLines using index i 
	 *Since onScreenLines is essentially a queue right now, 
	 *this is called using a hard 0 in the removeScreenLine function
	 *of this class.
	 * @param i index of line to be retrieved
	 * @return
	 */
	public String getScreenLine(int i){
		if(i>=0 && i<onScreenLines.size())
		return onScreenLines.get(i);
		
		return null;
	}
	
	/**
	 * returns a point from screenLinePointDB using index i
	 * @param i index of point to be retrieved
	 * @return Point at index i
	 */
	public Point getPointFromDB(int i){
		if(i>=0 && i<screenLinePointDB.size())
			return screenLinePointDB.get(i);
			
			return null;
	}
	/**
	 * translates screen lines to the left of the screen
	 * Might want to use the translate method of the Point class,
	 * but this works for now
	 */
	public void translateScreenLines(){
		double x;
		double y;
		for(int i=0; i<screenLinePointDB.size(); i++){
			x=  screenLinePointDB.get(i).getX();
			y=  screenLinePointDB.get(i).getY();
			x=x-10;
			screenLinePointDB.get(i).setLocation(x, y);
		}
	}
	/**
	 * puts a "random" line on the screen. 
	 * since there are only 3 lines in screenLinePointDB right now,
	 * use mod 3.
	 */
	public void newScreenLine(){
		int i =random.nextInt();
		if(i<0)
			i=i*-1;
		i = i % 3;
		onScreenLines.add(getCodeLine(i));
		screenLinePointDB.add(getRandomPoint());
	}
	/**
	 * since onScreenLines is treated as a queue, 
	 * we are always targeting the first line on the screen.
	 * therefore we remove next available line from the queue 
	 * after it has been correctly entered in the text box.
	 */
	public void removeScreenLine(){
		onScreenLines.remove(0);
		screenLinePointDB.remove(0);
	}
	
	
	
}
