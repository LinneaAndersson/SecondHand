package com.secondhand.physics;

import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.secondhand.debug.MyDebug;
import com.secondhand.model.CircleEntity;


// PASS ANDENGINE COORDINATES TO THIS CLASS!!!
public final class PhysicsAreaChecker {
	
	private PhysicsAreaChecker() {} 
	
	private static boolean occupied = false;

	public static boolean isRectangleAreaUnoccupied(final Vector2 position, final float width, final float height, final PhysicsWorld physicsWorld) {
		
		occupied = false;
		
		physicsWorld.QueryAABB(new Callback(),
				position.x / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT
				, position.y / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, 
				(position.x + width) / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,
				(position.y + height) / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT);
		
		return !occupied;
	}
	
	public static boolean isCircleAreaUnoccupied(final CircleEntity circleEntity, final PhysicsWorld physicsWorld) {
		return isRectangleAreaUnoccupied(
				new Vector2(circleEntity.getCircle().getX(), 
				circleEntity.getCircle().getY()),
				circleEntity.getCircle().getRadius(),
				circleEntity.getCircle().getRadius(),
				physicsWorld);
	}
	
	private static class Callback implements  QueryCallback {

		@Override
		public boolean reportFixture(final Fixture fixture) {
			
			
			MyDebug.d("reported class: " + fixture.getBody().getUserData().getClass());
			
			occupied = true;
			
			// stop querying for fixtures, we already know that the area is unoccupied. 
			return false;
		}
		
	}
}
