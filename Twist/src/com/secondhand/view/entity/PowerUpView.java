package com.secondhand.view.entity;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.secondhand.model.powerup.PowerUp;
import com.secondhand.view.physics.FixtureDefs;
import com.secondhand.view.resource.TextureRegions;

public class PowerUpView extends RectangleView {


	public PowerUpView(final PhysicsWorld physicsWorld, final PowerUp powerUp) {
		super(physicsWorld, 
				powerUp,
				new Sprite(powerUp.getInitialPosition().x, powerUp.getInitialPosition().y, powerUp.getWidth(), 
						powerUp.getHeight(), TextureRegions.getInstance().getPowerUpTexture(powerUp.getPowerUpType()))
				, FixtureDefs.POWER_UP_FIXTURE_DEF);
	}


}
