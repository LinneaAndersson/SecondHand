package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;

import com.badlogic.gdx.physics.box2d.FixtureDef;

// holds all the fixture defs of all the entities. 
// these are used to set things like friction on all entities. 
public final class FixtureDefs {

	private FixtureDefs() {}
	
	public final static FixtureDef BLACK_HOLE_FIXTURE_DEF = PhysicsFactory.createFixtureDef(1, 0.5f,0.5f);
	public final static FixtureDef PLANET_FIXTURE_DEF = PhysicsFactory.createFixtureDef(1, 0.5f,0.5f);
	public final static FixtureDef OBSTACLE_FIXTURE_DEF = PhysicsFactory.createFixtureDef(1, 0.5f,0.5f);
	public final static FixtureDef POWER_UP_FIXTURE_DEF = PhysicsFactory.createFixtureDef(1, 0.5f,0.5f);

}
