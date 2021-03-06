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
import com.secondhand.model.powerup.ScoreUp;
import com.secondhand.model.resource.PlanetType;

public class EnemyTest extends TestCase {
	Vector2 vector = new Vector2(2f, 4f);
	public float value;
	float rad = 3.2f;

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
			testVector = new Vector2(0, 0);
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
		public boolean isStraightLine(IPhysicsObject entity,
				IPhysicsObject enemy) {
			return isStraightLine;
		}

		public Vector2 getImpulseVector() {
			return this.testVector;
		}

		@Override
		public float getVelocity() {
			return 0;
		}
	}

	public void testConstructor() {
		Enemy enemy = new Enemy(vector, rad);
		Vector2 enemyPosition = enemy.getInitialPosition();
		assertEquals(rad, enemy.getRadius());
		assertEquals(vector.x, enemyPosition.x);
		assertEquals(vector.y, enemyPosition.y);

	}

	public void testGetMinSize() {
		assertEquals(Enemy.getMinSize(), 20f);
	}

	public void testGetMaxSize() {
		assertEquals(Enemy.getMaxSize(), 40f);
	}

	public void testGetHuntingArea() {
		float rad = 4.2f;
		Enemy enemy = new Enemy(vector, rad);

		// (r������*pi)*40 for the size*constant
		assertEquals(enemy.getHuntingArea(), 60 * (rad * rad * (float) Math.PI));
	}

	public void testGetDangerArea() {
		float rad = 2.2f;
		final int DANGER_AREA = 9000;
		Enemy enemy = new Enemy(vector, rad);

		assertEquals(enemy.getDangerArea(), enemy.getRadius() + DANGER_AREA);
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
		float rad = 4f;
		Enemy enemy = new Enemy(vector, rad);
		Enemy enemyNotStraightLine = new Enemy(vector, rad);
		Enemy enemySmall = new Enemy(vector, 2.5f);
		Enemy dangerEnemy = new Enemy(new Vector2(vector.x + 4, vector.x + 4),
				rad);

		EnemyTestPhysicsEntity enemyPhysics = new EnemyTestPhysicsEntity(enemy,
				true);
		EnemyTestPhysicsEntity enemySmallPhysics = new EnemyTestPhysicsEntity(
				enemySmall, true);
		EnemyTestPhysicsEntity enemyPhysicsNotStraightLine = new EnemyTestPhysicsEntity(
				enemyNotStraightLine, false);

		new EnemyTestPhysicsEntity(dangerEnemy, true);

		// different Player for different case.
		Player playerOutOfRange = new Player(new Vector2(200f, 200f), 3.0f, 3,
				1, 0);
		Player playerInRangeClose = new Player(new Vector2(vector.x + 1f,
				vector.x + 1f), 3.0f, 3, 1, 0);
		Player playerInRange = new Player(new Vector2(vector.x + 4f,
				vector.x + 4f), 3.0f, 3, 1, 0);
		Player playerInRangeNotStrightLine = new Player(new Vector2(
				vector.x + 4f, vector.x + 4f), 3.0f, 3, 1, 0);

		new EnemyTestPhysicsEntity(playerOutOfRange, true);
		new EnemyTestPhysicsEntity(playerInRangeClose, true);
		new EnemyTestPhysicsEntity(playerInRange, true);
		new EnemyTestPhysicsEntity(playerInRangeNotStrightLine, true);

		// A constant to multiply with when enemy will move.
		float enemyHuntingArea = 0.002f;

		float enemyDangerArea = 0.002f;

		// 1. I will check with enemy radius bigger than the other Entities

		// 1.1 Planet is in Enemies huntingRange.
		final List<Entity> entityList = new ArrayList<Entity>();
		entityList.add(new Planet(new Vector2(3f, 3f), 1.0f, PlanetType.DRUGS));
		entityList.add(new Planet(new Vector2(3f, 5f), 2.0f, PlanetType.DRUGS));
		new EnemyTestPhysicsEntity(entityList.get(0), true);
		new EnemyTestPhysicsEntity(entityList.get(1), true);
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

		// 1.1.3 when player is in range and closer to enemy, enemy will move
		// against player. sets the impulse-value to 0,0 to
		// see if it will move against Player.
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

		// 1.1.4 Player is in range, further away from enemy than Planet. Enemy
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

		// 1.2.1 Player is not in range & enemy has no velocity, therefore
		// enemy will move random
		enemyPhysics.applyImpulse(new Vector2(0, 0), 0);
		enemy.moveEnemy(playerOutOfRange, entityList);
		assertNotSame(enemyPhysics.getImpulseVector().x, 0.0f);
		assertNotSame(enemyPhysics.getImpulseVector().y, 0.0f);
		assertNotSame(enemyPhysics.getImpulseVector().x, (playerOutOfRange
				.getInitialPosition().x - enemy.getInitialPosition().x)
				* enemyHuntingArea);
		assertNotSame(enemyPhysics.getImpulseVector().y, (playerOutOfRange
				.getInitialPosition().y - enemy.getInitialPosition().y)
				* enemyHuntingArea);

		// 1.2.2 Player are in range

		// 1.2.3 Player is in the range for huntingArea and Planet isn't.
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
		entityList.add(new Obstacle(new Vector2(2f, 2f),
				new ArrayList<Vector2>()));
		entityList.add(new ScoreUp(new Vector2(4f, 4f)));

		new EnemyTestPhysicsEntity(entityList.get(0), true);
		new EnemyTestPhysicsEntity(entityList.get(1), true);

		enemyPhysics.applyImpulse(new Vector2(0, 0), 0);

		// 1.3.1 Don't want player to make any difference-not in range, 
		// Enemy will move random
		enemy.moveEnemy(playerOutOfRange, entityList);
		assertNotSame(enemyPhysics.getImpulseVector().x, 0.0f);
		assertNotSame(enemyPhysics.getImpulseVector().y, 0.0f);
		assertNotSame(enemyPhysics.getImpulseVector().x, (entityList.get(0)
				.getInitialPosition().x - enemy.getInitialPosition().x)
				* enemyHuntingArea);
		assertNotSame(enemyPhysics.getImpulseVector().y, (entityList.get(1)
				.getInitialPosition().y - enemy.getInitialPosition().y)
				* enemyHuntingArea);
		

		// 1.3.2 player in range, but not straight line. No chasing!
		enemyPhysicsNotStraightLine.applyImpulse(new Vector2(0, 0), 0);
		enemyNotStraightLine.moveEnemy(playerInRangeNotStrightLine, entityList);
		assertEquals(enemyPhysicsNotStraightLine.getImpulseVector().x, 0.0f);
		assertEquals(enemyPhysicsNotStraightLine.getImpulseVector().y, 0.0f);

		// 1.3.3 player in range and straight line, enemy will chase.
		enemy.moveEnemy(playerInRange, entityList);
		assertEquals(
				enemyPhysics.getImpulseVector().x,
				(playerInRange.getInitialPosition().x - enemy
						.getInitialPosition().x) * enemyHuntingArea);
		assertEquals(
				enemyPhysics.getImpulseVector().y,
				(playerInRange.getInitialPosition().y - enemy
						.getInitialPosition().y) * enemyHuntingArea);

		// 2 Enemy is smaller than player and player is in range and straight
		// line, enemy will retreat
		entityList.clear();
		enemySmallPhysics.applyImpulse(new Vector2(0, 0), 0);
		enemySmall.moveEnemy(playerInRange, entityList);

		assertEquals(
				enemySmallPhysics.getImpulseVector().x,
				(enemySmall.getInitialPosition().x - playerInRange
						.getInitialPosition().x) * enemyDangerArea);
		assertEquals(
				enemySmallPhysics.getImpulseVector().y,
				(enemySmall.getInitialPosition().y - playerInRange
						.getInitialPosition().y) * enemyDangerArea);

		// 2.1 Enemy retreats from another larger close-by enemy but not from
		// smaller enemy out of range.
		entityList.add(dangerEnemy);
		entityList.add(new Enemy(new Vector2(100, 100), 2f));
		new EnemyTestPhysicsEntity(entityList.get(1), true);
		enemySmallPhysics.applyImpulse(new Vector2(0, 0), 0);
		enemySmall.moveEnemy(playerOutOfRange, entityList);
		assertEquals(
				enemySmallPhysics.getImpulseVector().x,
				(enemySmall.getInitialPosition().x - dangerEnemy
						.getInitialPosition().x) * enemyDangerArea);
		assertEquals(
				enemySmallPhysics.getImpulseVector().y,
				(enemySmall.getInitialPosition().y - dangerEnemy
						.getInitialPosition().y) * enemyDangerArea);

		// 3 player out of range. two planets in range. test specific for
		// second branch of getSmaller
		/*
		 * entityList.clear(); entityList.add(new Planet(new Vector2(3f, 5f),
		 * 2.0f, PlanetType.DRUGS)); entityList.add(new Planet(new Vector2(3f,
		 * 3f), 1.0f, PlanetType.DRUGS)); enemyPhysics.applyImpulse(new
		 * Vector2(0, 0), 0); enemy.moveEnemy(playerOutOfRange, entityList);
		 */

	}
	
	
	public void testDanger(){
		Enemy enemy = new Enemy(new Vector2(), 50);
		Player player = new Player(new Vector2(10,10), 60,1,1,100);
		EnemyTestPhysicsEntity pPhysics = new EnemyTestPhysicsEntity(player, true);
		EnemyTestPhysicsEntity physics = new EnemyTestPhysicsEntity(enemy, true);
		player.setPhysics(pPhysics);
		enemy.setPhysics(physics);
		
		enemy.moveEnemy(player, null);
		Vector2 v = new Vector2(enemy.getCenterX() - player.getCenterX(),
				enemy.getCenterY() - player.getCenterY()).mul(0.002f);
		assertEquals(v.x, physics.getImpulseVector().x);
		assertEquals(v.y, physics.getImpulseVector().y);
		
		Player playerNoDanger = new Player(new Vector2(100,100), 60,1,1,100);
		EnemyTestPhysicsEntity pndPhysics = new EnemyTestPhysicsEntity(playerNoDanger, true);
		player.setPhysics(pndPhysics);
		List<Entity> entityList = new ArrayList<Entity>();
		
		Enemy enemyDanger = new Enemy(new Vector2(10, 10), 60);
		EnemyTestPhysicsEntity dphysics = new EnemyTestPhysicsEntity(enemyDanger, true);
		enemyDanger.setPhysics(dphysics);
		
		entityList.add(enemyDanger);
		entityList.add(enemy);
		
		enemy.moveEnemy(playerNoDanger, entityList);
		
		v = new Vector2(enemy.getCenterX() - enemyDanger.getCenterX(),
				enemy.getCenterY() - enemyDanger.getCenterY()).mul(0.002f);
		assertEquals(v.x, physics.getImpulseVector().x);
		assertEquals(v.y, physics.getImpulseVector().y);
	
		enemyDanger.moveEnemy(playerNoDanger, entityList);
		
		
	}
}
