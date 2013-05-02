package com.secondhand.view.resource;

import org.anddev.andengine.audio.sound.Sound;

import com.secondhand.loader.SoundLoader;
import com.secondhand.model.resource.SoundType;


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
	
}
