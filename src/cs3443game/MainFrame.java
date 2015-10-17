package cs3443game;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {

	/**
	 * View for the input. 
	 * Should probably implement an input model
	 * but the inputView is simple enough to run 
	 * on its own for now.
	 */
	private InputView inputView;
	
	/**
	 * View for the game model
	 */
	private GameView gameView;

	
	public MainFrame(GameModel model){
		super("cs3443 Game");
		inputView = new InputView(model);
		gameView = new GameView(model); 
		this.add(inputView,BorderLayout.SOUTH);
		this.add(gameView, BorderLayout.CENTER);

	}

	public static void createGame() {
		GameModel model = new GameModel();
		MainFrame game = new MainFrame(model);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setSize(700, 700);
		game.setVisible(true);
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createGame();
			}
		});
	}

}
