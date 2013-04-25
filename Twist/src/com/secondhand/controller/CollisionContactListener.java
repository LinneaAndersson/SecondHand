package com.secondhand.controller;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.secondhand.model.Universe;

public class CollisionContactListener implements ContactListener {

	/**
	 * Called when two fixtures begin to touch. So this is the method that we
	 * are interested in for handling the collision between black holes and
	 * other entities. You can simply ignore the other three
	 */
	@Override
	public void beginContact(final Contact contact) {
		Universe.getInstance().getLevel().checkCollision(contact);
	}

	@Override
	public void endContact(final Contact contact) {}
	
	@Override
	public void preSolve(final Contact contact, final Manifold oldManifold) {
	}
	@Override
	public void postSolve(final Contact contact, final ContactImpulse impulse) {
	}

}
