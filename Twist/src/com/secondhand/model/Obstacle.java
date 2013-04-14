package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

public class Obstacle extends Entity {

	public Obstacle(Vector2 vector, int radius) {
		super(vector,radius);
	}
	@Override
	public Entity_enum getEnum() {
		// TODO Auto-generated method stub
		return Entity_enum.OBSTACLE;
	}

}
