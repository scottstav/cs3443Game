package cs3443game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class HostController implements ActionListener{
    private HostView view;
public HostController(HostView v){
	view = v;
}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(arg0.getActionCommand());
		view.switchView(arg0.getActionCommand());
		

	}

}


