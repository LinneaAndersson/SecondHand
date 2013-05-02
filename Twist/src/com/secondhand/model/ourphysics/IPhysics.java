package com.secondhand.model.ourphysics;

import org.anddev.andengine.entity.shape.IShape;
import org.anddev.andengine.entity.shape.Shape;

import com.badlogic.gdx.physics.box2d.Body;

public abstract class IPhysics {

	public IPhysics() {
		init();
	}
	
	public abstract void init();

	public abstract void setWorldBounds(IShape[] shape);
	
	public abstract void removeWorldBounds();
	
	public abstract void registerBody(Body body, Boolean rotatation);
}
