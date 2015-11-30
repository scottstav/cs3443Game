package cs3443game;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Plays .wav files only. Do not use this class for sound effects.
 * @author Taylor
 *
 */
public class Music {
	
	private static Clip clip = null;
	private static boolean mute = false; //game is not muted by default
	
	public Music(){}

	/**
	 * Loads given audio file. Lots of error checking.
	 * @param fileName
	 */
	public void loadFile(String fileName) {

	        try {
	            File file = new File(fileName);
	            if (file.exists()) {
	                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
	                clip = AudioSystem.getClip();
	                clip.open(sound);
	            }
	            else {
	                throw new RuntimeException("Sound: file not found: " + fileName);
	            }
	        }
	        catch (MalformedURLException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Sound: Malformed URL: " + e);
	        }
	        catch (UnsupportedAudioFileException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Sound: Input/Output Error: " + e);
	        }
	        catch (LineUnavailableException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
	        }

	    }
	    
	/**
	 * Plays audio file, resetting to begin of track each call
	 */
	public void play(){
		clip.setFramePosition(0); //resets clip to the beginning
		clip.start();
	}
	    
	    
	/**
	 * Loops the audio file
	 */
	public void loop(){
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	    
	    
	/**
	 * Stops the current clip
	 */
	public void stop(){
		if (clip.isRunning()){ 
			System.out.println(clip.isRunning());
			clip.stop();
		}
	 }
	        
	/**
	 * Returns the current loaded clip
	 * @return clip the current clip
	 */
	public Clip getClip() {
		return clip;
	}    
	    
	/**
	 * Sets the mute to on (true) or off (false)
	 * @param changeMute
	 */
	public void setMute(boolean changeMute){
		mute = changeMute;
	}    
	    
	/**
	 * Gets current mute status - true is muted, false is not muted
	 * @return mute
	 */
	public boolean getMute(){
		return mute;
	}  
}