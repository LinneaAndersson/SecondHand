package com.secondhand.model;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.secondhand.model.entity.Enemy;
import com.secondhand.model.entity.Entity;
import com.secondhand.model.entity.Obstacle;
import com.secondhand.model.entity.Planet;
import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.IPhysicsEntity;
import com.secondhand.model.physics.IPhysicsObject;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.powerup.RandomPowerUp;
import com.secondhand.model.resource.PlanetType;

public class EnemyTest extends TestCase {
	Vector2 vector = new Vector2(2f, 4f);
	public float value;

	// This class in a class for testing enemy-methods. It just checks value and
	// return them.
	private class EnemyTestPhysicsEntity implements IPhysicsEntity {
		Entity entity;
		public Vector2 testVector;
		boolean isStraightLine;

		public EnemyTestPhysicsEntity(Entity entity, boolean isStraightLine) {
			this.entity = entity;
			entity.setPhysics(this);
			this.isStraightLine = isStraightLine;
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
		public boolean isStraightLine(IPhysicsObject entity, IPhysicsObject enemy) {
			return isStraightLine;
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

		// Cannot have a negativ radius
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

	public void testSetAndGetMaxSpeed() {
		float rad = 2.2f;
		Enemy enemy = new Enemy(vector, rad);

		enemy.setMaxSpeed(10000f);
		assertTrue(enemy.getMaxSpeed() == 10000f);

		// The Enemy just have a positiv speed (and 0).
		try {
			enemy.setMaxSpeed(-1);

			assertTrue(false);
		} catch (AssertionError er) {
			assertTrue(true);
		}

	}

	public void testMoveEnemy() {
		Vector2 vector = new Vector2(2f, 4f);
		float rad = 3.2f;
		Enemy enemy = new Enemy(vector, rad);
		Enemy enemyNotStraightLine = new Enemy(vector, rad);

		EnemyTestPhysicsEntity enemyPhysics = new EnemyTestPhysicsEntity(enemy,
				true);
		EnemyTestPhysicsEntity enemyPhysicsNotStraightLine = new EnemyTestPhysicsEntity(
				enemyNotStraightLine, false);

		// different Player for different case.
		Player playerOutOfRange = new Player(new Vector2(200f, 200f), 2.0f);
		Player playerInRangeClose = new Player(new Vector2(2f, 2f), 2.0f);
		Player playerInRange = new Player(new Vector2(6f, 6f), 2.0f);
		Player playerInRangeNotStrightLine = new Player(new Vector2(6f, 6f),
				2.0f);

		new EnemyTestPhysicsEntity(playerOutOfRange, true);
		new EnemyTestPhysicsEntity(playerInRangeClose, true);
		new EnemyTestPhysicsEntity(playerInRange, true);
		new EnemyTestPhysicsEntity(playerInRangeNotStrightLine, true);

		// A constant to multiply with when enemy will move.
		float enemyHuntingArea = 0.002f;

		// 1. I will check with enemy radius bigger than the other Entities

		// 1.1 Planet is in Enemies huntingrange.
		List<Entity> entityList = new ArrayList();
		entityList.add(new Planet(new Vector2(3f, 3f), 2.0f, PlanetType.DRUGS));
		new EnemyTestPhysicsEntity(entityList.get(0), true);

		// 1.1.1 When player is out of range, enemy will move against planet.
		enemy.moveEnemy(playerOutOfRange, entityList);
		// Checks the impulse-value (horizontal)
		assertEquals(enemyPhysics.getImpulseVector().x, (entityList.get(0)
				.getInitialPosition().x - enemy.getInitialPosition().x)
				* enemyHuntingArea);

		// Checks the impulse-value (vertical)
		assertEquals(enemyPhysics.getImpulseVector().y, (entityList.get(0)
				.getInitialPosition().y - enemy.getInitialPosition().y)
				* enemyHuntingArea);

		// 1.1.2 when player is in range and closer to enemy, enemy will move
		// against
		// player.
		// sets the impulse-value to 0,0 to see if it will move against Player.
		enemyPhysics.applyImpulse(new Vector2(0, 0), 0);
		enemy.moveEnemy(playerInRangeClose, entityList);
		assertEquals(
				enemyPhysics.getImpulseVector().x,
				(playerInRangeClose.getInitialPosition().x - enemy
						.getInitialPosition().x) * enemyHuntingArea);
		assertEquals(
				enemyPhysics.getImpulseVector().y,
				(playerInRangeClose.getInitialPosition().y - enemy
						.getInitialPosition().y) * enemyHuntingArea);

		// 1.1.3 Player is in range, further away from enemy than Planet. Enemy
		// still chase Player
		enemyPhysics.applyImpulse(new Vector2(0, 0), 0);
		enemy.moveEnemy(playerInRange, entityList);
		assertEquals(
				enemyPhysics.getImpulseVector().x,
				(playerInRange.getInitialPosition().x - enemy
						.getInitialPosition().x) * enemyHuntingArea);
		assertEquals(
				enemyPhysics.getImpulseVector().y,
				(playerInRange.getInitialPosition().y - enemy
						.getInitialPosition().y) * enemyHuntingArea);

		// 1.2 Planet are not in range for enemy.
		entityList.clear();
		entityList.add(new Planet(new Vector2(300f, 200f), 2.0f,
				PlanetType.DRUGS));
		new EnemyTestPhysicsEntity(entityList.get(0), true);

		// 1.2.1 Player are not in range
		enemyPhysics.applyImpulse(new Vector2(0, 0), 0);
		enemy.moveEnemy(playerOutOfRange, entityList);
		assertEquals(enemyPhysics.getImpulseVector().x, 0.0f);
		assertEquals(enemyPhysics.getImpulseVector().y, 0.0f);

		// 1.2.2 Player are in range

		// Third case: Player is in the range for huntingArea and Planet isn't.
		// Enemy will go for Player.
		enemyPhysics.applyImpulse(new Vector2(0, 0), 0);
		enemy.moveEnemy(playerInRange, entityList);
		assertEquals(
				enemyPhysics.getImpulseVector().x,
				(playerInRange.getInitialPosition().x - enemy
						.getInitialPosition().x) * enemyHuntingArea);
		assertEquals(
				enemyPhysics.getImpulseVector().y,
				(playerInRange.getInitialPosition().y - enemy
						.getInitialPosition().y) * enemyHuntingArea);

		// 1.3 Obstacle and PowerUp in the range of Enemy. Enemy will
		// not chase them. If a eatable-entity(Player or Planet) is in range and
		// there is straightLine then
		// enemy will chase the eatable-entity.
		entityList.clear();
		entityList.add(new Obstacle(new Vector2(2f, 2f), new ArrayList()));
		entityList.add(new RandomPowerUp(new Vector2(4f, 4f), null));

		new EnemyTestPhysicsEntity(entityList.get(0), true);
		new EnemyTestPhysicsEntity(entityList.get(1), true);

		enemyPhysics.applyImpulse(new Vector2(0, 0), 0);

		// 1.3.1 Don't want player to make any difference-not in range
		enemy.moveEnemy(playerOutOfRange, entityList);
		assertEquals(enemyPhysics.getImpulseVector().x, 0.0f);
		assertEquals(enemyPhysics.getImpulseVector().y, 0.0f);
		
		// 1.3.2 player in range, but not straight line. No chasing!
		enemyPhysicsNotStraightLine.applyImpulse(new Vector2(0, 0), 0);
		assertEquals(false, enemyPhysicsNotStraightLine.isStraightLine);
		enemyNotStraightLine.moveEnemy(playerInRangeNotStrightLine, entityList);
	//	assertEquals(enemyPhysicsNotStraightLine.getImpulseVector().x, enemyNotStraightLine.getInitialPosition().y-playerInRangeNotStrightLine.getInitialPosition().x);
		assertEquals(enemyPhysicsNotStraightLine.getImpulseVector().x, 0.0f);
		assertEquals(enemyPhysicsNotStraightLine.getImpulseVector().y, 0.0f);

		// 1.3.3 player in range and straight line, enemy will chase.
		/*enemy.moveEnemy(playerInRange, entityList);
		assertEquals(
				enemyPhysics.getImpulseVector().x,
				(playerInRange.getInitialPosition().x - enemy
						.getInitialPosition().x) * enemyHuntingArea);
		assertEquals(
				enemyPhysics.getImpulseVector().y,
				(playerInRange.getInitialPosition().x - enemy
						.getInitialPosition().x) * enemyHuntingArea);*/

	}
}
