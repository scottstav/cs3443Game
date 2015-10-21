package cs3443game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MenuView extends JFrame {
  
	MenuView() 
	{
		super("CyberBlocks version 1.0");
		add(new menuPanel());
		setSize(1280, 720); //window size
	}

	public static void createMenu() {
		MenuView frame = new MenuView();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false); //user cannot resize window
		frame.setVisible(true);
	}
}

@SuppressWarnings("serial")
class menuPanel extends JPanel {
	
	Image background_image = null;
	Image button_start = null;
	Image button_howtoplay = null;
	Image button_options = null;
	Image button_lboards = null;
	Image button_exit = null;
	
	/**
	 * menuPanel constructor
	 */
	menuPanel() {
	  
		MediaTracker mt = new MediaTracker(this);
		background_image = Toolkit.getDefaultToolkit().getImage("images/image_mainmenu.png");
		button_start = Toolkit.getDefaultToolkit().getImage("images/button_start.png");
		button_howtoplay = Toolkit.getDefaultToolkit().getImage("images/button_howtoplay.png");
		button_options = Toolkit.getDefaultToolkit().getImage("images/button_options.png");
		button_lboards = Toolkit.getDefaultToolkit().getImage("images/button_lboards.png");
		button_exit = Toolkit.getDefaultToolkit().getImage("images/button_exit.png");
		
		mt.addImage(background_image, 0);
		mt.addImage(button_start, 1);
		mt.addImage(button_howtoplay, 2);
		mt.addImage(button_options, 3);
		mt.addImage(button_lboards, 4);
		mt.addImage(button_exit, 5);
    
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		//places images at x,y coords on the screen
		g.drawImage(background_image, 1, 1, null);
		g.drawImage(button_start, 450, 310, null);
		g.drawImage(button_howtoplay, 450, 370, null);
		g.drawImage(button_options, 450, 430, null);
		g.drawImage(button_lboards, 450, 490, null);
		g.drawImage(button_exit, 450, 550, null);
	}
}

