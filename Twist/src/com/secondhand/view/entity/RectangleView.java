package com.secondhand.view.entity;

import org.anddev.andengine.entity.shape.RectangularShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.secondhand.model.entity.RectangleEntity;

public class RectangleView extends EntityView {

	public RectangleView(final PhysicsWorld physicsWorld,
			final RectangleEntity rectangleEntity,
			final RectangularShape rectangle, final FixtureDef fixtureDef) {
		super(physicsWorld, rectangleEntity, rectangle, PhysicsFactory
				.createBoxBody(physicsWorld, rectangle, BodyType.DynamicBody,
						fixtureDef));
	}
}