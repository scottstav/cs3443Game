package cs3443game;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	/**
	 * View for the input. 
	 * Should probably implement an input model
	 * but the inputView is simple enough to run 
	 * on its own for now.
	 */
	//private InputView inputView;
	
	/**
	 * View for the game model
	 */
	/*private GameView gameView;


	
	public MainFrame(GameModel model){
		super("CyberBlocks version 1.0");
		inputView = new InputView(model);
		gameView = new GameView(model); 
		this.add(inputView,BorderLayout.SOUTH);
		this.add(gameView, BorderLayout.CENTER);
	}


	public void createGame() {
		GameModel model = new GameModel();
		MainFrame game = new MainFrame(model);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setSize(1280, 720);  //game resolution, increased slightly
		game.setResizable(false); //disables window resizing
		game.setVisible(true);
	}
*/

	//I have commented this out while I work on the main menu screen
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
                HostView host = new HostView();
                HostController controller = new HostController(host);
                host.register(controller);
			}
		});
	}

}
