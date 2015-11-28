package cs3443game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class HostController implements ActionListener, KeyListener{
	private HostView view;
	private String s;
	public HostController(HostView v){
		view = v;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		System.out.println(arg0.getActionCommand());
		view.switchView(arg0.getActionCommand());
	}

	@Override

	/**
	 * note that you must press enter after your line
	 * is correctly typed in order for the screen line 
	 * to be removed
	 */
	public void keyPressed(KeyEvent e) {
		s=view.getText();
		//check if string exists on screen
		//if(model.contains(s)){
		//model.removeScreenLine(model.indexOf(s));
		//reset text field after a match
		if(view.process(s))
			view.resetText();
		//}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}


