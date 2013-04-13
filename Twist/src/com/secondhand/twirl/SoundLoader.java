package com.secondhand.twirl;

import java.io.IOException;

import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.audio.sound.SoundFactory;


/**
 * Singleton Convenience class for simplifying the loading of sounds.
 */
public class SoundLoader extends Loader {
	
	private static SoundLoader instance = null;
	
	public static SoundLoader getInstance() {
		if(instance == null) {
			instance = new SoundLoader();
		}
		return instance;
	}

	@Override
	public void setAssetBasePath(final String path) {
		SoundFactory.setAssetBasePath(path);
	}

	@Override
	public String getDefaultAssetBasePath() {
		return "sfx/";
	}
	
	public Sound loadSound(final String fileName) {
    	try {
    		Sound loadedSound = SoundFactory.createSoundFromAsset(
    				this.engine.getSoundManager(), this.context, fileName);
    		return loadedSound;
    	} 
    	catch (final IOException e) {
    		MyDebug.e("could not load sound \"" + fileName + "\":" + e);
    		return null;
    	}
    }

}
