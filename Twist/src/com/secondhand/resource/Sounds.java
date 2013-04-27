package com.secondhand.resource;

import org.anddev.andengine.audio.sound.Sound;

import com.secondhand.loader.SoundLoader;


public class Sounds {

	public Sound powerUpSound;
	public Sound growSound;
	public Sound obstacleCollisionSound;
	public Sound playerKilledSound;
	public Sound winSound;
	
	public Sound beep; 
	
	private static final String SOUND_BASEPATH = "sfx/";
	
	private static Sounds instance;
	
	public static Sounds getInstance() {
		if(instance == null) {
			instance = new Sounds();
		}
		return instance;
	}
	
	public void load() {

		this.powerUpSound = SoundLoader.getInstance().loadSound(SOUND_BASEPATH+"powerup.wav");
		this.growSound = SoundLoader.getInstance().loadSound(SOUND_BASEPATH+"grow.wav");
		this.obstacleCollisionSound = SoundLoader.getInstance().loadSound(SOUND_BASEPATH+"obstacle_collision.wav");
		this.beep = SoundLoader.getInstance().loadSound("sfx/beep.wav");
		this.playerKilledSound = SoundLoader.getInstance().loadSound("sfx/death.wav");
		this.winSound = SoundLoader.getInstance().loadSound("sfx/win.wav");
		
		
	}
	
}
