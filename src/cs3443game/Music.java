package cs3443game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * EVEN THOUGH THIS IS WORKING I HAVE READ ONLINE WE SHOULD NOT
 * BE USING SUN.AUDIO. 
 * 
 * I am going to leave it for now, if I find a better/working alternative
 * we can switch.
 * @author Taylor
 *
 */
public class Music {
		
	public Music(){}
	
	private AudioStream audioStream = null;
	
	//CURRENTLY NOT LOOPING
	public void play(String fileName){

//		ContinuousAudioDataStream loop = null;
	    InputStream in = null;
	    	    
	    try {
	    	in = new FileInputStream(fileName);
	    } catch (FileNotFoundException ex) {
	    	System.out.println("File not found: " + fileName);
	    }
	    try {
	    	audioStream = new AudioStream(in);
//	    	AudioData MD;
	    	AudioPlayer.player.start(audioStream);

	    	System.out.println("Music should have started.");
	    	} catch (IOException ex) {
	    		System.out.println(ex.getMessage());
	    	}
	}
	
	//this currently doesn't work, the music is not stopping when method is called
	public void stop(){
		
		AudioPlayer.player.stop(audioStream);
		System.out.println("Music should have stopped.");
	}
}