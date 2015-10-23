package cs3443game;

import java.awt.Color;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class paints the background image of the main menu.
 * It also paints and creates the JButtons. 
 * @author Taylor
 *
 */
@SuppressWarnings("serial")
public class MenuView extends JFrame {
    
    private Image background;
    private ImageIcon start;
    private ImageIcon howto;
    private ImageIcon options;
    private ImageIcon lboards;
    private ImageIcon exit;

    public MenuView() {
    	
    	JPanel menuContent = new MyBackground();
    	menuContent.setLayout(null); //this allows us to move the buttons to specific x,y coords
    	
    	setIcons(); //calls method to set the images to icons
    	setButtons(menuContent); //calls method to turn those images into JButtons
    
        add(menuContent);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(menuContent);
        setSize(1280, 720);
        setResizable(false);
        setVisible(true);
    }

	/**
	 * Class to set the JPanel background
	 *
	 */
	public class MyBackground extends JPanel {

        public MyBackground() {
            setBackground(new Color(0, true));
        }
        @Override

        public void paintComponent(Graphics g) {

            //Paint background
           g.drawImage (background, 0, 0, null);
        }
    }
    
    /**
     * Method that sets the icons with images
     */
    private void setIcons() {
    	
    	background = new ImageIcon("images/image_mainmenu.png").getImage();
    	start = new ImageIcon("images/button_start.png");
    	howto = new ImageIcon("images/button_howtoplay.png");
    	options = new ImageIcon("images/button_options.png");
    	lboards = new ImageIcon("images/button_lboards.png");
    	exit = new ImageIcon("images/button_exit.png");	
    }
    
    /**
     * Method that turns the images into JButtons
     * @param menuContent
     */
    private void setButtons(JPanel menuContent) {
	   	 
    	JButton button_start = new JButton(start);
    	button_start.setText("button_start");
	   	button_start.setLocation(450, 300);
		button_start.setSize(405, 50);
		button_start.setBorderPainted(false);
		button_start.setFocusPainted(false);
		button_start.setContentAreaFilled(false);
		button_start.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button_start.addMouseListener(new MenuModel());
	   	 
	   	JButton button_howto = new JButton(howto);
	   	button_howto.setText("button_howto");
	   	button_howto.setLocation(450, 360);
	   	button_howto.setSize(405, 50);
	   	button_howto.setBorderPainted(false);
	   	button_howto.setFocusPainted(false);
	   	button_howto.setContentAreaFilled(false);
	   	button_howto.setCursor(new Cursor(Cursor.HAND_CURSOR));
	   	button_howto.addMouseListener(new MenuModel());
	   	
	   	JButton button_options = new JButton(options);
	   	button_options.setText("button_options");
	   	button_options.setLocation(450, 420);
	   	button_options.setSize(405, 50);
	   	button_options.setBorderPainted(false);
	   	button_options.setFocusPainted(false);
	   	button_options.setContentAreaFilled(false);
	   	button_options.setCursor(new Cursor(Cursor.HAND_CURSOR));
	   	button_options.addMouseListener(new MenuModel());
		
	   	JButton button_lboards = new JButton(lboards);
	   	button_lboards.setText("button_lboards");
	   	button_lboards.setLocation(450, 480);
	   	button_lboards.setSize(405, 50);
	   	button_lboards.setBorderPainted(false);
	   	button_lboards.setFocusPainted(false);
	   	button_lboards.setContentAreaFilled(false);
	   	button_lboards.setCursor(new Cursor(Cursor.HAND_CURSOR));
	   	button_lboards.addMouseListener(new MenuModel());
	   	
	   	JButton button_exit = new JButton(exit);
	   	button_exit.setText("button_exit");
	   	button_exit.setLocation(450, 540);
	   	button_exit.setSize(405, 50);
	   	button_exit.setBorderPainted(false);
	   	button_exit.setFocusPainted(false);
	   	button_exit.setContentAreaFilled(false);
	   	button_exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
	   	button_exit.addMouseListener(new MenuModel());
	   	
	   	//adds the buttons to the JPanel
	    menuContent.add(button_start);
	    menuContent.add(button_howto);
	    menuContent.add(button_options);
	    menuContent.add(button_lboards);
	    menuContent.add(button_exit);
		
	}
}
