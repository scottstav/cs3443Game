package cs3443game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;



import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class EndGameView extends JPanel {
	
	private ArrayList<Player> users;
	private Image background;
	private ImageIcon mainmenu;
	private ImageIcon addplayer;
	private ImageIcon tryagain;
	JTextField textField;
	JList<String> listbox;
	String[] infoArray;

	public EndGameView()
	{	
    	this.setLayout(null); //this allows us to move the buttons to specific x,y coords
    	setIcons(); //calls method to set the images to icons
    	setButtons(); //calls method to turn those images into JButtons
    	setBackground(new Color(0, true));
        setSize(1280, 720);
        setVisible(true);
        setUsers(new ArrayList<Player>());
        
    }

	/**
	 * Class to set the JPanel background
	 *
	 */
    @Override
    public void paintComponent(Graphics g)
    {
        //Paint background
        g.drawImage (background, 0, 0, null);
        infoArray = new String[getUsers().size()];
        int i = 0;
		for(Player user : getUsers())
		{
        	infoArray[i] = user.toString();
        	i++;
		}
    	listbox = new JList<>(infoArray);
    	
    	listbox.setSize(200, 200);
    	listbox.setLocation(560, 360);
    	this.add(listbox);
    }


    /**
     * Method that sets the icons with images & the background image too
     */
    private void setIcons()
    {
		background = new ImageIcon("images/image_gameover.jpg").getImage();
		mainmenu = new ImageIcon("images/button_smmainmenu.png");
		addplayer = new ImageIcon("images/button_addplayer.png");
		tryagain = new ImageIcon("images/button_tryagain.png");
    }
	
    /**
     * Method that turns the images into JButtons
     * @param menuContent
     */
     private void setButtons()
     {
    	textField = new JTextField(10);
    	textField.setLocation(540,280);
		textField.setSize(120,20);
    	add(textField);
  
    	// Create a new listbox control
    	
    	JButton addPlayer = new JButton(addplayer);
    	addPlayer.setText("button_addplayer");
    	addPlayer.setLocation(680, 280);
		addPlayer.setSize(160, 20);
		addPlayer.setBorderPainted(false);
		addPlayer.setFocusPainted(false);
		addPlayer.setContentAreaFilled(false);
		addPlayer.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	
		JButton button_tryagain = new JButton(tryagain);
		button_tryagain.setText("button_tryagain");
		button_tryagain.setLocation(415, 600);
		button_tryagain.setSize(205, 50);
		button_tryagain.setBorderPainted(false);
		button_tryagain.setFocusPainted(false);
		button_tryagain.setContentAreaFilled(false);
		button_tryagain.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JButton button_mainmenu = new JButton(mainmenu);
		button_mainmenu.setText("button_mainmenu");
		button_mainmenu.setLocation(660, 600);
		button_mainmenu.setSize(205, 50);
		button_mainmenu.setBorderPainted(false);
		button_mainmenu.setFocusPainted(false);
		button_mainmenu.setContentAreaFilled(false);
		button_mainmenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		//adds the buttons to the JPanel
		this.add(button_mainmenu);
		this.add(button_tryagain);
		this.add(addPlayer);
	}
     
    public String getText(){
 		return textField.getText();
 	}

	public void addPlayer(int points) {
		Player player = getPlayer();
		player.setPoints(points);
		getUsers().add(player);
		
		this.repaint();
		
	}

	private Player getPlayer() {
		System.out.println(textField.getText());
		Player player = new Player(textField.getText());
		
		return player;
	}

	public ArrayList<Player> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<Player> users) {
		this.users = users;
	}
}