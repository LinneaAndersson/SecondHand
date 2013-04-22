package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.Circle;
import com.secondhand.opengl.TexturedCircle;

public class Planet extends CircleEntity {

	public Planet (final Vector2 position, final float radius, final TextureRegion textureRegion, final PhysicsWorld physicsWorld) {
		super(new TexturedCircle(position.x, position.y, radius, textureRegion), true, physicsWorld, true);
	}
	
	/**
	 * Create a colored planet. This constructor is much easier to test.
	 */
	public Planet (final Vector2 position, final float radius, final PhysicsWorld physicsWorld) {
		super(new Circle(position.x, position.y, radius), true, physicsWorld, true);
	}
}