package cs3443game;

import java.awt.BorderLayout;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class HostView extends JFrame {
	/**
	 * list of all screens in the game
	 */
	private ArrayList<JPanel> screenList = new ArrayList<JPanel>();
	private static final String START = "button_start";
	private static final String HOW_TO = "button_howto";
	private static final String OPTIONS = "button_options";
	private static final String LEADERBOARDS = "button_lboards";
	private static final String EXIT = "button_exit";
	private static final String MODE1 = "button_mode1";
	private static final String BACK = "button_back";
	private static final String MODE_SELECT ="Select Mode";
	/**
	 * index of the previous screen, used for back button
	 */
	int previousIndex;
	/**
	 * index of current screen
	 */
	int currentIndex;
	
	MenuView menu;
	GameView game;
	ModeSelection modeScreen;
	UserView userScreen;
	GameModel mode;
	


	public HostView(){
		menu = new MenuView();
		mode = new GameModel();
		game = new GameView(mode);
		modeScreen = new ModeSelection();
		userScreen = new UserView();
		
		screenList.add(menu);
		screenList.add(game);
		screenList.add(modeScreen);
		screenList.add(userScreen);
		
		previousIndex=0;
		currentIndex=0;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(menu); //first screen is always menu
		setSize(1280, 720);
		setResizable(false);
		setVisible(true);
	}
	
	/**
	 * registers all buttons of all components to a HostController instance.
	 * If your button doesn't deal with switching views, no worries. It will not
	 * affect the switch view function of this class. 
	 * @param c HostController instance
	 */
	public void register(HostController c){
		Component components[];
		for(int i=0; i<screenList.size(); i++){
			components = screenList.get(i).getComponents();
			for(Component com : components){
				if(com instanceof JButton){
					JButton button = (JButton)com;
					button.addActionListener(c);
				}
				if(com instanceof JTextField){
					JTextField field = (JTextField) com;
					com.addKeyListener(c);
				}

			}
		}
	}

	
	/**
	 * This is where you must add support for the button of your class
	 * that deals with switching views. Create a static final string that
	 * contains the text of the button you created, then check for it in an 
	 * if statement as shown below. notice that certain common functionalities 
	 * are already supported, like a back button that reverts to a previous screen.
	 * if you want to implement a back button in your screen, simply add a button 
	 * to your class with the text "button_back", and add it to the screenList ArrayList.
	 * make sure that you add a remove statement for any screen that you add. Might be good to make
	 * a sort of "clean" function with all remove statements in it, so that it may be called at the 
	 * beginning of the function.
	 * Back button functionality is buggy at the moment, consider linked list approach, or simply
	 * replace back button with a button that has the name of the screen you want to go back to instead. 
	 * For example, instead of a back button in the user view, have a button that says main menu, and so on.
	 *  
	 * @param screen the command given by the button. 
	 */
	public void switchView(String screen){
		remove(menu);
		remove(modeScreen);
		remove(userScreen);
		
		
		if(screen.equals(EXIT))
			System.exit(1);
		if(screen.equals(BACK))
			currentIndex=previousIndex;
		
		else if(screen.equals(START)){
			previousIndex=currentIndex;
			currentIndex = screenList.indexOf(userScreen);
		}		
		else if(screen.equals(MODE1)){
			previousIndex=currentIndex;
			currentIndex = screenList.indexOf(game);
		}
		else if(screen.equals(MODE_SELECT)){
			previousIndex=currentIndex;
			currentIndex = screenList.indexOf(modeScreen);
		}
	   
		if(currentIndex!=-1){
			this.add(screenList.get(currentIndex));
		}
		validate();
		repaint();
		
	}
	
	public String getText(){
		return game.getText();
	}
	
	public void resetText(){
		game.resetText();
	}
	
	public boolean process(String s){
		return mode.process(s);
	}
}
