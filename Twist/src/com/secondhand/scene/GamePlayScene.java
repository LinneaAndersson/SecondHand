package com.secondhand.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;

import com.secondhand.loader.TextureRegionLoader;

import android.content.Context;

public class GamePlayScene extends GameScene {
	
	Sprite player_sprite;
	
	public GamePlayScene(Engine engine, Context context) {
		super(engine, context);
	}

	@Override
	public void loadResources() {
		// load the resources of this scene
		player_sprite = new Sprite(0, 0, TextureRegionLoader.getInstance().loadTextureRegion("gfx/player.png", 512, 512)); // the path variable in TextureRegionLoader didnt provide "gfx/" correctly for me
	}

	@Override
	public void loadScene() {
		// now load the scene(attach all the entities)
		setBackground(new ColorBackground(1f, 1f, 1f));
		attachChild(player_sprite);
	}

}
