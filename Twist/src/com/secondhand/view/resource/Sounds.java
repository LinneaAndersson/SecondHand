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
		
		// make soundLoader accept SoundType
		this.powerUpSound = SoundLoader.getInstance().loadSound(SoundType.POWERUP_SOUND.getPath());
		this.growSound = SoundLoader.getInstance().loadSound(SoundType.GROW_SOUND.getPath());
		this.obstacleCollisionSound = SoundLoader.getInstance().loadSound(SoundType.OBSTACLE_COLLISION_SOUND.getPath());
		this.beep = SoundLoader.getInstance().loadSound(SoundType.BEEP_SOUND.getPath());
		this.playerKilledSound = SoundLoader.getInstance().loadSound(SoundType.PLAYER_KILLED_SOUND.getPath());
		this.winSound = SoundLoader.getInstance().loadSound(SoundType.WIN_SOUND.getPath());
		
		this.highScoreEntry = SoundLoader.getInstance().loadSound(SoundType.HIGHSCORE_SOUND.getPath());
			
	}
	
}
