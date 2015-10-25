package cs3443game;

import java.awt.Graphics;


import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GameView extends JPanel{
	EnemyGrunt grunt = new EnemyGrunt("int testVariable;", new Point(50,50));
	
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
	private Timer shiftLinesTimer;

	GameView (GameModel m){
		model = m;
		
		shiftLinesTimer = new Timer(30, new ActionListener(){
      
			public void actionPerformed(ActionEvent e){
				model.translateScreenLines();
				GameView.this.repaint();
			
			}
		});
		
		newLineTimer = new Timer(5000, new ActionListener(){
		      
			public void actionPerformed(ActionEvent e){
				model.newScreenLine();
				model.createGrunt();
			    GameView.this.repaint();
			}
		});
		shiftLinesTimer.start();
		newLineTimer.start();
        
	}
	@Override
	/**
	 * repaints the newly shifted/created lines
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawImage(grunt.getImage(), 300, 300, null);
		
		String s = model.getScreenLine(0);
		Point p = model.getPointFromDB(0);
		Enemy enemy = model.getScreenEnemy(0);
		
		for(int i=0;s!=null; i++){
			g.drawString(s, (int) p.getX() , (int) p.getY());
			s=model.getScreenLine(i);
			p=model.getPointFromDB(i);
		
		}
		
		for(int j=0; enemy!=null; j++){
			g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), null);
			enemy = model.getScreenEnemy(j);
		}
		
	}

}
