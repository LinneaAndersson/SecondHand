package com.secondhand.view.entities;

import org.anddev.andengine.entity.shape.RectangularShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.secondhand.model.CircleEntity;
import com.secondhand.view.opengl.Circle;

public class CircleView extends EntityView {
	
	
	public CircleView(final PhysicsWorld physicsWorld, final CircleEntity circleEntity , final Circle circle,
			final FixtureDef fixtureDef) {
	
		super(physicsWorld, circleEntity, circle,
				PhysicsFactory.createCircleBody(physicsWorld,
						circle.getX(), circle.getY(), circle.getRadius(),circle.getRotation(), BodyType.DynamicBody, fixtureDef));
	}
	
	public CircleView(final PhysicsWorld physicsWorld, final CircleEntity circleEntity , final RectangularShape rectangle,
			final FixtureDef fixtureDef) {
	
		super(physicsWorld, circleEntity, rectangle,
				PhysicsFactory.createCircleBody(physicsWorld,
						circleEntity.getInitialPosition().x, circleEntity.getInitialPosition().y, circleEntity.getRadius(), 
						rectangle.getRotation(), 
						BodyType.DynamicBody, fixtureDef));
	}

}
