package cs3443game;

import java.util.TimerTask;

public class setFalse extends TimerTask {


	@Override
	public void run() {
		GameModel.pIncoming = false;
		GameModel.pUpAvail = 0;
	}

}
