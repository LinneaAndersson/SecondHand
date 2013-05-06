package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.secondhand.model.CircleEntity;
import com.secondhand.view.opengl.Circle;

public class CircleView extends EntityView {

	
	public static Body createCircleBody(final PhysicsWorld pPhysicsWorld, final IShape pShape, final BodyType pBodyType, final FixtureDef pFixtureDef) {

	
	public CircleView(final PhysicsWorld physicsWorld, final CircleEntity circleEntity , final Circle circle) {
	
		super(PhysicsFactory.createCircleBody(physicsWorld, circle, ));
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
	}
}
