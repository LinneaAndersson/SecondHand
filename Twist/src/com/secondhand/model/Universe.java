package com.secondhand.model;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Shape;
import com.secondhand.debug.MyDebug;
import com.secondhand.physics.PhysicsDestroyer;
import com.secondhand.twirl.GlobalResources;


/**
 * Singelton class for describing the universe.
 */
public final class Universe {
	private Level currentLevel;

	private final PhysicsDestroyer physicsDestroyer;

	private Engine engine;

	// flag for when the game is over
	// check by update by gamePlayScene
	private boolean gameOver;

	private static Universe instance;

	// perhaps create a tutorialLevel?
	private Universe() {
		currentLevel = new Level();
		physicsDestroyer = new PhysicsDestroyer();
		gameOver = false;
	}

	public static Universe getInstance() {
		if (instance == null) {
			instance = new Universe();
		}
		return instance;
	}

	// needs a better way to reach the engine than setting it
	public void setEngine(final Engine engine) {
		this.engine = engine;
	}

	
	private void handlePlanetBlackHoleCollision(final Entity entityA, final Entity entityB) {

		BlackHole blackHole;
		if (entityA instanceof BlackHole) {
			blackHole = (BlackHole) entityA;
		} else {
			blackHole = (BlackHole) entityB;
		}

		Planet planet;
		if (entityA instanceof Planet) {
			planet = (Planet) entityA;
		} else {
			planet = (Planet) entityB;
		}
		if (blackHole.canEat(planet)) {

			GlobalResources.getInstance().growSound.play();

			// Detach the planet from AndEngine
			planet.getShape().detachSelf();

			// increase the size of the black hole.
			// TODO seems like there is something wrong here. after eating,
			// the
			// contact area extends outside of the shape. it also seems like
			// the screen area you can touch for movement decreases(could
			// just be me)
			final Shape blackHoleShape = blackHole.getBody()
					.getFixtureList().get(0).getShape();
			// perhaps we could divide radius by 2?
			// otherwise i believe it would grow to fast
			blackHole.increaseSize(planet.getRadius());
			// see this for setting the correct radius:
			// http://www.emanueleferonato.com/2009/12/12/scaling-objects-with-box2d/
			blackHoleShape.setRadius(blackHole.getRadius()
					/ PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);

			MyDebug.d("black hole should now eat planet.");

			physicsDestroyer.destroy(planet.getShape(), true, this.currentLevel.getPhysicsWorld(), this.engine);
		}

	}
	
	
	private void handlePowerUpPlayerCollision(final Entity entityA, final Entity entityB) {

		GlobalResources.getInstance().powerUpSound.play();

		PowerUp power;
		if (entityA instanceof PowerUp) {
			power = (PowerUp) entityA;
		} else {
			power = (PowerUp) entityB;
		}

		power.getShape().detachSelf();
		MyDebug.d("Now the powerup should dissappear");

		physicsDestroyer.destroy(power.getShape(), true, this.currentLevel.getPhysicsWorld(), this.engine);

		// TODO (in Level?) now we need a way to have the power up take
		// effect and decide a way to have the effect for a duration
		// the effect should be visible on the players shape

		currentLevel.activateEffect(power.getEffect());

	}
	
	
	// TODO Now there is alot of duplicated code in this method.
	// Perhaps we could extract that code into a new method.
	public void checkCollision(final Contact contact) {
		// if one or both is null, then we are dealing with a collision
		// involving one or
		// two non-entities
		// (ie, a black hole collides with the wall),
		// and we are not interested in handling such a collision
		if (contact.getFixtureA().getBody().getUserData() == null
				|| contact.getFixtureB().getBody().getUserData() == null) {
			return;
		}

		// now we know both the bodies are entities.
		final Entity entityA = (Entity) contact.getFixtureA().getBody()
				.getUserData();
		final Entity entityB = (Entity) contact.getFixtureB().getBody()
				.getUserData();

		if (entityA instanceof BlackHole && entityB instanceof Planet
				|| entityB instanceof BlackHole && entityA instanceof Planet) {
			handlePlanetBlackHoleCollision(entityA, entityB);
		} else if (entityA instanceof Player && entityB instanceof PowerUp
				|| entityB instanceof Player && entityA instanceof PowerUp) {
			handlePowerUpPlayerCollision(entityA, entityB);
		} else if (entityA instanceof Player && entityB instanceof Obstacle
				|| entityB instanceof Player && entityA instanceof Obstacle) {
			GlobalResources.getInstance().obstacleCollisionSound.play();
		}

	}

	public boolean isGameOver() {
		return gameOver;
	}
	
	// perhaps not needed if we only set gameover
	private void gameOver() {
		gameOver = true;
		// gameOver flag that gameplayScene checks each update
		// other gameOver stuff in this method

	}


	public Level getLevel() {
		return currentLevel;
	}

	// TODO how to decide what to have on each successive level?

	// If after gameOver a new game should start, this method
	// could be called. Also the case where the player starts
	// a new game when there already is one saved could be
	// handled here. In both cases there already is a Universe
	// so everything having to do with new level could be handled here.

	public void nextLevel() {
		if (!gameOver) {
			currentLevel = new Level(currentLevel);
		} else {
			gameOver = false;
			currentLevel = new Level();
		}
	}

	public void update(final Vector2 v) {

		if (currentLevel.checkPlayerBigEnough()) {

			nextLevel();

		} else {

			currentLevel.moveEntities(v);
		}
	}
}
