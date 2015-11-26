package cs3443game;

import java.awt.Cursor;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class UserView extends JPanel {

	JButton chooseMode;
	JButton back;

	public UserView(){
		chooseMode = new JButton("Select Mode");
		back = new JButton("button_back");


		add(back);
		add(chooseMode);

	}
}
