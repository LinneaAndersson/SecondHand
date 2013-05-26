package com.secondhand.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

import com.secondhand.model.entity.BlackHole;
import com.secondhand.model.entity.Enemy;
import com.secondhand.model.entity.Entity;
import com.secondhand.model.entity.GameWorld;
import com.secondhand.model.entity.IPowerUp;
import com.secondhand.model.entity.IPowerUpFactory;
import com.secondhand.model.entity.Planet;
import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.ICollisionResolver;
import com.secondhand.model.physics.IPhysicsEntity;
import com.secondhand.model.physics.IPhysicsObject;
import com.secondhand.model.physics.IPhysicsWorld;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.powerup.PowerUp;
import com.secondhand.model.powerup.PowerUpFactory;
import com.secondhand.model.resource.PlanetType;
import com.secondhand.model.resource.PowerUpType;

/* This class tests not only GameWorld but also EntityManager*/
public class GameWorldTest extends TestCase implements PropertyChangeListener {
	private class PUp extends PowerUp {

		public PUp(Vector2 position, PowerUpType powerUpType, float duration) {
			super(position, powerUpType, duration);
		}

		@Override
		public void activateEffect(Player player) {
		}

		public void fire(String name, Entity entity) {
			pcs.firePropertyChange(name, null, entity);
		}

	}

	private class SpecialTestFactory implements IPowerUpFactory {
		PUp s = new PUp(new Vector2(), PowerUpType.SCORE_UP, 0);

		@Override
		public IPowerUp getRandomPowerUp(Vector2 position) {

			return s;
		}

		@Override
		public void setRandom(Random rng) {

		}

		public PUp getSpecialEntity() {
			return s;
		}

	}

	private class TestPhysicsWorld implements IPhysicsWorld {

		@Override
		public void setWorldBounds(int levelWidth, int levelHeight) {
		}

		@Override
		public void removeWorldBounds() {
		}

		@Override
		public boolean isAreaUnOccupied(float x, float y, float r) {
			return false;
		}

		@Override
		public Vector2 getRandomUnOccupiedArea(int worldWidth, int worldHeight,
				float r, Random rng) {
			return new Vector2(20, 20);
		}

		@Override
		public void setContactListener() {
		}

		@Override
		public void unsetContactListener() {
		}

		@Override
		public void setCollisionResolver(ICollisionResolver collisionResolver) {
		}

	}

	private class TestPhysicsEntity implements IPhysicsEntity {
		private Vector2 pos = new Vector2();
		private Vector2 impulse;
		boolean delete = false;

		@Override
		public float getCenterX() {
			return pos.x;
		}

		@Override
		public float getCenterY() {
			return pos.y;
		}

		@Override
		public void deleteBody() {
			delete = true;
		}

		@Override
		public void applyImpulse(Vector2 impulsePosition, float maxSpeed) {
		}

		@Override
		public void applyImpulse(Vector2 impulse, Vector2 impulsePosition) {

		}

		@Override
		public void setLinearDamping(float f) {
		}

		@Override
		public void detachSelf() {
		}

		@Override
		public float computePolygonRadius(List<Vector2> polygon) {
			return 0;
		}

		@Override
		public void setTransform(Vector2 position) {
			pos = position;
		}

		@Override
		public void stopMovment() {
		}

		@Override
		public boolean isStraightLine(IPhysicsObject entity,
				IPhysicsObject enemy) {
			return true;
		}

		@Override
		public float getVelocity() {
			return 0;
		}

	}

	private int lives = 1;
	private int levelNumber = 1;
	private int score = 100;
	private String name;
	private PropertyChangeSupport support = new PropertyChangeSupport(this);

	// TODO test that enteties are properly removed from entityList.
	// PropertyChangeTest for entityManager
	public void testConstructor() {

		IPhysicsWorld physics = new TestPhysicsWorld();

		GameWorld gWorld = new GameWorld(physics, levelNumber, lives, score,
				new PowerUpFactory());

		assertEquals(levelNumber, gWorld.getLevelNumber());
		assertEquals(score, gWorld.getPlayer().getScore());
		assertEquals(lives, gWorld.getPlayer().getLives());
		assertEquals(physics, gWorld.getPhysics());

		assertEquals(1500, gWorld.getLevelHeight());
		assertEquals(1500, gWorld.getLevelWidth());

	}

	public void testIsGameOver() {
		IPhysicsWorld physics = new TestPhysicsWorld();
		GameWorld gWorld = new GameWorld(physics, levelNumber, lives, score,
				new PowerUpFactory());

		assertEquals(gWorld.getPlayer().lostAllLives(), gWorld.isGameOver());

	}

	public void testUpdateGameWorld() {
		// TODO entityManager part
		IPhysicsWorld physics = new TestPhysicsWorld();
		GameWorld gWorld = new GameWorld(physics, levelNumber, lives, score,
				new PowerUpFactory());
		gWorld.addListener(this);
		for (Entity e : gWorld.getEntityList()) {
			e.setPhysics(new TestPhysicsEntity());
		}
		gWorld.getPlayer().setPhysics(new TestPhysicsEntity());

		// PLayer not big enough
		gWorld.updateGameWorld();
		assertEquals(levelNumber, gWorld.getLevelNumber());

		// Player bigger than maxSize
		gWorld.getPlayer().setRadius(gWorld.getPlayer().getMaxSize());
		gWorld.updateGameWorld();
		assertEquals("NextLevel", name);
		assertEquals(levelNumber + 1, gWorld.getLevelNumber());
	}

	public void testPpropertyChange() {
		IPhysicsWorld physics = new TestPhysicsWorld();
		GameWorld gWorld = new GameWorld(physics, levelNumber, lives, score,
				new PowerUpFactory());
		for (Entity e : gWorld.getEntityList()) {
			e.setPhysics(new TestPhysicsEntity());
		}
		Player player = gWorld.getPlayer();
		player.setPhysics(new TestPhysicsEntity());
		support.addPropertyChangeListener(gWorld);
		support.firePropertyChange(Player.RANDOMLY_REPOSITION_PLAYER, true,
				false);

		gWorld.updateGameWorld();

		assertEquals(20f, player.getCenterX());
		assertEquals(20f, player.getCenterY());

		support.removePropertyChangeListener(gWorld);

	}

	public void testUpdateWithTouchInput() {
		IPhysicsWorld physics = new TestPhysicsWorld();
		GameWorld gWorld = new GameWorld(physics, levelNumber, lives, score,
				new PowerUpFactory());

		Player player = gWorld.getPlayer();
		player.setPhysics(new TestPhysicsEntity());

		gWorld.updateWithTouchInput(new Vector2(20f, 20f));

		assertNotSame(player.getInitialPosition().x, player.getCenterX());
		assertNotSame(player.getInitialPosition().y, player.getCenterY());

	}

	public void testGetCompletion() {
		IPhysicsWorld physics = new TestPhysicsWorld();
		GameWorld gWorld = new GameWorld(physics, levelNumber, lives, score,
				new PowerUpFactory());

		Player player = gWorld.getPlayer();
		player.setPhysics(new TestPhysicsEntity());

		assertEquals(0f, gWorld.getCompletion());

		player.setRadius(player.getMaxSize());

		assertEquals(1f, gWorld.getCompletion());

	}

	public void testManagerPropertyDeletion() {
		IPhysicsWorld physics = new TestPhysicsWorld();
		SpecialTestFactory factory = new SpecialTestFactory();
		GameWorld gWorld = new GameWorld(physics, levelNumber, lives, score,
				factory);

		PUp s = factory.getSpecialEntity();

		Enemy enemy = new Enemy(new Vector2(), 100);
		TestPhysicsEntity testPhysicsEnemy = new TestPhysicsEntity();
		enemy.setPhysics(testPhysicsEnemy);

		Planet planet = new Planet(new Vector2(), 50, PlanetType.BLOOD);
		TestPhysicsEntity testPhysicsPlanet = new TestPhysicsEntity();
		planet.setPhysics(testPhysicsPlanet);

		// test deletion
		s.fire(Entity.IS_SCHEDULED_FOR_DELETION, enemy);
		s.fire(Entity.IS_SCHEDULED_FOR_DELETION, planet);

		// Don't know why, but this only works if you run the test for only this
		// class. Else you will get nullpointer exception
		gWorld.updateGameWorld();

		assertEquals(true, testPhysicsEnemy.delete);
		assertEquals(true, testPhysicsPlanet.delete);

	}

	public void testManagerPropertyBlackHole() {
		IPhysicsWorld physics = new TestPhysicsWorld();
		SpecialTestFactory factory = new SpecialTestFactory();
		GameWorld gWorld = new GameWorld(physics, levelNumber, lives, score,
				factory);

		Player player = gWorld.getPlayer();
		TestPhysicsEntity testPhysicsPlayer = new TestPhysicsEntity();
		player.setPhysics(testPhysicsPlayer);

		Planet planet = new Planet(new Vector2(), 5, PlanetType.BLOOD);
		TestPhysicsEntity testPhysicsPlanet = new TestPhysicsEntity();
		planet.setPhysics(testPhysicsPlanet);

		// Don't know why, but this only works if you run the test for only this
		// class. Else you will get nullpointer exception
		player.eatEntity(planet);
		while (player.getIncreaseSize() >= 0.2f) {
			gWorld.updateGameWorld();
		}
		gWorld.updateGameWorld();

		assertEquals((planet.getRadius() * BlackHole.GROWTH_FACTOR)
				+ GameWorld.PLAYER_STARTING_SIZE, player.getRadius(), 0.0005f);

	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		name = event.getPropertyName();
	}

}
