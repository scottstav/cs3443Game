package cs3443game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

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
	private Timer shiftLinesTimer;

	GameView (GameModel m){
		model =m;
		
		shiftLinesTimer = new Timer(1000, new ActionListener(){
      
			public void actionPerformed(ActionEvent e){
				model.translateScreenLines();
				GameView.this.repaint();
			
			}
		});
		
		newLineTimer = new Timer(5000, new ActionListener(){
		      
			public void actionPerformed(ActionEvent e){
				model.newScreenLine();
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
		String s = model.getScreenLine(0);
		Point p = model.getPointFromDB(0);
		for(int i=0;s!=null; i++){
			g.drawString(s, (int) p.getX() , (int) p.getY());
			s=model.getScreenLine(i);
			p=model.getPointFromDB(i);
		
		}
		
	}

}
