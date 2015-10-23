package cs3443game;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class InputView extends JPanel {
    /**
     * input text box
     */
	JTextField inputField;
	/**
	 * game model
	 */
	GameModel model;
	/**
	 * holds current text in the box
	 * not really necessary to have this variable
	 * just here for code readability
	 */
	String s;


	public InputView(GameModel m){
		super();
		inputField = new JTextField(15);
		model = m;
		inputField.addKeyListener( new KeyListener(){

			@Override
			
			/**
			 * note that you must press enter after your line
			 * is correctly typed in order for the screen line 
			 * to be removed
			 */
			public void keyPressed(KeyEvent e) {
                s=inputField.getText();
				if (s.equals(model.getScreenLine(0))){
					model.removeScreenLine(0);
					//reset text field after a match
					inputField.setText("");
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});
		this.add(inputField,BorderLayout.SOUTH);
	}

}
