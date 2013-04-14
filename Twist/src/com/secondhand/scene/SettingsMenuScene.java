package com.secondhand.scene;


import org.anddev.andengine.audio.music.MusicManager;
import org.anddev.andengine.audio.sound.SoundManager;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.scene.menu.item.TextMenuItem;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;


import com.secondhand.controller.SceneManager.AllScenes;
import com.secondhand.debug.MyDebug;
import com.secondhand.twirl.GlobalResources;

public class SettingsMenuScene extends GameMenuScene implements IOnMenuItemClickListener {

	private static final int MENU_HIGHER = 0;
	private static final int MENU_LOWER = 1;
	
	// maxium value in the volume scale(minimum is always 0)
	private static final int VOLUME_SCALE_MAX = 100;
	// the volume change when a button in the control is pressed.
	private static final int VOLUME_CHANGE = 10;
	
	
	private final SoundManager soundManager;
	private final MusicManager musicManager;
	
	private ChangeableText volumeText;
	
	public SettingsMenuScene(Engine engine) {
		super(engine);
		
		// used for volume control:
		this.soundManager =this.engine.getSoundManager();
		this.musicManager =this.engine.getMusicManager();
	}

	@Override
	public void loadResources() {
		// TODO: load the saved volume settings from a file.
	}

	@Override
	public void loadScene() {
		
		// layout headline
		int menuStartY = layoutHeadline("Settings");	
		
		
		// constants
		
		// the x-position of the settings items. 
		final int x = 100;
		// ie, the spacing between volume and the actual volume control.
		final int subheadlineInbetweenSpacing = 5;
		
		final Font font = GlobalResources.getInstance().menuItemFont;
		final float fontHeight = new Text(0, 0, font, "lorem ipsum").getHeight();
		
		// now layout the menu items:
		int y = menuStartY;
		
		// volume subheading
		this.attachChild(new Text(x,y, font, "volume"));
		y += fontHeight + subheadlineInbetweenSpacing;
		
		// the actual volume control:
		// TODO: make it look prettier.
		
		volumeText = new ChangeableText(x,y, font, "100%");
		this.attachChild(volumeText);
		y += fontHeight + subheadlineInbetweenSpacing;
		
		IMenuItem higherItem = new TextMenuItem(MENU_HIGHER, font, "Higher");
		higherItem.setPosition(x, y);
		addMenuItem(higherItem);
		y += fontHeight + subheadlineInbetweenSpacing;
		
		IMenuItem lowerItem = new TextMenuItem(MENU_LOWER, font, "Lower");
		lowerItem.setPosition(x, y);
		addMenuItem(lowerItem);
		
		this.setOnMenuItemClickListener(this);
	}
	
	// set the music and sound volume(a scale of 0-100 is used.
	private void setSoundAndMusicVolume(final int newVolume) {
		// sanity checking:
		if(newVolume < 0 || newVolume > VOLUME_SCALE_MAX) {
			throw new IllegalArgumentException("The volume be set in a scale of 0-100");
		}
		
		this.soundManager.setMasterVolume((float)newVolume / (float)VOLUME_SCALE_MAX);
		this.musicManager.setMasterVolume((float)newVolume / (float)VOLUME_SCALE_MAX);
	}
	
	private int getSoundAndMusicVolume() {
		return Math.round(this.soundManager.getMasterVolume() * VOLUME_SCALE_MAX);
	}
	
	// the volume is always kept in the range 0-100.
	// it is also specified in this range.
	private void changeSoundAndMusicVolume(final int volumeChange) {
		final int oldVolume = getSoundAndMusicVolume();
		int newVolume = oldVolume + volumeChange;
		
		// keep in range
		if(newVolume < 0)  
			newVolume = 0;
		if(newVolume > VOLUME_SCALE_MAX) 
			newVolume = VOLUME_SCALE_MAX;
		
		setSoundAndMusicVolume(newVolume);
	}

	private void changeVolumeAndUpdateVolumeText(int volumeChange) {
		changeSoundAndMusicVolume(volumeChange);
		volumeText.setText(getSoundAndMusicVolume() + "%");
	}
	
	@Override
	public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		
		switch(pMenuItem.getID()) {
		case MENU_HIGHER:
			changeVolumeAndUpdateVolumeText(VOLUME_CHANGE);
			return true;
		case MENU_LOWER:
			changeVolumeAndUpdateVolumeText(-VOLUME_CHANGE);
			return true;
		default:
			return false;
		}

	}

	@Override
	public AllScenes getParentScene() {
		return AllScenes.MAIN_MENU_SCENE;
	}
	
}
