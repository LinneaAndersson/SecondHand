package com.secondhand.model;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import com.secondhand.model.entity.Enemy;
import com.secondhand.model.entity.Entity;
import com.secondhand.model.entity.Planet;
import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.IPhysicsEntity;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PlanetType;

public class EnemyTest extends TestCase {
	Vector2 vector = new Vector2(2f, 4f);
	
	public IPhysicsEntity getPhysicsEntity(){
		return new IPhysicsEntity(){
			public float value;
			public Vector2 vector;
			@Override
			public float getCenterX() {return 0;}

			@Override
			public float getCenterY() {return 0;}

			@Override
			public void deleteBody() {}

			@Override
			public void applyImpulse(Vector2 impulsePosition, float maxSpeed) {
				this.vector=impulsePosition;
			}

			@Override
			public void applyImpulse(Vector2 impulsePosition, Vector2 impulse) {
			}

			@Override
			public void setLinearDamping(float f) {}

			@Override
			public void detachSelf() {}

			@Override
			public float computePolygonRadius(List<Vector2> polygon) {return value;}

			@Override
			public void setTransform(Vector2 position) {}

			@Override
			public void stopMovment() {}

			@Override
			public boolean isStraightLine(Entity entity, Enemy enemy) {return false;}
		};
	}

	public void testConstructor() {
		float rad = 3.2f;

		Enemy enemy = new Enemy(vector, rad);
		Vector2 enemyPosition = enemy.getInitialPosition();
		assertEquals(rad, enemy.getRadius());
		assertEquals(vector.x, enemyPosition.x);
		assertEquals(vector.y, enemyPosition.y);

		float rad1 = -3.2f;

		Enemy enemy1 = new Enemy(vector, rad1);
		assertNotSame(rad1, enemy1.getRadius());
		assertEquals(Math.abs(rad1), enemy1.getRadius());
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
		enemy.setMaxSpeed(-10f);
		assertFalse(enemy.getMaxSpeed() == -10f);
		assertTrue(enemy.getMaxSpeed() == 10f);
		enemy.setMaxSpeed(10000f);
		assertTrue(enemy.getMaxSpeed() == 10000f);

	}

	public void testMoveEnemy() {
		
		float rad = 3.2f;
		Enemy enemy = new Enemy(new Vector2(2f, 4f), rad);
		PhysicsWorld physicsWorld = new PhysicsWorld(new com.badlogic.gdx.math.Vector2(),true);
		
		/*IShape shape = new Circle(enemy.getInitialPosition().x, enemy.getInitialPosition().y,
				enemy.getRadius());
		Body body = PhysicsFactory.createCircleBody(physicsWorld,
				shape.getX(), shape.getY(), enemy.getRadius(), shape.getRotation(), BodyType.DynamicBody, FixtureDefs.BLACK_HOLE_FIXTURE_DEF);
				IPhysicsEntity physicsEntity = new MyPhysicsEntity(physicsWorld, enemy, shape, body);*/

		// First I will check with enemy radius bigger than the other Entities

		// First case: Enemy is closer to Planet so enemy will move against
		// planet
		List<Entity> entityList = new ArrayList();
		Player player = new Player(new Vector2(200, 200), 2.0f);
		entityList
		.add(new Planet(new Vector2(450, 500), 2.0f, PlanetType.DRUGS));
		
		enemy.moveEnemy(player, entityList);
		assertFalse(enemy.getCenterX() == 200);

		// Second case: Enemy is closer to player, but not in the range for
		// hunting player, i.e the game will move against planet
		List<Entity> entityList1 = new ArrayList();
		Player player1 = new Player(new Vector2(200, 200), 2.0f);
		entityList1.add(new Planet(new Vector2(300, 200), 2.0f,
				PlanetType.DRUGS));

		// Third case: Enemy is in the range for huntingArea but planet is
		// closer
		List<Entity> entityList2 = new ArrayList();
		Player player2 = new Player(new Vector2(200, 200), 2.0f);
		entityList2.add(new Planet(new Vector2(300, 200), 2.0f,
				PlanetType.DRUGS));

		// fourth case: player is in the range and closer to enemy.
		List<Entity> entityList3 = new ArrayList();
		Player player3 = new Player(new Vector2(200, 200), 2.0f);
		entityList3.add(new Planet(new Vector2(300, 200), 2.0f,
				PlanetType.DRUGS));
	}
}
