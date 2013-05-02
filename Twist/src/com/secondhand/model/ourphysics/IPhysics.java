package com.secondhand.model.ourphysics;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.Shape;

public abstract class IPhysics {

	public IPhysics() {
		init();
	}
	
	public abstract void init();

	public abstract void setWorldBounds(IShape shape, IShape shape2, IShape shape3,
			IShape shape4);
}
