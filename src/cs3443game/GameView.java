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
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import cs3443game.Collidable;

@SuppressWarnings("serial")
public class GameView extends JPanel{

	/**
	 * model for the game
	 */
	private GameModel model;
	/**
	 * timer that dictates when to put a new line
	 * on the screen
	 */
	private Timer newLineTimer;
	/**
	 * timer that controls how often to shift the lines to the right
	 */
	private Timer shiftTimer;

	private JTextField input;
	private SoundEffects button_press = new SoundEffects();
    private static String BUTTON_PRESS = "soundeffects/button_boop.wav";
    private static String MUTE_PRESS = "soundeffects/mute_button_sound.wav";
	private Image background;
	private Earth earth;
	private double angle = 0;
	private JLabel score;
	//private Projectile projo;
	public int speed;
	//private ImageIcon mute_unmute;
	private ImageIcon back;
	//private ImageIcon music_on;
	//private ImageIcon music_off;
	

	EnemyGrunt grunt= new EnemyGrunt("int", new Point(300,300));

	GameView (){
	
		this.setLayout(null);
		model = null;
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

		JTextField field = new JTextField(15);
		field.setLocation(500,640);

		speed = 5;
		add(input);
		background = new ImageIcon("images/space.jpg").getImage();
		
		earth = new Earth();
		
		//sets up health stat instance
		
		// speed up time foe easier testing: sped up from 30 to 10
		shiftTimer = new Timer(speed, new ActionListener(){

			public void actionPerformed(ActionEvent e){
				if(!GameModel.gameOver)
				{
					model.translate();
					GameView.this.repaint();
				}
				else
				{
					endGame();
					
				}

			}
		});
       
		newLineTimer = new Timer(speed*500, new ActionListener(){

			public void actionPerformed(ActionEvent e){
				if(!GameModel.gameOver)
				{
					model.createGrunt();
					//model.createProjo();
					GameView.this.repaint();
				}
				
			}
		});

		//shiftTimer.start();
		//newLineTimer.start();
	}
	
	public void endGame()
	{
		
		shiftTimer.stop();
		newLineTimer.stop();
		setIcons();
		goToLeaderboard();
		//this.setVisible(false);
	}
	
	 private void setIcons() {

			//background = new ImageIcon("images/image_mainmenu.png").getImage();
			back = new ImageIcon("images/button_goback.png");
			//music_on = new ImageIcon("images/button_musicon.png");
			//music_off = new ImageIcon("images/button_musicoff.png");
			//mute_unmute = new ImageIcon("images/muteunmute.png");
			
	}
	
	 private void goToLeaderboard() {
	    	
			JButton button_mainmenu = new JButton(back);
			button_mainmenu.setText("button_endgame");
			button_mainmenu.setLocation(450, 600);
			button_mainmenu.setSize(405, 50);
			button_mainmenu.setBorderPainted(false);
			button_mainmenu.setFocusPainted(false);
			button_mainmenu.setContentAreaFilled(false);
			button_mainmenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
			this.add(button_mainmenu);
			//button_mainmenu.doClick();
	 }
	 
	public void setMode(GameModel m){
		model = m;
	}
	/**
	 * not currently used. the idea is to start the 
	 * timers as soon as the game starts, else the timers begin
	 * right when the instance is created, so the user might hop into
	 * a game that has already begun, depending on how long he takes in 
	 * the menu.
	 */
	public void start(){
		shiftTimer.start();
		newLineTimer.start();
	}
	

	/**
	 * not currently used. the idea is to reset the timers for different
	 * modes of the game. 
	 */
	public void reset(){
		shiftTimer.restart();
		newLineTimer.restart();
	}

	public String getText(){
		return input.getText();
	}

	public void resetText(){
		input.setText("");
	}

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
	 * repaints the newly shifted/created lines
	 */
	protected void paintComponent(Graphics g) {

		
		if(GameModel.gameOver)
			return;
		
		super.paintComponent(g);
		g.drawImage (background, 0, 0, null);
		g.drawImage (earth.getImage(), 0, 0, null);
		
		// access the model's health bar instance
		Graphics2D g2 = (Graphics2D) g;
		Stroke oldStroke = g2.getStroke();
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(6));
		g2.drawRect(model.earth.hbEarth.getX(), model.earth.hbEarth.getY(), (int) model.earth.hbEarth.getWidth(), model.earth.hbEarth.getHeight() );
		g2.setStroke(oldStroke);
		g.setColor(model.earth.hbEarth.getColor());
		g.fillRect(model.earth.hbEarth.getX(), model.earth.hbEarth.getY(), (int) model.earth.hbEarth.getWidth(), model.earth.hbEarth.getHeight() );
		
		if(model.bossOnScreen)
		{
			g2.setColor(Color.WHITE);
			g2.setStroke(new BasicStroke(6));
			g2.drawRect(model.boss.bossHb.getX(), model.boss.bossHb.getY(), (int) model.boss.bossHb.getWidth(), model.boss.bossHb.getHeight() );
			g2.setStroke(oldStroke);
			g.setColor(model.boss.bossHb.getColor());
			g.fillRect(model.boss.bossHb.getX(), model.boss.bossHb.getY(), (int) model.boss.bossHb.getWidth(), model.boss.bossHb.getHeight() );
		}
		
		Enemy enemy = model.getScreenEnemy(0);
		Projectile projo = model.getScreenProjo(0);
	    PowerUp pUp = model.getScreenPowerUp(0);
		Bullet bullet = model.getScreenBossBullet(0);
		
		//g.drawImage(boss.getImage(), boss.getX(), boss.getY(), null);

		//g.setColor(Color.BLACK);
		//g.drawString("int a;", bullet.getX()+20 , bullet.getY()+30);
		//Projectile projo = null;

		//repaints all enemies
		ImageIcon icon = new ImageIcon("images/blueShip.png");

	/*	for(int j=0; bullet!=null; j++){
=======
		for(int j=0; bullet!=null; j++){
>>>>>>> master
			g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), null);
			g.setColor(Color.BLACK);
			g.drawString(bullet.getLine(), (int) bullet.getX() +20 , (int) bullet.getY()+30);

			bullet = model.getScreenBossBullet(j);
<<<<<<< HEAD
		}*/
		
        //Projectile projo = null;
		for(int j=0; enemy!=null; j++){
			g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), null);
			g.setColor(Color.WHITE);
			g.drawString(enemy.getLine(), (int) enemy.getX() , (int) enemy.getY()+enemy.getHeight()+10);

			enemy = model.getScreenEnemy(j);
          
		}
	     score.setText(model.getScore());
		//repaints all projectiles
 /**
 * 	THIS STUFF ONLY PAINTED IF :
 * 	* powerups exist
 *  * maybe we launch a projectile at enemies whose lines have been typed
		Graphics2D g2d = (Graphics2D) g;
		//int tx = 100 + icon.getIconWidth() / 2;
		//int ty = 100 + icon.getIconHeight() / 2;
		//g2d.drawImage(icon.getImage(), 50, 50, null);
		AffineTransform oldXForm = g2d.getTransform();
		for(int h=0; projo!=null; h++){
			int tx = projo.getX() + projo.getWidth() / 2;
			int ty = projo.getY() + projo.getHeight() / 2;
			g2d.translate(tx, ty);
			g2d.rotate(angle);
			g2d.translate(tx * -1, ty * -1);
			projo.calculateBounds(angle);
			g2d.drawImage(projo.getImage(), projo.getX(), projo.getY(), null);
			projo.updateBufferedImage(angle);
			projo = model.getScreenProjo(h);
			g2d.setTransform(oldXForm);


		}
 **/
		for(int i=0; pUp!=null; i++){
			if(pUp.isRotated())
				paintRotatedObject(pUp.getImage(), pUp,g, pUp.getAngle());
				else
					g.drawImage(pUp.getImage(), pUp.getX(), pUp.getY(), null);			
			pUp = model.getScreenPowerUp(i);

		}

		



	
		
		
		
		/*int tx = projo.getX() + projo.getWidth() / 2;
		int ty = projo.getY() + projo.getHeight() / 2;
		g2d.translate(tx, ty);
		g2d.rotate(angle);
		g2d.translate(tx * -1, ty * -1);
		g2d.drawImage(projo.getImage(), projo.getX(), projo.getY(), null);

	    g2d.setTransform(oldXForm);
	    g2d.drawRect((int)projo.getBounds().getX(),(int)projo.getBounds().getY(),(int)projo.getBounds().getWidth(),(int)projo.getBounds().getHeight());
	    //System.out.printf("%d  %d  %d  %d\n",(int)projo.getBounds().getX(),(int)projo.getBounds().getY(),(int)projo.getBounds().getWidth(),(int)projo.getBounds().getHeight());
	    projo.calculateBounds(angle);*/
		//System.out.println(projo.getBounds());
		//BufferedImage img = new BufferedImage(400,400,BufferedImage.TYPE_INT_RGB);

		//icon.paintIcon(null, img.createGraphics(),100,100);



		//g2d.drawImage(icon.getImage(), 100, 100, null);
		angle=angle+.01d;
		if(angle >= 6.28)
			angle = 0;
	}
	
	

}
