package cs3443game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
@SuppressWarnings("serial")
public class ModeSelection extends JPanel{



	private Image background;
	private ImageIcon mode1;
	private ImageIcon mode2;
	private ImageIcon endGame;
	private ImageIcon mode4;
	private ImageIcon mainmenu;

	public ModeSelection() {

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
	 * Method that sets the icons with images
	 */
	private void setIcons() {

		background = new ImageIcon("images/image_mainmenu.png").getImage();
		mode1 = new ImageIcon("images/button_endlessmode.png");
		mode2 = new ImageIcon("images/button_timedmode.png");
		endGame = new ImageIcon("images/button_mode_placeholder.png");
		mode4 = new ImageIcon("images/button_mode_placeholder.png");
		mainmenu = new ImageIcon("images/button_goback.png");	
	}

	/**
	 * Method that turns the images into JButtons
	 * @param menuContent
	 */
	private void setButtons(JPanel menuContent) {

		JButton button_mode1 = new JButton(mode1);
		button_mode1.setText("button_mode1");
		button_mode1.setLocation(450, 300);
		button_mode1.setSize(405, 50);
		button_mode1.setBorderPainted(false);
		button_mode1.setFocusPainted(false);
		button_mode1.setContentAreaFilled(false);
		button_mode1.setCursor(new Cursor(Cursor.HAND_CURSOR));


		JButton button_mode2 = new JButton(mode2);
		button_mode2.setText("button_mode2");
		button_mode2.setLocation(450, 360);
		button_mode2.setSize(405, 50);
		button_mode2.setBorderPainted(false);
		button_mode2.setFocusPainted(false);
		button_mode2.setContentAreaFilled(false);
		button_mode2.setCursor(new Cursor(Cursor.HAND_CURSOR));


		JButton button_endGame = new JButton(endGame);
		button_endGame.setText("button_endgame");
		button_endGame.setLocation(450, 420);
		button_endGame.setSize(405, 50);
		button_endGame.setBorderPainted(false);
		button_endGame.setFocusPainted(false);
		button_endGame.setContentAreaFilled(false);
		button_endGame.setCursor(new Cursor(Cursor.HAND_CURSOR));


		JButton button_mode4 = new JButton(mode4);
		button_mode4.setText("button_mode4");
		button_mode4.setLocation(450, 480);
		button_mode4.setSize(405, 50);
		button_mode4.setBorderPainted(false);
		button_mode4.setFocusPainted(false);
		button_mode4.setContentAreaFilled(false);
		button_mode4.setCursor(new Cursor(Cursor.HAND_CURSOR));


		JButton button_back = new JButton(mainmenu);
		button_back.setText("button_goback");
		button_back.setLocation(450, 600);
		button_back.setSize(405, 50);
		button_back.setBorderPainted(false);
		button_back.setFocusPainted(false);
		button_back.setContentAreaFilled(false);
		button_back.setCursor(new Cursor(Cursor.HAND_CURSOR));


		//adds the buttons to the JPanel
		menuContent.add(button_mode1);
		menuContent.add(button_mode2);
		menuContent.add(button_endGame);
		menuContent.add(button_mode4);
		menuContent.add(button_back);

	}
}

