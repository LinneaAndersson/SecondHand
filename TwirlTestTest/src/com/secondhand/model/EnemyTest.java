package com.secondhand.model;

import junit.framework.TestCase;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.secondhand.model.entity.Enemy;
import com.secondhand.model.entity.GameWorld;
import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.Vector2;
import com.secondhand.view.physics.MyPhysicsWorld;

public class EnemyTest extends TestCase {
	

	public void testConstructor() {
		Vector2 vector1 = new Vector2(2f, 4f);
		float rad = 3.2f;

		Enemy enemy = new Enemy(vector1, rad);
		Vector2 enemyPosition = enemy.getInitialPosition();
		assertEquals(rad, enemy.getRadius());
		assertEquals(vector1.x, enemyPosition.x);
		assertEquals(vector1.y, enemyPosition.y);
	}

	public void testIsBiggerThan() {
		Vector2 vector2 = new Vector2(2f, 4f);
		float rad = 3.2f;

		Enemy enemy = new Enemy(vector2, rad);

		Player other = new Player(vector2, rad - 1);
		assertTrue(enemy.canEat(other));
		
		other = new Player(vector2, rad+1);
		assertFalse(enemy.canEat(other));
	}
	
	public void testGetHuntingArea(){
		Vector2 vector2 = new Vector2(2f, 4f);
		float rad = 3.2f;

		Enemy enemy = new Enemy(vector2, rad);
		
		assertEquals(enemy.getHuntingArea(),40f*(rad*rad*(float)Math.PI));
		assertFalse(enemy.getHuntingArea() == 41*(rad*rad*(float)Math.PI));
	}

}
