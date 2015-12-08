package cs3443game;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
/**
 * controls the game's host screen and everything contained within it. 
 * @author Paco
 *
 */
public class HostController implements ActionListener, KeyListener{
	private HostView view;
	private String s;

	/**
	 * creates a controller of a specified host view
	 * @param v
	 */
	public HostController(HostView v){
		view = v;
		//view.register(this);
	}

	@Override
	/**
	 * processes commands for all buttons here and forwards the command to the 
	 * host view
	 */
	public void actionPerformed(ActionEvent arg0) {

		System.out.println(arg0.getActionCommand());
		view.switchView(arg0.getActionCommand());
		
	}
	
	@Override
	/**
	 *receives keyboard events here and sends them to the 
	 *host view for processing.
	 */
	public void keyPressed(KeyEvent e){
	    //check if enter was pressed
		if(e.getKeyCode()==10){
			s=view.getText();
			if(view.process(s))
				view.resetText();
		}
		

	}

	@Override
	/**
	 * not used
	 */
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	
	@Override
	/**
	 * not used
	 */
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}