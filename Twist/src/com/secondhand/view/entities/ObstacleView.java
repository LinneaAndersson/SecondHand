package com.secondhand.view.entities;

import java.beans.PropertyChangeEvent;

import org.anddev.andengine.entity.shape.IShape;

import com.secondhand.model.Obstacle;

public class ObstacleView extends EntityView {

	public ObstacleView(final Obstacle entity, final IShape shape) {
		super(entity, shape);
	}

	@Override
	public void propertyChange(final PropertyChangeEvent event) {
		
	}

}
