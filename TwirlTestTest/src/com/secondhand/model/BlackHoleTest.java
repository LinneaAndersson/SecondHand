package com.secondhand.model;


import junit.framework.TestCase;

import com.secondhand.model.entity.BlackHole;
import com.secondhand.model.physics.Vector2;

public class BlackHoleTest extends TestCase {
	
	
	public BlackHole getNewBlackHole(Vector2 position, final float radius, final int score) {
		return new BlackHole(position,radius, score) {

		};
	}
	
	public void testConstructor() {
		Vector2 position = new Vector2();
		
		BlackHole blackHole = getNewBlackHole(position, 10, 0);
		
		assertEquals(false, blackHole.canEatInedibles());
	}	
}
