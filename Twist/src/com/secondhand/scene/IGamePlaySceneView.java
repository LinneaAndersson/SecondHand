package com.secondhand.scene;

import com.badlogic.gdx.math.Vector2;

// the level uses this interface to talk with the view
public interface IGamePlaySceneView {

	void pickedUpScorePowerUp(final int score, final Vector2 position);
	
}
