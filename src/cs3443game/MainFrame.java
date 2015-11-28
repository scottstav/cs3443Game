package cs3443game;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * main
 */
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
