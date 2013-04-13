package com.secondhand.twirl;

import java.util.List;

import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;

public class GameScene extends Scene {

	public GameScene (List<IEntity> entities, ColorBackground background) {
		for (IEntity entity : entities)
			attachChild(entity);
		setBackground(background);
	}
	
}
