package cs3443game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class HowToView extends JPanel {
	
	private Image background;
	private ImageIcon back;
	private SoundEffects button_press = new SoundEffects();
    private static String BUTTON_PRESS = "soundeffects/button_boop.wav";
    
	public HowToView() {
    	
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
    public void paintComponent(Graphics g) {

        //Paint background
       g.drawImage (background, 0, 0, null);
    }


    /**
     * Method that sets the icons with images & the background image too
     */
    private void setIcons() {

		background = new ImageIcon("images/image_mainmenu.png").getImage();
		back = new ImageIcon("images/button_goback.png");
		
	}
	
    /**
     * Method that turns the images into JButtons
     * @param menuContent
     */private void setButtons(JPanel howToContent)
     {
    	 
    	 JTextArea textArea = new JTextArea(
    			    "How to play CyberBlocks" + "\n" +
    			    "CyberBlocks is a typing test game that challenges the user at typing lines of code." +
    			    "More info goes here " +
    			    "info info info"
    			);
    	textArea.setFont(new Font("Consolas", Font.ITALIC, 16));
    	textArea.setSize(400,250);
    	textArea.setLocation(440,260);
    	textArea.setLineWrap(true);
    	textArea.setWrapStyleWord(true);
    	add(textArea);
    	
		JButton button_mainmenu = new JButton(back);
		button_mainmenu.setText("button_goback");
		button_mainmenu.setLocation(450, 600);
		button_mainmenu.setSize(405, 50);
		button_mainmenu.setBorderPainted(false);
		button_mainmenu.setFocusPainted(false);
		button_mainmenu.setContentAreaFilled(false);
		button_mainmenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button_mainmenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				button_press.playSound(BUTTON_PRESS);
			}
		});
		
		//adds the buttons to the JPanel
		howToContent.add(button_mainmenu);
	}

}
