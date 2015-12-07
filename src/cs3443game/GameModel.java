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

/**
 * the main mode of the game. This class contains 
 * methods for screen-object translation, collision detection, 
 * screen clean up, random code line generation, enemy creation, 
 * and input processing. 
 * @author Paco
 *
 */
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

	/**
	 * collection of enemies on screen
	 */
	private ArrayList<Enemy> onScreenEnemies;
	/**
	 * collection of projectiles on screen
	 */
	private ArrayList<Projectile>onScreenProjectiles;
	/**
	 * collection of power ups on screen
	 */
	private ArrayList<PowerUp>onScreenPowerUps;
	
	//private ArrayList<Bullet>bossProjectiles;
	/**
	 * the game's boss
	 */
	public Boss boss;
	/**
	 * timer that determines the boss's fire rate
	 */
	private Timer bossFireTimer;
	/**
	 * timer that determines the rate that the boss changes code lines
	 */
	private Timer bossChangeLine;
	boolean bossOnScreen;
	public boolean pIncoming;
	/**
	 * determines whether the boss is on screen or not
	 */
	//private ArrayList<String> onScreenLines;
	
	private boolean gameOver;


	/**
	 * the amount of points accumulated by the player
	 */
	private Integer points;
	
	
	/**
	 * used to generate random code lines
	 */
	private Random random;
	/**
	 * the game's earth object
	 */
	public Earth earth;
	
	public int pUpAvail;
	//public static boolean pIncoming;
	private int bossCount;

	/**
	 * constructs the game's main mode
	 */
	public GameModel(){
		earth = new Earth();
		random = new Random();
		codeLineDB = new ArrayList<String>();
		shortLineDB = new ArrayList<String>();

		onScreenEnemies = new ArrayList<Enemy>();
		onScreenProjectiles = new ArrayList<Projectile>();
		points=0;
		bossOnScreen=false;
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
	 *this method dictates movement on screen by calling the translate method of all screen
	 *objects.
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
	
	/**
	 * when the boss has reached its resting position, this method 
	 * begins the boss's firing sequence. This method also causes the boss
	 * to begin displaying a code line. 
	 */
		public void beginFireSequence(){
			while(pIncoming)
			{
				
			}
			
			bossFireTimer.start();
			bossChangeLine.start();
		}

	/*
	 * creates a power up ship that helps clear the screen of enemies.
	 */
		
		public void createPowerUp(){
			onScreenPowerUps.add(new PowerUp("images/powerUpShip.png"));
		}
		
		/**
		 * creates the game's boss
		 */
		public void createBoss(){
			if(!bossOnScreen){
				boss = new Boss(""  , "images/boss.png", "images/explosion.png", new Point(1300,70) );
				onScreenEnemies.add(boss);
				bossOnScreen = true;
			}

		}

		/**
		 * returns a screen enemy from the enemy collection at the specified position
		 * @param i index of enemy to return
		 * @return enemy at specified position, null if invalid value was given
		 */
		public Enemy getScreenEnemy(int i){
			if(i>=0 && i<onScreenEnemies.size())
				return onScreenEnemies.get(i);
		
		return null;
		}
	

	/**
	 * creates grunt enemy and loads it with a random code line
	 */
	public void createGrunt(){
		if(pIncoming || bossOnScreen)
			return;
		Enemy enemy = new EnemyGrunt(getCodeLine(), getRandomPoint());
		onScreenEnemies.add(enemy);
	}



	public Projectile getScreenProjo(int i){
		if(i>=0 && i<onScreenProjectiles.size())
	    	    return onScreenProjectiles.get(i);

		return null;
	}
	
	/**
	 * returns a power up from the power up collection at the specified position
	 * @param i index of power up to return
	 * @return power up at specified position, null if invalid value was given
	 */
	public PowerUp getScreenPowerUp(int i){
		if(i>=0 && i<onScreenPowerUps.size())
		    return onScreenPowerUps.get(i);

		return null;
		}

/**
 * processes the player's input.
 * checks the input text to see if it matches with the code lines of enemies on screen.
 * If text matches an enemy's line, the enemy takes damage or it is destroyed. 
 * also checks to see if the player is attempting to use a power up. 
 */
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
/**
 * updates the player's score. Also creates a boss if the player has reached a certain score
 * @param amount the amount of points the score will go up by
 */
		public void updateScore(int amount){
			points=points+amount;
			if((points == bossCount) && !bossOnScreen)
				createBoss();

		}
	
        /**
         * checks to see if the bounds of on screen objects intersect. 
         * If bounds intersect, pixel perfect collision is called to see
         * if the images are truly colliding. 
         */
		public void collisions(){

			Enemy enemy;
			Projectile projo;
			PowerUp pUp;

			for(int i=0; i<onScreenEnemies.size(); i++){
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
				for(int j=0; j<onScreenProjectiles.size(); j++){
					projo = getScreenProjo(j);
					if(projo.isTrash())
						continue;
					if(collided(projo,enemy))
						break;
				}
			}

		}

        /**
         * method that is used by the collisions method to check if
         * two collidable objects have truly collided
         * @param a first collidable object
         * @param b second collidable object
         * @return true if objects collided, false otherwise
         */
		public boolean collided(Collidable a, Collidable b){
					
			if(a.getBounds().intersects(b.getBounds())){
				
				if(pixelPerfectCollision(a, b)){
					a.collision();
					b.collision();
					return true;
				}
			}
		return false;
	}
	
	
        /**
         * gets the current score
         * @return current score
         */
		public String getScore(){
			return "Score: "+points.toString();
		}

		/**
		 * checks collisions down to the pixel. Calculates the area of overlap between the two
		 * collidable objects, and checks the pixels of that area to see the two images overlap
		 * @param collidableA first collidable object
		 * @param collidableB second collidable object
		 * @return true if collision occurred, false otherwise
		 */
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
        /**
         * cleans up objects that are ready to be removed from the screen. 
         */
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
