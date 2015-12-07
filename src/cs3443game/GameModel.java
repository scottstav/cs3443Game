  package cs3443game;

import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.Timer;

public class GameModel {
	/**
	 * Contains lines of code to be put on screen
	 */
	private ArrayList<String> codeLineDB;
	private ArrayList<String> shortLineDB;


	/**
	 * Scanner to be used to populate codeLineDB
	 */
	private Scanner input;

	private ArrayList<Enemy> onScreenEnemies;
	private ArrayList<Projectile>onScreenProjectiles;
	private ArrayList<PowerUp>onScreenPowerUps;
	private ArrayList<Bullet>bossProjectiles;
	public Boss boss;
	private Timer bossFireTimer;
	private Timer bossChangeLine;
	boolean bossOnScreen;
	private Integer points;
	public boolean pIncoming;
	/**
	 * Collection of lines that are currently being displayed.
	 * This is treated as a queue. As of now, the player must always
	 * target the first line that appears on the screen.  
	 * 
	 * 
	 */
	//private ArrayList<String> onScreenLines;
	
	private boolean gameOver;


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
	public Earth earth;
	public int pUpAvail;
	//public static boolean pIncoming;
	private int bossCount;

	/**
	 * put three arbitrary code lines into codeLineDB
	 */
	public GameModel(){
		earth = new Earth();
		random = new Random();
		codeLineDB = new ArrayList<String>();
		shortLineDB = new ArrayList<String>();

		onScreenEnemies = new ArrayList<Enemy>();
		onScreenProjectiles = new ArrayList<Projectile>();
		bossProjectiles = new ArrayList<Bullet>();
		points=0;
		bossOnScreen = false;
		pUpAvail = 0;
		pIncoming = false;
		gameOver = false;
		bossCount = 100;
		
		bossChangeLine =  new Timer(5000, new ActionListener(){
		
			public void actionPerformed(ActionEvent e){
				if(GameModel.this.bossOnScreen==true){
					if(GameModel.this.boss.hasLine())
						GameModel.this.boss.setLine("");
					else   
					{
						String line = GameModel.this.getCodeLine();
						while(line.length() > 10)
							line = GameModel.this.getCodeLine();
						GameModel.this.boss.setLine(line);
					}
				}
			}
				
		});

		bossFireTimer =  new Timer(2000, new ActionListener(){

			public void actionPerformed(ActionEvent e){
				int cannon;
				Random r = new Random();
				if(GameModel.this.bossOnScreen==true){
					cannon=r.nextInt()%3;
					if(cannon==0)
						GameModel.this.onScreenEnemies.add(boss.fireCannon0(getShortCodeLine()));
					if(cannon==1)
						GameModel.this.onScreenEnemies.add(boss.fireCannon1(getShortCodeLine()));
					if(cannon==2)
						GameModel.this.onScreenEnemies.add(boss.fireCannon2(getShortCodeLine()));

				}
			}

			
		});

		
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
			if(line.length() > 8)
				codeLineDB.add(line);
			else
				shortLineDB.add(line);
		}	
		
		input.close();
	}
	
	public String getShortCodeLine() {
		int index = random.nextInt(shortLineDB.size());
		return shortLineDB.get(index);
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
		y = y % 720;
		
		if((y+200) >= 720)
			y=y-((y+200)-720);

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
        PowerUp u = null;
        
        if(earth.hbEarth.health <= 0)
        {
        	gameOver = true;
        	return;
        }
        
        // first power up option clears the screen
        if(pIncoming && pUpAvail == 1)
        {
        	for(int i=0; i<onScreenPowerUps.size(); i++){
    			u=onScreenPowerUps.get(i);
    			u.translate(1);
    			//u.paintToImage();
        	}
        	collisions();
    		cleanUp();
        	return;
        }
        
        // second power up freezes the screen for 15 seconds
        if(pIncoming && pUpAvail == 2)
        {
        	collisions();
        	cleanUp();
        	return;
        }
        
        // 3rd power up refills health by 30 points
        if(pIncoming && pUpAvail == 3)
        {
        	earth.hbEarth.hit(-30);
        	pIncoming = false;
        	pUpAvail = 0;
        	
        }

        for(int i=0; i<onScreenPowerUps.size(); i++){
       		u=onScreenPowerUps.get(i);
       		u.translate(-1);
       		if(u.inPosition == true)
       			onScreenPowerUps.remove(u);
       		//u.paintToImage();
       	}

		for(int i=0; i<onScreenEnemies.size(); i++){
			e=onScreenEnemies.get(i);
			if(e instanceof Boss){
				if(!bossOnScreen)
					boss.translate(1);
				else if(boss.translate(-1)){
					beginFireSequence();
				}
			}
			else 
			{
				if(bossOnScreen && (e instanceof Bullet))
				{
					e.translate(-1,0);
					
				}
				else
				{
					e.translate(-1, 0);
				}	
			}
			
			e.paintToImage();
		}
	

			for(int i=0; i<onScreenProjectiles.size(); i++){
				p=onScreenProjectiles.get(i);
				p.translate(1, 0);
				p.paintToImage();

			}


			//check for collisions after this translation
			collisions();
			cleanUp();
		}
	
		public void beginFireSequence(){
			
			while(pIncoming){}
			
			bossFireTimer.start();
			bossChangeLine.start();
		}

		/**
		 * creates grunt enemy. will be loaded with different
		 * code lines instead of just int a later on
		 */
		
		public void createPowerUp(){
			onScreenPowerUps.add(new PowerUp("images/powerUpShip.png"));
		}
		
		public void createBoss(){
			if(!bossOnScreen){
				boss = new Boss(""  , "images/boss.png", "images/explosion.png", new Point(1300,70) );
				onScreenEnemies.add(boss);
				bossOnScreen = true;
			}

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
	

	/**
	 * creates grunt enemy. will be loaded with different
	 * code lines instead of just int a later on
	 */
	public void createGrunt(){
		if(pIncoming || bossOnScreen)
			return;
		Enemy enemy = new EnemyGrunt(getCodeLine(), getRandomPoint());
		onScreenEnemies.add(enemy);
	}



	public Bullet getScreenBossBullet(int i){
		if(i>=0 && i<bossProjectiles.size())
			return bossProjectiles.get(i);

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
			Boolean processed=false;
			
			//dont compare blank lines and return false
			if(s.equals(""))
				return false;
			
			if(s.equals("power up") && pUpAvail > 0 && !bossOnScreen){
				
				pIncoming = true;
				if(pUpAvail == 1)
					createPowerUp();
				if(pUpAvail != 3)
				{
					java.util.Timer pUpSequence = new java.util.Timer();
					pUpSequence.schedule(new setFalse(this), 15*1000);
				}
				processed=true;
				return true;
			}

			for(int i=0; i<onScreenEnemies.size(); i++){
				enemy=onScreenEnemies.get(i);
				//System.out.println(enemy.getLine());
				if(enemy!=null && enemy.getX()<=1280){
					if(enemy.getLine().equals(s)){
						if(enemy instanceof Boss){
							Boss b = (Boss) enemy;
							if(!b.hasLine())
								continue;
							else if(b.getLine().equals(s))
							{
								boss.bossHb.hit(27);
								processed = true;
								boss.setLine("");
							}
							
							if(boss.bossHb.health <= 0)
							{
								//onScreenEnemies.remove(boss);
								bossOnScreen=false;
								// ***pick a power up, corresponding to 1- ship,2 - freeze, or 3- health***//
								pUpAvail = random.nextInt(3) + 1;
								System.out.println("Boss defeated, acquired powerup: " + pUpAvail);
								boss.collision();
								updateScore(50);
								bossCount = points + 100;
							}
							return processed;
						}
						
						enemy.collision();
						
						//********* updating score by 100 for testing******
						updateScore(100);
						processed = true;
					}
				}
			}
			return processed;
			
		}

		public void updateScore(int amount){
			points=points+amount;
			if((points == bossCount) && !bossOnScreen)
				createBoss();

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
				//System.out.println("intersection");
				if(pixelPerfectCollision(a, b)){
					a.collision();
					b.collision();
					return true;
				}
			}
		return false;
	}
	
	

		public String getScore(){
			return "Score: "+points.toString();
		}

		public boolean pixelPerfectCollision(Collidable collidableA, Collidable collidableB){

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

		public boolean gameOver() {
			return gameOver;
		}
		
		/**
		 * end all game functions
		 */
		
		public void endGame() {
		
			
		}

		public void newGame() {
			// TODO Auto-generated method stub
			gameOver = false;
			
		}
		
		
	}
