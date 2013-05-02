package com.secondhand.view.scene;

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

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.secondhand.model.resource.Fonts;
import com.secondhand.model.resource.LocalizationStrings;
import com.secondhand.model.resource.Sounds;


public class SettingsMenuScene extends GameMenuScene implements
		IOnMenuItemClickListener {

	private static final int MENU_HIGHER = 0;
	private static final int MENU_LOWER = 1;

	// maxium value in the volume scale(minimum is always 0)
	private static final int VOLUME_SCALE_MAX = 100;
	// the volume change when a button in the control is pressed.
	private static final int VOLUME_CHANGE = 10;

	// the strings used to identify the volume setting in the settings.
	private static final String VOLUME_SETTING_STRING = "volume";

	private static final int DEFAULT_VOLUME = VOLUME_SCALE_MAX;

	private final SoundManager soundManager;
	private final MusicManager musicManager;

	private ChangeableText volumeText;

	private final SharedPreferences sharedPreferences;
	private final SharedPreferences.Editor sharedPreferencesEditor;


	public SettingsMenuScene(final Engine engine, final Context context) {
		super(engine, context);

		// used for volume control:
		this.soundManager = this.engine.getSoundManager();
		this.musicManager = this.engine.getMusicManager();
		
		// load the preferences:
		sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(this.context);
		sharedPreferencesEditor = sharedPreferences.edit();

	}
	
	@Override
	public void loadScene() {
		super.loadScene();

		// layout headline
		final int menuStartY = layoutHeadline(LocalizationStrings.getInstance()
				.getLocalizedString("menu_settings"));

		// constants

		// the x-position of the settings items.
		final int x = 100;
		// ie, the spacing between volume and the actual volume control.
		final int subheadlineInbetweenSpacing = 5;

		final Font font = Fonts.getInstance().menuItemFont;
		final float fontHeight = new Text(0, 0, font, "lorem ipsum")
				.getHeight();

		// now layout the menu items:
		int y = menuStartY;

		// volume subheading
		this.attachChild(new Text(x, y, font, LocalizationStrings.getInstance()
				.getLocalizedString("menu_volume")));
		y += fontHeight + subheadlineInbetweenSpacing;

		// the actual volume control:
		// TODO: make it look prettier.

		volumeText = new ChangeableText(x, y, font, "iojiojo");
		updateVolumeText();
		setSoundAndMusicVolume(sharedPreferences.getInt(VOLUME_SETTING_STRING,
				DEFAULT_VOLUME));

		this.attachChild(volumeText);
		y += fontHeight + subheadlineInbetweenSpacing;

		final IMenuItem higherItem = new TextMenuItem(MENU_HIGHER, font, "Higher");
		higherItem.setPosition(x, y);
		addMenuItem(higherItem);
		y += fontHeight + subheadlineInbetweenSpacing;

		final IMenuItem lowerItem = new TextMenuItem(MENU_LOWER, font, "Lower");
		lowerItem.setPosition(x, y);
		addMenuItem(lowerItem);

		this.setOnMenuItemClickListener(this);
	}

	private void updateVolumeText() {
		final int volume = sharedPreferences.getInt(VOLUME_SETTING_STRING,
				DEFAULT_VOLUME);
		volumeText.setText(volume + "%");
	}

	// set the music and sound volume(a scale of 0-100 is used.
	private void setSoundAndMusicVolume(final int newVolume) {
		// sanity checking:
		if (newVolume < 0 || newVolume > VOLUME_SCALE_MAX) {
			throw new IllegalArgumentException(
					"The volume be set in a scale of 0-100");
		}

		this.soundManager.setMasterVolume((float) newVolume
				/ (float) VOLUME_SCALE_MAX);
		this.musicManager.setMasterVolume((float) newVolume
				/ (float) VOLUME_SCALE_MAX);

		// also save it in the settings:
		sharedPreferencesEditor.putInt(VOLUME_SETTING_STRING, newVolume);
		sharedPreferencesEditor.commit();
	}

	private int getSoundAndMusicVolume() {
		return Math.round(this.soundManager.getMasterVolume()
				* VOLUME_SCALE_MAX);
	}

	// the volume is always kept in the range 0-100.
	// it is also specified in this range.
	private void changeSoundAndMusicVolume(final int volumeChange) {
		final int oldVolume = getSoundAndMusicVolume();
		int newVolume = oldVolume + volumeChange;

		// keep in range
		if (newVolume < 0) {
			newVolume = 0;
		}
		if (newVolume > VOLUME_SCALE_MAX) {
			newVolume = VOLUME_SCALE_MAX;
		}

		setSoundAndMusicVolume(newVolume);
	}

	private void changeVolumeAndUpdateVolumeText(final int volumeChange) {
		changeSoundAndMusicVolume(volumeChange);
		updateVolumeText();
	}

	@Override
	public boolean onMenuItemClicked(final MenuScene pMenuScene,
			final IMenuItem pMenuItem, final float pMenuItemLocalX,
		final float pMenuItemLocalY) {

		switch (pMenuItem.getID()) {
		case MENU_HIGHER:
			changeVolumeAndUpdateVolumeText(VOLUME_CHANGE);
			Sounds.getInstance().beep.play();
			return true;
		case MENU_LOWER:
			changeVolumeAndUpdateVolumeText(-VOLUME_CHANGE);
			Sounds.getInstance().beep.play();
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
