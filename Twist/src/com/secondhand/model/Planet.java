package com.secondhand.model;

import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.Circle;
import com.secondhand.opengl.TexturedCircle;

public class Planet extends CircleEntity {

	public Planet (final Vector2 position, final float radius, final TextureRegion textureRegion) {
		super(new TexturedCircle(position.x, position.y, radius, textureRegion), true);
	}
	
	/**
	 * Create a colored planet. This constructor is much easier to test.
	 */
	public Planet (final Vector2 position, final float radius) {
		super(new Circle(position.x, position.y, radius), true);
	}
}