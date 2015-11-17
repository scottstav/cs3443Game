package cs3443game;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Input extends JTextField {
    /**
     * input text box
     */
	//JTextField inputField;
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
	
	public Input(GameModel m){
		super(15);
		//inputField = new JTextField(15);
		model = m;
		this.addKeyListener( new KeyListener(){

			@Override
			
			/**
			 * note that you must press enter after your line
			 * is correctly typed in order for the screen line 
			 * to be removed
			 */
			public void keyPressed(KeyEvent e) {
                s=Input.this.getText();
                //check if string exists on screen
                //if(model.contains(s)){
					//model.removeScreenLine(model.indexOf(s));
					//reset text field after a match
                if(model.process(s))
					Input.this.setText("");
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

		});
		//this.add(inputField);
	}

}
