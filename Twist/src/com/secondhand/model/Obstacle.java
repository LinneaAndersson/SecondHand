package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.secondhand.opengl.Polygon;

public class Obstacle extends PolygonEntity {

	public Obstacle(final Polygon polygon, final PhysicsWorld physicsWorld) {
		super(polygon, false, physicsWorld);
	}
}
