package org.example;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
	Clip clip;
	URL[] soundURL = new URL[30];
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
		soundURL[1] = getClass().getResource("/sound/coin.wav");
		soundURL[2] = getClass().getResource("/sound/levelup.wav");
		soundURL[3] = getClass().getResource("/sound/unlock.wav");
		soundURL[4] = getClass().getResource("/sound/fanfare.wav");
	}
	
	public void setFile(int soundIndex) {
		if (soundIndex < 0 || soundIndex > 4) {
			System.out.println("Sound index out of range.");
			throw new IllegalArgumentException();
		}
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[soundIndex]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		if ( clip != null) {
			clip.start();
		}
	}
	
	public void loop() {
		if ( clip != null) {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	
	public void stop() {
		if ( clip != null) {
			clip.stop();
		}
	}
}
