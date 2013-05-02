package com.secondhand.view.loader;

import java.io.IOException;

import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.audio.sound.SoundFactory;

import com.secondhand.debug.MyDebug;
import com.secondhand.model.resource.SoundType;

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


	public Sound loadSound(final SoundType sound) {
    	try {
    		final Sound loadedSound = SoundFactory.createSoundFromAsset(
    				this.engine.getSoundManager(), this.context, sound.getPath());
    		return loadedSound;
    	} 
    	catch (final IOException e) {
    		MyDebug.e("could not load sound \"" + sound.getPath() + "\":" + e);
    		return null;
    	}
    }

}
