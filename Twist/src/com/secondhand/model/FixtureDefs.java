package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;

import com.badlogic.gdx.physics.box2d.FixtureDef;

// holds all the fixture defs of all the entities. 
// these are used to set things like friction on all entities. 
public final class FixtureDefs {

	private FixtureDefs() {}
	
	
	private final static float FRICTION = 1	;
	
	// http://www.box2d.org/manual.html chapter 6 for an explanation of the parameters.
	
	public final static FixtureDef BLACK_HOLE_FIXTURE_DEF = PhysicsFactory.createFixtureDef(1, 0.25f, FRICTION);
	
	// if we set restitution too high, planets will bounce like crazy
	public final static FixtureDef PLANET_FIXTURE_DEF = PhysicsFactory.createFixtureDef(5f ,0.25f, FRICTION);
	
	// obstacles have big densities because we want them to get in the way
	// for the same reason, restitution is also low.
	public final static FixtureDef OBSTACLE_FIXTURE_DEF = PhysicsFactory.createFixtureDef(10, 0.1f, FRICTION);
	
	public final static FixtureDef POWER_UP_FIXTURE_DEF = PhysicsFactory.createFixtureDef(1, 0.1f, FRICTION);

}
