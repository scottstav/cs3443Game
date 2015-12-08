package cs3443game;



import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * view that is used to display the different views of the game.
 * This view "hosts" the remaining views of the game.
 * @author Paco
 *
 */
public class HostView extends JFrame {
	/**
	 * list of all screens in the game
	 */
	private ArrayList<JPanel> screenList = new ArrayList<JPanel>();

	//static final strings of the names of the buttons used in the game.
	private static final String START = "button_start";
	private static final String HOW_TO = "button_howto";
	private static final String OPTIONS = "button_options";
	private static final String LEADERBOARDS = "button_lboards";
	private static final String ENDGAME = "button_endgame";
	private static final String EXIT = "button_exit";
	private static final String MAIN = "button_mainmenu";
	private static final String MODE1 = "button_mode1";
	private static final String BACK = "button_goback";

	private static final String RETRY = "button_tryagain";
	private static final String NEWPLAYER = "button_addplayer";


	/**
	 * index of the previous screen, used for back button
	 */
	int previousIndex;
	/**
	 * index of current screen
	 */
	int currentIndex;

	/**
	 * Music for menu, in-game, and game over screens
	 */
	private Music music = new Music(); //main menu music
	private static final String MAIN_MENU_MUSIC = "music/mainmenu.wav";
	private static final String GAME_MUSIC = "music/gamemusic.wav";
	private static final String GAME_OVER = "music/gameover.wav";
	/**
	 * the game's menu view
	 */
	MenuView menu;
	/**
	 * the game's game view
	 */
	private GameView game;
	/**
	 * the game's mode
	 */
	GameModel mode;
	/**
	 * game's how-to screen
	 */
	HowToView howToScreen;
	/**
	 * game's setting view
	 */
	SettingsView settingsScreen;
	/**
	 * game's leaderboard view
	 */
	LeaderboardView leaderboardScreen;
	/**
	 * game's game over view
	 */
	EndGameView endGameScreen;
	


	/**
	 * creates the host view. All views are instantiated and put into a collection
	 * that is accessed by the host view when choosing which screen to display
	 */
	public HostView(){
		menu = new MenuView();
		mode = new GameModel(this);
		game = new GameView(this);
		howToScreen = new HowToView();
		settingsScreen = new SettingsView();
		leaderboardScreen = new LeaderboardView();
		endGameScreen = new EndGameView();

		screenList.add(menu);
		screenList.add(game);
	
		screenList.add(howToScreen);
		screenList.add(settingsScreen);
		screenList.add(leaderboardScreen);
		screenList.add(endGameScreen);

		previousIndex=0;
		currentIndex=0;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(menu); //first screen is always menu

		music.loadFile(MAIN_MENU_MUSIC); //this loads the menu music on game load
		music.play();
		music.loop(); //loops the main menu music

		setSize(1280, 720);
		setResizable(false);
		setVisible(true);
	}
	public void setMode(GameModel mode){
		game.setMode(mode);
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
	 *receives a command fro the controller and uses it to switch to the
	 *view that the command specifies. This function calls the model's start function if needed. 
	 * @param screen the command given by the button. 
	 */
	public void switchView(String screen){
		remove(menu);
		//remove(modeScreen);
		//remove(userScreen);
		remove(howToScreen);
		remove(settingsScreen);
		remove(leaderboardScreen);
		remove(endGameScreen);

		if(screen.equals(EXIT))
			System.exit(1);
		if(screen.equals(BACK))
			currentIndex=previousIndex;

		else if(screen.equals(START) || screen.equals(RETRY)){
			
			
			System.out.println(mode.gameOver());
			
			if(mode.gameOver())
			{
                HostController controller = new HostController(this);
                this.register(controller);
				mode = new GameModel(this);
				game = new GameView(this);
				game.setMode(mode);
				screenList.add(game);
			}
			
			previousIndex=currentIndex;
			currentIndex = screenList.indexOf(game);
			
			game.setMode(mode);
			game.start();
			
			currentIndex = screenList.indexOf(game);
			
			music.stop();//this should stop the main menu music

			if(music.getMute() == false){
				music.loadFile(GAME_MUSIC);
				music.play();		 
				music.loop(); //loops the game music
			}		
			
		}
		
		else if(screen.equals(HOW_TO)){
			previousIndex=currentIndex;
			currentIndex = screenList.indexOf(howToScreen);
		}
		else if(screen.equals(OPTIONS)){
			previousIndex=currentIndex;
			currentIndex = screenList.indexOf(settingsScreen);
		}
		else if(screen.equals(LEADERBOARDS)){
			previousIndex=currentIndex;
			currentIndex = screenList.indexOf(leaderboardScreen);
		}
		else if(screen.equals(ENDGAME)){
			if(music.getMute() == false)
				music.stop();
			music.stop();//this should stop the in-game music

			if(music.getMute() == false){
				music.loadFile(GAME_OVER);
				music.play();		 
				music.loop(); //loops the end-game music
			}
			
			previousIndex=currentIndex;
			currentIndex = screenList.indexOf(endGameScreen);
		}
		else if(screen.equals(MAIN)){
			previousIndex=currentIndex;
			currentIndex = screenList.indexOf(menu);
			
			music.stop();//this should stop the end-of-game music

			if(music.getMute() == false){
				music.loadFile(MAIN_MENU_MUSIC);
				music.play();		 
				music.loop(); //loops the menu music
			}
			
		}
		else if(screen.equals(NEWPLAYER)){
			endGameScreen.addPlayer(mode.getPoints());
		}
		
		if(currentIndex!=-1){
			this.add(screenList.get(currentIndex));
		}
		validate();
		repaint();

	}
    /**
     * returns the text of game's text field
     * @return player's input
     */
	public String getText(){
		return game.getText();
	}
	
	/**
	 * resets the game's input field
	 */
	public void resetText(){
		game.resetText();
	}
    
	/**
	 * calls a function in the model that processes the player's input
	 * @param s string to be processed
	 * @return true if the string was processed, false if input was invalid 
	 */
	public boolean process(String s){
		return mode.process(s);
	}
}
