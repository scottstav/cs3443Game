package cs3443game;

import javax.swing.JFrame;

import javax.swing.SwingUtilities;

import cs3443game.HostController;
import cs3443game.HostView;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

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
