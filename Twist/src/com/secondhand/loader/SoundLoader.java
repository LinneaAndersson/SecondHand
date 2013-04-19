package com.secondhand.loader;

import java.io.IOException;

import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.audio.sound.SoundFactory;

import com.secondhand.debug.MyDebug;

/**
 * Singleton Convenience class for simplifying the loading of sounds.
 */
public class SoundLoader extends Loader {
	
	private static SoundLoader instance;
	
	public static SoundLoader getInstance() {
		if(instance == null) {
			instance = new SoundLoader();
		}
		return instance;
	}


	public Sound loadSound(final String fileName) {
    	try {
    		final Sound loadedSound = SoundFactory.createSoundFromAsset(
    				this.engine.getSoundManager(), this.context, fileName);
    		return loadedSound;
    	} 
    	catch (final IOException e) {
    		MyDebug.e("could not load sound \"" + fileName + "\":" + e);
    		return null;
    	}
    }

}
