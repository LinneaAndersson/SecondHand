package com.secondhand.controller;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.BlackHole;
import com.secondhand.model.Entity;
import com.secondhand.model.Planet;

public class CollisionContactListener implements ContactListener{

	/**
     * Called when two fixtures begin to touch.
     * So this is the method that we are interested in for handling the collision between 
     * black holes and other entities. You can simply ignore the other three
     */
	@Override
	public void beginContact(Contact contact) {
		
		// if one or both is null, then we are dealing with a involving one or two non-entities
		// (ie, a black hole collides with the wall), 
		//and we are not interested in handling such a collision
		if(contact.getFixtureA().getBody().getUserData() == null ||
				contact.getFixtureB().getBody().getUserData() == null) {
			return;
		}
	
		// now we know both the bodies are entities.
		
		Entity entityA = (Entity)contact.getFixtureA().getBody().getUserData();
		Entity entityB = (Entity)contact.getFixtureB().getBody().getUserData();
		
		if(
				entityA instanceof BlackHole && entityB instanceof Planet ||		
				entityB instanceof BlackHole && entityA instanceof Planet) {
			MyDebug.d("collision between black hole and planet. black hole should now eat planet.");
		}
		
		
	}
	
	// ignore these
	@Override
	public void endContact(Contact contact) {}
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) { }
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {}

}
