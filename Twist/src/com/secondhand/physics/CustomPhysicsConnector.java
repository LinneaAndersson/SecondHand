package com.secondhand.physics;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.util.MathUtils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.secondhand.model.CircleEntity;
import com.secondhand.model.Entity;

public class CustomPhysicsConnector extends PhysicsConnector {

	private boolean isCircle;
	
	public CustomPhysicsConnector(final Entity entity, final Body body, final boolean updatePosition, final boolean updateRotation) {
		super(entity.getShape(), body, updatePosition, updateRotation );
		
		this.isCircle = entity instanceof CircleEntity;
	}
	
	
	@Override
	public void onUpdate(final float pSecondsElapsed) {
		final IShape shape = this.mShape;
		final Body body = this.mBody;

		if(this.mUpdatePosition) {
			final Vector2 position = body.getPosition();
			final float pixelToMeterRatio = this.mPixelToMeterRatio;
			
			float x = position.x * pixelToMeterRatio;
			float y = position.y * pixelToMeterRatio;
			
			// the Circle class is positioned by center.
			if(!isCircle) {
				x -=  this.mShapeHalfBaseWidth;
				y -=  this.mShapeHalfBaseHeight;
				
			}
			
			shape.setPosition(x , y);
		}

		// TODO: this makes it impossible for circle to rotate when colliding, fix this.
		if(this.mUpdateRotation && !isCircle) {
			final float angle = body.getAngle();
			shape.setRotation(MathUtils.radToDeg(angle));
		}
	}
	
}
