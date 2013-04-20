package com.secondhand.model;

import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.Circle;
import com.secondhand.opengl.TexturedCircle;

public class Planet extends Entity {

	public Planet (Vector2 position, float radius, TextureRegion textureRegion) {
		super(position,radius,  
				new TexturedCircle(position.x, position.y, radius, textureRegion), true);
	}
	
	/**
	 * Create a colored planet. This constructor is much easier to test.
	 */
	public Planet (Vector2 position, float radius) {
		super(position,radius,  
				new Circle(position.x, position.y, radius), true);
	}
}