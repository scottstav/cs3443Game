package cs3443game;



import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import cs3443game.Collidable;

@SuppressWarnings("serial")
/**
 * the screen in which the game is played
 * @author Paco
 *
 */
public class GameView extends JPanel{

	/**
	 * model for the game
	 */
	private GameModel model;
	/**
	 * timer that dictates when to put a new enemy
	 * on the screen
	 */
	private Timer newEnemyTimer;
	/**
	 * timer that controls how often to translate screen objects
	 */
	private Timer shiftTimer;

	/**
	 * receives input from the user
	 */
	private JTextField input;
	/**
     *background image for game
	 */
	private SoundEffects button_press = new SoundEffects();
    private static String BUTTON_PRESS = "soundeffects/button_boop.wav";
    private static String MUTE_PRESS = "soundeffects/mute_button_sound.wav";
	private Image background;
	/**
	 * the game's earth object
	 */
	private Earth earth;
	
	/**
	 * label of the player's score
	 */
	private JLabel score;
	//private Projectile projo;
	public int speed;
	//private ImageIcon mute_unmute;
	private ImageIcon back;
	//private ImageIcon music_on;
	//private ImageIcon music_off;
	private ImageIcon pUpIcon;
	private JTextField field;

	/**
	 * creates the gameView screen.
	 */
	GameView (HostView h){

		this.setLayout(null);
		model = new GameModel(h);
		score = new JLabel("Score: 0");
		score.setLocation(1150,0);
		score.setSize(150,50);
		//goToLeaderBoard(); 
		setIcons();
		score.setForeground(Color.WHITE);
		add(score);
		input= new JTextField(15);
		input.setLocation(0,360);
		input.setSize(300,20);
		field = new JTextField(15);
		field.setLocation(500,640);
		setMode(model);
		
		speed = 10;
		add(input);
		background = new ImageIcon("images/space.jpg").getImage();

		earth = new Earth();
		
		shiftTimer = new Timer(speed, new ActionListener(){

			public void actionPerformed(ActionEvent e){
				if(model.gameOver())
				{
					endGame();
				}
				
				model.translate();
				GameView.this.repaint();
			}
		});

		newEnemyTimer = new Timer(3000, new ActionListener(){

			public void actionPerformed(ActionEvent e){
				if(model.gameOver())
				{
					endGame();
				}

				model.createGrunt();
				//model.createProjo();
				GameView.this.repaint();
				
			}
		});
		
		
	}


	/**
	 * used to switch the screen's current game mode.
	 * currently, only one mode is supported.
	 * @param m mode to be set
	 */
	
	public void endGame()
	{
		//setIcons();
		shiftTimer.stop();
		newEnemyTimer.stop();

		//remove(input);
		//remove(score);
		//remove(field);
		this.setVisible(false);
		model.endGame();
		
		
	}
	
	 private void setIcons() {

			//background = new ImageIcon("images/image_mainmenu.png").getImage();
			back = new ImageIcon("images/button_goback.png");
			//music_on = new ImageIcon("images/button_musicon.png");
			//music_off = new ImageIcon("images/button_musicoff.png");
			//mute_unmute = new ImageIcon("images/muteunmute.png");
			
	}
	
	 
	 
	public void setMode(GameModel m){
		model = m;
	}
	
	/**
	 *begins the game's timers
	 * start and stop game time 
	 */
	public void start(){
		model.newGame();
		shiftTimer.start();
		newEnemyTimer.start();
	}


	/**
     *resets the game's timers 
	 */
	public void reset(){
		shiftTimer.restart();
		newEnemyTimer.restart();
	}

	/**
	 * gets the player's input text
	 * @return input text
	 */
	public String getText(){
		return input.getText();
	}

	/**
	 * resets the text of the input box to an empty string
	 */
	public void resetText(){
		input.setText("");
	}

	/**
	 * rotates the graphics object to paint an object at a specified angle
	 * @param image image to be painted
	 * @param coll collidable whose image will be pained
	 * @param g graphics object to paint rotated object with
	 * @param angle angle of rotation
	 */
	private void paintRotatedObject(Image image, Collidable coll, Graphics g, double angle){

		Graphics2D g2d = (Graphics2D) g;
		AffineTransform oldXForm = g2d.getTransform();
		int tx = coll.getX() + coll.getWidth() / 2;
		int ty = coll.getY() + coll.getHeight() / 2;
		g2d.translate(tx, ty);
		g2d.rotate(angle);
		g2d.translate(tx * -1, ty * -1);
		g2d.drawImage(image, coll.getX(), coll.getY(), null);
		g2d.setTransform(oldXForm);

	}


	@Override
	/**
	 * repaints the game view
	 */
	protected void paintComponent(Graphics g) {

		Image icon;
		
		super.paintComponent(g);
		g.drawImage (background, 0, 0, null);
		g.drawImage (earth.getImage(), 0, 0, null);
		
		// draw power up icons if available
		if(model.pUpAvail != 0)
		{
			if(model.pUpAvail == 1)
			{
				icon = new ImageIcon("images/powerUpShipIcon.png").getImage();

				g.drawImage(icon, 400, 10, null);
			}
			else if(model.pUpAvail == 2)
			{
				icon = new ImageIcon("images/Freeze.png").getImage();

				g.drawImage(icon, 400, 10, null);
			}
			else if(model.pUpAvail == 3)
			{
				icon = new ImageIcon("images/Health.png").getImage();

				g.drawImage(icon, 400, 10, null);
			}
	
		}

		// access the model's health bar instance
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(model.earth.hbEarth.getColor());
		g.fillRect(model.earth.hbEarth.getX(), model.earth.hbEarth.getY(), (int) model.earth.hbEarth.getWidth(), model.earth.hbEarth.getHeight() );
		Stroke oldStroke = g2.getStroke();
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(6));
		g2.drawRect(model.earth.hbEarth.getX(), model.earth.hbEarth.getY(), 350, 30 );
		g2.setStroke(oldStroke);
		
		if(model.bossOnScreen)
		{
			
			g.setColor(model.boss.bossHb.getColor());
			g.fillRect(model.boss.bossHb.getX(), model.boss.bossHb.getY(), (int) model.boss.bossHb.getWidth(), model.boss.bossHb.getHeight() );
			g2.setColor(Color.WHITE);
			g2.setStroke(new BasicStroke(6));
			g2.drawRect(model.boss.bossHb.getX(), model.boss.bossHb.getY(), 350, 30 );
			g2.setStroke(oldStroke);
		}

		Enemy enemy = model.getScreenEnemy(0);
		PowerUp pUp = model.getScreenPowerUp(0);


		//g.drawImage(boss.getImage(), boss.getX(), boss.getY(), null);

		//g.setColor(Color.BLACK);
		//g.drawString("int a;", bullet.getX()+20 , bullet.getY()+30);
		//Projectile projo = null;

		//repaints all enemies
		
		/*	for(int j=0; bullet!=null; j++){
=======
		//ImageIcon icon = new ImageIcon("images/blueShip.png");

	/*	for(int j=0; bullet!=null; j++){
>>>>>>> health bar, bug fixes, etc
=======
		for(int j=0; bullet!=null; j++){
>>>>>>> master
			g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), null);
			g.setColor(Color.BLACK);
			g.drawString(bullet.getLine(), (int) bullet.getX() +20 , (int) bullet.getY()+30);

			bullet = model.getScreenBossBullet(j);
<<<<<<< HEAD
		}*/

	
		for(int j=0; enemy!=null; j++){
			g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), null);
			g.setColor(Color.WHITE);
			g.drawString(enemy.getLine(), (int) enemy.getX() , (int) enemy.getY()+enemy.getHeight()+10);

			enemy = model.getScreenEnemy(j);
          
		}
	    score.setText(model.getScore());
		score.setText(model.getScore());
		
		for(int i=0; pUp!=null; i++){
			if(pUp.isRotated())
				paintRotatedObject(pUp.getImage(), pUp,g, pUp.getAngle());
			else
				g.drawImage(pUp.getImage(), pUp.getX(), pUp.getY(), null);			
			pUp = model.getScreenPowerUp(i);

		}
	}
}
	
	

