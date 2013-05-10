package com.secondhand.view.resource;

import org.anddev.andengine.audio.sound.Sound;

import com.secondhand.model.resource.SoundType;
import com.secondhand.view.loader.SoundLoader;


public class Sounds {

	public Sound powerUpSound;
	public Sound growSound;
	public Sound obstacleCollisionSound;
	public Sound playerKilledSound;
	public Sound winSound;
	public Sound highScoreEntry;
	
	public Sound beep; 
		
	private static Sounds instance;
	
	public static Sounds getInstance() {
		if(instance == null) {
			instance = new Sounds();
		}
		return instance;
	}
	
	public void load() {
		
		this.powerUpSound = SoundLoader.getInstance().loadSound(SoundType.POWERUP_SOUND);
		this.growSound = SoundLoader.getInstance().loadSound(SoundType.GROW_SOUND);
		this.obstacleCollisionSound = SoundLoader.getInstance().loadSound(SoundType.OBSTACLE_COLLISION_SOUND);
		this.beep = SoundLoader.getInstance().loadSound(SoundType.BEEP_SOUND);
		this.playerKilledSound = SoundLoader.getInstance().loadSound(SoundType.PLAYER_KILLED_SOUND);
		this.winSound = SoundLoader.getInstance().loadSound(SoundType.WIN_SOUND);
		this.highScoreEntry = SoundLoader.getInstance().loadSound(SoundType.HIGHSCORE_SOUND);
			
	}
	
	public Sound getSound(String soundName){
		if(soundName.equalsIgnoreCase("PowerUp"))
			return powerUpSound;
		
		if(soundName.equalsIgnoreCase("grow"))
			return growSound;
		
		if(soundName.equalsIgnoreCase("obstacleCollision"))
			return obstacleCollisionSound;
		
		if(soundName.equalsIgnoreCase("Beep"))
			return beep;
		
		if(soundName.equalsIgnoreCase("playerKilled"))
			return playerKilledSound;
		
		if(soundName.equalsIgnoreCase("Win"))
			return winSound;
	
		if(soundName.equalsIgnoreCase("Highscore"))
			return highScoreEntry;
		
		return null;
	}
	
}
