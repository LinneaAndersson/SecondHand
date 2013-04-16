package com.secondhand.model;

import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.TexturedCircle;

public class Obstacle extends Entity {

	public Obstacle(Vector2 position, float radius, TextureRegion textureRegion) {
		// TODO: pass down the shape of the obstacle
		super(position,radius, new TexturedCircle(position.x, position.y, radius, textureRegion));
	}
}
