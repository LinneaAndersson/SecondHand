package com.secondhand.model;

import java.util.List;

import com.secondhand.model.entity.Player;
import com.secondhand.model.physics.IPhysicsEntity;
import com.secondhand.model.physics.IPhysicsObject;
import com.secondhand.model.physics.Vector2;

import junit.framework.TestCase;

public class PlayerTest extends TestCase{

	public void testConstructor() {
		
		
		Player player = new Player(new Vector2(), 10, 3, // lives
				100, // starting score
				200); // maxsize
		
		assertEquals(200, player.getMaxSize());
		assertEquals(false, player.isMirroredMovement());
		assertEquals(1, player.getSpeedMultiplier(), 0.0001f);
		assertEquals(1, player.getScoreMultiplier(), 0.0001f);
		assertEquals(3, player.getLives());
	}
	
	public void testReactToTouch() {
		
		final float FORCE = 60;
		
		
		final class PhysicsEntity implements IPhysicsEntity {
			
			public int state;
			
			public boolean test1Pass;
			public boolean test2Pass;

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
				
				if(state == 1) {
					if(impulse.almostEquals(new Vector2(3 * FORCE * -1, 0)) && 
							impulsePosition.almostEquals(new Vector2(50, 0))	) {
						this.test1Pass = true;
					}
				}
				
				if(state == 2) {
					if(impulse.almostEquals(new Vector2(3 * FORCE, 0)) && 
							impulsePosition.almostEquals(new Vector2(-50, 0))	) {
						this.test2Pass = true;
					}
				}
			}

			@Override
			public void setLinearDamping(float f) {
			}

			@Override
			public void detachSelf() {
			}

			@Override
			public float computePolygonRadius(List<Vector2> polygon) {
				return 10;
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
		}
		

		Player player = new Player(new Vector2(), 10, 3, // lives
				100, // starting score
				200); // maxsize
		PhysicsEntity physics = new PhysicsEntity();
		player.setPhysics(physics);
		player.setSpeedMultiplier(3);
		
		// with speed mutlipliers:
		
		physics.state = 1;
		physics.test1Pass = false;
		
		player.reachToTouch(new Vector2(50,0));
		
		assertTrue(physics.test1Pass);
		
		// now with mirrored movement
		physics.state = 2;
		physics.test2Pass = false;
		player.setMirroredMovement(true);
		
		player.reachToTouch(new Vector2(50,0));
		
		assertTrue(physics.test2Pass);
		
	}

}
