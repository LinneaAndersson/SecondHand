package com.secondhand.view.entities;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.secondhand.model.BlackHole;
import com.secondhand.view.physics.FixtureDefs;

public class BlackHoleView extends CircleView {

	public BlackHoleView(final PhysicsWorld physicsWorld,
			final BlackHole blackHole, final TextureRegion textureRegion) {
		super(physicsWorld, blackHole, new Sprite(
				blackHole.getInitialPosition().x, 
				blackHole.getInitialPosition().y, 
				blackHole.getRadius() * 2,
				blackHole.getRadius() * 2,
				textureRegion
				),
				FixtureDefs.BLACK_HOLE_FIXTURE_DEF);
	}
	
}
