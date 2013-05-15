package com.secondhand.model;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import android.test.AssertionFailedError;

import com.secondhand.model.entity.Enemy;
import com.secondhand.model.entity.Entity;
import com.secondhand.model.entity.Planet;
import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.IPhysicsEntity;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PlanetType;

public class EnemyTest extends TestCase {
	Vector2 vector = new Vector2(2f, 4f);
	public float value;

	// This class in a class for testing enemy-methods. It just checks value and
	// return them.
	private class EnemyTestPhysicsEntity implements IPhysicsEntity {
		Entity entity;
		public Vector2 testVector;

		public EnemyTestPhysicsEntity(Entity entity) {
			this.entity = entity;
			entity.setPhysics(this);
		}

		@Override
		public float getCenterX() {
			return entity.getInitialPosition().x;
		}

		@Override
		public float getCenterY() {
			return entity.getInitialPosition().y;
		}

		@Override
		public void deleteBody() {
		}

		@Override
		public void applyImpulse(Vector2 impulsePosition, float maxSpeed) {
			testVector = impulsePosition;
		}

		@Override
		public void applyImpulse(Vector2 impulsePosition, Vector2 impulse) {
		}

		@Override
		public void setLinearDamping(float f) {
		}

		@Override
		public void detachSelf() {
		}

		@Override
		public float computePolygonRadius(List<Vector2> polygon) {
			return value;
		}

		@Override
		public void setTransform(Vector2 position) {
		}

		@Override
		public void stopMovment() {
		}

		@Override
		public boolean isStraightLine(Entity entity, Enemy enemy) {
			return true;
		}

		public Vector2 getImpulseVector() {
			return this.testVector;
		}
	}

	public void testConstructor() {
		float rad = 3.2f;

		Enemy enemy = new Enemy(vector, rad);
		Vector2 enemyPosition = enemy.getInitialPosition();
		assertEquals(rad, enemy.getRadius());
		assertEquals(vector.x, enemyPosition.x);
		assertEquals(vector.y, enemyPosition.y);

		try {
			enemy.setRadius(-1);
			
			assertTrue(false);
		} catch (AssertionError er) {
			assertTrue(true);
		}
	}

	public void testIsBiggerThan() {
		float rad = 3.2f;
		Enemy enemy = new Enemy(vector, rad);

		Player other = new Player(vector, rad - 1);
		assertTrue(enemy.canEat(other));

		other = new Player(vector, rad + 1);
		assertFalse(enemy.canEat(other));
	}

	public void testGetHuntingArea() {
		float rad = 4.2f;
		Enemy enemy = new Enemy(vector, rad);

		// (r²*pi)*40 for the size*constant
		assertEquals(enemy.getHuntingArea(), 40 * (rad * rad * (float) Math.PI));
	}

	public void testGetDangerArea() {
		float rad = 2.2f;
		Enemy enemy = new Enemy(vector, rad);

		assertEquals(enemy.getDangerArea(), 5 + (rad * rad * (float) Math.PI));
	}

	// The Enemy just have a positiv speed (and 0).
	public void testSetAndGetMaxSpeed() {
		float rad = 2.2f;
		Enemy enemy = new Enemy(vector, rad);
		try {
			enemy.setMaxSpeed(-1);
			
			assertTrue(false);
		} catch (AssertionError er) {
			assertTrue(true);
		}
		enemy.setMaxSpeed(10000f);
		assertTrue(enemy.getMaxSpeed() == 10000f);

	}

	public void testMoveEnemy() {
		Vector2 vector = new Vector2(2f, 4f);
		float rad = 3.2f;
		Enemy enemy = new Enemy(vector, rad);

		// First I will check with enemy radius bigger than the other Entities

		// First case: Enemy is closer to Planet so enemy will move against
		// planet
		List<Entity> entityList = new ArrayList();
		Player player = new Player(new Vector2(200f, 200f), 2.0f);
		entityList.add(new Planet(new Vector2(3f, 3f), 2.0f, PlanetType.DRUGS));
		new EnemyTestPhysicsEntity(player);
		new EnemyTestPhysicsEntity(entityList.get(0));

		EnemyTestPhysicsEntity enemyPhysics = new EnemyTestPhysicsEntity(enemy);
		enemy.moveEnemy(player, entityList);

		// Checks the impulse-value (horizontal)
		assertEquals(enemyPhysics.getImpulseVector().x,
				(entityList.get(0).getInitialPosition().x - enemy
						.getInitialPosition().x) * 0.002f);
		// Checks the impulse-value (vertical)
		assertEquals(enemyPhysics.getImpulseVector().y,
				(entityList.get(0).getInitialPosition().y - enemy
						.getInitialPosition().y) * 0.002f);

		// Second case: Enemy is closer to player, but not in the range for
		// hunting player, i.e the game will move against planet
		entityList.clear();
		entityList
				.add(new Planet(new Vector2(300, 200), 2.0f, PlanetType.DRUGS));
		Player player1 = new Player(new Vector2(200, 200), 2.0f);

		// Third case: Enemy is in the range for huntingArea but planet is
		// closer
		entityList.clear();
		entityList
				.add(new Planet(new Vector2(300, 200), 2.0f, PlanetType.DRUGS));
		Player player2 = new Player(new Vector2(200, 200), 2.0f);

		// fourth case: player is in the range and closer to enemy.
		entityList.clear();
		entityList
				.add(new Planet(new Vector2(300, 200), 2.0f, PlanetType.DRUGS));
		Player player3 = new Player(new Vector2(200, 200), 2.0f);

	}
}
