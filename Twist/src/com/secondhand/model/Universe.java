package com.secondhand.model;

import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Shape;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.PowerUp.Effect;

/**
 * Singelton class for describing the universe.
 */
public final class Universe {
	private Level currentLevel;

	private static Universe instance;

	// perhaps create a tutorialLevel?
	private Universe() {
		currentLevel = new Level();
	}

	public static Universe getInstance() {
		if (instance == null) {
			instance = new Universe();
		}
		return instance;
	}

	// Now there is alot of duplicated code in this method.
	// Perhaps we could extract that code into a new method.
	public void checkCollision(Contact contact) {
		// if one or both is null, then we are dealing with a involving one or
		// two non-entities
		// (ie, a black hole collides with the wall),
		// and we are not interested in handling such a collision
		if (contact.getFixtureA().getBody().getUserData() == null
				|| contact.getFixtureB().getBody().getUserData() == null) {
			return;
		}

		// now we know both the bodies are entities.
		MyDebug.d(contact.getFixtureA().getBody().getUserData().getClass()
				+ " is the A class");
		Entity entityA = (Entity) contact.getFixtureA().getBody().getUserData();
		Entity entityB = (Entity) contact.getFixtureB().getBody().getUserData();

		if (entityA instanceof BlackHole && entityB instanceof Planet
				|| entityB instanceof BlackHole && entityA instanceof Planet) {

			BlackHole blackHole;
			if (entityA instanceof BlackHole) {
				blackHole = (BlackHole) entityA;
			} else {
				blackHole = (BlackHole) entityB;
			}

			Planet planet;
			if (entityA instanceof Planet) {
				planet = (Planet) entityA;
			} else
				planet = (Planet) entityB;

			if (blackHole.canEat(planet)) {

				// remove the planet from physics handling.
				// TODO: this crashes the physics engine. Figure out how to fix.
				// See:
				// http://stackoverflow.com/questions/10835349/crash-when-destroying-bodies
				// and maybe also
				// http://www.box2d.org/forum/viewtopic.php?f=8&t=5601&p=26070#p26070
				/*
				 * final Body planetBody = planet.getBody(); PhysicsWorld pw =
				 * this.level.getPhysicsWorld(); pw.unregisterPhysicsConnector(
				 * pw
				 * .getPhysicsConnectorManager().findPhysicsConnectorByShape(planet
				 * .getShape())); pw.destroyBody(planetBody);
				 */

				// Detach the planet from AndEngine
				planet.getShape().detachSelf();

				// increase the size of the black hole.
				Shape blackHoleShape = blackHole.getBody().getFixtureList()
						.get(0).getShape();
				blackHole.increaseSize(planet.getRadius());
				blackHoleShape.setRadius(blackHole.getRadius()
						/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);

				MyDebug.d("black hole should now eat planet.");
			}
		} else if (entityA instanceof Player && entityB instanceof PowerUp
				|| entityB instanceof Player && entityA instanceof PowerUp) {

			Player player;
			if (entityA instanceof Player) {
				player = (Player) entityA;
			} else {
				player = (Player) entityB;
			}

			PowerUp power;
			if (entityA instanceof PowerUp) {
				power = (PowerUp) entityA;
			} else {
				power = (PowerUp) entityB;
			}
			power.getShape().detachSelf();
			MyDebug.d("Now the powerup should dissapear");

			// now we need a way to have the power up take effect and decide
			// a way to have the effect for a duration
			// Also need to destroy the powerups body
			// the effect should be visible on the players shape

			// lets level take care on what to do with the effect
			currentLevel.activateEffect(power.getEffect());

		}
	}

	public Level getLevel() {
		return currentLevel;
	}

	// TODO how to decide what to have on each successive level?
	public void nextLevel() {
		currentLevel = new Level();
	}

	public void update(Vector2 v) {

		if (currentLevel.checkPlayerBigEnough()) {

			nextLevel();

		} else {

			currentLevel.moveEntities(v);
		}
	}
}
