package cs3443game;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
	
	private static Clip clip = null;
	private static boolean mute = false; //game is not muted by default
	
	public Music(){}

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
	    public void play(){
	    	
	        clip.setFramePosition(0); //resets clip to the beginning
	        clip.start();
	    }
	    
	    public void loop(){
	    	
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	    }
	    
	    public void stop(){
	    	if (clip.isRunning()) 
	    		System.out.println(clip.isRunning());
	    		clip.stop();
	     }
	    
	    public Clip getClip() {
	    	return clip;
	    }
	    
	    public void setMute(boolean changeMute){
	    	mute = changeMute;
	    }
	    
	    public boolean getMute(){
	    	return mute;
	    }
	    
}
	
//	//CURRENTLY NOT LOOPING
//	public void play(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
//
//		try {
//		    File yourFile = new File(fileName);
//		    AudioFormat format;
//		    DataLine.Info info;
//
//		    stream = AudioSystem.getAudioInputStream(yourFile);
//		    format = stream.getFormat();
//		    info = new DataLine.Info(Clip.class, format);
//		    clip = (Clip)AudioSystem.getLine(info);
//		    clip.open(stream);
//		    clip.start();
//		}
//		catch (FileNotFoundException ex) {
//			System.out.println("File not found: " + fileName);
//		}
//		
//////		ContinuousAudioDataStream loop = null;
////	    InputStream in = null;
////	    	    
////	    try {
////	    	in = new FileInputStream(fileName);
////	    } catch (FileNotFoundException ex) {
////	    	System.out.println("File not found: " + fileName);
////	    }
////	    try {
////	    	audioStream = new AudioStream(in);
//////	    	AudioData MD;
////	    	AudioPlayer.player.start(audioStream);
////
////	    	System.out.println("Music should have started.");
////	    	} catch (IOException ex) {
////	    		System.out.println(ex.getMessage());
////	    	}
//	}
//	
//	//this currently doesn't work, the music is not stopping when method is called
//	public void stop(){
//		
//		if(clip.isRunning() == true){		
//			clip.stop();
//			System.out.println("Music should have stopped.");
//		}
//	}
//}