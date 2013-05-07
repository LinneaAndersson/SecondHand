package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;

import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.secondhand.model.PolygonEntity;
import com.secondhand.view.opengl.Polygon;

public class PolygonView extends EntityView {
	
	public PolygonView(final PhysicsWorld physicsWorld, final PolygonEntity polygonEntity , final Polygon polygon,
			final FixtureDef fixtureDef) {
	
		super(physicsWorld, polygonEntity, polygon,
				PhysicsFactory.createCircleBody(physicsWorld,
						circle.getX(), circle.getY(), circle.getRadius(),circle.getRotation(), BodyType.DynamicBody, fixtureDef));
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
	}
}
