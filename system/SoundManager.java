package ar.zgames.zshot.system;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.jl.player.Player;

/**
 * Handles sound and music
 */
public class SoundManager {

	private boolean music = true; // Whether to play music or mute it
	private boolean sound = true; // Whether to play sound or mute it
	private ArrayList<MusicPlayer> musicPlayers = new ArrayList<MusicPlayer>(); // MusicPlayer array

	/**
	 * Play music with specified ID
	 * @param id
	 * - Id of music track to be played
	 * @param loop
	 * - Whether the music should play once or loop continuously
	 */
	public void playMusic(int id, boolean loop) {
		if (music) {
			MusicPlayer mp = new MusicPlayer(id, loop);
			musicPlayers.add(mp);
			mp.start();
		}
	}

	/**
	 * Stops all currently playing music
	 */
	public void stopMusic() {
		for (int i = 0; i < musicPlayers.size(); i++) {
			if (musicPlayers.get(i) != null){
				musicPlayers.get(i).close();
			}
			musicPlayers.remove(i--);
		}
	}

	/**
	 * Play GatlingGun sound
	 */
	public void playGatling() {
		if (sound) {
			Sounds.GATLING.play();
		}
	}

	/**
	 * Play Cannon sound
	 */
	public void playCannon() {
		if (sound) {
			Sounds.CANNON.play();
		}
	}

	/**
	 * Play SpreadGun sound
	 */
	public void playSpread() {
		if (sound) {
			Sounds.SPREAD.play();
		}
	}

	/**
	 * Play Gun sound
	 */
	public void playGun() {
		if (sound) {
			Sounds.GUN.play();
		}
	}

	/**
	 * Play chime sound
	 */
	public void playChime() {
		if (sound) {
			Sounds.CHIME.play();
		}
	}

	/**
	 * Play warning sound
	 */
	public void playWarning() {
		if (sound) {
			Sounds.WARNING.play();
		}
	}

	/**
	 * Play charging sound
	 */
	public void playCharging() {
		if (sound) {
			Sounds.CHARGING.play();
		}
	}

	/**
	 * Play "chuing" (?) sound
	 */
	public void playChuing() {
		if (sound) {
			Sounds.CHUING.play();
		}
	}
	
	/**
	 * Play Blaster sound
	 */
	public void playBlaster() {
		if (sound) {
			Sounds.BLASTER.play();
		}
	}
	
	/**
	 * Play Stream sound
	 */
	public void playStream() {
		if (sound) {
			Sounds.STREAM.play();
		}
	}
	
	/**
	 * Play Mine launcher sound
	 */
	public void playMine() {
		if (sound) {
			Sounds.MINE.play();
		}
	}
	
	/**
	 * Play Fire arrow sound
	 */
	public void playFire() {
		if (sound) {
			Sounds.FIRE.play();
		}
	}
	
	/**
	 * Play Sword sound
	 */
	public void playSword() {
		if (sound) {
			Sounds.SWORD.play();
		}
	}
	
	/**
	 * Play Buzzer sound
	 */
	public void playBuzzer() {
		if (sound) {
			Sounds.BUZZER.play();
		}
	}
	
	/**
	 * Play Option sound
	 */
	public void playOption() {
		if (sound) {
			Sounds.OPTION.play();
		}
	}
	
	/**
	 * Play Small Explosion sound
	 */
	public void playSmallExplosion() {
		if (sound) {
			Sounds.SMALL_EXPLOSION.play();
		}
	}
	
	/**
	 * Play Big Explosion sound
	 */
	public void playBigExplosion() {
		if (sound) {
			Sounds.BIG_EXPLOSION.play();
		}
	}
	
	/**
	 * Play Death sound
	 */
	public void playDeath() {
		if (sound) {
			Sounds.DEATH.play();
		}
	}
	
	/**
	 * Play Boss Death sound
	 */
	public void playBossDeath() {
		if (sound) {
			Sounds.BOSS_DEATH.play();
		}
	}
	
	/**
	 * Play Open sound
	 */
	public void playOpen() {
		if (sound) {
			Sounds.OPEN.play();
		}
	}
	
	/**
	 * Play Close sound
	 */
	public void playClose() {
		if (sound) {
			Sounds.CLOSE.play();
		}
	}
	
	/**
	 * Play Eyes gather sound
	 */
	public void playEyesGather() {
		if (sound) {
			Sounds.EYES_GATHER.play();
		}
	}
	
	/**
	 * Play Eyes revive sound
	 */
	public void playEyesRevive() {
		if (sound) {
			Sounds.EYES_REVIVE.play();
		}
	}
	
	/**
	 * Play Increase sound
	 */
	public void playIncrease() {
		if (sound) {
			Sounds.INCREASE.play();
		}
	}
	
	/**
	 * Play Decrease sound
	 */
	public void playDecrease() {
		if (sound) {
			Sounds.DECREASE.play();
		}
	}

	/**
	 * Toggle music on/off
	 */
	public void toggleMusic() {
		music = !music;
		if(!music)
			stopMusic();
		else
			playMusic(0, false);
	}

	/**
	 * Toggle sound on/off
	 */
	public void toggleSound() {
		sound = !sound;
	}

	/**
	 * Checks if music is on/off
	 * @return
	 * true if on, false if off
	 */
	public boolean isMusic() {
		return music;
	}

	/**
	 * Checks if sound is on/off
	 * @return
	 * true if on, false if off
	 */
	public boolean isSound() {
		return sound;
	}

	/**
	 * Class to handle the playing of MP3 files
	 */
	private class MusicPlayer extends Thread {
		private String filePath; // MP3 file path
		private boolean loop; // Whether to play once or loop continuously
		private Player musicPlayer; // Player object from JLayer Javazoom

		/**
		 * Constructs a new MusicPlayer object from specified id
		 * @param id
		 * - Id of music track to be played
		 * @param loop
		 * - Whether the music should play once or loop continuously
		 */
		public MusicPlayer(int id, boolean loop) {
			filePath = "music/";
			switch (id) {
			case 0:
				filePath += "Intro.mp3";
				break;
			case 1:
				filePath += "Level1.mp3";
				break;
			case 2:
				filePath += "Boss1.mp3";
				break;
			case 3:
				filePath += "StageStart.mp3";
				break;
			case 4:
				filePath += "Shop.mp3";
				break;
			case 5:
				filePath += "GameOver.mp3";
				break;
			case 6:
				filePath += "Victory.mp3";
				break;
			case 7:
				filePath += "Level2.mp3";
				break;
			case 8:
				filePath += "Boss2.mp3";
				break;
			case 9:
				filePath += "Level3.mp3";
				break;
			case 10:
				filePath += "Boss3.mp3";
				break;
			}
			this.loop = loop;
		}

		@Override
		public void run() {
			try {
				do {
					BufferedInputStream bis = new BufferedInputStream(
							getClass().getClassLoader().getResource(filePath)
									.openStream());
					musicPlayer = new Player(bis);
					musicPlayer.play();
				} while (loop);
			} catch (Exception ioe) {
				ioe.printStackTrace();
			}
		}

		/**
		 * Stops the music track
		 */
		public void close() {
			loop = false;
			boolean closed = false;
			do{
				try{
					musicPlayer.close();
					closed = true;
				}
				catch(NullPointerException e){}
			} while (!closed);
			this.interrupt();
		}
	}

	/**
	 * Contains the information to construct sounds
	 */
	public enum Sounds {
		GATLING("sound/gatling.wav", 5),
		CANNON("sound/cannon.wav", 5),
		SPREAD("sound/spread.wav", 5), 
		GUN("sound/gun.wav", 5), 
		WARNING("sound/warning.wav", 2), 
		CHIME("sound/chime.wav", 3), 
		CHARGING("sound/charging.wav", 1), 
		CHUING("sound/chuing.wav", 2),
		BLASTER("sound/blaster.wav", 5),
		STREAM("sound/stream.wav", 5),
		MINE("sound/minelauncher.wav", 3),
		FIRE("sound/firearrow.wav", 4),
		SWORD("sound/sword.wav", 2),
		OPTION("sound/option.wav", 2),
		BUZZER("sound/buzzer.wav", 2),
		SMALL_EXPLOSION("sound/smallexplosion.wav", 5),
		BIG_EXPLOSION("sound/bigexplosion.wav", 5),
		DEATH("sound/death.wav", 3),
		BOSS_DEATH("sound/bossdeath.wav", 1),
		OPEN("sound/open.wav", 3),
		CLOSE("sound/close.wav", 3),
		EYES_GATHER("sound/eyesgather.wav", 1),
		EYES_REVIVE("sound/eyesrevive.wav", 1),
		INCREASE("sound/increase.wav", 2),
		DECREASE("sound/decrease.wav", 2);

		private Clip[] clip; // Clip array to be played
		private int frame; // Current position in the clip array

		/**
		 * Constructs a Sounds enum constant
		 * @param soundFileName
		 * - File name of .wav file
		 * @param length
		 * - length of Clip array
		 */
		private Sounds(String soundFileName, int length) {
			clip = new Clip[length];
			try {
				for (int i = 0; i < length; i++) {
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource(soundFileName));
					clip[i] = AudioSystem.getClip();
					clip[i].open(audioInputStream);
				}
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			frame = 0;
		}

		/**
		 * Stops the previous sound in the Clip array.
		 * Checks the next sound to be played and stops it if it was already playing
		 * <p>
		 * Then plays the sound from the beginning and goes to next frame
		 */
		private void play() {
			int prevFrame;
			if (frame == 0)
				prevFrame = clip.length - 1;
			else
				prevFrame = frame - 1;
			clip[prevFrame].stop();
			if (clip[frame].isRunning())
				clip[frame].stop();
			clip[frame].setFramePosition(0);
			clip[frame].start();
			frame++;
			frame %= clip.length;
		}
	}
}
