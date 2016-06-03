package player;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio
{
	private Clip clip; // clip-sound effect koji se pusta
	
	// Konstruktor
	public Audio(String path)
	{
		try 
		{
			//AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(path));
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
					baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
			
			//Decoded ais
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
		} 
		catch (UnsupportedAudioFileException e) 
		{		
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		catch (LineUnavailableException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// Metoda koja startuje SFX 
	public void play(float volume, int framePos)
	{
		playClip(volume, framePos);
		clip.start();
	}
	
	// Metoda koja stopira SFX
	public void stop()
	{
		if(clip.isRunning())
			clip.stop();
	}
	
	// Metoda koja zatvara SFX
	public void close()
	{
		stop();
		clip.close();
	}
	
	public void playAfterPause(int frame)
	{
		clip.setFramePosition(frame);
		clip.start();
	}
	
	public int getFramePosition()
	{
		return clip.getFramePosition();
	}
	
	// Metoda koja sluzi za podesavanje jacine zvuka sound effect-a
	private void playClip(float volume, int framePos)
	{
		if(clip == null)
			return;
		
		stop();
		
		FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		try
		{
			gainControl.setValue(volume);
			System.out.println(volume);
		}
		catch(IllegalArgumentException e)
		{
			//e.printStackTrace();
			if(volume > gainControl.getMaximum())
				volume = gainControl.getMaximum();
			else if(volume < gainControl.getMinimum())
				volume = gainControl.getMinimum();
		}	
		clip.setFramePosition(framePos);
	}
}
