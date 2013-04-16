package com.secondhand.model;

import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.badlogic.gdx.math.Vector2;
import com.secondhand.opengl.Circle;
import com.secondhand.opengl.TexturedCircle;

public class Planet extends Entity {

	public Planet (Vector2 position, float radius, TextureRegion textureRegion) {
		// give a textured circle. 
		super(position,radius,  
				new TexturedCircle(position.x, position.y, radius, textureRegion));
	}
}