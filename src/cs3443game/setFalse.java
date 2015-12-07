package cs3443game;

import java.util.TimerTask;

public class setFalse extends TimerTask {


	private GameModel model;
	public setFalse(GameModel m)
	{
		model = m;
	}
	
	@Override
	public void run() {
		model.pIncoming = false;
		model.pUpAvail = 0;
	}


}
