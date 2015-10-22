package cs3443game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class MenuModel implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		JButton button = (JButton)arg0.getSource();
		String str = button.getText();
		
		if(str.equals("button_exit")) {
			System.exit(0); //exits the game if "exit" button is pressed
		}
		//this is currently broken, hitting "start" will keep current frame open & open new game frame
		else if(str.equals("button_start")) {
			GameModel gm = new GameModel();
			MainFrame mf = new MainFrame(gm);
			new MenuView().setVisible(false);
			mf.createGame();	
		}	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
