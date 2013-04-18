package com.secondhand.controller;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.Shape;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.BlackHole;
import com.secondhand.model.Entity;
import com.secondhand.model.Level;
import com.secondhand.model.Planet;
import com.secondhand.model.Universe;
import com.secondhand.scene.GamePlayScene;

public class CollisionContactListener implements ContactListener{


	/**
	 * Called when two fixtures begin to touch.
	 * So this is the method that we are interested in for handling the collision between
	 * black holes and other entities. You can simply ignore the other three
	 */
	@Override
	public void beginContact(Contact contact) {
		Universe.getInstance().checkCollision(contact);
	}




	// ignore these
	@Override
	public void endContact(Contact contact) {}
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) { }
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {}

}
