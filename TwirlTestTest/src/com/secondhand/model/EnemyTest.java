package com.secondhand.model;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.opengl.texture.atlas.bitmap.source.decorator.shape.CircleBitmapTextureAtlasSourceDecoratorShape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.shapes.Shape;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.secondhand.model.entity.Enemy;
import com.secondhand.model.entity.Entity;
import com.secondhand.model.entity.Planet;
import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.IPhysicsEntity;
import com.secondhand.model.physics.Vector2;
import com.secondhand.model.resource.PlanetType;
import com.secondhand.view.physics.MyPhysicsEntity;

public class EnemyTest extends TestCase {
	Vector2 vector = new Vector2(2f, 4f);

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

	/*public void testMoveEnemy() {
		float rad = 2.2f;
		Enemy enemy = new Enemy(new Vector2(500, 500), rad);
		PhysicsWorld physicsWorld = new PhysicsWorld(new Vector2(),true);
		IShape shape = new CircleBitmapTextureAtlasSourceDecoratorShape();
		IPhysicsEntity physicsEntity = new MyPhysicsEntity(physicsWorld, enemy, shape, new Body()) {

			@Override
			public void draw(Canvas canvas, Paint paint) {
				// TODO Auto-generated method stub

			}
		}, body)
		// First I will check with enemy radius bigger than the other Entities

		// First case: Enemy is closer to Planet so enemy will move against
		// planet
		List<Entity> entityList = new ArrayList();
		Player player = new Player(new Vector2(200, 200), 2.0f);
		entityList
		.add(new Planet(new Vector2(300, 200), 2.0f, PlanetType.DRUGS));


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
	} */
}
