package com.secondhand.model;

import com.badlogic.gdx.math.Vector2;

import junit.framework.TestCase;

public class EnemyTest extends TestCase{

	public void testConstructor() {

		Vector2 pos = new Vector2(2f, 4f);
		float rad = 3.2f;
		
		Enemy enemy = new Enemy(pos, rad);
		
		assertEquals(rad, enemy.getRadius());
		assertEquals(pos.x, enemy.getPosition().x);
		assertEquals(pos.y, enemy.getPosition().y);
	}
	
}
