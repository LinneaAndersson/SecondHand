package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

public class Planet extends Entity {

	public Planet (Vector2 position, float radius) {
		// give a textured circle. 
		super(position,radius, null);
	}

}