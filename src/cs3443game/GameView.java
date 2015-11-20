package cs3443game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

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

	private Image background;
	private Earth earth;
	
	EnemyGrunt grunt= new EnemyGrunt("int", new Point(300,300));
	Graphics hbVisual;
	
	GameView (GameModel m){
		this.setLayout(null);
		model = m;
		input= new JTextField(15);
		input.setLocation(0,360);
		input.setSize(300,20);
		
		JTextField field = new JTextField(15);
		field.setLocation(500,640);
 
		add(input);
		background = new ImageIcon("images/space.jpg").getImage();
		
		earth = new Earth();
		
		//sets up health stat instance
		
		// speed up time foe easier testing: sped up from 30 to 10
		shiftTimer = new Timer(10, new ActionListener(){

			public void actionPerformed(ActionEvent e){
				model.translate();
				GameView.this.repaint();

			}
		});

		newLineTimer = new Timer(5000, new ActionListener(){

			public void actionPerformed(ActionEvent e){
				model.createGrunt();
				model.createProjo();
				GameView.this.repaint();
			}
		});

		shiftTimer.start();
		newLineTimer.start();
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
	
	@Override
	/**
	 * repaints the newly shifted/created lines
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage (background, 0, 0, null);
		g.drawImage (earth.getImage(), 0, 0, null);
		
		g.setColor(earth.hbEarth.hbColor);
		System.out.println(earth.hbEarth.hbScale);
		g.fillRect(earth.hbEarth.getX(), earth.hbEarth.getY(), (int) (earth.hbEarth.getWidth()*earth.hbEarth.hbScale), earth.hbEarth.getHeight() );
		
		Enemy enemy = model.getScreenEnemy(0);
		Projectile projo = model.getScreenProjo(0); 

		//repaints all enemies
		for(int j=0; enemy!=null; j++){
			g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), null);
			g.setColor(Color.WHITE);
			g.drawString(enemy.getLine(), (int) enemy.getX() , (int) enemy.getY()+enemy.getHeight()+10);
			enemy = model.getScreenEnemy(j);

		}

		//repaints all projectiles
		for(int h=0; projo!=null; h++){
			g.drawImage(projo.getImage(), projo.getX(), projo.getY(), null);
			projo = model.getScreenProjo(h);

		}
	}
}
