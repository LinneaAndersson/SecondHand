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
import com.secondhand.scene.GamePlayScene;

public class CollisionContactListener implements ContactListener{

	private GamePlayScene scene;
	private Level level;

	public CollisionContactListener(GamePlayScene scene, Level level) {
		this.scene = scene;
		this.level = level;
	}

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

		final Entity entityA = (Entity)contact.getFixtureA().getBody().getUserData();
		final Entity entityB = (Entity)contact.getFixtureB().getBody().getUserData();

		if(
				entityA instanceof BlackHole && entityB instanceof Planet ||
				entityB instanceof BlackHole && entityA instanceof Planet) {

			BlackHole blackHole;
			if(entityA instanceof BlackHole) {
				blackHole = (BlackHole)entityA;
			} else {
				blackHole = (BlackHole)entityB;
			}
			
			Planet planet;
			if(entityA instanceof Planet) {
				planet = (Planet)entityA;
			} else {
				planet = (Planet)entityB;
			}
				
			if(blackHole.canEat(planet)) {

				// remove the planet from physics handling.
			// TODO: this crashes the physics engine. Figure out how to fix. See:
			// http://stackoverflow.com/questions/10835349/crash-when-destroying-bodies
                        // and maybe also http://www.box2d.org/forum/viewtopic.php?f=8&t=5601&p=26070#p26070
                            /*final Body planetBody = planet.getBody();
				PhysicsWorld pw = this.level.getPhysicsWorld();
				pw.unregisterPhysicsConnector(
						pw.getPhysicsConnectorManager().findPhysicsConnectorByShape(planet.getShape()));
				pw.destroyBody(planetBody);*/

				// deattach the planet from AndEngine
				planet.getShape().detachSelf();

				// increase the size of the black hole.
				final Shape blackHoleShape = blackHole.getBody().getFixtureList().get(0).getShape();
				blackHole.increaseSize(planet.getRadius());
				blackHoleShape.setRadius(blackHole.getRadius() / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);

				MyDebug.d("black hole should now eat planet.");

			}

		}


	}

	// ignore these
	@Override
	public final void endContact(Contact contact) {}
	@Override
	public final void preSolve(Contact contact, Manifold oldManifold) { }
	@Override
	public final void postSolve(Contact contact, ContactImpulse impulse) {}

}
