package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;

import com.badlogic.gdx.physics.box2d.Body;
import com.secondhand.model.Obstacle;

public class ObstacleView extends EntityView {

	public ObstacleView(final PhysicsWorld physicsWorld, final Obstacle entity, final IShape shape, final Body body) {
		super(physicsWorld, entity, shape, body);
	}

	@Override
	public void propertyChange(final PropertyChangeEvent event) {
		
	}

}
