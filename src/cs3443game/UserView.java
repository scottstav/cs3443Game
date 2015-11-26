package cs3443game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class UserView extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Image background;
    private ImageIcon createUser;
    private ImageIcon selectUser;
    private ImageIcon goBack;

	public UserView()
	{
		this.setLayout(null); //this allows us to move the buttons to specific x,y coords
    	setIcons(); //calls method to set the images to icons
    	setButtons(this); //calls method to turn those images into JButtons
    	setBackground(new Color(0, true));
        setSize(1280, 720);
        setVisible(true);
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
    }
	
	/**
     * Method that sets the icons with images
     */
    private void setIcons()
    {
    	background = new ImageIcon("images/image_mainmenu.png").getImage();
    	createUser = new ImageIcon("images/button_createuser.png");
    	selectUser = new ImageIcon("images/button_selectuser.png");
    	goBack = new ImageIcon("images/button_goback.png");  	
    }
    
    /**
     * Method that turns the images into JButtons
     * @param menuContent
     */
    private void setButtons(JPanel menuContent)
    {
    	JButton button_createUser = new JButton(createUser);
    	button_createUser.setText("button_createUser");
    	button_createUser.setLocation(450, 300);
    	button_createUser.setSize(405, 50);
    	button_createUser.setBorderPainted(false);
    	button_createUser.setFocusPainted(false);
    	button_createUser.setContentAreaFilled(false);
    	button_createUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
	   	JButton button_selectUser = new JButton(selectUser);
	   	button_selectUser.setText("button_selectUser");
	   	button_selectUser.setLocation(450, 360);
	   	button_selectUser.setSize(405, 50);
	   	button_selectUser.setBorderPainted(false);
	   	button_selectUser.setFocusPainted(false);
	   	button_selectUser.setContentAreaFilled(false);
	   	button_selectUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
	   	
		JButton button_goBack = new JButton(goBack);
		button_goBack.setText("button_goBack");
	   	button_goBack.setLocation(450, 420);
	   	button_goBack.setSize(405, 50);
	   	button_goBack.setBorderPainted(false);
	   	button_goBack.setFocusPainted(false);
	   	button_goBack.setContentAreaFilled(false);
	   	button_goBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
	   	
	    //adds the buttons to the JPanel
	    menuContent.add(button_createUser);
	    menuContent.add(button_selectUser);
	    menuContent.add(button_goBack);
    }   
}