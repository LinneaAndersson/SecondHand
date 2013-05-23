package com.secondhand.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import junit.framework.TestCase;

import com.secondhand.model.entity.CollisionResolver;
import com.secondhand.model.entity.Enemy;
import com.secondhand.model.entity.Entity;
import com.secondhand.model.entity.IGameWorld;
import com.secondhand.model.entity.Player;
import com.secondhand.model.entity.PowerUpList;
import com.secondhand.model.physics.ICollisionResolver;
import com.secondhand.model.physics.IPhysicsEntity;
import com.secondhand.model.physics.IPhysicsObject;
import com.secondhand.model.physics.IPhysicsWorld;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.powerup.PowerUp;
import com.secondhand.model.powerup.SpeedUp;

public class CollisionResolverTest extends TestCase {
	private class TestPhysicsEntity implements IPhysicsEntity {
		@Override
		public float getCenterX() {
			return 0;
		}

		@Override
		public float getCenterY() {
			return 0;
		}

		@Override
		public void deleteBody() {
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
		}

		@Override
		public void stopMovment() {
		}

		@Override
		public boolean isStraightLine(IPhysicsObject entity,
				IPhysicsObject enemy) {
			return false;
		}

		@Override
		public float getVelocity() {
			// TODO Auto-generated method stub
			return 0;
		}
	}

	private class TestGameWorld implements IGameWorld, PropertyChangeListener {
		private PowerUpList powerUpList;
		private PropertyChangeSupport support;
		private String name;

		public TestGameWorld() {
			powerUpList = new PowerUpList(
					new Player(new Vector2(), 4f, 1, 1, 1));
			support = new PropertyChangeSupport(this);
			addListener(this);
		}

		@Override
		public void addListener(PropertyChangeListener listener) {
			support.addPropertyChangeListener(listener);
		}

		@Override
		public void removeListener(PropertyChangeListener listener) {
		}

		@Override
		public PropertyChangeSupport getPropertyChangeSupport() {
			return support;
		}

		@Override
		public int getLevelNumber() {
			return 0;
		}

		@Override
		public IPhysicsWorld getPhysics() {
			return null;
		}

		@Override
		public int getLevelWidth() {
			return 0;
		}

		@Override
		public int getLevelHeight() {
			return 0;
		}

		@Override
		public Player getPlayer() {
			return null;
		}

		@Override
		public List<Entity> getEntityList() {
			return null;
		}

		@Override
		public boolean isGameOver() {
			return false;
		}

		@Override
		public void updateGameWorld() {

		}

		@Override
		public void updateWithTouchInput(Vector2 v) {

		}

		@Override
		public PowerUpList getPowerUpList() {
			return powerUpList;
		}

		@Override
		public void propertyChange(PropertyChangeEvent event) {
			name = event.getPropertyName();

		}

		public String getName() {
			return name;

		}

		@Override
		public float getCompletion() {
			return 0;
		}

	}

	public void testCheckCollision() {
		TestGameWorld gWorld = new TestGameWorld();
		ICollisionResolver resolver = new CollisionResolver(gWorld);

		// fault
		resolver.checkCollision(null, null);
		assertEquals(null, gWorld.getName());

		// wall hit
		resolver.checkCollision(null, new Player(new Vector2(), 1, 1, 1, 1));
		assertEquals("PlayerWallCollision", gWorld.getName());

	}

	public void testHandleBlackHoleCollision() {
		TestGameWorld gWorld = new TestGameWorld();
		ICollisionResolver resolver = new CollisionResolver(gWorld);

		// Player & PowerUp
		PowerUp powerUp = new SpeedUp(new Vector2());
		powerUp.setPhysics(new TestPhysicsEntity());

		resolver.checkCollision(powerUp, new Player(new Vector2(), 1, 1, 1, 1));
		assertEquals(powerUp, gWorld.getPowerUpList().get(0));

		resolver.checkCollision(new Player(new Vector2(), 1, 1, 1, 1), powerUp);
		assertEquals(powerUp, gWorld.getPowerUpList().get(1));

		gWorld.getPowerUpList().clear();

		// Enemy & PowerUp
		resolver.checkCollision(powerUp, new Enemy(new Vector2(), 1));
		assertEquals(true, gWorld.getPowerUpList().isEmpty());

	}

}
