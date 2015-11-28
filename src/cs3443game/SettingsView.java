package cs3443game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.sun.scenario.Settings;

@SuppressWarnings("serial")
public class SettingsView extends JPanel {
	
	/**
	 * Image variables
	 */
	private Image background;
	private ImageIcon mute_unmute;
	private ImageIcon back;
	private ImageIcon music_on;
	private ImageIcon music_off;
	
	private Clip clip = null;
	private Music music = new Music();
	
	public SettingsView() {
    	
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
		music_on = new ImageIcon("images/button_musicon.png");
		music_off = new ImageIcon("images/button_musicoff.png");
		mute_unmute = new ImageIcon("images/muteunmute.png");
		
	}
	
    /**
     * Method that turns the images into JButtons
     * @param menuContent
     */private void setButtons(JPanel settings) {
    	
		JButton button_mainmenu = new JButton(back);
		button_mainmenu.setText("button_goback");
		button_mainmenu.setLocation(450, 600);
		button_mainmenu.setSize(405, 50);
		button_mainmenu.setBorderPainted(false);
		button_mainmenu.setFocusPainted(false);
		button_mainmenu.setContentAreaFilled(false);
		button_mainmenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JButton button_music_off = new JButton(music_off);
		button_music_off.setText("button_music_off");
		button_music_off.setLocation(640, 370);
		button_music_off.setSize(50, 39);
		button_music_off.setBorderPainted(false);
		button_music_off.setFocusPainted(false);
		button_music_off.setContentAreaFilled(false);
		button_music_off.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JButton button_music_on = new JButton(music_on);
		button_music_on.setText("button_music_on");
		button_music_on.setLocation(640, 370);
		button_music_on.setSize(50, 39);
		button_music_on.setBorderPainted(false);
		button_music_on.setFocusPainted(false);
		button_music_on.setContentAreaFilled(false);
		button_music_on.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		//if the music on button is pressed, it mutes the music
		button_music_on.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				settings.remove(button_music_on);
				settings.add(button_music_off);
				settings.revalidate();
				settings.repaint();
				
				clip = music.getClip();
				clip.stop();
				music.setMute(true); //mutes
			}
		});
		
		button_music_off.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				settings.remove(button_music_off);
				settings.add(button_music_on);
				settings.revalidate();
				settings.repaint();
				
				music.setMute(false); //unmute
				music.play();
			}
		});
		
    	JButton button_mute = new JButton(mute_unmute);
    	button_mute.setLocation(350, 300);
    	button_mute.setSize(600, 50);
    	button_mute.setBorderPainted(false);
    	button_mute.setFocusPainted(false);
    	button_mute.setContentAreaFilled(false);
		
		//adds the buttons to the JPanel
		settings.add(button_mainmenu);
		settings.add(button_music_on);
		settings.add(button_mute);
	}
     

}
