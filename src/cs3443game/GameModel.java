package cs3443game;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameModel {
	/**
	 * Contains lines of code to be put on screen
	 */
	private ArrayList<String> codeLineDB;


	/**
	 * Scanner to be used to populate codeLineDB
	 */
	private Scanner input;

	private ArrayList<Enemy> onScreenEnemies;
	private ArrayList<Projectile>onScreenProjectiles;
	private ArrayList<PowerUp>onScreenPowerUps;
	/**
	 * Collection of lines that are currently being displayed.
	 * This is treated as a queue. As of now, the player must always
	 * target the first line that appears on the screen.  
	 * 
	 * 
	 */
	//private ArrayList<String> onScreenLines;


	/**
	 * Collection of the current (x,y) points of all lines on screen
	 * index of onScreenLines corresponds to index of this variable
	 */
	//private ArrayList<Point> screenLinePointDB;

	/**
	 * Used to generate random starting points
	 * Used to generate random array index
	 */
	private Random random;
	Earth earth;
	

	/**
	 * put three arbitrary code lines into codeLineDB
	 */
	public GameModel(){
		earth = new Earth();
		random = new Random();
		codeLineDB = new ArrayList<String>();
		onScreenEnemies = new ArrayList<Enemy>();
		onScreenProjectiles = new ArrayList<Projectile>();
		
		onScreenPowerUps = new ArrayList<PowerUp>();

		try {
			input = new Scanner(new File("input"));
		} catch (Exception FileNotFoundException) {
			System.err.println("failed to open data.txt");
			System.exit(1);
		}
		String line = null;

		while (input.hasNext()) {
			line = input.nextLine();
			codeLineDB.add(line);	
		}	
		input.close();
	}

	/**
	 * gets a random code line from codeLineDB
	 * 
	 * @return code line
	 */
	public String getCodeLine(){
		int index = random.nextInt(codeLineDB.size());
		return codeLineDB.get(index);
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
		return new Point(1280, y);
	}
	public Point getRandomProjoPoint(){
		int y =random.nextInt();
		if(y<0)
			y=y*-1;
		y = y % 650;
		return new Point(-50, y);
	}

	/**
	 * translates screen lines to the left of the screen
	 * Might want to use the translate method of the Point class,
	 * but this works for now
	 */
	public void translate(){
		Enemy e;
		Projectile p;
        PowerUp u;
		for(int i=0; i<onScreenEnemies.size(); i++){
			e=onScreenEnemies.get(i);
			e.translate(-1, 0);
			e.paintToImage();
		}

		for(int i=0; i<onScreenProjectiles.size(); i++){
			p=onScreenProjectiles.get(i);
			p.translate(1, 0);
			p.paintToImage();

		}
		
		for(int i=0; i<onScreenPowerUps.size(); i++){
			u=onScreenPowerUps.get(i);
			u.translate();
			//u.paintToImage();

		}

		//check for collisions after this translation
		collisions();
		cleanUp();
	}

	/**
	 * creates grunt enemy. will be loaded with different
	 * code lines instead of just int a later on
	 */
	public void createGrunt(){
		onScreenEnemies.add( new EnemyGrunt(getCodeLine(), getRandomPoint()));
	}
	public void createProjo(){
		onScreenProjectiles.add(new Projectile(getRandomProjoPoint()));
	}
	public void createPowerUp(){
		onScreenPowerUps.add(new PowerUp("images/powerUpShip.png"));
	}

	/**
	 * returns a screen enemy at the specified position
	 * @param i index of enemy to return
	 * @return enemy at specified position, null if invalid value was given
	 */
	public Enemy getScreenEnemy(int i){
		if(i>=0 && i<onScreenEnemies.size())
			return onScreenEnemies.get(i);

		return null;
	}

	public Projectile getScreenProjo(int i){
		if(i>=0 && i<onScreenProjectiles.size())
			return onScreenProjectiles.get(i);

		return null;
	}
	
	public PowerUp getScreenPowerUp(int i){
		if(i>=0 && i<onScreenPowerUps.size())
			return onScreenPowerUps.get(i);

		return null;
	}


	public boolean process(String s){
		Enemy enemy;
		
		if(s.equals("power up")){
			createPowerUp();
			return true;
		}
		
		for(int i=0; i<onScreenEnemies.size(); i++){
			enemy=onScreenEnemies.get(i);
			System.out.println(enemy.getLine());
			if(enemy!=null){
				if(enemy.getLine().equals(s)){
					enemy.collision();
					return true;
				}
			}
		}
		return false;
	}



	public int getProjoSize(){
		return onScreenProjectiles.size();
	}

	public int getEnemySize(){
		return onScreenEnemies.size();
	}

	public void collisions(){

		Enemy enemy;
		Projectile projo;
		PowerUp pUp;

		for(int i=0; i<getEnemySize(); i++){
			enemy = getScreenEnemy(i);
			if(enemy.exploded())
				continue;
			if(collided(earth,enemy))
				continue;
			
			for(int h=0; h<onScreenPowerUps.size(); h++){
				pUp=onScreenPowerUps.get(h);
				if(collided(pUp,enemy))
					continue;
			}
			for(int j=0; j<getProjoSize(); j++){
				projo = getScreenProjo(j);
				if(projo.isTrash())
					continue;
				if(collided(projo,enemy))
					break;
			}
		}
		
	}

	
	public boolean collided(Collidable a, Collidable b){

		if(a.getBounds().intersects(b.getBounds())){
			System.out.println("intersection");
			if(pixelPerfectCollision(a, b)){
				a.collision();
				b.collision();
				return true;
			}
		}
		return false;

	}
	
	public boolean pixelPerfectCollision(Collidable collidableA, Collidable collidableB){

		//int xStart= Math.max(collidableA.getX(), collidableB.getX());
		//int xEnd= Math.min(collidableA.getX() + collidableA.getWidth(), collidableB.getX() + collidableB.getWidth());
		//int yStart= Math.max(collidableA.getY(), collidableB.getY());
		//int yEnd= Math.min(collidableA.getY() + collidableA.getHeight(), collidableB.getY() + collidableB.getHeight());
		//if(collidableA instanceof Projectile){
		int xStart=(int) Math.max(collidableA.getBounds().getX(), collidableB.getX());
		int xEnd= (int)(Math.min(collidableA.getBounds().getX() + collidableA.getBounds().getWidth(),
				collidableB.getBounds().getX() + collidableB.getBounds().getWidth()));
		int yStart= (int)( Math.max(collidableA.getBounds().getY(), collidableB.getBounds().getY()));
		int yEnd= (int)(Math.min(collidableA.getBounds().getY() + collidableA.getBounds().getHeight(),
				collidableB.getBounds().getY() + collidableB.getBounds().getHeight()));
	//	}
		for(int i=xStart; i<xEnd; i++){
			for(int j=yStart; j<yEnd; j++){
				if(i>1280 || j>720)
					continue;
				if(collidableA.getRGB(i,j)!= 0xff000000 && collidableB.getRGB(i, j)!= 0xff000000 )
					return true;
			}
		}
		return false;
	}

	public void cleanUp(){
		for(int i=0; i<onScreenEnemies.size(); i++){
			if(onScreenEnemies.get(i).isTrash()){
				onScreenEnemies.remove(i);
			}
		}

		for(int i=0; i<onScreenProjectiles.size(); i++){
			if(onScreenProjectiles.get(i).isTrash()){
				onScreenProjectiles.remove(i);
			}
		}
	}
}
