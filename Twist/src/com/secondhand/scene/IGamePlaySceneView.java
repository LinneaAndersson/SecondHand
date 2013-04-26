package com.secondhand.scene;

import com.badlogic.gdx.math.Vector2;

// the level uses this interface to talk with the view
public interface IGamePlaySceneView {

	void showFadingTextNotifier(final String str, final Vector2 position);
	
	void newLevelStarted();
}
