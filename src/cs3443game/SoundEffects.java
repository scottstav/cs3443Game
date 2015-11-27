package cs3443game;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

public class SoundEffects {

	public void playSound(String soundName)
	 {
	   try 
	   {
	    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
	    Clip clip = AudioSystem.getClip( );
	    clip.open(audioInputStream);
	    clip.start( );
	   }
	   catch(Exception ex)
	   {
	     System.out.println("Error with playing sound.");
	     ex.printStackTrace( );
	   }
	 }
}
