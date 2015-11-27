package cs3443game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LeaderboardView extends JPanel
{
	private Image background;
	private ImageIcon back;
	JTextField textField;
	
	public LeaderboardView()
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
     * Method that sets the icons with images & the background image too
     */
    private void setIcons()
    {
		background = new ImageIcon("images/image_mainmenu.png").getImage();
		back = new ImageIcon("images/button_goback.png");
    }
    
    /**
     * Method that turns the images into JButtons
     * @param menuContent
     */
     private void setButtons(JPanel lboardContent)
     {
		JButton button_back = new JButton(back);
		button_back.setText("button_goback");
		button_back.setLocation(450, 600);
		button_back.setSize(405, 50);
		button_back.setBorderPainted(false);
		button_back.setFocusPainted(false);
		button_back.setContentAreaFilled(false);
		button_back.setCursor(new Cursor(Cursor.HAND_CURSOR));

		lboardContent.add(button_back);
	}
}
